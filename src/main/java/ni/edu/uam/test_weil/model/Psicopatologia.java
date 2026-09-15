package ni.edu.uam.test_weil.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Mapea la tabla 'psicopatologias'.
 * Contiene las 8 escalas clínicas del modelo UAM
 * (Hs, D, Hy, Pd, Pa, Pt, Sc, Ma).
 * La columna id es SMALLSERIAL → se mapea como Integer.
 */
@Entity
@Table(name = "psicopatologias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Psicopatologia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "codigo", length = 10, unique = true, nullable = false)
    private String codigo;

    @Column(name = "nombre", length = 120, nullable = false)
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;
}