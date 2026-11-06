package pe.edu.upeu.lms.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lms.dto.InsigniaEstudianteRequest;
import pe.edu.upeu.lms.dto.InsigniaEstudianteResponse;
import pe.edu.upeu.lms.service.InsigniaEstudianteService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/insignias-estudiante")
@RequiredArgsConstructor
public class InsigniaEstudianteController {

    private final InsigniaEstudianteService insigniaEstudianteService;

    @GetMapping
    public List<InsigniaEstudianteResponse> listar() {
        return insigniaEstudianteService.listar();
    }

    @GetMapping("/{id}")
    public InsigniaEstudianteResponse obtener(@PathVariable Long id) {
        return insigniaEstudianteService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InsigniaEstudianteResponse crear(@Valid @RequestBody InsigniaEstudianteRequest request) {
        return insigniaEstudianteService.crear(request);
    }

    @PutMapping("/{id}")
    public InsigniaEstudianteResponse actualizar(@PathVariable Long id, @Valid @RequestBody InsigniaEstudianteRequest request) {
        return insigniaEstudianteService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        insigniaEstudianteService.eliminar(id);
    }
}
