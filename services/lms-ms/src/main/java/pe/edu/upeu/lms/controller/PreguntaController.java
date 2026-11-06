package pe.edu.upeu.lms.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lms.dto.PreguntaRequest;
import pe.edu.upeu.lms.dto.PreguntaResponse;
import pe.edu.upeu.lms.service.PreguntaService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/preguntas")
@RequiredArgsConstructor
public class PreguntaController {

    private final PreguntaService preguntaService;

    @GetMapping
    public List<PreguntaResponse> listar() {
        return preguntaService.listar();
    }

    @GetMapping("/{id}")
    public PreguntaResponse obtener(@PathVariable Long id) {
        return preguntaService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PreguntaResponse crear(@Valid @RequestBody PreguntaRequest request) {
        return preguntaService.crear(request);
    }

    @PutMapping("/{id}")
    public PreguntaResponse actualizar(@PathVariable Long id, @Valid @RequestBody PreguntaRequest request) {
        return preguntaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        preguntaService.eliminar(id);
    }
}
