/*
Nombre: Cristian Estuardo Orellana Dieguez
Clase: Explorador extendida Jugador
Descripcion: Esta clase construye al Explorador heredado de Jugador
 */
public class Explorador extends Jugador {
    public Explorador(String nombre) {
        // Explorador: vida y ataque normales, más ítems
        super(nombre, 100, 15);
    }

    @Override
    public String tomarTurno(Tablero tablero) {
        return String.format("Explorador %s listo para actuar.", nombre);
    }
}
