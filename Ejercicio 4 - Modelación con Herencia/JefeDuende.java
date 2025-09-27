/*
Nombre: Cristian Estuardo Orellana Dieguez
Clase: JefeDuemde extendida Duende
Descripcion: Esta clase construye al jefe duende que tiene busteo e habilidades
 */
public class JefeDuende extends Duende {
    public JefeDuende(String nombre) {
        super(nombre);
        this.vida = 110;
        this.ataque = 20;
    }

    // Habilidad extra: doble ataque
    @Override
    public String usarHabilidadEspecial(Tablero tablero) {
        // Doble ataque contra jugador objetivo
        for (Combatiente c : tablero.getTodosCombatientes()) {
            if (c instanceof Jugador && c.estaVivo()) {
                c.setVida(c.getVida() - this.ataque);
                c.setVida(c.getVida() - this.ataque); // dos veces
                String accion = String.format("%s (Jefe) realiza un doble ataque sobre %s.", nombre, c.getNombre());
                tablero.registrarAccion(accion);
                tablero.registrarAccion(String.format("%s recibe %d daño total.", c.getNombre(), this.ataque*2));
                return accion;
            }
        }
        return String.format("%s (Jefe) no encuentra objetivo para doble ataque.", nombre);
    }
}
