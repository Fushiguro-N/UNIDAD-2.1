package pe.edu.upeu.lms.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lms.dto.PrerequisitoCursoRequest;
import pe.edu.upeu.lms.dto.PrerequisitoCursoResponse;
import pe.edu.upeu.lms.service.PrerequisitoCursoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/prerequisitos-curso")
@RequiredArgsConstructor
public class PrerequisitoCursoController {

    private final PrerequisitoCursoService prerequisitoCursoService;

    @GetMapping
    public List<PrerequisitoCursoResponse> listar() {
        return prerequisitoCursoService.listar();
    }

    @GetMapping("/{id}")
    public PrerequisitoCursoResponse obtener(@PathVariable Long id) {
        return prerequisitoCursoService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PrerequisitoCursoResponse crear(@Valid @RequestBody PrerequisitoCursoRequest request) {
        return prerequisitoCursoService.crear(request);
    }

    @PutMapping("/{id}")
    public PrerequisitoCursoResponse actualizar(@PathVariable Long id, @Valid @RequestBody PrerequisitoCursoRequest request) {
        return prerequisitoCursoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        prerequisitoCursoService.eliminar(id);
    }
}
