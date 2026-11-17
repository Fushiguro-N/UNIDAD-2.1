package pe.edu.upeu.lms.controller;

import pe.edu.upeu.lms.dto.PageResponse;
import pe.edu.upeu.lms.util.PageUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lms.dto.MatriculaRequest;
import pe.edu.upeu.lms.dto.MatriculaResponse;
import pe.edu.upeu.lms.service.MatriculaService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/matriculas")
@RequiredArgsConstructor
public class MatriculaController {

    private final MatriculaService matriculaService;

    @PostMapping("/enrolar")
    @ResponseStatus(HttpStatus.CREATED)
    public MatriculaResponse enrolar(@Valid @RequestBody MatriculaRequest request,
                                      @AuthenticationPrincipal Jwt jwt) {
        String idEstudiante = jwt.getSubject();
        String nombres = jwt.getClaimAsString("given_name");
        String apellidos = jwt.getClaimAsString("family_name");
        return matriculaService.crear(request, idEstudiante, nombres, apellidos);
    }

    @GetMapping
    public PageResponse<MatriculaResponse> listar(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size) {
        return PageUtil.paginate(matriculaService.listar(), page, size);
    }

    @GetMapping("/{id}")
    public MatriculaResponse findById(@PathVariable Long id) {
        return matriculaService.findById(id);
    }
}
