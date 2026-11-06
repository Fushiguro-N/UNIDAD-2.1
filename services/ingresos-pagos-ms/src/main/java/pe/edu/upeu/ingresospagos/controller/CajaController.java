package pe.edu.upeu.ingresospagos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.ingresospagos.dto.CajaRequest;
import pe.edu.upeu.ingresospagos.dto.CajaResponse;
import pe.edu.upeu.ingresospagos.service.CajaService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cajas")
@RequiredArgsConstructor
public class CajaController {

    private final CajaService cajaService;

    @GetMapping
    public List<CajaResponse> listar() {
        return cajaService.findAll();
    }

    @GetMapping("/{id}")
    public CajaResponse obtener(@PathVariable Long id) {
        return cajaService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CajaResponse crear(@Valid @RequestBody CajaRequest request) {
        return cajaService.create(request);
    }

    @PutMapping("/{id}")
    public CajaResponse actualizar(@PathVariable Long id, @Valid @RequestBody CajaRequest request) {
        return cajaService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        cajaService.delete(id);
    }
}
