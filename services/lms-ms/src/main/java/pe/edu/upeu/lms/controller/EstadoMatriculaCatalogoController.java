package pe.edu.upeu.lms.controller;

import pe.edu.upeu.lms.dto.PageResponse;
import pe.edu.upeu.lms.util.PageUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lms.dto.EstadoMatriculaCatalogoRequest;
import pe.edu.upeu.lms.dto.EstadoMatriculaCatalogoResponse;
import pe.edu.upeu.lms.service.EstadoMatriculaCatalogoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/estados-matricula")
@RequiredArgsConstructor
public class EstadoMatriculaCatalogoController {

    private final EstadoMatriculaCatalogoService estadoMatriculaCatalogoService;

    @GetMapping
    public PageResponse<EstadoMatriculaCatalogoResponse> listar(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size) {
        return PageUtil.paginate(estadoMatriculaCatalogoService.listar(), page, size);
    }

    @GetMapping("/{id}")
    public EstadoMatriculaCatalogoResponse obtener(@PathVariable Long id) {
        return estadoMatriculaCatalogoService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoMatriculaCatalogoResponse crear(@Valid @RequestBody EstadoMatriculaCatalogoRequest request) {
        return estadoMatriculaCatalogoService.crear(request);
    }

    @PutMapping("/{id}")
    public EstadoMatriculaCatalogoResponse actualizar(@PathVariable Long id, @Valid @RequestBody EstadoMatriculaCatalogoRequest request) {
        return estadoMatriculaCatalogoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        estadoMatriculaCatalogoService.eliminar(id);
    }
}
