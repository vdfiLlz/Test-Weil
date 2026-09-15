package ni.edu.uam.test_weil.repository;

import ni.edu.uam.test_weil.model.Psicopatologia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PsicopatologiaRepository extends JpaRepository<Psicopatologia, Integer> {
    Optional<Psicopatologia> findByCodigo(String codigo);
    boolean existsByCodigo(String codigo);
}