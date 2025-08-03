public class Ronda {
    private Digimon digimon1Jugador;
    private Digimon digimon2Jugador;
    private String accion1Jugador;
    private String accion2Jugador;
    private String ganadorRonda;

    public Ronda() {
    }

    public Digimon getDigimon1Jugador() {
        return digimon1Jugador;
    }

    public void setDigimon1Jugador(Digimon digimon1Jugador) {
        this.digimon1Jugador = digimon1Jugador;
    }

    public Digimon getDigimon2Jugador() {
        return digimon2Jugador;
    }

    public void setDigimon2Jugador(Digimon digimon2Jugador) {
        this.digimon2Jugador = digimon2Jugador;
    }

    public String getAccion1Jugador() {
        return accion1Jugador;
    }

    public void setAccion1Jugador(String accion1Jugador) {
        this.accion1Jugador = accion1Jugador;
    }

    public String getAccion2Jugador() {
        return accion2Jugador;
    }

    public void setAccion2Jugador(String accion2Jugador) {
        this.accion2Jugador = accion2Jugador;
    }

    public String getGanadorRonda() {
        return ganadorRonda;
    }

    public void setGanadorRonda(String ganadorRonda) {
        this.ganadorRonda = ganadorRonda;
    }
}
