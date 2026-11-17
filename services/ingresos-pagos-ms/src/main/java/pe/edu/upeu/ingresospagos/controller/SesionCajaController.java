package pe.edu.upeu.ingresospagos.controller;

import pe.edu.upeu.ingresospagos.dto.PageResponse;
import pe.edu.upeu.ingresospagos.util.PageUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.ingresospagos.dto.SesionCajaRequest;
import pe.edu.upeu.ingresospagos.dto.SesionCajaResponse;
import pe.edu.upeu.ingresospagos.service.SesionCajaService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sesiones-caja")
@RequiredArgsConstructor
public class SesionCajaController {

    private final SesionCajaService sesionCajaService;

    @GetMapping
    public PageResponse<SesionCajaResponse> listar(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size) {
        return PageUtil.paginate(sesionCajaService.listar(), page, size);
    }

    @GetMapping("/{id}")
    public SesionCajaResponse obtener(@PathVariable Long id) {
        return sesionCajaService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SesionCajaResponse crear(@Valid @RequestBody SesionCajaRequest request) {
        return sesionCajaService.crear(request);
    }

    @PutMapping("/{id}")
    public SesionCajaResponse actualizar(@PathVariable Long id, @Valid @RequestBody SesionCajaRequest request) {
        return sesionCajaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        sesionCajaService.eliminar(id);
    }
}
