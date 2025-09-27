/*
Nombre: Cristian Estuardo Orellana Dieguez
Clase: Duende extendida enemigo
Descripcion: Esta clase construye al duende heredado de enemigos el cual tiene su habilidad especial y esquivo
 */

import java.util.*;

public class Duende extends Enemigo {
    private double probEsquivar = 0.3;

    public Duende(String nombre) {
        super(nombre, 50, 10);
    }

    @Override
    public String usarHabilidadEspecial(Tablero tablero) {
        // Esquivar: de forma pasiva tendrá chance de evitar el próximo ataque; implementado como aumento temporal de vida mínima (simulación)
        // Aquí la habilidad se traduce en intentar esquivar: no hace daño, pero registra la acción.
        return String.format("%s intenta esquivar el próximo ataque.", nombre);
    }

    // Añadimos un metodo para verificar si esquiva un ataque (llamado por quien ataque al duende)
    public boolean intentoEsquivar() {
        return Math.random() < probEsquivar;
    }
}
