/*
Clase: Ficha
Autor: Cristian Estuardo Orellana Dieguez - 25664
Descripción: Representa una ficha del tablero, con símbolo, estado visible y emparejada.
*/

public class Ficha {
    private String simbolo;
    private boolean visible;
    private boolean emparejada;

    public Ficha(String simbolo) {
        this.simbolo = simbolo;
        this.visible = false;
        this.emparejada = false;
    }

    public String getSimbolo() { return simbolo; }
    public void setVisible(boolean visible) { this.visible = visible; }
    public boolean isVisible() { return visible; }
    public void setEmparejada(boolean emparejada) { this.emparejada = emparejada; }
    public boolean isEmparejada() { return emparejada; }

    public String toString() {
        if (emparejada) return " ";
        return visible ? simbolo : "X"; // 'X' representa casilla oculta
    }
}
