package pe.edu.upeu.lms.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lms.dto.EntregaTareaRequest;
import pe.edu.upeu.lms.dto.EntregaTareaResponse;
import pe.edu.upeu.lms.service.EntregaTareaService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/entregas-tarea")
@RequiredArgsConstructor
public class EntregaTareaController {

    private final EntregaTareaService entregaTareaService;

    @GetMapping
    public List<EntregaTareaResponse> listar() {
        return entregaTareaService.listar();
    }

    @GetMapping("/{id}")
    public EntregaTareaResponse obtener(@PathVariable Long id) {
        return entregaTareaService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaTareaResponse crear(@Valid @RequestBody EntregaTareaRequest request) {
        return entregaTareaService.crear(request);
    }

    @PutMapping("/{id}")
    public EntregaTareaResponse actualizar(@PathVariable Long id, @Valid @RequestBody EntregaTareaRequest request) {
        return entregaTareaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        entregaTareaService.eliminar(id);
    }
}
