package pe.edu.upeu.lms.controller;

import pe.edu.upeu.lms.dto.PageResponse;
import pe.edu.upeu.lms.util.PageUtil;

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
    public PageResponse<SesionClaseResponse> listar(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size) {
        return PageUtil.paginate(sesionClaseService.listar(), page, size);
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
