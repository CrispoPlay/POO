/*
Nombre: Cristian Estuardo Orellana Dieguez
Clase: Enemigo extendida enemigo
Descripcion: Esta clase construye a los enemigos heredado de enemigo el cual tiene su habilidad especial y esquivo
 */
public abstract class Enemigo extends Combatiente {
    public Enemigo(String nombre, int vida, int ataque) {
        super(nombre, vida, ataque);
    }

    // Cada enemigo tiene una habilidad especial
    public abstract String usarHabilidadEspecial(Tablero tablero);

    @Override
    public String tomarTurno(Tablero tablero) {
        // Por defecto un enemigo intentará usar su habilidad especial con alguna probabilidad; sino atacará.
        return usarHabilidadEspecial(tablero);
    }
}
