package pe.edu.upeu.lms.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "matricula_detalles")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatriculaDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_matricula", nullable = false)
    private Matricula matricula;

    @Column(name = "id_seccion_curso", nullable = false)
    private Long idSeccionCurso;

    @Column(name = "nombre_curso", length = 100)
    private String nombreCurso;

    @Column(name = "nombre_seccion", length = 10)
    private String nombreSeccion;
}
