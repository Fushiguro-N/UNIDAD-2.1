package pe.edu.upeu.lms.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lms.dto.PageResponse;
import pe.edu.upeu.lms.dto.TareaRequest;
import pe.edu.upeu.lms.dto.TareaResponse;
import pe.edu.upeu.lms.service.TareaService;
import pe.edu.upeu.lms.util.PageUtil;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tareas")
@RequiredArgsConstructor
public class TareaController {

    private final TareaService tareaService;

    @GetMapping
    public PageResponse<TareaResponse> listar(@AuthenticationPrincipal Jwt jwt,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return PageUtil.paginate(tareaService.listar(jwt.getClaimAsString("email")), page, size);
    }

    @GetMapping("/{id}")
    public TareaResponse obtener(@PathVariable Long id) {
        return tareaService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TareaResponse crear(@Valid @RequestBody TareaRequest request) {
        return tareaService.crear(request);
    }

    @PutMapping("/{id}")
    public TareaResponse actualizar(@PathVariable Long id, @Valid @RequestBody TareaRequest request) {
        return tareaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        tareaService.eliminar(id);
    }
}
