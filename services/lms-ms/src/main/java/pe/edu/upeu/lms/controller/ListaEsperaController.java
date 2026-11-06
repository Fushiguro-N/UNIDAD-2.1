package pe.edu.upeu.lms.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lms.dto.ListaEsperaRequest;
import pe.edu.upeu.lms.dto.ListaEsperaResponse;
import pe.edu.upeu.lms.service.ListaEsperaService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/listas-espera")
@RequiredArgsConstructor
public class ListaEsperaController {

    private final ListaEsperaService listaEsperaService;

    @GetMapping
    public List<ListaEsperaResponse> listar() {
        return listaEsperaService.listar();
    }

    @GetMapping("/{id}")
    public ListaEsperaResponse obtener(@PathVariable Long id) {
        return listaEsperaService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ListaEsperaResponse crear(@Valid @RequestBody ListaEsperaRequest request) {
        return listaEsperaService.crear(request);
    }

    @PutMapping("/{id}")
    public ListaEsperaResponse actualizar(@PathVariable Long id, @Valid @RequestBody ListaEsperaRequest request) {
        return listaEsperaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        listaEsperaService.eliminar(id);
    }
}
