package pe.edu.upeu.lms.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.lms.dto.AvanceContenidoRequest;
import pe.edu.upeu.lms.dto.AvanceContenidoResponse;
import pe.edu.upeu.lms.dto.PageResponse;
import pe.edu.upeu.lms.service.AvanceContenidoService;
import pe.edu.upeu.lms.util.JwtRoleUtils;
import pe.edu.upeu.lms.util.PageUtil;

import java.util.List;

@RestController
@RequestMapping("/api/v1/avances-contenido")
@RequiredArgsConstructor
public class AvanceContenidoController {

    private final AvanceContenidoService avanceContenidoService;

    @GetMapping
    public PageResponse<AvanceContenidoResponse> listar(@AuthenticationPrincipal Jwt jwt,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        List<AvanceContenidoResponse> result = JwtRoleUtils.isSoloEstudiante(jwt)
                ? avanceContenidoService.listarPorEstudiante(jwt.getSubject())
                : avanceContenidoService.listar();
        return PageUtil.paginate(result, page, size);
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

    @GetMapping("/progreso")
    public java.math.BigDecimal progreso(@AuthenticationPrincipal Jwt jwt, @RequestParam String idEstudiante, @RequestParam Long idCurso) {
        if (JwtRoleUtils.isSoloEstudiante(jwt)) {
            return avanceContenidoService.calcularProgresoCurso(jwt.getSubject(), idCurso);
        }
        return avanceContenidoService.calcularProgresoCurso(idEstudiante, idCurso);
    }
}
