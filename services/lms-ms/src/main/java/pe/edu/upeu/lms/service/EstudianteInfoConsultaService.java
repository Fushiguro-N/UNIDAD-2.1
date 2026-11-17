package pe.edu.upeu.lms.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import pe.edu.upeu.lms.client.EstudianteInfoClient;
import pe.edu.upeu.lms.dto.EstudianteInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EstudianteInfoConsultaService {

    private final EstudianteInfoClient estudianteInfoClient;

    @CircuitBreaker(name = "ingresosPagos", fallbackMethod = "fallbackInfo")
    public EstudianteInfoResponse consultarInfo(String idEstudiante) {
        return estudianteInfoClient.obtenerInfo(idEstudiante);
    }

    public EstudianteInfoResponse fallbackInfo(String idEstudiante, Throwable ex) {
        log.warn("[INGRESOS-PAGOS] Fallback activado para idEstudiante {}. Motivo: {}", idEstudiante, ex.getMessage());
        return null;
    }
}
