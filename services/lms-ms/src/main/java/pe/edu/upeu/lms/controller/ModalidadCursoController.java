package pe.edu.upeu.lms.controller;

import pe.edu.upeu.lms.dto.PageResponse;
import pe.edu.upeu.lms.util.PageUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lms.dto.ModalidadCursoRequest;
import pe.edu.upeu.lms.dto.ModalidadCursoResponse;
import pe.edu.upeu.lms.service.ModalidadCursoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/modalidades-curso")
@RequiredArgsConstructor
public class ModalidadCursoController {

    private final ModalidadCursoService modalidadCursoService;

    @GetMapping
    public PageResponse<ModalidadCursoResponse> listar(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size) {
        return PageUtil.paginate(modalidadCursoService.listar(), page, size);
    }

    @GetMapping("/{id}")
    public ModalidadCursoResponse obtener(@PathVariable Long id) {
        return modalidadCursoService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ModalidadCursoResponse crear(@Valid @RequestBody ModalidadCursoRequest request) {
        return modalidadCursoService.crear(request);
    }

    @PutMapping("/{id}")
    public ModalidadCursoResponse actualizar(@PathVariable Long id, @Valid @RequestBody ModalidadCursoRequest request) {
        return modalidadCursoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        modalidadCursoService.eliminar(id);
    }
}
