package pe.edu.upeu.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.lms.entity.Docente;

import java.util.Optional;

public interface DocenteRepository extends JpaRepository<Docente, Long> {

    Optional<Docente> findByEmail(String email);
}
