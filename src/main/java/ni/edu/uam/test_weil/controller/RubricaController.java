package ni.edu.uam.test_weil.controller;

import ni.edu.uam.test_weil.dto.MensajeRespuesta;
import ni.edu.uam.test_weil.dto.RubricaDTO;
import ni.edu.uam.test_weil.service.RubricaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/rubricas")
@RequiredArgsConstructor
public class RubricaController {

    private final RubricaService service;

    @GetMapping
    public ResponseEntity<List<RubricaDTO>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RubricaDTO> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<RubricaDTO> crear(@Valid @RequestBody RubricaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RubricaDTO> actualizar(@PathVariable UUID id,
                                                 @Valid @RequestBody RubricaDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensajeRespuesta> eliminar(@PathVariable UUID id) {
        service.eliminar(id);
        return ResponseEntity.ok(new MensajeRespuesta(200, "Rúbrica eliminada correctamente"));
    }
}