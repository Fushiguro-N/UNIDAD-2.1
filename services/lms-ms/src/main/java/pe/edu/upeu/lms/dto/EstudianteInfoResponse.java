package pe.edu.upeu.lms.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteInfoResponse {
    private String id;
    private String codigoEstudiante;
    private String nombres;
    private String apellidos;
}
