package ni.edu.uam.test_weil.service;

import ni.edu.uam.test_weil.dto.AspiranteDTO;
import ni.edu.uam.test_weil.exception.ResourceNotFoundException;
import ni.edu.uam.test_weil.model.Aspirante;
import ni.edu.uam.test_weil.repository.AspiranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AspiranteService {

    private final AspiranteRepository repo;

    public List<AspiranteDTO> listarTodos() {
        return repo.findAll().stream().map(this::toDTO).toList();
    }

    public AspiranteDTO buscarPorId(UUID id) {
        Aspirante a = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Aspirante no encontrado con id: " + id));
        return toDTO(a);
    }

    public AspiranteDTO buscarPorCif(String cif) {
        Aspirante a = repo.findByCif(cif)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Aspirante no encontrado con CIF: " + cif));
        return toDTO(a);
    }

    public AspiranteDTO crear(AspiranteDTO dto) {
        if (repo.existsByCif(dto.getCif())) {
            throw new IllegalArgumentException(
                    "Ya existe un aspirante con el CIF " + dto.getCif());
        }
        Aspirante entidad = toEntity(dto);
        entidad.setId(null);
        return toDTO(repo.save(entidad));
    }

    public AspiranteDTO actualizar(UUID id, AspiranteDTO dto) {
        Aspirante a = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Aspirante no encontrado con id: " + id));
        a.setCif(dto.getCif());
        a.setNombreCompleto(dto.getNombreCompleto());
        a.setCarrera(dto.getCarrera());
        a.setGrupoBaremo(dto.getGrupoBaremo());
        a.setEmail(dto.getEmail());
        return toDTO(repo.save(a));
    }

    public void eliminar(UUID id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Aspirante no encontrado con id: " + id);
        }
        repo.deleteById(id);
    }

    /* ---------- mapeos ---------- */

    private AspiranteDTO toDTO(Aspirante a) {
        return AspiranteDTO.builder()
                .id(a.getId())
                .cif(a.getCif())
                .nombreCompleto(a.getNombreCompleto())
                .carrera(a.getCarrera())
                .grupoBaremo(a.getGrupoBaremo())
                .email(a.getEmail())
                .creadoEn(a.getCreadoEn())
                .build();
    }

    private Aspirante toEntity(AspiranteDTO d) {
        return Aspirante.builder()
                .id(d.getId())
                .cif(d.getCif())
                .nombreCompleto(d.getNombreCompleto())
                .carrera(d.getCarrera())
                .grupoBaremo(d.getGrupoBaremo())
                .email(d.getEmail())
                .build();
    }
}