package pe.edu.upeu.lms.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lms.dto.AsistenciaRequest;
import pe.edu.upeu.lms.dto.AsistenciaResponse;
import pe.edu.upeu.lms.dto.PageResponse;
import pe.edu.upeu.lms.service.AsistenciaService;
import pe.edu.upeu.lms.util.JwtRoleUtils;
import pe.edu.upeu.lms.util.PageUtil;

import java.util.List;

@RestController
@RequestMapping("/api/v1/asistencias")
@RequiredArgsConstructor
public class AsistenciaController {

    private final AsistenciaService asistenciaService;

    @GetMapping
    public PageResponse<AsistenciaResponse> listar(@AuthenticationPrincipal Jwt jwt,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        List<AsistenciaResponse> result = JwtRoleUtils.isSoloEstudiante(jwt)
                ? asistenciaService.listarPorEstudiante(jwt.getSubject())
                : asistenciaService.listar(jwt.getClaimAsString("email"));
        return PageUtil.paginate(result, page, size);
    }

    @GetMapping("/{id}")
    public AsistenciaResponse obtener(@PathVariable Long id) {
        return asistenciaService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AsistenciaResponse crear(@Valid @RequestBody AsistenciaRequest request) {
        return asistenciaService.crear(request);
    }

    @PutMapping("/{id}")
    public AsistenciaResponse actualizar(@PathVariable Long id, @Valid @RequestBody AsistenciaRequest request) {
        return asistenciaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        asistenciaService.eliminar(id);
    }
}
