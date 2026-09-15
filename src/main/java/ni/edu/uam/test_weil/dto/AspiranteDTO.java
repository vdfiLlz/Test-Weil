package ni.edu.uam.test_weil.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AspiranteDTO {

    private UUID id;

    @NotBlank(message = "El CIF es obligatorio")
    @Pattern(regexp = "^[0-9]{6,9}$",
            message = "El CIF debe tener entre 6 y 9 dígitos numéricos")
    private String cif;

    @NotBlank(message = "El nombre completo es obligatorio")
    @Size(max = 160, message = "El nombre no puede superar 160 caracteres")
    private String nombreCompleto;

    @NotBlank(message = "La carrera es obligatoria")
    @Size(max = 120)
    private String carrera;

    @NotBlank(message = "El grupo de baremo es obligatorio")
    private String grupoBaremo;

    @Email(message = "El email no tiene un formato válido")
    @Size(max = 160)
    private String email;

    private Instant creadoEn;
}