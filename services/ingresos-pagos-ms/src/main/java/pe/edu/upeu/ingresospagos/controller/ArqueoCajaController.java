package pe.edu.upeu.ingresospagos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.ingresospagos.dto.ArqueoCajaRequest;
import pe.edu.upeu.ingresospagos.dto.ArqueoCajaResponse;
import pe.edu.upeu.ingresospagos.service.ArqueoCajaService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/arqueos-caja")
@RequiredArgsConstructor
public class ArqueoCajaController {

    private final ArqueoCajaService arqueoCajaService;

    @GetMapping
    public List<ArqueoCajaResponse> listar() {
        return arqueoCajaService.listar();
    }

    @GetMapping("/{id}")
    public ArqueoCajaResponse obtener(@PathVariable Long id) {
        return arqueoCajaService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArqueoCajaResponse crear(@Valid @RequestBody ArqueoCajaRequest request) {
        return arqueoCajaService.crear(request);
    }

    @PutMapping("/{id}")
    public ArqueoCajaResponse actualizar(@PathVariable Long id, @Valid @RequestBody ArqueoCajaRequest request) {
        return arqueoCajaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        arqueoCajaService.eliminar(id);
    }
}
