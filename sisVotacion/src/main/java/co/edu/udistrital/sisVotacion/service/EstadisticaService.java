package co.edu.udistrital.sisVotacion.service;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import co.edu.udistrital.sisVotacion.model.CandidatoDTO;
import co.edu.udistrital.sisVotacion.repository.VotacionRepository;

@Service
public class EstadisticaService {

    private final VotacionRepository repo;
    private final VotacionService votacionService;

    public EstadisticaService(VotacionRepository repo, VotacionService votacionService) {
        this.repo = repo;
        this.votacionService = votacionService;
    }

    public Map<String, Object> generarEstadistica() {

        List<CandidatoDTO> candidatos = repo.findAllCandidatos();
        int total = votacionService.totalVotos();

        Map<String, Object> resultado = new LinkedHashMap<>();
        resultado.put("fechaConsulta", LocalDateTime.now().toString());
        resultado.put("totalVotaciones", total);

        for (CandidatoDTO c : candidatos) {
            double porcentaje = total == 0 ? 0 : (c.getVotos() * 100.0 / total);
            resultado.put(c.getNombre(), porcentaje);
        }

        int blancos = (int) repo.findAllVotos().stream().filter(v -> v.getCandidatoNumero() == 6).count();
        resultado.put("Voto en blanco", total == 0 ? 0 : (blancos * 100.0 / total));

        return resultado;
    }
}
