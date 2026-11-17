package pe.edu.upeu.lms.controller;

import pe.edu.upeu.lms.dto.PageResponse;
import pe.edu.upeu.lms.util.PageUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lms.dto.PeriodoAcademicoRequest;
import pe.edu.upeu.lms.dto.PeriodoAcademicoResponse;
import pe.edu.upeu.lms.service.PeriodoAcademicoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/periodos-academicos")
@RequiredArgsConstructor
public class PeriodoAcademicoController {

    private final PeriodoAcademicoService periodoAcademicoService;

    @GetMapping
    public PageResponse<PeriodoAcademicoResponse> listar(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size) {
        return PageUtil.paginate(periodoAcademicoService.listar(), page, size);
    }

    @GetMapping("/{id}")
    public PeriodoAcademicoResponse obtener(@PathVariable Long id) {
        return periodoAcademicoService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PeriodoAcademicoResponse crear(@Valid @RequestBody PeriodoAcademicoRequest request) {
        return periodoAcademicoService.crear(request);
    }

    @PutMapping("/{id}")
    public PeriodoAcademicoResponse actualizar(@PathVariable Long id, @Valid @RequestBody PeriodoAcademicoRequest request) {
        return periodoAcademicoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        periodoAcademicoService.eliminar(id);
    }
}
