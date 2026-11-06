package pe.edu.upeu.lms.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lms.dto.RespuestaForoRequest;
import pe.edu.upeu.lms.dto.RespuestaForoResponse;
import pe.edu.upeu.lms.service.RespuestaForoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/respuestas-foro")
@RequiredArgsConstructor
public class RespuestaForoController {

    private final RespuestaForoService respuestaForoService;

    @GetMapping
    public List<RespuestaForoResponse> listar() {
        return respuestaForoService.listar();
    }

    @GetMapping("/{id}")
    public RespuestaForoResponse obtener(@PathVariable Long id) {
        return respuestaForoService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RespuestaForoResponse crear(@Valid @RequestBody RespuestaForoRequest request) {
        return respuestaForoService.crear(request);
    }

    @PutMapping("/{id}")
    public RespuestaForoResponse actualizar(@PathVariable Long id, @Valid @RequestBody RespuestaForoRequest request) {
        return respuestaForoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        respuestaForoService.eliminar(id);
    }
}
