/*
Nombre: Cristian Orellana
Carnet: 25664
Clase: DronMonitoreo
Fecha de Creacion: 3/11/2025
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DronMonitoreo extends Dispositivo implements Accionable, Medible, Registrable {
    private String camaraResolucion;
    private double ultimoIndice;
    private final List<String> registros = new ArrayList<>();

    public DronMonitoreo(String id, String nombre, double consumoElectrico, String camaraResolucion) {
        super(id, nombre, consumoElectrico);
        setCamaraResolucion(camaraResolucion);
        this.ultimoIndice = 0.0;
    }

    public String getCamaraResolucion() { return camaraResolucion; }
    public void setCamaraResolucion(String camaraResolucion) {
        if (camaraResolucion == null || camaraResolucion.isBlank())
            throw new IllegalArgumentException("camaraResolucion no puede ser null/blank");
        this.camaraResolucion = camaraResolucion;
    }

    @Override
    public double medir() { return ultimoIndice; }

    @Override
    public void ejecutarAccion(String accion) {
        if ("fotografiar".equalsIgnoreCase(accion)) {
            registros.add("Accion DronMonitoreo: fotografiar @res=" + camaraResolucion);
        } else {
            registros.add("Accion DronMonitoreo desconocida: " + accion);
        }
    }

    @Override
    public void ejecutarAccion(String accion, double parametro) {
        if (parametro < 0) throw new IllegalArgumentException("parametro no puede ser negativo");
        if ("fotografiar".equalsIgnoreCase(accion)) {
            registros.add("Accion DronMonitoreo: fotografiar altura=%.1f m @res=%s".formatted(parametro, camaraResolucion));
        } else if ("ajustarIndice".equalsIgnoreCase(accion)) {
            ultimoIndice = parametro;
            registros.add("Accion DronMonitoreo: ajustarIndice=%.3f".formatted(parametro));
        } else {
            registros.add("Accion DronMonitoreo desconocida: %s (param=%.2f)".formatted(accion, parametro));
        }
    }

    @Override
    public String generarRegistro() {
        String r = "Registro DronMonitoreo: indice=%.3f, camara=%s".formatted(ultimoIndice, camaraResolucion);
        registros.add(r);
        return r;
    }

    @Override
    public List<String> getRegistros() { return Collections.unmodifiableList(registros); }

    @Override
    public String toString() {
        return super.toString() + " [camaraResolucion=%s, indice=%.3f]".formatted(camaraResolucion, ultimoIndice);
    }
}
