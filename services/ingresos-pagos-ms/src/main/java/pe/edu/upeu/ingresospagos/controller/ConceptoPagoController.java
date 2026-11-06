package pe.edu.upeu.ingresospagos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.ingresospagos.dto.ConceptoPagoRequest;
import pe.edu.upeu.ingresospagos.dto.ConceptoPagoResponse;
import pe.edu.upeu.ingresospagos.service.ConceptoPagoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/conceptos-pago")
@RequiredArgsConstructor
public class ConceptoPagoController {

    private final ConceptoPagoService conceptoPagoService;

    @GetMapping
    public List<ConceptoPagoResponse> listar() {
        return conceptoPagoService.findAll();
    }

    @GetMapping("/{id}")
    public ConceptoPagoResponse obtener(@PathVariable Long id) {
        return conceptoPagoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConceptoPagoResponse crear(@Valid @RequestBody ConceptoPagoRequest request) {
        return conceptoPagoService.create(request);
    }

    @PutMapping("/{id}")
    public ConceptoPagoResponse actualizar(@PathVariable Long id, @Valid @RequestBody ConceptoPagoRequest request) {
        return conceptoPagoService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        conceptoPagoService.delete(id);
    }
}
