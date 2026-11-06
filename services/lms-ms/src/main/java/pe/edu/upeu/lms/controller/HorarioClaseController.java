package pe.edu.upeu.lms.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lms.dto.HorarioClaseRequest;
import pe.edu.upeu.lms.dto.HorarioClaseResponse;
import pe.edu.upeu.lms.service.HorarioClaseService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/horarios-clase")
@RequiredArgsConstructor
public class HorarioClaseController {

    private final HorarioClaseService horarioClaseService;

    @GetMapping
    public List<HorarioClaseResponse> listar() {
        return horarioClaseService.listar();
    }

    @GetMapping("/{id}")
    public HorarioClaseResponse obtener(@PathVariable Long id) {
        return horarioClaseService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HorarioClaseResponse crear(@Valid @RequestBody HorarioClaseRequest request) {
        return horarioClaseService.crear(request);
    }

    @PutMapping("/{id}")
    public HorarioClaseResponse actualizar(@PathVariable Long id, @Valid @RequestBody HorarioClaseRequest request) {
        return horarioClaseService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        horarioClaseService.eliminar(id);
    }
}
