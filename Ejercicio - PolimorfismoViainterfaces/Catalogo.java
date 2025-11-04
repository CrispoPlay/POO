/*
Nombre: Cristian Orellana
Carnet: 25664
Clase: Catalogo
Fecha de Creacion: 3/11/2025
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class Catalogo {
    private final List<Dispositivo> dispositivos = new ArrayList<>();

    public void agregarDispositivo(Dispositivo d) {
        if (d == null) throw new IllegalArgumentException("dispositivo null");
        if (buscarPorId(d.getId()) != null) throw new IllegalArgumentException("Ya existe un dispositivo con id=" + d.getId());
        dispositivos.add(d);
    }

    public List<Dispositivo> listarTodos() { return Collections.unmodifiableList(dispositivos); }

    public Dispositivo buscarPorId(String id) {
        if (id == null) return null;
        for (Dispositivo d : dispositivos) if (d.getId().equalsIgnoreCase(id)) return d;
        return null;
    }

    public List<Dispositivo> buscarPorNombre(String nombreParcial) {
        if (nombreParcial == null || nombreParcial.isBlank()) return List.of();
        String needle = nombreParcial.toLowerCase(Locale.ROOT);
        List<Dispositivo> res = new ArrayList<>();
        for (Dispositivo d : dispositivos)
            if (d.getNombre().toLowerCase(Locale.ROOT).contains(needle)) res.add(d);
        return res;
    }

    public List<Dispositivo> ordenarPorConsumo() {
        dispositivos.sort(Comparator.naturalOrder());
        return Collections.unmodifiableList(dispositivos);
    }
}
