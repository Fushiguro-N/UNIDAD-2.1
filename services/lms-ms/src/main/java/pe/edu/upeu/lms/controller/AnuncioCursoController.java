package pe.edu.upeu.lms.controller;

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
    public List<AnuncioCursoResponse> listar() {
        return anuncioCursoService.listar();
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
