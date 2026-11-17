package pe.edu.upeu.lms.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lms.dto.PageResponse;
import pe.edu.upeu.lms.dto.SeccionCursoRequest;
import pe.edu.upeu.lms.dto.SeccionCursoResponse;
import pe.edu.upeu.lms.service.SeccionCursoService;
import pe.edu.upeu.lms.util.PageUtil;

import java.util.List;

@RestController
@RequestMapping("/api/v1/secciones-curso")
@RequiredArgsConstructor
public class SeccionCursoController {

    private final SeccionCursoService seccionCursoService;

    @GetMapping
    public PageResponse<SeccionCursoResponse> listar(@AuthenticationPrincipal Jwt jwt,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return PageUtil.paginate(seccionCursoService.listar(jwt.getClaimAsString("email")), page, size);
    }

    @GetMapping("/{id}")
    public SeccionCursoResponse obtener(@PathVariable Long id) {
        return seccionCursoService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SeccionCursoResponse crear(@Valid @RequestBody SeccionCursoRequest request) {
        return seccionCursoService.crear(request);
    }

    @PutMapping("/{id}")
    public SeccionCursoResponse actualizar(@PathVariable Long id, @Valid @RequestBody SeccionCursoRequest request) {
        return seccionCursoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        seccionCursoService.eliminar(id);
    }
}
