package co.edu.udistrital.sisVotacion.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.udistrital.sisVotacion.model.CandidatoDTO;
import co.edu.udistrital.sisVotacion.service.CandidatoService;
import co.edu.udistrital.sisVotacion.service.VotacionService;
import co.edu.udistrital.sisVotacion.service.EstadisticaService;

@RestController
@RequestMapping("/sisvotacion")
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
    @PostMapping("/candidato")
    public ResponseEntity<String> registrar(@RequestBody CandidatoDTO candidato) {
        return ResponseEntity.ok(candidatoService.agregar(candidato));
    }

    // Tarjetón
    @GetMapping("/tarjeton")
    public ResponseEntity<Object> tarjeton() {
        List<CandidatoDTO> lista = candidatoService.listar();

        if (lista.size() < 5) {
            return ResponseEntity.ok("Aun no hay suficiente candidatos para mostrar el tarjeton.");
        }

        return ResponseEntity.ok(lista);
    }

    // Votar
    @PostMapping("/votar/{numero}")
    public ResponseEntity<String> votar(@PathVariable int numero) {
        return ResponseEntity.ok(votacionService.votar(numero));
    }

    // Resultados
    @GetMapping("/estadisticas")
    public ResponseEntity<Map<String, Object>> estadisticas() {
        return ResponseEntity.ok(estadisticaService.generarEstadistica());
    }

    // Autor
    @GetMapping("/autor")
    public ResponseEntity<String> autor() {
        return ResponseEntity.ok("Cristian Velasco, Bryan Bohorquez, Andres Rodriguez");
    }
}
