package pe.edu.upeu.lms.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lms.dto.EncuestaSatisfaccionRequest;
import pe.edu.upeu.lms.dto.EncuestaSatisfaccionResponse;
import pe.edu.upeu.lms.service.EncuestaSatisfaccionService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/encuestas-satisfaccion")
@RequiredArgsConstructor
public class EncuestaSatisfaccionController {

    private final EncuestaSatisfaccionService encuestaSatisfaccionService;

    @GetMapping
    public List<EncuestaSatisfaccionResponse> listar() {
        return encuestaSatisfaccionService.listar();
    }

    @GetMapping("/{id}")
    public EncuestaSatisfaccionResponse obtener(@PathVariable Long id) {
        return encuestaSatisfaccionService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EncuestaSatisfaccionResponse crear(@Valid @RequestBody EncuestaSatisfaccionRequest request) {
        return encuestaSatisfaccionService.crear(request);
    }

    @PutMapping("/{id}")
    public EncuestaSatisfaccionResponse actualizar(@PathVariable Long id, @Valid @RequestBody EncuestaSatisfaccionRequest request) {
        return encuestaSatisfaccionService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        encuestaSatisfaccionService.eliminar(id);
    }
}
