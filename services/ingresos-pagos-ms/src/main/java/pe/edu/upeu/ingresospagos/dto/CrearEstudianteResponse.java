package pe.edu.upeu.ingresospagos.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrearEstudianteResponse {
    private String id;
    private String codigoEstudiante;
    private String username;
    private String temporaryPassword;
}
