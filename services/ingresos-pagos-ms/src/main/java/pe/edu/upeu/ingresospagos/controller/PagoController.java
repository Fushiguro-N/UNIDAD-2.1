package pe.edu.upeu.ingresospagos.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.ingresospagos.dto.PagoResponse;
import pe.edu.upeu.ingresospagos.service.PagoService;

import java.util.List;

// Solo lectura: los pagos se registran via la pasarela/evento de matricula, no se crean por API directa
@RestController
@RequestMapping("/api/v1/pagos")
@RequiredArgsConstructor
public class PagoController {

    private final PagoService pagoService;

    @GetMapping
    public List<PagoResponse> listar() {
        return pagoService.listar();
    }

    @GetMapping("/{id}")
    public PagoResponse obtener(@PathVariable Long id) {
        return pagoService.obtener(id);
    }

    @GetMapping("/por-matricula/{matriculaId}")
    public ResponseEntity<PagoResponse> buscarPorMatricula(@PathVariable Long matriculaId) {
        return pagoService.buscarPorMatricula(matriculaId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
