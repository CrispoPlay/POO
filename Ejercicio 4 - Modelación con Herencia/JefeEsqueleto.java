/*
Nombre: Cristian Estuardo Orellana Dieguez
Clase: JefeDuemde extendida Esqueleto
Descripcion: Esta clase construye al jefe esqueleto que tiene busteo y habilidades
 */
public class JefeEsqueleto extends Esqueleto {
    public JefeEsqueleto(String nombre) {
        super(nombre);
        // Mejora de jefe
        this.vida = 120;
        this.ataque = 22;
    }

    // Habilidad extra: curarse
    @Override
    public String usarHabilidadEspecial(Tablero tablero) {
        // Prioriza curarse a sí mismo
        int cura = 20;
        this.setVida(this.getVida() + cura);
        String accion = String.format("%s (Jefe) se cura %d puntos.", nombre, cura);
        tablero.registrarAccion(accion);
        return accion;
    }
}
