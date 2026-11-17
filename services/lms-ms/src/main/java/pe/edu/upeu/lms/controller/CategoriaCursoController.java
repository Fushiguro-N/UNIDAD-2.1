package pe.edu.upeu.lms.controller;

import pe.edu.upeu.lms.dto.PageResponse;
import pe.edu.upeu.lms.util.PageUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lms.dto.CategoriaCursoRequest;
import pe.edu.upeu.lms.dto.CategoriaCursoResponse;
import pe.edu.upeu.lms.service.CategoriaCursoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categorias-curso")
@RequiredArgsConstructor
public class CategoriaCursoController {

    private final CategoriaCursoService categoriaCursoService;

    @GetMapping
    public PageResponse<CategoriaCursoResponse> listar(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size) {
        return PageUtil.paginate(categoriaCursoService.listar(), page, size);
    }

    @GetMapping("/{id}")
    public CategoriaCursoResponse obtener(@PathVariable Long id) {
        return categoriaCursoService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaCursoResponse crear(@Valid @RequestBody CategoriaCursoRequest request) {
        return categoriaCursoService.crear(request);
    }

    @PutMapping("/{id}")
    public CategoriaCursoResponse actualizar(@PathVariable Long id, @Valid @RequestBody CategoriaCursoRequest request) {
        return categoriaCursoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        categoriaCursoService.eliminar(id);
    }
}
