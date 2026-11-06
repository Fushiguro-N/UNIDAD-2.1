package pe.edu.upeu.ingresospagos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.ingresospagos.dto.BitacoraAuditoriaPagoRequest;
import pe.edu.upeu.ingresospagos.dto.BitacoraAuditoriaPagoResponse;
import pe.edu.upeu.ingresospagos.service.BitacoraAuditoriaPagoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bitacoras-auditoria-pago")
@RequiredArgsConstructor
public class BitacoraAuditoriaPagoController {

    private final BitacoraAuditoriaPagoService bitacoraAuditoriaPagoService;

    @GetMapping
    public List<BitacoraAuditoriaPagoResponse> listar() {
        return bitacoraAuditoriaPagoService.listar();
    }

    @GetMapping("/{id}")
    public BitacoraAuditoriaPagoResponse obtener(@PathVariable Long id) {
        return bitacoraAuditoriaPagoService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BitacoraAuditoriaPagoResponse crear(@Valid @RequestBody BitacoraAuditoriaPagoRequest request) {
        return bitacoraAuditoriaPagoService.crear(request);
    }

    @PutMapping("/{id}")
    public BitacoraAuditoriaPagoResponse actualizar(@PathVariable Long id, @Valid @RequestBody BitacoraAuditoriaPagoRequest request) {
        return bitacoraAuditoriaPagoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        bitacoraAuditoriaPagoService.eliminar(id);
    }
}
