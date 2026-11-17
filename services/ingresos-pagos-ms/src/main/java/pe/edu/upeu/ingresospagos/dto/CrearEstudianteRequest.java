package pe.edu.upeu.ingresospagos.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrearEstudianteRequest {
    private String dni;
    private String nombres;
    private String apellidos;
    private String email;
    private String celular;
}
