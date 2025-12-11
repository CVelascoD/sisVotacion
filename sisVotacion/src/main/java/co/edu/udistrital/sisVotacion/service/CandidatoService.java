package co.edu.udistrital.sisVotacion.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

        candidato.setVotos(0);

        lista.add(candidato);
        repo.saveCandidatos(lista);
        return "Candidato registrado correctamente.";
    }

    public Object obtenerTarjetonPublico() {
        List<CandidatoDTO> lista = repo.findAllCandidatos();

        if (lista.size() < 5) {
            return "Aun no hay suficientes candidatos para mostrar el tarjeton.";
        }

        List<Map<String, Object>> tarjetonPublico = new ArrayList<>();

        for (CandidatoDTO c : lista) {
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("numero", c.getNumero());
            item.put("nombre", c.getNombre());
            item.put("partido", c.getPartido());
            tarjetonPublico.add(item);
        }

        Map<String, Object> votoBlanco = new LinkedHashMap<>();
        votoBlanco.put("numero", 6);
        votoBlanco.put("nombre", "Voto en blanco");
        votoBlanco.put("partido", "N/A");
        tarjetonPublico.add(votoBlanco);

        return tarjetonPublico;
    }
}