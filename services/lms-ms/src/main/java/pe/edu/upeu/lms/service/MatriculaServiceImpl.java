package pe.edu.upeu.lms.service;

import pe.edu.upeu.lms.dto.*;
import pe.edu.upeu.lms.entity.*;
import pe.edu.upeu.lms.event.MatriculaCreadaEvento;
import pe.edu.upeu.lms.exception.MatriculaDuplicadaException;
import pe.edu.upeu.lms.messaging.MatriculaEventosProducer;
import pe.edu.upeu.lms.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MatriculaServiceImpl implements MatriculaService {

    private static final BigDecimal NOTA_MINIMA_APROBATORIA = BigDecimal.valueOf(11);

    private final MatriculaRepository matriculaRepository;
    private final MatriculaDetalleRepository matriculaDetalleRepository;
    private final SeccionCursoRepository seccionCursoRepository;
    private final PrerequisitoCursoRepository prerequisitoCursoRepository;
    private final CalificacionRepository calificacionRepository;
    private final EstadoCuentaConsultaService estadoCuentaConsultaService;
    private final EstudianteInfoConsultaService estudianteInfoConsultaService;
    private final MatriculaEventosProducer producer;

    @Override
    @Transactional
    public MatriculaResponse crear(MatriculaRequest request, String idEstudiante, String nombresEstudiante, String apellidosEstudiante) {
        for (DetalleMatriculaRequest item : request.getDetalles()) {
            long yaInscrito = matriculaDetalleRepository.contarInscripcionExistente(
                    item.getIdSeccionCurso(), idEstudiante, EstadoMatricula.RECHAZADA);
            if (yaInscrito > 0) {
                throw new MatriculaDuplicadaException(
                        "Ya tienes una matricula registrada en la seccion " + item.getIdSeccionCurso());
            }
        }

        Matricula matricula = Matricula.builder()
                .idEstudiante(idEstudiante)
                .periodoAcademico(request.getPeriodoAcademico())
                .metodoPago(request.getMetodoPago())
                .build();

        EstadoCuentaResponse estadoCuenta = estadoCuentaConsultaService.consultarEstadoCuenta(idEstudiante);

        if (estadoCuenta == null) {
            matricula.setEstado(EstadoMatricula.PENDIENTE_VALIDACION);
            Matricula guardada = matriculaRepository.save(matricula);
            return toResponse(guardada);
        }

        if (Boolean.TRUE.equals(estadoCuenta.getTieneDeudaPendiente())) {
            matricula.setEstado(EstadoMatricula.RECHAZADA);
            Matricula guardada = matriculaRepository.save(matricula);
            return toResponse(guardada);
        }

        Set<Long> aprobados = cursosAprobados(idEstudiante);

        List<MatriculaDetalle> detalles = new ArrayList<>();
        boolean validacionCompleta = true;

        for (DetalleMatriculaRequest item : request.getDetalles()) {
            SeccionCurso seccion = seccionCursoRepository.findById(item.getIdSeccionCurso()).orElse(null);

            if (seccion == null || !Boolean.TRUE.equals(seccion.getActivo())
                    || !Boolean.TRUE.equals(seccion.getPeriodoAcademico().getActivo())) {
                validacionCompleta = false;
                detalles.add(MatriculaDetalle.builder()
                        .matricula(matricula)
                        .idSeccionCurso(item.getIdSeccionCurso())
                        .build());
                continue;
            }

            long inscritos = matriculaDetalleRepository.contarInscritosEnSeccion(
                    seccion.getId(), EstadoMatricula.RECHAZADA);

            boolean cupoDisponible = inscritos < seccion.getCupoMaximo();
            boolean prerequisitosOk = prerequisitosCumplidos(seccion.getCurso().getId(), aprobados);

            if (!cupoDisponible || !prerequisitosOk) {
                validacionCompleta = false;
            }

            detalles.add(MatriculaDetalle.builder()
                    .matricula(matricula)
                    .idSeccionCurso(seccion.getId())
                    .nombreCurso(seccion.getCurso().getNombre())
                    .nombreSeccion(seccion.getNombreSeccion())
                    .build());
        }

        matricula.setDetalles(detalles);
        matricula.setEstado(validacionCompleta ? EstadoMatricula.PENDIENTE_PAGO : EstadoMatricula.PENDIENTE_VALIDACION);

        Matricula guardada = matriculaRepository.save(matricula);

        if (guardada.getEstado() == EstadoMatricula.PENDIENTE_PAGO) {
            producer.publicarTrasCommit(MatriculaCreadaEvento.builder()
                    .tipoEvento("matricula.creada")
                    .matriculaId(guardada.getId())
                    .idEstudiante(guardada.getIdEstudiante())
                    .nombresEstudiante(nombresEstudiante)
                    .apellidosEstudiante(apellidosEstudiante)
                    .total(guardada.getTotal())
                    .metodoPago(guardada.getMetodoPago())
                    .origen("lms-ms")
                    .timestamp(Instant.now().toEpochMilli())
                    .build());
        }
        return toResponse(guardada);
    }

    private Set<Long> cursosAprobados(String idEstudiante) {
        return calificacionRepository.findAllConEvaluacion().stream()
                .filter(c -> c.getIdEstudiante().equals(idEstudiante))
                .filter(c -> c.getNota().compareTo(NOTA_MINIMA_APROBATORIA) >= 0)
                .map(c -> c.getEvaluacion().getCurso().getId())
                .collect(Collectors.toSet());
    }

    private boolean prerequisitosCumplidos(Long idCurso, Set<Long> aprobados) {
        Set<Long> requeridos = prerequisitoCursoRepository.findAllConCursos().stream()
                .filter(p -> p.getCurso().getId().equals(idCurso))
                .map(p -> p.getCursoPrerequisito().getId())
                .collect(Collectors.toSet());
        return aprobados.containsAll(requeridos);
    }

    @Override
    @Transactional
    public void marcarConfirmada(Long matriculaId) {
        Matricula matricula = matriculaRepository.findById(matriculaId).orElse(null);
        if (matricula == null || matricula.getEstado() != EstadoMatricula.PENDIENTE_PAGO) {
            log.warn("component=processor matriculaId={} status=ignored motivo=\"la matricula no existe o no esta pendiente de pago\"", matriculaId);
            return;
        }
        matricula.setEstado(EstadoMatricula.CONFIRMADA);
        log.info("component=processor matriculaId={} estado={} status=processed", matriculaId, matricula.getEstado());
    }

    @Override
    @Transactional(readOnly = true)
    public MatriculaResponse findById(Long id) {
        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matricula no encontrada: " + id));
        return toResponse(matricula);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MatriculaResponse> listar() {
        return matriculaRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private String nonBlank(String value) {
        return value != null ? value : "";
    }

    private MatriculaResponse toResponse(Matricula matricula) {
        List<DetalleMatriculaResponse> detalles = matricula.getDetalles().stream()
                .map(d -> DetalleMatriculaResponse.builder()
                        .idSeccionCurso(d.getIdSeccionCurso())
                        .nombreCurso(d.getNombreCurso())
                        .nombreSeccion(d.getNombreSeccion())
                        .build())
                .collect(Collectors.toList());

        EstudianteInfoResponse info = estudianteInfoConsultaService.consultarInfo(matricula.getIdEstudiante());

        return MatriculaResponse.builder()
                .id(matricula.getId())
                .codigoEstudiante(info != null ? info.getCodigoEstudiante() : null)
                .nombreEstudiante(info != null
                        ? String.join(" ", nonBlank(info.getNombres()), nonBlank(info.getApellidos())).trim()
                        : null)
                .periodoAcademico(matricula.getPeriodoAcademico())
                .createdAt(matricula.getCreatedAt())
                .estado(matricula.getEstado().name())
                .total(matricula.getTotal())
                .detalles(detalles)
                .build();
    }
}
