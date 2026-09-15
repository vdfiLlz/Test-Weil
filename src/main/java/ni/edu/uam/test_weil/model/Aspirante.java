package ni.edu.uam.test_weil.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

/**
 * Mapea la tabla 'aspirantes'.
 * La columna 'grupo_baremo' es de tipo ENUM en PostgreSQL
 * (grupo_baremo: 'UN','AD','7-16','AN') y se mapea como String.
 */
@Entity
@Table(name = "aspirantes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Aspirante {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "cif", length = 9, unique = true, nullable = false)
    private String cif;

    @Column(name = "nombre_completo", length = 160, nullable = false)
    private String nombreCompleto;

    @Column(name = "carrera", length = 120, nullable = false)
    private String carrera;

    /** Valores posibles: 'UN', 'AD', '7-16', 'AN'. */
    @Column(name = "grupo_baremo", length = 20, nullable = false)
    private String grupoBaremo;

    @Column(name = "email", length = 160)
    private String email;

    @Column(name = "creado_en", nullable = false, updatable = false)
    private Instant creadoEn;

    @PrePersist
    public void prePersist() {
        if (creadoEn == null) creadoEn = Instant.now();
        if (grupoBaremo == null) grupoBaremo = "UN";
    }
}