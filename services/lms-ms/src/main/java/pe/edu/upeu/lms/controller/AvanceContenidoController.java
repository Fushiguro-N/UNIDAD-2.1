package pe.edu.upeu.lms.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lms.dto.AvanceContenidoRequest;
import pe.edu.upeu.lms.dto.AvanceContenidoResponse;
import pe.edu.upeu.lms.service.AvanceContenidoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/avances-contenido")
@RequiredArgsConstructor
public class AvanceContenidoController {

    private final AvanceContenidoService avanceContenidoService;

    @GetMapping
    public List<AvanceContenidoResponse> listar() {
        return avanceContenidoService.listar();
    }

    @GetMapping("/{id}")
    public AvanceContenidoResponse obtener(@PathVariable Long id) {
        return avanceContenidoService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AvanceContenidoResponse crear(@Valid @RequestBody AvanceContenidoRequest request) {
        return avanceContenidoService.crear(request);
    }

    @PutMapping("/{id}")
    public AvanceContenidoResponse actualizar(@PathVariable Long id, @Valid @RequestBody AvanceContenidoRequest request) {
        return avanceContenidoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        avanceContenidoService.eliminar(id);
    }
}
