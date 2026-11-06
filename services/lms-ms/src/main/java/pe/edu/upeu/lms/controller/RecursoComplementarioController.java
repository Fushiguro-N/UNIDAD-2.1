package pe.edu.upeu.lms.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lms.dto.RecursoComplementarioRequest;
import pe.edu.upeu.lms.dto.RecursoComplementarioResponse;
import pe.edu.upeu.lms.service.RecursoComplementarioService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recursos-complementarios")
@RequiredArgsConstructor
public class RecursoComplementarioController {

    private final RecursoComplementarioService recursoComplementarioService;

    @GetMapping
    public List<RecursoComplementarioResponse> listar() {
        return recursoComplementarioService.listar();
    }

    @GetMapping("/{id}")
    public RecursoComplementarioResponse obtener(@PathVariable Long id) {
        return recursoComplementarioService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RecursoComplementarioResponse crear(@Valid @RequestBody RecursoComplementarioRequest request) {
        return recursoComplementarioService.crear(request);
    }

    @PutMapping("/{id}")
    public RecursoComplementarioResponse actualizar(@PathVariable Long id, @Valid @RequestBody RecursoComplementarioRequest request) {
        return recursoComplementarioService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        recursoComplementarioService.eliminar(id);
    }
}
