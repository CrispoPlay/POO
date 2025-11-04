/*
Nombre: Cristian Orellana
Carnet: 25664
Clase: DronRiego
Fecha de Creacion: 3/11/2025
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DronRiego extends Dispositivo implements Accionable, Registrable {
    private double capacidadAgua; // litros disponibles
    private final List<String> registros = new ArrayList<>();

    public DronRiego(String id, String nombre, double consumoElectrico, double capacidadAgua) {
        super(id, nombre, consumoElectrico);
        setCapacidadAgua(capacidadAgua);
    }

    public double getCapacidadAgua() { return capacidadAgua; }
    public void setCapacidadAgua(double capacidadAgua) {
        if (capacidadAgua < 0) throw new IllegalArgumentException("capacidadAgua no puede ser negativa");
        this.capacidadAgua = capacidadAgua;
    }

    @Override
    public void ejecutarAccion(String accion) {
        if ("regar".equalsIgnoreCase(accion)) {
            ejecutarAccion(accion, 1.0);
        } else {
            registros.add("Accion DronRiego desconocida: " + accion);
        }
    }

    @Override
    public void ejecutarAccion(String accion, double parametro) {
        if (parametro < 0) throw new IllegalArgumentException("parametro no puede ser negativo");
        if ("regar".equalsIgnoreCase(accion)) {
            double usado = Math.min(parametro, capacidadAgua);
            capacidadAgua -= usado;
            registros.add("Accion DronRiego: regar=%.2f L (restante=%.2f L)".formatted(usado, capacidadAgua));
        } else {
            registros.add("Accion DronRiego desconocida: %s (param=%.2f)".formatted(accion, parametro));
        }
    }

    @Override
    public String generarRegistro() {
        String r = "Registro DronRiego: capacidadAgua=%.2f L".formatted(capacidadAgua);
        registros.add(r);
        return r;
    }

    @Override
    public List<String> getRegistros() { return Collections.unmodifiableList(registros); }

    @Override
    public String toString() {
        return super.toString() + " [capacidadAgua=%.2f L]".formatted(capacidadAgua);
    }
}
