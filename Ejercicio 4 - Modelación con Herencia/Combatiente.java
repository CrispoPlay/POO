/*
Nombre: Cristian Estuardo Orellana Dieguez
Clase: Combatiente
Descripcion: Esta es una clase abstracta que construye la base de los combatientes que se heredaran
en las demas clases de emeigos y jugadores
 */
import java.util.*;

public abstract class Combatiente {
    protected String nombre;
    protected int vida;
    protected int ataque;

    public Combatiente(String nombre, int vida, int ataque) {
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
    }

    public String getNombre() { return nombre; }
    public int getVida() { return vida; }
    public int getAtaque() { return ataque; }

    public void setVida(int vida) { this.vida = Math.max(0, vida); }

    // recibirAtaque: reduce vida en la cantidad indicada (considera defensa simple)
    public String recibirAtaque(int danio) {
        int vidaAntes = this.vida;
        setVida(this.vida - danio);
        int recibido = vidaAntes - this.vida;
        return String.format("%s recibió %d de daño (vida: %d -> %d)", nombre, recibido, vidaAntes, this.vida);
    }

    public boolean estaVivo() { return this.vida > 0; }

    // Durante el turno, el combatiente actúa sobre el tablero. Devuelve una descripción de la acción.
    public abstract String tomarTurno(Tablero tablero);

    // Mensajes para inicio y final — devuelven cadenas para que la clase principal las imprima.
    public String mensajeInicio() {
        return String.format("%s entra a la batalla (Vida: %d, Ataque: %d)", nombre, vida, ataque);
    }

    public String mensajeFinal() {
        if (estaVivo()) {
            return String.format("%s ha ganado la batalla con %d de vida restante.", nombre, vida);
        } else {
            return String.format("%s ha sido derrotado.", nombre);
        }
    }

    /**
     * ToString
     * @return Valores de (String)nombre (String)Vida y (String)Ataque
     */
    public String toString() {
        return String.format("%s [Vida=%d, Ataque=%d]", nombre, vida, ataque);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Combatiente)) return false;
        Combatiente c = (Combatiente) o;
        return this.nombre.equals(c.nombre);
    }
}
