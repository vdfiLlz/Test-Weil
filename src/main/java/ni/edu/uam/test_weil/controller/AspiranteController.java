package ni.edu.uam.test_weil.controller;

import ni.edu.uam.test_weil.dto.AspiranteDTO;
import ni.edu.uam.test_weil.dto.MensajeRespuesta;
import ni.edu.uam.test_weil.service.AspiranteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/aspirantes")
@RequiredArgsConstructor
public class AspiranteController {

    private final AspiranteService service;

    @GetMapping
    public ResponseEntity<List<AspiranteDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AspiranteDTO> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/cif/{cif}")
    public ResponseEntity<AspiranteDTO> buscarPorCif(@PathVariable String cif) {
        return ResponseEntity.ok(service.buscarPorCif(cif));
    }

    @PostMapping
    public ResponseEntity<AspiranteDTO> crear(@Valid @RequestBody AspiranteDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AspiranteDTO> actualizar(@PathVariable UUID id,
                                                   @Valid @RequestBody AspiranteDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensajeRespuesta> eliminar(@PathVariable UUID id) {
        service.eliminar(id);
        return ResponseEntity.ok(new MensajeRespuesta(200, "Aspirante eliminado correctamente"));
    }
}