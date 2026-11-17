package pe.edu.upeu.ingresospagos.controller;

import pe.edu.upeu.ingresospagos.dto.PageResponse;
import pe.edu.upeu.ingresospagos.util.PageUtil;

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
    public PageResponse<SerieComprobanteResponse> listar(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size) {
        return PageUtil.paginate(serieComprobanteService.listar(), page, size);
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
