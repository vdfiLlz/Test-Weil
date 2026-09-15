package ni.edu.uam.test_weil.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PsicopatologiaDTO {

    private Integer id;

    @NotBlank(message = "El código es obligatorio")
    @Size(max = 10)
    private String codigo;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 120)
    private String nombre;

    private String descripcion;
}