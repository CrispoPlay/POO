import java.util.ArrayList;
import java.util.List;

public class Entrenador {
    private String nombreEntrenador;
    private List<Digimon> equipoDigimon;
    private List<Digimon> digimonUsados;

    public Entrenador() {
        equipoDigimon = new ArrayList<>();
        digimonUsados = new ArrayList<>();
    }

    public String getNombreEntrenador() {
        return nombreEntrenador;
    }

    public void setNombreEntrenador(String nombreEntrenador) {
        this.nombreEntrenador = nombreEntrenador;
    }

    public List<Digimon> getEquipoDigimon() {
        return equipoDigimon;
    }

    public void setEquipoDigimon(List<Digimon> equipoDigimon) {
        this.equipoDigimon = equipoDigimon;
    }

    public List<Digimon> getDigimonUsados() {
        return digimonUsados;
    }

    public void setDigimonUsados(List<Digimon> digimonUsados) {
        this.digimonUsados = digimonUsados;
    }

    public void agregarDigimon(Digimon d) {
        if (equipoDigimon.size() < 3) {
            equipoDigimon.add(d);
        }
    }

    public Digimon seleccionarDigimonRonda() {
        for (Digimon d : equipoDigimon) {
            if (!digimonUsados.contains(d)) {
                return d;
            }
        }
        return null;
    }

    public void registrarUsoDigimon(Digimon d) {
        digimonUsados.add(d);
    }
}
