/*
Nombre: Cristian Estuardo Orellana Dieguez
Clase: Duende extendida enemigo
Descripcion: Esta clase construye al esqueleto heredado de enemigos el cual tiene su habilidad especial
 */
import java.util.*;

public class Esqueleto extends Enemigo {
    private boolean envenenadoObjetivo = false;

    public Esqueleto(String nombre) {
        super(nombre, 60, 12);
    }

    @Override
    public String usarHabilidadEspecial(Tablero tablero) {
        // Envenenar: aplicará daño por turno a un objetivo (simulado aquí como restar vida adicional)
        List<Combatiente> blancos = tablero.getTodosCombatientes();
        // Buscar jugador vivo
        for (Combatiente c : blancos) {
            if (c instanceof Jugador && c.estaVivo()) {
                // aplicar veneno simple: daño inmediato y una marca en el tablero para daños posteriores
                c.setVida(c.getVida() - 6); // daño por veneno inmediato
                String accion = String.format("%s usa Envenenar sobre %s.", nombre, c.getNombre());
                tablero.registrarAccion(accion);
                tablero.registrarAccion(String.format("%s sufre 6 de daño por veneno.", c.getNombre()));
                return accion;
            }
        }
        // Si no hay jugador vivo, pasa turno
        return String.format("%s no encuentra objetivos para envenenar.", nombre);
    }
}
