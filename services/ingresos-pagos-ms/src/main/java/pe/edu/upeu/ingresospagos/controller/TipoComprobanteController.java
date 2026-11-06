package pe.edu.upeu.ingresospagos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.ingresospagos.dto.TipoComprobanteRequest;
import pe.edu.upeu.ingresospagos.dto.TipoComprobanteResponse;
import pe.edu.upeu.ingresospagos.service.TipoComprobanteService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tipos-comprobante")
@RequiredArgsConstructor
public class TipoComprobanteController {

    private final TipoComprobanteService tipoComprobanteService;

    @GetMapping
    public List<TipoComprobanteResponse> listar() {
        return tipoComprobanteService.findAll();
    }

    @GetMapping("/{id}")
    public TipoComprobanteResponse obtener(@PathVariable Long id) {
        return tipoComprobanteService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TipoComprobanteResponse crear(@Valid @RequestBody TipoComprobanteRequest request) {
        return tipoComprobanteService.create(request);
    }

    @PutMapping("/{id}")
    public TipoComprobanteResponse actualizar(@PathVariable Long id, @Valid @RequestBody TipoComprobanteRequest request) {
        return tipoComprobanteService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        tipoComprobanteService.delete(id);
    }
}
