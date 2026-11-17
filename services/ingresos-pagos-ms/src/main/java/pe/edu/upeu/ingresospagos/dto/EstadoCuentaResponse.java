package pe.edu.upeu.ingresospagos.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstadoCuentaResponse {
    private String idEstudiante;
    private String nombres;
    private String apellidos;
    private String dni;
    private Boolean tieneDeudaPendiente;
}
