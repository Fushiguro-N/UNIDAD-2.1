package pe.edu.upeu.ingresospagos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.ingresospagos.dto.BecaRequest;
import pe.edu.upeu.ingresospagos.dto.BecaResponse;
import pe.edu.upeu.ingresospagos.service.BecaService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/becas")
@RequiredArgsConstructor
public class BecaController {

    private final BecaService becaService;

    @GetMapping
    public List<BecaResponse> listar() {
        return becaService.findAll();
    }

    @GetMapping("/{id}")
    public BecaResponse obtener(@PathVariable Long id) {
        return becaService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BecaResponse crear(@Valid @RequestBody BecaRequest request) {
        return becaService.create(request);
    }

    @PutMapping("/{id}")
    public BecaResponse actualizar(@PathVariable Long id, @Valid @RequestBody BecaRequest request) {
        return becaService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        becaService.delete(id);
    }
}
