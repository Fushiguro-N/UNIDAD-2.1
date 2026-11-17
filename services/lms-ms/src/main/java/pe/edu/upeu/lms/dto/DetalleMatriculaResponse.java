package pe.edu.upeu.lms.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetalleMatriculaResponse {
    private Long idSeccionCurso;
    private String nombreCurso;
    private String nombreSeccion;
}
