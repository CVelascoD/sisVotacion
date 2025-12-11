package co.edu.udistrital.sisVotacion.service;

import java.util.List;

import org.springframework.stereotype.Service;

import co.edu.udistrital.sisVotacion.model.CandidatoDTO;
import co.edu.udistrital.sisVotacion.repository.VotacionRepository;

@Service
public class CandidatoService {

    private final VotacionRepository repo;

    public CandidatoService(VotacionRepository repo) {
        this.repo = repo;
    }

    public List<CandidatoDTO> listar() {
        return repo.findAllCandidatos();
    }

    public String agregar(CandidatoDTO candidato) {
        List<CandidatoDTO> lista = repo.findAllCandidatos();

        if (lista.size() >= 5) {
            return "Ya se alcanzo el maximo permitido de 5 candidatos.";
        }

        lista.add(candidato);
        repo.saveCandidatos(lista);
        return "Candidato registrado correctamente.";
    }
}
