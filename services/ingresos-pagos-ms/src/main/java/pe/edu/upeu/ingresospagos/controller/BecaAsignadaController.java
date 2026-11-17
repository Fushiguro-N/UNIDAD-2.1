package pe.edu.upeu.ingresospagos.controller;

import pe.edu.upeu.ingresospagos.dto.PageResponse;
import pe.edu.upeu.ingresospagos.util.PageUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.ingresospagos.dto.BecaAsignadaRequest;
import pe.edu.upeu.ingresospagos.dto.BecaAsignadaResponse;
import pe.edu.upeu.ingresospagos.service.BecaAsignadaService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/becas-asignadas")
@RequiredArgsConstructor
public class BecaAsignadaController {

    private final BecaAsignadaService becaAsignadaService;

    @GetMapping
    public PageResponse<BecaAsignadaResponse> listar(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size) {
        return PageUtil.paginate(becaAsignadaService.listar(), page, size);
    }

    @GetMapping("/{id}")
    public BecaAsignadaResponse obtener(@PathVariable Long id) {
        return becaAsignadaService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BecaAsignadaResponse crear(@Valid @RequestBody BecaAsignadaRequest request) {
        return becaAsignadaService.crear(request);
    }

    @PutMapping("/{id}")
    public BecaAsignadaResponse actualizar(@PathVariable Long id, @Valid @RequestBody BecaAsignadaRequest request) {
        return becaAsignadaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        becaAsignadaService.eliminar(id);
    }
}
