/*
Nombre: Cristian Estuardo Orellana Dieguez
Clase: Enemigo extendida enemigo
Descripcion: Esta clase construye a los Jugadores
 */
import java.util.*;

public abstract class Jugador extends Combatiente {
    protected List<Item> inventario;

    public Jugador(String nombre, int vida, int ataque) {
        super(nombre, vida, ataque);
        this.inventario = new ArrayList<>();
    }

    public void agregarItem(Item item) {
        this.inventario.add(item);
    }

    public List<Item> getInventario() {
        return inventario;
    }

    // usarItem: aplica el item sobre uno o varios combatientes (retorna descripción)
    public String usarItem(int indice, List<Combatiente> objetivos) {
        if (indice < 0 || indice >= inventario.size()) {
            return "Índice de ítem inválido.";
        }
        Item item = inventario.get(indice);
        String resultado = item.usar(this, objetivos);
        if (item.getCantidad() <= 0) {
            inventario.remove(indice);
        }
        return resultado;
    }

    public String mostrarInventario() {
        if (inventario.isEmpty()) return "Inventario vacío";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < inventario.size(); i++) {
            Item it = inventario.get(i);
            sb.append(String.format("%d: %s x%d (%s)\n", i, it.getNombre(), it.getCantidad(), it.getTipo()));
        }
        return sb.toString();
    }

    // Por defecto, el jugador no decide su turno automáticamente; será controlado por la clase principal.
    @Override
    public String tomarTurno(Tablero tablero) {
        return String.format("Es el turno del jugador %s.", nombre);
    }
}
