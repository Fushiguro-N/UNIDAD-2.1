package pe.edu.upeu.lms.controller;

import pe.edu.upeu.lms.dto.PageResponse;
import pe.edu.upeu.lms.util.PageUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lms.dto.InsigniaRequest;
import pe.edu.upeu.lms.dto.InsigniaResponse;
import pe.edu.upeu.lms.service.InsigniaService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/insignias")
@RequiredArgsConstructor
public class InsigniaController {

    private final InsigniaService insigniaService;

    @GetMapping
    public PageResponse<InsigniaResponse> listar(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size) {
        return PageUtil.paginate(insigniaService.listar(), page, size);
    }

    @GetMapping("/{id}")
    public InsigniaResponse obtener(@PathVariable Long id) {
        return insigniaService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InsigniaResponse crear(@Valid @RequestBody InsigniaRequest request) {
        return insigniaService.crear(request);
    }

    @PutMapping("/{id}")
    public InsigniaResponse actualizar(@PathVariable Long id, @Valid @RequestBody InsigniaRequest request) {
        return insigniaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        insigniaService.eliminar(id);
    }
}
