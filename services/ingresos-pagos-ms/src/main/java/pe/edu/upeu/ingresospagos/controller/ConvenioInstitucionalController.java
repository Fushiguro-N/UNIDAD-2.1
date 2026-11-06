package pe.edu.upeu.ingresospagos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.ingresospagos.dto.ConvenioInstitucionalRequest;
import pe.edu.upeu.ingresospagos.dto.ConvenioInstitucionalResponse;
import pe.edu.upeu.ingresospagos.service.ConvenioInstitucionalService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/convenios-institucionales")
@RequiredArgsConstructor
public class ConvenioInstitucionalController {

    private final ConvenioInstitucionalService convenioInstitucionalService;

    @GetMapping
    public List<ConvenioInstitucionalResponse> listar() {
        return convenioInstitucionalService.findAll();
    }

    @GetMapping("/{id}")
    public ConvenioInstitucionalResponse obtener(@PathVariable Long id) {
        return convenioInstitucionalService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConvenioInstitucionalResponse crear(@Valid @RequestBody ConvenioInstitucionalRequest request) {
        return convenioInstitucionalService.create(request);
    }

    @PutMapping("/{id}")
    public ConvenioInstitucionalResponse actualizar(@PathVariable Long id, @Valid @RequestBody ConvenioInstitucionalRequest request) {
        return convenioInstitucionalService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        convenioInstitucionalService.delete(id);
    }
}
