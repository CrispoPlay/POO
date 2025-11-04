/*
Nombre: Cristian Orellana
Carnet: 25664
Clase: Estacion Meteorologica
Fecha de Creacion: 3/11/2025
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EstacionMeteorologica extends Dispositivo implements Medible, Registrable {
    private double temperatura; // (°C)
    private double presion;     // (hPa)
    private final List<String> registros = new ArrayList<>();

    public EstacionMeteorologica(String id, String nombre, double consumoElectrico, double temperatura, double presion) {
        super(id, nombre, consumoElectrico);
        setTemperatura(temperatura);
        setPresion(presion);
    }

    public double getTemperatura() { return temperatura; }
    public double getPresion() { return presion; }

    public void setTemperatura(double temperatura) { this.temperatura = temperatura; }
    public void setPresion(double presion) {
        if (presion < 0) throw new IllegalArgumentException("presion no puede ser negativa");
        this.presion = presion;
    }

    @Override
    public double medir() { return temperatura; }

    @Override
    public String generarRegistro() {
        String r = "Registro Estacion: temperatura=%.1f°C, presion=%.1f hPa".formatted(temperatura, presion);
        registros.add(r);
        return r;
    }

    @Override
    public List<String> getRegistros() { return Collections.unmodifiableList(registros); }

    @Override
    public String toString() {
        return super.toString() + " [temperatura=%.1f°C, presion=%.1f hPa]".formatted(temperatura, presion);
    }
}
