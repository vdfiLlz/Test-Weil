package ni.edu.uam.test_weil.repository;

import ni.edu.uam.test_weil.model.Aspirante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AspiranteRepository extends JpaRepository<Aspirante, UUID> {
    Optional<Aspirante> findByCif(String cif);
    boolean existsByCif(String cif);
}