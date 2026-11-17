package pe.edu.upeu.lms.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lms.dto.CalificacionRequest;
import pe.edu.upeu.lms.dto.CalificacionResponse;
import pe.edu.upeu.lms.dto.PageResponse;
import pe.edu.upeu.lms.service.CalificacionService;
import pe.edu.upeu.lms.util.JwtRoleUtils;
import pe.edu.upeu.lms.util.PageUtil;

import java.util.List;

@RestController
@RequestMapping("/api/v1/calificaciones")
@RequiredArgsConstructor
public class CalificacionController {

    private final CalificacionService calificacionService;

    @GetMapping
    public PageResponse<CalificacionResponse> listar(@AuthenticationPrincipal Jwt jwt,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        List<CalificacionResponse> result = JwtRoleUtils.isSoloEstudiante(jwt)
                ? calificacionService.listarPorEstudiante(jwt.getSubject())
                : calificacionService.listar();
        return PageUtil.paginate(result, page, size);
    }

    @GetMapping("/{id}")
    public CalificacionResponse obtener(@PathVariable Long id) {
        return calificacionService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CalificacionResponse crear(@Valid @RequestBody CalificacionRequest request) {
        return calificacionService.crear(request);
    }

    @PutMapping("/{id}")
    public CalificacionResponse actualizar(@PathVariable Long id, @Valid @RequestBody CalificacionRequest request) {
        return calificacionService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        calificacionService.eliminar(id);
    }
}
