package pe.edu.upeu.ingresospagos.controller;

import pe.edu.upeu.ingresospagos.dto.PageResponse;
import pe.edu.upeu.ingresospagos.util.PageUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.ingresospagos.dto.FinanciamientoEducativoRequest;
import pe.edu.upeu.ingresospagos.dto.FinanciamientoEducativoResponse;
import pe.edu.upeu.ingresospagos.service.FinanciamientoEducativoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/financiamientos-educativos")
@RequiredArgsConstructor
public class FinanciamientoEducativoController {

    private final FinanciamientoEducativoService financiamientoEducativoService;

    @GetMapping
    public PageResponse<FinanciamientoEducativoResponse> listar(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size) {
        return PageUtil.paginate(financiamientoEducativoService.listar(), page, size);
    }

    @GetMapping("/{id}")
    public FinanciamientoEducativoResponse obtener(@PathVariable Long id) {
        return financiamientoEducativoService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FinanciamientoEducativoResponse crear(@Valid @RequestBody FinanciamientoEducativoRequest request) {
        return financiamientoEducativoService.crear(request);
    }

    @PutMapping("/{id}")
    public FinanciamientoEducativoResponse actualizar(@PathVariable Long id, @Valid @RequestBody FinanciamientoEducativoRequest request) {
        return financiamientoEducativoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        financiamientoEducativoService.eliminar(id);
    }
}
