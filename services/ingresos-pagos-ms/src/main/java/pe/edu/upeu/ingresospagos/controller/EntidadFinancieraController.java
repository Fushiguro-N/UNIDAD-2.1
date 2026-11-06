package pe.edu.upeu.ingresospagos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.ingresospagos.dto.EntidadFinancieraRequest;
import pe.edu.upeu.ingresospagos.dto.EntidadFinancieraResponse;
import pe.edu.upeu.ingresospagos.service.EntidadFinancieraService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/entidades-financieras")
@RequiredArgsConstructor
public class EntidadFinancieraController {

    private final EntidadFinancieraService entidadFinancieraService;

    @GetMapping
    public List<EntidadFinancieraResponse> listar() {
        return entidadFinancieraService.findAll();
    }

    @GetMapping("/{id}")
    public EntidadFinancieraResponse obtener(@PathVariable Long id) {
        return entidadFinancieraService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntidadFinancieraResponse crear(@Valid @RequestBody EntidadFinancieraRequest request) {
        return entidadFinancieraService.create(request);
    }

    @PutMapping("/{id}")
    public EntidadFinancieraResponse actualizar(@PathVariable Long id, @Valid @RequestBody EntidadFinancieraRequest request) {
        return entidadFinancieraService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        entidadFinancieraService.delete(id);
    }
}
