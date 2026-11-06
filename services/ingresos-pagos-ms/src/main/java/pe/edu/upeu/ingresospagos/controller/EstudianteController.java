package pe.edu.upeu.ingresospagos.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.ingresospagos.dto.EstadoCuentaResponse;
import pe.edu.upeu.ingresospagos.service.EstudianteService;

// Solo lectura: el estudiante se define en lms-ms, aqui solo se consulta su estado de cuenta de pagos
@RestController
@RequestMapping("/api/v1/estudiantes")
@RequiredArgsConstructor
public class EstudianteController {

    private final EstudianteService estudianteService;

    @GetMapping("/{id}/estado-cuenta")
    public EstadoCuentaResponse obtenerEstadoCuenta(@PathVariable("id") String id) {
        return estudianteService.obtenerEstadoCuenta(id);
    }
}
