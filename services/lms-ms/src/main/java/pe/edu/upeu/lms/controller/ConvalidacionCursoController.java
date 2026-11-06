package pe.edu.upeu.lms.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lms.dto.ConvalidacionCursoRequest;
import pe.edu.upeu.lms.dto.ConvalidacionCursoResponse;
import pe.edu.upeu.lms.service.ConvalidacionCursoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/convalidaciones-curso")
@RequiredArgsConstructor
public class ConvalidacionCursoController {

    private final ConvalidacionCursoService convalidacionCursoService;

    @GetMapping
    public List<ConvalidacionCursoResponse> listar() {
        return convalidacionCursoService.listar();
    }

    @GetMapping("/{id}")
    public ConvalidacionCursoResponse obtener(@PathVariable Long id) {
        return convalidacionCursoService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConvalidacionCursoResponse crear(@Valid @RequestBody ConvalidacionCursoRequest request) {
        return convalidacionCursoService.crear(request);
    }

    @PutMapping("/{id}")
    public ConvalidacionCursoResponse actualizar(@PathVariable Long id, @Valid @RequestBody ConvalidacionCursoRequest request) {
        return convalidacionCursoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        convalidacionCursoService.eliminar(id);
    }
}
