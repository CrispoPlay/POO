/*
Clase: JuegoMemoria
Autor: Cristian Estuardo Orellana Dieguez - 25664
Descripción: Controla la lógica del juego, turnos, validaciones y resultados.
*/

public class JuegoMemoria {
    private Tablero tablero;
    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador turnoActual;
    private boolean terminado;

    public JuegoMemoria(int filas, int columnas, String nombre1, String nombre2) throws Exception {
        this.tablero = new Tablero(filas, columnas);
        this.tablero.inicializar();
        this.jugador1 = new Jugador(nombre1);
        this.jugador2 = new Jugador(nombre2);
        this.turnoActual = jugador1;
        this.terminado = false;
    }

    public Tablero getTablero() { return tablero; }
    public Jugador getJugador1() { return jugador1; }
    public Jugador getJugador2() { return jugador2; }
    public Jugador getTurnoActual() { return turnoActual; }

    public String jugarTurno(int r1, int c1, int r2, int c2) {
        if (r1 == r2 && c1 == c2) return "No se puede seleccionar la misma casilla dos veces.";
        try {
            Ficha f1 = tablero.obtenerFicha(r1, c1);
            Ficha f2 = tablero.obtenerFicha(r2, c2);
            if (f1.isEmparejada() || f2.isEmparejada()) return "Alguna ficha ya está emparejada.";
            tablero.revelarFicha(r1, c1);
            tablero.revelarFicha(r2, c2);
            String s1 = f1.getSimbolo();
            String s2 = f2.getSimbolo();
            StringBuilder resultado = new StringBuilder();
            resultado.append(tablero.mostrarEstado());
            if (s1.equals(s2)) {
                tablero.marcarEmparejadas(r1, c1, r2, c2);
                turnoActual.sumarPunto();
                resultado.append("\n¡Emparejaste! " + turnoActual.getNombre() + " gana 1 punto.\n");
            } else {
                tablero.ocultarFicha(r1, c1);
                tablero.ocultarFicha(r2, c2);
                resultado.append("\nNo coinciden. Se cambia el turno.\n");
                cambiarTurno();
            }
            if (tablero.todasEmparejadas()) {
                terminado = true;
                resultado.append(mostrarResultados());
            }
            return resultado.toString();
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    public void cambiarTurno() {
        if (turnoActual == jugador1) turnoActual = jugador2;
        else turnoActual = jugador1;
    }

    public boolean verificarFin() { return terminado; }

    public String mostrarResultados() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nRESULTADOS FINALES:\n");
        sb.append(jugador1.getNombre() + ": " + jugador1.getPuntos() + " puntos\n");
        sb.append(jugador2.getNombre() + ": " + jugador2.getPuntos() + " puntos\n");
        if (jugador1.getPuntos() > jugador2.getPuntos()) sb.append("Ganador: " + jugador1.getNombre());
        else if (jugador2.getPuntos() > jugador1.getPuntos()) sb.append("Ganador: " + jugador2.getNombre());
        else sb.append("Empate.");
        return sb.toString();
    }
}
