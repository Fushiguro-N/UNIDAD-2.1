package pe.edu.upeu.ingresospagos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.ingresospagos.dto.RecargoMoraRequest;
import pe.edu.upeu.ingresospagos.dto.RecargoMoraResponse;
import pe.edu.upeu.ingresospagos.service.RecargoMoraService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recargos-mora")
@RequiredArgsConstructor
public class RecargoMoraController {

    private final RecargoMoraService recargoMoraService;

    @GetMapping
    public List<RecargoMoraResponse> listar() {
        return recargoMoraService.findAll();
    }

    @GetMapping("/{id}")
    public RecargoMoraResponse obtener(@PathVariable Long id) {
        return recargoMoraService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RecargoMoraResponse crear(@Valid @RequestBody RecargoMoraRequest request) {
        return recargoMoraService.create(request);
    }

    @PutMapping("/{id}")
    public RecargoMoraResponse actualizar(@PathVariable Long id, @Valid @RequestBody RecargoMoraRequest request) {
        return recargoMoraService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        recargoMoraService.delete(id);
    }
}
