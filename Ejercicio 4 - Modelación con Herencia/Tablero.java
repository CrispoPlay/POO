/*
Nombre: Cristian Estuardo Orellana Dieguez
Clase: Tablero
Descripcion: Esta clase es el corazon del programa donde se realiza el flujo de los datos
 */
import java.util.*;

public class Tablero {
    private Jugador jugador;
    private List<Enemigo> enemigos;
    private List<String> registroAcciones;

    public Tablero(Jugador jugador, List<Enemigo> enemigos) {
        this.jugador = jugador;
        this.enemigos = new ArrayList<>(enemigos);
        this.registroAcciones = new ArrayList<>();
    }

    // Ejecuta el turno del jugador: la lógica de interacción se hace en JuegoBatalla; aquí solo sirve para integrar si es necesario
    public String turnoJugador() {
        return jugador.tomarTurno(this);
    }

    // Ejecuta los turnos de los enemigos en orden
    public void turnoEnemigos() {
        for (Enemigo e : new ArrayList<>(enemigos)) {
            if (!e.estaVivo()) continue;
            String accion = e.tomarTurno(this);
            registrarAccion(accion);
        }
    }

    public void mostrarEstado() {
        // No imprimir (evitar System.out fuera de la clase principal). Proveer datos via getters.
    }

    public boolean batallaFinalizada() {
        return !jugador.estaVivo() || enemigos.stream().noneMatch(Enemigo::estaVivo);
    }

    public boolean jugadorEstaVivo() {
        return jugador.estaVivo();
    }

    public List<Combatiente> getTodosCombatientes() {
        List<Combatiente> todos = new ArrayList<>();
        todos.add(jugador);
        todos.addAll(enemigos);
        return todos;
    }

    public List<String> getUltimasAcciones(int n) {
        int start = Math.max(0, registroAcciones.size() - n);
        return registroAcciones.subList(start, registroAcciones.size());
    }

    public List<String> getUltimasAcciones() {
        return getUltimasAcciones(3);
    }

    public void registrarAccion(String s) {
        if (s == null) return;
        registroAcciones.add(s);
        // limitar tamaño para evitar crecer indefinidamente
        if (registroAcciones.size() > 100) {
            registroAcciones.remove(0);
        }
    }

    public Jugador getJugador() { return jugador; }
    public List<Enemigo> getEnemigos() { return enemigos; }
}
