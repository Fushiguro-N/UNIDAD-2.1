package pe.edu.upeu.lms.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetalleMatriculaRequest {

    @NotNull
    private Long idSeccionCurso;
}
