package pe.edu.upeu.ingresospagos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.ingresospagos.dto.TipoCambioRequest;
import pe.edu.upeu.ingresospagos.dto.TipoCambioResponse;
import pe.edu.upeu.ingresospagos.service.TipoCambioService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tipos-cambio")
@RequiredArgsConstructor
public class TipoCambioController {

    private final TipoCambioService tipoCambioService;

    @GetMapping
    public List<TipoCambioResponse> listar() {
        return tipoCambioService.findAll();
    }

    @GetMapping("/{id}")
    public TipoCambioResponse obtener(@PathVariable Long id) {
        return tipoCambioService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TipoCambioResponse crear(@Valid @RequestBody TipoCambioRequest request) {
        return tipoCambioService.create(request);
    }

    @PutMapping("/{id}")
    public TipoCambioResponse actualizar(@PathVariable Long id, @Valid @RequestBody TipoCambioRequest request) {
        return tipoCambioService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        tipoCambioService.delete(id);
    }
}
