package pe.edu.upeu.lms.controller;

import pe.edu.upeu.lms.dto.PageResponse;
import pe.edu.upeu.lms.util.PageUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.upeu.lms.dto.EntregaTareaRequest;
import pe.edu.upeu.lms.dto.EntregaTareaResponse;
import pe.edu.upeu.lms.service.EntregaTareaService;
import pe.edu.upeu.lms.service.FileStorageService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/entregas-tarea")
@RequiredArgsConstructor
public class EntregaTareaController {

    private final EntregaTareaService entregaTareaService;
    private final FileStorageService fileStorageService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, String> subirArchivo(@RequestParam("file") MultipartFile file) {
        String filename = fileStorageService.store(file);
        return Map.of("url", "/api/v1/entregas-tarea/archivos/" + filename);
    }

    @GetMapping("/archivos/{filename}")
    public ResponseEntity<Resource> descargarArchivo(@PathVariable String filename) {
        Resource resource = fileStorageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                .body(resource);
    }

    @GetMapping
    public PageResponse<EntregaTareaResponse> listar(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size) {
        return PageUtil.paginate(entregaTareaService.listar(), page, size);
    }

    @GetMapping("/{id}")
    public EntregaTareaResponse obtener(@PathVariable Long id) {
        return entregaTareaService.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaTareaResponse crear(@Valid @RequestBody EntregaTareaRequest request) {
        return entregaTareaService.crear(request);
    }

    @PutMapping("/{id}")
    public EntregaTareaResponse actualizar(@PathVariable Long id, @Valid @RequestBody EntregaTareaRequest request) {
        return entregaTareaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        entregaTareaService.eliminar(id);
    }
}
