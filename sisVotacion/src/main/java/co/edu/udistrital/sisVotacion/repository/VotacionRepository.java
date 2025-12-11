package co.edu.udistrital.sisVotacion.repository;

import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Repository;
import co.edu.udistrital.sisVotacion.model.CandidatoDTO;
import co.edu.udistrital.sisVotacion.model.VotoDTO;
import co.edu.udistrital.sisVotacion.util.StorageJSON;

@Repository
public class VotacionRepository {

    private final String FILE_CANDIDATOS = Paths.get(System.getProperty("user.dir"), "data", "candidatos.json").toString();
    private final String FILE_VOTOS = Paths.get(System.getProperty("user.dir"), "data", "votos.json").toString();

    public List<CandidatoDTO> findAllCandidatos() {
        return StorageJSON.load(FILE_CANDIDATOS, CandidatoDTO.class);
    }

    public void saveCandidatos(List<CandidatoDTO> candidatos) {
        StorageJSON.save(FILE_CANDIDATOS, candidatos);
    }

    public List<VotoDTO> findAllVotos() {
        return StorageJSON.load(FILE_VOTOS, VotoDTO.class);
    }

    public void saveVotos(List<VotoDTO> votos) {
        StorageJSON.save(FILE_VOTOS, votos);
    }
}
