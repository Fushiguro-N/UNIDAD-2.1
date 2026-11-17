package pe.edu.upeu.lms.controller;

import pe.edu.upeu.lms.dto.PageResponse;
import pe.edu.upeu.lms.util.PageUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lms.dto.PreguntaBancoRequest;
import pe.edu.upeu.lms.dto.PreguntaBancoResponse;
import pe.edu.upeu.lms.service.PreguntaBancoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/preguntas-banco")
@RequiredArgsConstructor
public class PreguntaBancoController {

    private final PreguntaBancoService preguntaBancoService;

    @GetMapping
    public PageResponse<PreguntaBancoResponse> listar(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size) {
        return PageUtil.paginate(preguntaBancoService.listar(), page, size);
    }

    @GetMapping("/{id}")
    public PreguntaBancoResponse obtener(@PathVariable Long id) {
        return preguntaBancoService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PreguntaBancoResponse crear(@Valid @RequestBody PreguntaBancoRequest request) {
        return preguntaBancoService.crear(request);
    }

    @PutMapping("/{id}")
    public PreguntaBancoResponse actualizar(@PathVariable Long id, @Valid @RequestBody PreguntaBancoRequest request) {
        return preguntaBancoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        preguntaBancoService.eliminar(id);
    }
}
