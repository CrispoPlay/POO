/*
Nombre: Cristian Orellana
Carnet: 25664
Clase: Sensorsuelo
Fecha de Creacion: 3/11/2025
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SensorSuelo extends Dispositivo implements Medible, Registrable {
    private double humedad;     // (%)
    private double temperatura; // (°C)
    private final List<String> registros = new ArrayList<>();

    public SensorSuelo(String id, String nombre, double consumoElectrico, double humedad, double temperatura) {
        super(id, nombre, consumoElectrico);
        setHumedad(humedad);
        setTemperatura(temperatura);
    }

    public double getHumedad() { return humedad; }
    public double getTemperatura() { return temperatura; }

    public void setHumedad(double humedad) {
        if (humedad < 0) throw new IllegalArgumentException("humedad no puede ser negativa");
        this.humedad = humedad;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    @Override
    public double medir() { return humedad; }

    @Override
    public String generarRegistro() {
        String r = "Registro SensorSuelo: humedad=%.1f%%, temperatura=%.1f°C".formatted(humedad, temperatura);
        registros.add(r);
        return r;
    }

    @Override
    public List<String> getRegistros() { return Collections.unmodifiableList(registros); }

    @Override
    public String toString() {
        return super.toString() + " [humedad=%.1f%%, temperatura=%.1f°C]".formatted(humedad, temperatura);
    }
}
