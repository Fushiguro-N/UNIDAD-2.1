package pe.edu.upeu.lms.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lms.dto.PlantillaCertificadoRequest;
import pe.edu.upeu.lms.dto.PlantillaCertificadoResponse;
import pe.edu.upeu.lms.service.PlantillaCertificadoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/plantillas-certificado")
@RequiredArgsConstructor
public class PlantillaCertificadoController {

    private final PlantillaCertificadoService plantillaCertificadoService;

    @GetMapping
    public List<PlantillaCertificadoResponse> listar() {
        return plantillaCertificadoService.listar();
    }

    @GetMapping("/{id}")
    public PlantillaCertificadoResponse obtener(@PathVariable Long id) {
        return plantillaCertificadoService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlantillaCertificadoResponse crear(@Valid @RequestBody PlantillaCertificadoRequest request) {
        return plantillaCertificadoService.crear(request);
    }

    @PutMapping("/{id}")
    public PlantillaCertificadoResponse actualizar(@PathVariable Long id, @Valid @RequestBody PlantillaCertificadoRequest request) {
        return plantillaCertificadoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        plantillaCertificadoService.eliminar(id);
    }
}
