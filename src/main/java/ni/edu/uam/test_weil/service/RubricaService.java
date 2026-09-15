package ni.edu.uam.test_weil.service;

import ni.edu.uam.test_weil.dto.RubricaDTO;
import ni.edu.uam.test_weil.exception.ResourceNotFoundException;
import ni.edu.uam.test_weil.model.Rubrica;
import ni.edu.uam.test_weil.repository.RubricaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RubricaService {

    private final RubricaRepository repo;

    public List<RubricaDTO> listarTodas() {
        return repo.findAll().stream().map(this::toDTO).toList();
    }

    public RubricaDTO buscarPorId(UUID id) {
        Rubrica r = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Rúbrica no encontrada con id: " + id));
        return toDTO(r);
    }

    public RubricaDTO crear(RubricaDTO dto) {
        Rubrica r = toEntity(dto);
        r.setId(null);
        return toDTO(repo.save(r));
    }

    public RubricaDTO actualizar(UUID id, RubricaDTO dto) {
        Rubrica r = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Rúbrica no encontrada con id: " + id));
        r.setNombre(dto.getNombre());
        r.setDescripcion(dto.getDescripcion());
        r.setPuntajeMax(dto.getPuntajeMax());
        if (dto.getActiva() != null) r.setActiva(dto.getActiva());
        return toDTO(repo.save(r));
    }

    public void eliminar(UUID id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Rúbrica no encontrada con id: " + id);
        }
        repo.deleteById(id);
    }

    private RubricaDTO toDTO(Rubrica r) {
        return RubricaDTO.builder()
                .id(r.getId())
                .nombre(r.getNombre())
                .descripcion(r.getDescripcion())
                .puntajeMax(r.getPuntajeMax())
                .activa(r.getActiva())
                .creadoEn(r.getCreadoEn())
                .build();
    }

    private Rubrica toEntity(RubricaDTO d) {
        return Rubrica.builder()
                .id(d.getId())
                .nombre(d.getNombre())
                .descripcion(d.getDescripcion())
                .puntajeMax(d.getPuntajeMax())
                .activa(d.getActiva() == null ? Boolean.TRUE : d.getActiva())
                .build();
    }
}