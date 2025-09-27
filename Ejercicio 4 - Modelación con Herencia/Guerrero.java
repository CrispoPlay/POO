/*
Nombre: Cristian Estuardo Orellana Dieguez
Clase: Guerrero extendida Jugador
Descripcion: Esta clase construye al Guerrero heredado de Jugador
 */
public class Guerrero extends Jugador {
    public Guerrero(String nombre) {
        // Guerrero: más vida y ataque, menos ítems (se asignan en JuegoBatalla)
        super(nombre, 150, 25);
    }

    @Override
    public String tomarTurno(Tablero tablero) {
        // Retorna un mensaje corto; la lógica de selección de acción la maneja JuegoBatalla.
        return String.format("Guerrero %s listo para actuar.", nombre);
    }
}
