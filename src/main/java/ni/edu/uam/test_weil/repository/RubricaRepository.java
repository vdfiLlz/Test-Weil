package ni.edu.uam.test_weil.repository;

import ni.edu.uam.test_weil.model.Rubrica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RubricaRepository extends JpaRepository<Rubrica, UUID> { }