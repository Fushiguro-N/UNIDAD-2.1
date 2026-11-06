package pe.edu.upeu.lms.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lms.dto.ForoHiloRequest;
import pe.edu.upeu.lms.dto.ForoHiloResponse;
import pe.edu.upeu.lms.service.ForoHiloService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/foros-hilo")
@RequiredArgsConstructor
public class ForoHiloController {

    private final ForoHiloService foroHiloService;

    @GetMapping
    public List<ForoHiloResponse> listar() {
        return foroHiloService.listar();
    }

    @GetMapping("/{id}")
    public ForoHiloResponse obtener(@PathVariable Long id) {
        return foroHiloService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ForoHiloResponse crear(@Valid @RequestBody ForoHiloRequest request) {
        return foroHiloService.crear(request);
    }

    @PutMapping("/{id}")
    public ForoHiloResponse actualizar(@PathVariable Long id, @Valid @RequestBody ForoHiloRequest request) {
        return foroHiloService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        foroHiloService.eliminar(id);
    }
}
