package pe.edu.upeu.lms.controller;

import pe.edu.upeu.lms.dto.PageResponse;
import pe.edu.upeu.lms.util.PageUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lms.dto.DocenteRequest;
import pe.edu.upeu.lms.dto.DocenteResponse;
import pe.edu.upeu.lms.service.DocenteService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/docentes")
@RequiredArgsConstructor
public class DocenteController {

    private final DocenteService docenteService;

    @GetMapping
    public PageResponse<DocenteResponse> listar(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size) {
        return PageUtil.paginate(docenteService.listar(), page, size);
    }

    @GetMapping("/{id}")
    public DocenteResponse obtener(@PathVariable Long id) {
        return docenteService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DocenteResponse crear(@Valid @RequestBody DocenteRequest request) {
        return docenteService.crear(request);
    }

    @PutMapping("/{id}")
    public DocenteResponse actualizar(@PathVariable Long id, @Valid @RequestBody DocenteRequest request) {
        return docenteService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        docenteService.eliminar(id);
    }
}
