package pe.edu.upeu.lms.controller;

import pe.edu.upeu.lms.dto.PageResponse;
import pe.edu.upeu.lms.util.PageUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lms.dto.MallaCurricularRequest;
import pe.edu.upeu.lms.dto.MallaCurricularResponse;
import pe.edu.upeu.lms.service.MallaCurricularService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mallas-curriculares")
@RequiredArgsConstructor
public class MallaCurricularController {

    private final MallaCurricularService mallaCurricularService;

    @GetMapping
    public PageResponse<MallaCurricularResponse> listar(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size) {
        return PageUtil.paginate(mallaCurricularService.listar(), page, size);
    }

    @GetMapping("/{id}")
    public MallaCurricularResponse obtener(@PathVariable Long id) {
        return mallaCurricularService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MallaCurricularResponse crear(@Valid @RequestBody MallaCurricularRequest request) {
        return mallaCurricularService.crear(request);
    }

    @PutMapping("/{id}")
    public MallaCurricularResponse actualizar(@PathVariable Long id, @Valid @RequestBody MallaCurricularRequest request) {
        return mallaCurricularService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        mallaCurricularService.eliminar(id);
    }
}
