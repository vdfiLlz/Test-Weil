package ni.edu.uam.test_weil.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RubricaDTO {

    private UUID id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 120)
    private String nombre;

    private String descripcion;

    @NotNull(message = "El puntaje máximo es obligatorio")
    @DecimalMin(value = "0.00", message = "El puntaje debe ser positivo")
    @Digits(integer = 3, fraction = 2)
    private BigDecimal puntajeMax;

    private Boolean activa;

    private Instant creadoEn;
}