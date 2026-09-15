package ni.edu.uam.test_weil.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

/**
 * Mapea la tabla 'rubricas'.
 */
@Entity
@Table(name = "rubricas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rubrica {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "nombre", length = 120, nullable = false)
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "puntaje_max", nullable = false, precision = 5, scale = 2)
    private BigDecimal puntajeMax;

    @Column(name = "activa", nullable = false)
    private Boolean activa;

    @Column(name = "creado_en", nullable = false, updatable = false)
    private Instant creadoEn;

    @PrePersist
    public void prePersist() {
        if (creadoEn == null) creadoEn = Instant.now();
        if (activa == null) activa = true;
    }
}