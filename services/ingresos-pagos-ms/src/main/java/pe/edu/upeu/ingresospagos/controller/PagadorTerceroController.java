package pe.edu.upeu.ingresospagos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.ingresospagos.dto.PagadorTerceroRequest;
import pe.edu.upeu.ingresospagos.dto.PagadorTerceroResponse;
import pe.edu.upeu.ingresospagos.service.PagadorTerceroService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pagadores-terceros")
@RequiredArgsConstructor
public class PagadorTerceroController {

    private final PagadorTerceroService pagadorTerceroService;

    @GetMapping
    public List<PagadorTerceroResponse> listar() {
        return pagadorTerceroService.findAll();
    }

    @GetMapping("/{id}")
    public PagadorTerceroResponse obtener(@PathVariable Long id) {
        return pagadorTerceroService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PagadorTerceroResponse crear(@Valid @RequestBody PagadorTerceroRequest request) {
        return pagadorTerceroService.create(request);
    }

    @PutMapping("/{id}")
    public PagadorTerceroResponse actualizar(@PathVariable Long id, @Valid @RequestBody PagadorTerceroRequest request) {
        return pagadorTerceroService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        pagadorTerceroService.delete(id);
    }
}
