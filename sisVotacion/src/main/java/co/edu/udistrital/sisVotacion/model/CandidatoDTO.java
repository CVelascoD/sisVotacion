package co.edu.udistrital.sisVotacion.model;

public class CandidatoDTO {

    private int numero;
    private String nombre;
    private String partido;
    private int votos;

    public CandidatoDTO() {}

    public CandidatoDTO(int numero, String nombre, String partido) {
        this.numero = numero;
        this.nombre = nombre;
        this.partido = partido;
        this.votos = 0;
    }

    public int getNumero() { return numero; }
    public String getNombre() { return nombre; }
    public String getPartido() { return partido; }
    public int getVotos() { return votos; }

    public void setNumero(int numero) { this.numero = numero; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPartido(String partido) { this.partido = partido; }
    public void setVotos(int votos) { this.votos = votos; }
}
