package pe.edu.upeu.ingresospagos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.ingresospagos.dto.PlantillaNotificacionRequest;
import pe.edu.upeu.ingresospagos.dto.PlantillaNotificacionResponse;
import pe.edu.upeu.ingresospagos.service.PlantillaNotificacionService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/plantillas-notificacion")
@RequiredArgsConstructor
public class PlantillaNotificacionController {

    private final PlantillaNotificacionService plantillaNotificacionService;

    @GetMapping
    public List<PlantillaNotificacionResponse> listar() {
        return plantillaNotificacionService.findAll();
    }

    @GetMapping("/{id}")
    public PlantillaNotificacionResponse obtener(@PathVariable Long id) {
        return plantillaNotificacionService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlantillaNotificacionResponse crear(@Valid @RequestBody PlantillaNotificacionRequest request) {
        return plantillaNotificacionService.create(request);
    }

    @PutMapping("/{id}")
    public PlantillaNotificacionResponse actualizar(@PathVariable Long id, @Valid @RequestBody PlantillaNotificacionRequest request) {
        return plantillaNotificacionService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        plantillaNotificacionService.delete(id);
    }
}
