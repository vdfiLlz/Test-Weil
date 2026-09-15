package ni.edu.uam.test_weil.dto;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorRespuesta {
    private int codigo;
    private String mensaje;
    private Map<String, String> detalles;
}