package pe.edu.upeu.lms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.lms.dto.AvanceContenidoRequest;
import pe.edu.upeu.lms.dto.AvanceContenidoResponse;
import pe.edu.upeu.lms.entity.AvanceContenido;
import pe.edu.upeu.lms.entity.RecursoComplementario;
import pe.edu.upeu.lms.exception.ResourceNotFoundException;
import pe.edu.upeu.lms.mapper.AvanceContenidoMapper;
import pe.edu.upeu.lms.repository.AvanceContenidoRepository;
import pe.edu.upeu.lms.repository.RecursoComplementarioRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AvanceContenidoService {

    private final AvanceContenidoRepository avanceContenidoRepository;
    private final AvanceContenidoMapper avanceContenidoMapper;
    private final RecursoComplementarioRepository recursoComplementarioRepository;

    public List<AvanceContenidoResponse> listar() {
        return avanceContenidoRepository.findAllConRecurso().stream()
                .map(avanceContenidoMapper::toResponse)
                .toList();
    }

    public List<AvanceContenidoResponse> listarPorEstudiante(String idEstudiante) {
        return avanceContenidoRepository.findAllConRecurso().stream()
                .filter(a -> idEstudiante.equals(a.getIdEstudiante()))
                .map(avanceContenidoMapper::toResponse)
                .toList();
    }

    public AvanceContenidoResponse obtener(Long id) {
        return avanceContenidoMapper.toResponse(buscarOFallar(id));
    }

    public AvanceContenidoResponse crear(AvanceContenidoRequest request) {
        AvanceContenido avanceContenido = avanceContenidoMapper.toEntity(request);
        avanceContenido.setRecurso(buscarRecursoOFallar(request.getIdRecurso()));
        avanceContenido.setFechaActualizacion(LocalDateTime.now());
        return avanceContenidoMapper.toResponse(avanceContenidoRepository.save(avanceContenido));
    }

    public AvanceContenidoResponse actualizar(Long id, AvanceContenidoRequest request) {
        AvanceContenido avanceContenido = buscarOFallar(id);
        avanceContenido.setIdEstudiante(request.getIdEstudiante());
        avanceContenido.setConsumido(request.getConsumido());
        avanceContenido.setRecurso(buscarRecursoOFallar(request.getIdRecurso()));
        avanceContenido.setFechaActualizacion(LocalDateTime.now());
        return avanceContenidoMapper.toResponse(avanceContenidoRepository.save(avanceContenido));
    }

    public void eliminar(Long id) {
        avanceContenidoRepository.delete(buscarOFallar(id));
    }

    public BigDecimal calcularProgresoCurso(String idEstudiante, Long idCurso) {
        List<AvanceContenido> avances = avanceContenidoRepository
                .findAllConRecursoPorEstudianteYCurso(idEstudiante, idCurso);
        if (avances.isEmpty()) {
            return BigDecimal.ZERO;
        }
        long completados = avances.stream().filter(a -> Boolean.TRUE.equals(a.getConsumido())).count();
        return BigDecimal.valueOf(completados)
                .multiply(BigDecimal.valueOf(100))
                .divide(BigDecimal.valueOf(avances.size()), 2, RoundingMode.HALF_UP);
    }

    private AvanceContenido buscarOFallar(Long id) {
        return avanceContenidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Avance de contenido no encontrado: " + id));
    }

    private RecursoComplementario buscarRecursoOFallar(Long idRecurso) {
        return recursoComplementarioRepository.findById(idRecurso)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso complementario no encontrado: " + idRecurso));
    }
}
