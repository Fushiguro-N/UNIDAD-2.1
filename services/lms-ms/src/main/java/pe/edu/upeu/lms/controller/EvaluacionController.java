package pe.edu.upeu.lms.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lms.dto.EvaluacionRequest;
import pe.edu.upeu.lms.dto.EvaluacionResponse;
import pe.edu.upeu.lms.service.EvaluacionService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/evaluaciones")
@RequiredArgsConstructor
public class EvaluacionController {

    private final EvaluacionService evaluacionService;

    @GetMapping
    public List<EvaluacionResponse> listar() {
        return evaluacionService.listar();
    }

    @GetMapping("/{id}")
    public EvaluacionResponse obtener(@PathVariable Long id) {
        return evaluacionService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EvaluacionResponse crear(@Valid @RequestBody EvaluacionRequest request) {
        return evaluacionService.crear(request);
    }

    @PutMapping("/{id}")
    public EvaluacionResponse actualizar(@PathVariable Long id, @Valid @RequestBody EvaluacionRequest request) {
        return evaluacionService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        evaluacionService.eliminar(id);
    }
}
