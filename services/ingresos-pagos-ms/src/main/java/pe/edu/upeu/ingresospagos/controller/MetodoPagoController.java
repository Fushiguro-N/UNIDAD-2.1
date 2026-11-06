package pe.edu.upeu.ingresospagos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.ingresospagos.dto.MetodoPagoRequest;
import pe.edu.upeu.ingresospagos.dto.MetodoPagoResponse;
import pe.edu.upeu.ingresospagos.service.MetodoPagoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/metodos-pago")
@RequiredArgsConstructor
public class MetodoPagoController {

    private final MetodoPagoService metodoPagoService;

    @GetMapping
    public List<MetodoPagoResponse> listar() {
        return metodoPagoService.findAll();
    }

    @GetMapping("/{id}")
    public MetodoPagoResponse obtener(@PathVariable Long id) {
        return metodoPagoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MetodoPagoResponse crear(@Valid @RequestBody MetodoPagoRequest request) {
        return metodoPagoService.create(request);
    }

    @PutMapping("/{id}")
    public MetodoPagoResponse actualizar(@PathVariable Long id, @Valid @RequestBody MetodoPagoRequest request) {
        return metodoPagoService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        metodoPagoService.delete(id);
    }
}
