package pe.edu.upeu.ingresospagos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.ingresospagos.entity.Estudiante;

import java.util.Optional;

public interface EstudianteRepository extends JpaRepository<Estudiante, String> {

    long countByCodigoEstudianteStartingWith(String prefijo);

    boolean existsByDni(String dni);

    Optional<Estudiante> findByCodigoEstudiante(String codigoEstudiante);
}
