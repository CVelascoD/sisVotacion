package co.edu.udistrital.sisVotacion.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.udistrital.sisVotacion.model.CandidatoDTO;
import co.edu.udistrital.sisVotacion.service.CandidatoService;
import co.edu.udistrital.sisVotacion.service.VotacionService;
import co.edu.udistrital.sisVotacion.service.EstadisticaService;

@RestController
@RequestMapping("/votacion")
public class VotacionController {

    private final CandidatoService candidatoService;
    private final VotacionService votacionService;
    private final EstadisticaService estadisticaService;

    public VotacionController(CandidatoService candidatoService,
                              VotacionService votacionService,
                              EstadisticaService estadisticaService) {
        this.candidatoService = candidatoService;
        this.votacionService = votacionService;
        this.estadisticaService = estadisticaService;
    }

    // Registrar candidato
    // POST http://localhost:8084/sisVotacion/votacion/candidato
    @PostMapping("/candidato")
    public ResponseEntity<String> registrar(@RequestBody CandidatoDTO candidato) {
        return ResponseEntity.ok(candidatoService.agregar(candidato));
    }

    // Tarjeton
    // GET http://localhost:8084/sisVotacion/votacion/tarjeton
    @GetMapping("/tarjeton")
    public ResponseEntity<Object> tarjeton() {
        return ResponseEntity.ok(candidatoService.obtenerTarjetonPublico());
    }

    // Votar
    // http://localhost:8084/sisVotacion/votacion/votar/{1}
    @PostMapping("/votar/{numero}")
    public ResponseEntity<String> votar(@PathVariable int numero) {
        return ResponseEntity.ok(votacionService.votar(numero));
    }

    // Consulta de votos
    // GET http://localhost:8084/sisVotacion/votacion/votos
    @GetMapping("/votos")
    public ResponseEntity<Map<String, Integer>> consultarVotos() {
        return ResponseEntity.ok(votacionService.obtenerResultados());
    }

    // Estadísticas
    // GET http://localhost:8084/sisVotacion/votacion/estadisticas
    @GetMapping("/estadisticas")
    public ResponseEntity<Map<String, Object>> estadisticas() {
        return ResponseEntity.ok(estadisticaService.generarEstadistica());
    }

    // Autor
    // GET http://localhost:8084/sisVotacion/votacion/autor
    @GetMapping("/autor")
    public ResponseEntity<String> autor() {
        return ResponseEntity.ok("Cristian Velasco, Bryan Bohorquez, Andres Rodriguez");
    }
}