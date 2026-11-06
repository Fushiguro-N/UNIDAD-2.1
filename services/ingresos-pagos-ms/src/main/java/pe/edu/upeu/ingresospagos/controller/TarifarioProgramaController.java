package pe.edu.upeu.ingresospagos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.ingresospagos.dto.TarifarioProgramaRequest;
import pe.edu.upeu.ingresospagos.dto.TarifarioProgramaResponse;
import pe.edu.upeu.ingresospagos.service.TarifarioProgramaService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tarifarios-programa")
@RequiredArgsConstructor
public class TarifarioProgramaController {

    private final TarifarioProgramaService tarifarioProgramaService;

    @GetMapping
    public List<TarifarioProgramaResponse> listar() {
        return tarifarioProgramaService.listar();
    }

    @GetMapping("/{id}")
    public TarifarioProgramaResponse obtener(@PathVariable Long id) {
        return tarifarioProgramaService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TarifarioProgramaResponse crear(@Valid @RequestBody TarifarioProgramaRequest request) {
        return tarifarioProgramaService.crear(request);
    }

    @PutMapping("/{id}")
    public TarifarioProgramaResponse actualizar(@PathVariable Long id, @Valid @RequestBody TarifarioProgramaRequest request) {
        return tarifarioProgramaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        tarifarioProgramaService.eliminar(id);
    }
}
