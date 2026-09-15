package ni.edu.uam.test_weil.service;

import ni.edu.uam.test_weil.dto.PsicopatologiaDTO;
import ni.edu.uam.test_weil.exception.ResourceNotFoundException;
import ni.edu.uam.test_weil.model.Psicopatologia;
import ni.edu.uam.test_weil.repository.PsicopatologiaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PsicopatologiaService {

    private final PsicopatologiaRepository repo;

    public List<PsicopatologiaDTO> listarTodas() {
        return repo.findAll().stream().map(this::toDTO).toList();
    }

    public PsicopatologiaDTO buscarPorId(Integer id) {
        Psicopatologia p = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Psicopatología no encontrada con id: " + id));
        return toDTO(p);
    }

    public PsicopatologiaDTO buscarPorCodigo(String codigo) {
        Psicopatologia p = repo.findByCodigo(codigo)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Psicopatología no encontrada con código: " + codigo));
        return toDTO(p);
    }

    public PsicopatologiaDTO crear(PsicopatologiaDTO dto) {
        if (repo.existsByCodigo(dto.getCodigo())) {
            throw new IllegalArgumentException(
                    "Ya existe una psicopatología con el código " + dto.getCodigo());
        }
        Psicopatologia p = toEntity(dto);
        p.setId(null);
        return toDTO(repo.save(p));
    }

    public PsicopatologiaDTO actualizar(Integer id, PsicopatologiaDTO dto) {
        Psicopatologia p = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Psicopatología no encontrada con id: " + id));
        p.setCodigo(dto.getCodigo());
        p.setNombre(dto.getNombre());
        p.setDescripcion(dto.getDescripcion());
        return toDTO(repo.save(p));
    }

    public void eliminar(Integer id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Psicopatología no encontrada con id: " + id);
        }
        repo.deleteById(id);
    }

    private PsicopatologiaDTO toDTO(Psicopatologia p) {
        return PsicopatologiaDTO.builder()
                .id(p.getId())
                .codigo(p.getCodigo())
                .nombre(p.getNombre())
                .descripcion(p.getDescripcion())
                .build();
    }

    private Psicopatologia toEntity(PsicopatologiaDTO d) {
        return Psicopatologia.builder()
                .id(d.getId())
                .codigo(d.getCodigo())
                .nombre(d.getNombre())
                .descripcion(d.getDescripcion())
                .build();
    }
}