package pe.edu.upeu.lms.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lms.dto.RubricaEvaluacionRequest;
import pe.edu.upeu.lms.dto.RubricaEvaluacionResponse;
import pe.edu.upeu.lms.service.RubricaEvaluacionService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rubricas-evaluacion")
@RequiredArgsConstructor
public class RubricaEvaluacionController {

    private final RubricaEvaluacionService rubricaEvaluacionService;

    @GetMapping
    public List<RubricaEvaluacionResponse> listar() {
        return rubricaEvaluacionService.listar();
    }

    @GetMapping("/{id}")
    public RubricaEvaluacionResponse obtener(@PathVariable Long id) {
        return rubricaEvaluacionService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RubricaEvaluacionResponse crear(@Valid @RequestBody RubricaEvaluacionRequest request) {
        return rubricaEvaluacionService.crear(request);
    }

    @PutMapping("/{id}")
    public RubricaEvaluacionResponse actualizar(@PathVariable Long id, @Valid @RequestBody RubricaEvaluacionRequest request) {
        return rubricaEvaluacionService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        rubricaEvaluacionService.eliminar(id);
    }
}
