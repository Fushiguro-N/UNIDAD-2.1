package pe.edu.upeu.ingresospagos.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.ingresospagos.dto.CrearEstudianteRequest;
import pe.edu.upeu.ingresospagos.dto.CrearEstudianteResponse;
import pe.edu.upeu.ingresospagos.dto.EstadoCuentaResponse;
import pe.edu.upeu.ingresospagos.dto.EstudianteInfoResponse;
import pe.edu.upeu.ingresospagos.service.EstudianteService;
import pe.edu.upeu.ingresospagos.util.JwtRoleUtils;

@RestController
@RequestMapping("/api/v1/estudiantes")
@RequiredArgsConstructor
public class EstudianteController {

    private final EstudianteService estudianteService;

    @GetMapping("/{id}/estado-cuenta")
    public ResponseEntity<EstadoCuentaResponse> obtenerEstadoCuenta(@PathVariable("id") String id) {
        EstadoCuentaResponse response = estudianteService.obtenerEstadoCuenta(id);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public EstudianteInfoResponse obtenerInfo(@PathVariable("id") String id) {
        return estudianteService.obtenerInfo(id);
    }

    @PostMapping
    public ResponseEntity<CrearEstudianteResponse> crear(
            @RequestBody CrearEstudianteRequest request, @AuthenticationPrincipal Jwt jwt) {
        if (!JwtRoleUtils.isAdmin(jwt)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(estudianteService.crearNuevo(request));
    }
}
