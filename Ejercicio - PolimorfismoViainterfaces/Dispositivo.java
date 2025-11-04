/*
Nombre: Cristian Orellana
Carnet: 25664
Clase: Dispositivo
Fecha de Creacion: 3/11/2025
 */
import java.util.Objects;

public abstract class Dispositivo implements Comparable<Dispositivo> {
    private final String id;
    private String nombre;
    private double consumoElectrico; // watts

    protected Dispositivo(String id, String nombre, double consumoElectrico) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("id no puede ser null/blank");
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("nombre no puede ser null/blank");
        if (consumoElectrico < 0) throw new IllegalArgumentException("consumoElectrico no puede ser negativo");
        this.id = id;
        this.nombre = nombre;
        this.consumoElectrico = consumoElectrico;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("nombre no puede ser null/blank");
        this.nombre = nombre;
    }
    public double getConsumoElectrico() { return consumoElectrico; }
    public void setConsumoElectrico(double consumoElectrico) {
        if (consumoElectrico < 0) throw new IllegalArgumentException("consumoElectrico no puede ser negativo");
        this.consumoElectrico = consumoElectrico;
    }

    @Override
    public int compareTo(Dispositivo o) {
        return Double.compare(this.consumoElectrico, o.consumoElectrico);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dispositivo that = (Dispositivo) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return "%s{id='%s', nombre='%s', consumoElectrico=%.2f}"
                .formatted(getClass().getSimpleName(), id, nombre, consumoElectrico);
    }
}
