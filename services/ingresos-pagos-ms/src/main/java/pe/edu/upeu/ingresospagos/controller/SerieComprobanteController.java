package pe.edu.upeu.ingresospagos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.ingresospagos.dto.SerieComprobanteRequest;
import pe.edu.upeu.ingresospagos.dto.SerieComprobanteResponse;
import pe.edu.upeu.ingresospagos.service.SerieComprobanteService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/series-comprobante")
@RequiredArgsConstructor
public class SerieComprobanteController {

    private final SerieComprobanteService serieComprobanteService;

    @GetMapping
    public List<SerieComprobanteResponse> listar() {
        return serieComprobanteService.listar();
    }

    @GetMapping("/{id}")
    public SerieComprobanteResponse obtener(@PathVariable Long id) {
        return serieComprobanteService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SerieComprobanteResponse crear(@Valid @RequestBody SerieComprobanteRequest request) {
        return serieComprobanteService.crear(request);
    }

    @PutMapping("/{id}")
    public SerieComprobanteResponse actualizar(@PathVariable Long id, @Valid @RequestBody SerieComprobanteRequest request) {
        return serieComprobanteService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        serieComprobanteService.eliminar(id);
    }
}
