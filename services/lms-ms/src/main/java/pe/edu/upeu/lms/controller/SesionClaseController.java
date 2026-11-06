package pe.edu.upeu.lms.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lms.dto.SesionClaseRequest;
import pe.edu.upeu.lms.dto.SesionClaseResponse;
import pe.edu.upeu.lms.service.SesionClaseService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sesiones-clase")
@RequiredArgsConstructor
public class SesionClaseController {

    private final SesionClaseService sesionClaseService;

    @GetMapping
    public List<SesionClaseResponse> listar() {
        return sesionClaseService.listar();
    }

    @GetMapping("/{id}")
    public SesionClaseResponse obtener(@PathVariable Long id) {
        return sesionClaseService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SesionClaseResponse crear(@Valid @RequestBody SesionClaseRequest request) {
        return sesionClaseService.crear(request);
    }

    @PutMapping("/{id}")
    public SesionClaseResponse actualizar(@PathVariable Long id, @Valid @RequestBody SesionClaseRequest request) {
        return sesionClaseService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        sesionClaseService.eliminar(id);
    }
}
