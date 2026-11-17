package pe.edu.upeu.ingresospagos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.ingresospagos.dto.CrearEstudianteRequest;
import pe.edu.upeu.ingresospagos.dto.CrearEstudianteResponse;
import pe.edu.upeu.ingresospagos.dto.EstadoCuentaResponse;
import pe.edu.upeu.ingresospagos.dto.EstudianteInfoResponse;
import pe.edu.upeu.ingresospagos.entity.EstadoPago;
import pe.edu.upeu.ingresospagos.entity.Estudiante;
import pe.edu.upeu.ingresospagos.repository.EstudianteRepository;
import pe.edu.upeu.ingresospagos.repository.PagoRepository;

import java.security.SecureRandom;
import java.time.Year;

@Service
@RequiredArgsConstructor
public class EstudianteServiceImpl implements EstudianteService {

    private static final String PASSWORD_CHARS = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz23456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    private final PagoRepository pagoRepository;
    private final EstudianteRepository estudianteRepository;
    private final KeycloakAdminService keycloakAdminService;

    @Override
    @Transactional(readOnly = true)
    public EstadoCuentaResponse obtenerEstadoCuenta(String idEstudiante) {
        Estudiante estudiante = estudianteRepository.findById(idEstudiante)
                .or(() -> estudianteRepository.findByCodigoEstudiante(idEstudiante))
                .orElse(null);
        if (estudiante == null) {
            return null;
        }

        boolean tieneDeudaPendiente = !pagoRepository
                .findByIdEstudianteAndEstadoNot(estudiante.getId(), EstadoPago.VALIDADO)
                .isEmpty();

        return EstadoCuentaResponse.builder()
                .idEstudiante(estudiante.getId())
                .nombres(estudiante.getNombres())
                .apellidos(estudiante.getApellidos())
                .dni(estudiante.getDni())
                .tieneDeudaPendiente(tieneDeudaPendiente)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public EstudianteInfoResponse obtenerInfo(String idEstudiante) {
        Estudiante estudiante = estudianteRepository.findById(idEstudiante).orElse(null);
        if (estudiante == null) {
            return null;
        }
        return EstudianteInfoResponse.builder()
                .id(estudiante.getId())
                .codigoEstudiante(estudiante.getCodigoEstudiante())
                .nombres(estudiante.getNombres())
                .apellidos(estudiante.getApellidos())
                .build();
    }

    @Override
    @Transactional
    public void registrarSiNoExiste(String idEstudiante, String nombres, String apellidos) {
        if (idEstudiante == null || estudianteRepository.existsById(idEstudiante)) {
            return;
        }
        estudianteRepository.save(Estudiante.builder()
                .id(idEstudiante)
                .nombres(nombres)
                .apellidos(apellidos)
                .codigoEstudiante(generarCodigoEstudiante())
                .build());
    }

    @Override
    @Transactional
    public CrearEstudianteResponse crearNuevo(CrearEstudianteRequest request) {
        if (estudianteRepository.existsByDni(request.getDni())) {
            throw new IllegalArgumentException("Ya existe un estudiante registrado con ese DNI");
        }

        String username = request.getDni();
        String temporaryPassword = generarPasswordTemporal();

        String idEstudiante = keycloakAdminService.crearUsuarioEstudiante(
                username, request.getEmail(), request.getNombres(), request.getApellidos(), temporaryPassword);

        String codigoEstudiante = generarCodigoEstudiante();
        estudianteRepository.save(Estudiante.builder()
                .id(idEstudiante)
                .nombres(request.getNombres())
                .apellidos(request.getApellidos())
                .email(request.getEmail())
                .dni(request.getDni())
                .celular(request.getCelular())
                .codigoEstudiante(codigoEstudiante)
                .build());

        return CrearEstudianteResponse.builder()
                .id(idEstudiante)
                .codigoEstudiante(codigoEstudiante)
                .username(username)
                .temporaryPassword(temporaryPassword)
                .build();
    }

    private String generarPasswordTemporal() {
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            sb.append(PASSWORD_CHARS.charAt(RANDOM.nextInt(PASSWORD_CHARS.length())));
        }
        return sb.toString();
    }

    private String generarCodigoEstudiante() {
        String anio = String.valueOf(Year.now().getValue());
        long siguiente = estudianteRepository.countByCodigoEstudianteStartingWith(anio) + 1;
        return anio + String.format("%05d", siguiente);
    }
}
