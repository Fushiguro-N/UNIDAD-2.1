package pe.edu.upeu.ingresospagos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.ingresospagos.dto.NotaCreditoDebitoRequest;
import pe.edu.upeu.ingresospagos.dto.NotaCreditoDebitoResponse;
import pe.edu.upeu.ingresospagos.service.NotaCreditoDebitoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notas-credito-debito")
@RequiredArgsConstructor
public class NotaCreditoDebitoController {

    private final NotaCreditoDebitoService notaCreditoDebitoService;

    @GetMapping
    public List<NotaCreditoDebitoResponse> listar() {
        return notaCreditoDebitoService.listar();
    }

    @GetMapping("/{id}")
    public NotaCreditoDebitoResponse obtener(@PathVariable Long id) {
        return notaCreditoDebitoService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NotaCreditoDebitoResponse crear(@Valid @RequestBody NotaCreditoDebitoRequest request) {
        return notaCreditoDebitoService.crear(request);
    }

    @PutMapping("/{id}")
    public NotaCreditoDebitoResponse actualizar(@PathVariable Long id, @Valid @RequestBody NotaCreditoDebitoRequest request) {
        return notaCreditoDebitoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        notaCreditoDebitoService.eliminar(id);
    }
}
