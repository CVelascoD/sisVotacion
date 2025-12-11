package co.edu.udistrital.sisVotacion.service;

import java.util.List;

import org.springframework.stereotype.Service;

import co.edu.udistrital.sisVotacion.model.CandidatoDTO;
import co.edu.udistrital.sisVotacion.model.VotoDTO;
import co.edu.udistrital.sisVotacion.repository.VotacionRepository;

@Service
public class VotacionService {

    private final VotacionRepository repo;

    public VotacionService(VotacionRepository repo) {
        this.repo = repo;
    }

    public String votar(int numero) {

        List<CandidatoDTO> candidatos = repo.findAllCandidatos();

        if (candidatos.size() < 5) {
            return "No se puede votar hasta que existan exactamente 5 candidatos.";
        }

        if (numero < 1 || numero > 6) {
            return "Numero de opcion invalido.";
        }

        List<VotoDTO> votos = repo.findAllVotos();
        votos.add(new VotoDTO(numero));
        repo.saveVotos(votos);

        if (numero != 6) {
            for (CandidatoDTO c : candidatos) {
                if (c.getNumero() == numero) {
                    c.setVotos(c.getVotos() + 1);
                }
            }
            repo.saveCandidatos(candidatos);
        }

        return "Voto registrado correctamente.";
    }

    public int totalVotos() {
        return repo.findAllVotos().size();
    }
}
