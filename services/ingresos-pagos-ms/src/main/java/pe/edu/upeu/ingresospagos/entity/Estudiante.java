package pe.edu.upeu.ingresospagos.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estudiantes")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante {

    @Id
    @Column(length = 36)
    private String id;

    @Column(length = 150)
    private String nombres;

    @Column(length = 150)
    private String apellidos;

    @Column(name = "codigo_estudiante", length = 9, unique = true)
    private String codigoEstudiante;

    @Column(length = 150)
    private String email;

    @Column(name = "id_usuario_auth")
    private Long idUsuarioAuth;

    @Column(length = 8, unique = true)
    private String dni;

    @Column(length = 15)
    private String celular;
}
