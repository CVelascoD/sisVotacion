package co.edu.udistrital.sisVotacion.model;

import java.time.LocalDateTime;

public class VotoDTO {

    private int candidatoNumero;
    private LocalDateTime fechaHora;

    public VotoDTO() {}

    public VotoDTO(int candidatoNumero) {
        this.candidatoNumero = candidatoNumero;
        this.fechaHora = LocalDateTime.now();
    }

    public int getCandidatoNumero() { return candidatoNumero; }
    public LocalDateTime getFechaHora() { return fechaHora; }

    public void setCandidatoNumero(int candidatoNumero) { this.candidatoNumero = candidatoNumero; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
}
