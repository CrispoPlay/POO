/*
Clase: Jugador
Autor: Cristian Estuardo Orellana Dieguez - 25664
Descripción: Representa a un jugador con nombre y puntaje.
*/

public class Jugador {
    private String nombre;
    private int puntos;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntos = 0;
    }

    public String getNombre() { return nombre; }
    public int getPuntos() { return puntos; }
    public void sumarPunto() { puntos++; }
}
