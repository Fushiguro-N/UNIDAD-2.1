package pe.edu.upeu.lms.client;

import pe.edu.upeu.lms.dto.EstudianteInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ingresos-pagos-ms", contextId = "estudianteInfoClient")
public interface EstudianteInfoClient {

    @GetMapping("/api/v1/estudiantes/{id}")
    EstudianteInfoResponse obtenerInfo(@PathVariable("id") String id);
}
