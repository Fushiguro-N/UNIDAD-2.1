package pe.edu.upeu.lms.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lms.dto.TareaRequest;
import pe.edu.upeu.lms.dto.TareaResponse;
import pe.edu.upeu.lms.service.TareaService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tareas")
@RequiredArgsConstructor
public class TareaController {

    private final TareaService tareaService;

    @GetMapping
    public List<TareaResponse> listar() {
        return tareaService.listar();
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
