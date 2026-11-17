package pe.edu.upeu.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.upeu.lms.entity.EstadoMatricula;
import pe.edu.upeu.lms.entity.MatriculaDetalle;

public interface MatriculaDetalleRepository extends JpaRepository<MatriculaDetalle, Long> {

    @Query("SELECT COUNT(d) FROM MatriculaDetalle d WHERE d.idSeccionCurso = :idSeccionCurso "
            + "AND d.matricula.estado <> :estadoExcluido")
    long contarInscritosEnSeccion(@Param("idSeccionCurso") Long idSeccionCurso,
                                   @Param("estadoExcluido") EstadoMatricula estadoExcluido);

    @Query("SELECT COUNT(d) FROM MatriculaDetalle d WHERE d.idSeccionCurso = :idSeccionCurso "
            + "AND d.matricula.idEstudiante = :idEstudiante AND d.matricula.estado <> :estadoExcluido")
    long contarInscripcionExistente(@Param("idSeccionCurso") Long idSeccionCurso,
                                     @Param("idEstudiante") String idEstudiante,
                                     @Param("estadoExcluido") EstadoMatricula estadoExcluido);
}
