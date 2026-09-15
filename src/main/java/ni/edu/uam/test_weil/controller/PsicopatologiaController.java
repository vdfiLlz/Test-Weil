package ni.edu.uam.test_weil.controller;

import ni.edu.uam.test_weil.dto.MensajeRespuesta;
import ni.edu.uam.test_weil.dto.PsicopatologiaDTO;
import ni.edu.uam.test_weil.service.PsicopatologiaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/psicopatologias")
@RequiredArgsConstructor
public class PsicopatologiaController {

    private final PsicopatologiaService service;

    @GetMapping
    public ResponseEntity<List<PsicopatologiaDTO>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PsicopatologiaDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<PsicopatologiaDTO> buscarPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(service.buscarPorCodigo(codigo));
    }

    @PostMapping
    public ResponseEntity<PsicopatologiaDTO> crear(@Valid @RequestBody PsicopatologiaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PsicopatologiaDTO> actualizar(@PathVariable Integer id,
                                                        @Valid @RequestBody PsicopatologiaDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensajeRespuesta> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.ok(new MensajeRespuesta(200, "Psicopatología eliminada correctamente"));
    }
}