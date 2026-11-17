package pe.edu.upeu.lms.controller;

import pe.edu.upeu.lms.dto.PageResponse;
import pe.edu.upeu.lms.util.PageUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lms.dto.AnuncioCursoRequest;
import pe.edu.upeu.lms.dto.AnuncioCursoResponse;
import pe.edu.upeu.lms.service.AnuncioCursoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/anuncios-curso")
@RequiredArgsConstructor
public class AnuncioCursoController {

    private final AnuncioCursoService anuncioCursoService;

    @GetMapping
    public PageResponse<AnuncioCursoResponse> listar(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size) {
        return PageUtil.paginate(anuncioCursoService.listar(), page, size);
    }

    @GetMapping("/{id}")
    public AnuncioCursoResponse obtener(@PathVariable Long id) {
        return anuncioCursoService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnuncioCursoResponse crear(@Valid @RequestBody AnuncioCursoRequest request) {
        return anuncioCursoService.crear(request);
    }

    @PutMapping("/{id}")
    public AnuncioCursoResponse actualizar(@PathVariable Long id, @Valid @RequestBody AnuncioCursoRequest request) {
        return anuncioCursoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        anuncioCursoService.eliminar(id);
    }
}
