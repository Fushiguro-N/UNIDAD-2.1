package pe.edu.upeu.ingresospagos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.ingresospagos.dto.LogTransaccionPasarelaRequest;
import pe.edu.upeu.ingresospagos.dto.LogTransaccionPasarelaResponse;
import pe.edu.upeu.ingresospagos.service.LogTransaccionPasarelaService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/logs-transaccion-pasarela")
@RequiredArgsConstructor
public class LogTransaccionPasarelaController {

    private final LogTransaccionPasarelaService logTransaccionPasarelaService;

    @GetMapping
    public List<LogTransaccionPasarelaResponse> listar() {
        return logTransaccionPasarelaService.listar();
    }

    @GetMapping("/{id}")
    public LogTransaccionPasarelaResponse obtener(@PathVariable Long id) {
        return logTransaccionPasarelaService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LogTransaccionPasarelaResponse crear(@Valid @RequestBody LogTransaccionPasarelaRequest request) {
        return logTransaccionPasarelaService.crear(request);
    }

    @PutMapping("/{id}")
    public LogTransaccionPasarelaResponse actualizar(@PathVariable Long id, @Valid @RequestBody LogTransaccionPasarelaRequest request) {
        return logTransaccionPasarelaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        logTransaccionPasarelaService.eliminar(id);
    }
}
