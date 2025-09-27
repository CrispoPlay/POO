/*
Nombre: Cristian Estuardo Orellana Dieguez
Clase: Item
Descripcion: Esta clase sirve para modelar los distintos items que puede usar el jugador
 */
import java.util.*;

public class Item {
    private String nombre;
    private String tipo; // cura, buff
    private int efecto; // cantidad de curación o aumento de ataque
    private int cantidad;

    public Item(String nombre, String tipo, int efecto, int cantidad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.efecto = efecto;
        this.cantidad = cantidad;
    }

    public String getNombre() { return nombre; }
    public String getTipo() { return tipo; }
    public int getEfecto() { return efecto; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int c) { this.cantidad = c; }

    // usar: aplica el efecto sobre los objetivos. Devuelve descripción.
    public String usar(Jugador usuario, List<Combatiente> objetivos) {
        if (cantidad <= 0) return "No quedan unidades del ítem.";
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s usa %s. ", usuario.getNombre(), nombre));
        if (tipo.equalsIgnoreCase("cura")) {
            for (Combatiente c : objetivos) {
                if (c.estaVivo()) {
                    c.setVida(c.getVida() + efecto);
                    sb.append(String.format("%s recupera %d vida. ", c.getNombre(), efecto));
                }
            }
        } else if (tipo.equalsIgnoreCase("buff")) {
            // Buff simple: aumenta ataque de los objetivos por un turno — modelaremos como aumento permanente pequeño por simplicidad
            for (Combatiente c : objetivos) {
                // solo si está vivo
                if (c.estaVivo()) {
                    c.ataque += efecto;
                    sb.append(String.format("%s gana +%d de ataque. ", c.getNombre(), efecto));
                }
            }
        } else {
            sb.append("Efecto desconocido.");
        }
        cantidad--;
        return sb.toString();
    }

    @Override
    public String toString() {
        return String.format("%s (%s) x%d", nombre, tipo, cantidad);
    }
}
