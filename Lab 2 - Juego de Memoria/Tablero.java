/*
Clase: Tablero
Autor: Cristian Estuardo Orellana Dieguez - 25664
Descripción: Representa el tablero de fichas del juego de memoria.
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tablero {
    private int filas;
    private int columnas;
    private Ficha[][] fichas;

    public Tablero(int filas, int columnas) throws IllegalArgumentException {
        if ((filas * columnas) % 2 != 0) {
            throw new IllegalArgumentException("El tablero debe tener un número par de casillas.");
        }
        this.filas = filas;
        this.columnas = columnas;
        this.fichas = new Ficha[filas][columnas];
    }

    public void inicializar() throws IllegalStateException {
        String[] simbolosBase = new String[]{"😀","🐶","🍎","⚽","🚗","🌟","🎵","🦋","🍩","🌈","🔥","🍀","🐱","🐵","🐼","🍕","🎈","🧩"};
        int totalCasillas = filas * columnas;
        int paresNecesarios = totalCasillas / 2;
        if (paresNecesarios > simbolosBase.length) {
            throw new IllegalStateException("No hay suficientes símbolos únicos para este tablero.");
        }

        List<String> listaSimbolos = new ArrayList<>();
        for (int i = 0; i < paresNecesarios; i++) {
            listaSimbolos.add(simbolosBase[i]);
            listaSimbolos.add(simbolosBase[i]);
        }
        Collections.shuffle(listaSimbolos);

        int idx = 0;
        for (int r = 0; r < filas; r++) {
            for (int c = 0; c < columnas; c++) {
                fichas[r][c] = new Ficha(listaSimbolos.get(idx));
                idx++;
            }
        }
    }

    public Ficha obtenerFicha(int fila, int columna) throws IndexOutOfBoundsException {
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas) {
            throw new IndexOutOfBoundsException("Posición fuera del tablero.");
        }
        return fichas[fila][columna];
    }

    public String revelarFicha(int fila, int columna) throws IllegalStateException {
        Ficha f = obtenerFicha(fila, columna);
        if (f.isEmparejada()) {
            throw new IllegalStateException("Ficha ya emparejada.");
        }
        f.setVisible(true);
        return f.getSimbolo();
    }

    public void ocultarFicha(int fila, int columna) {
        Ficha f = obtenerFicha(fila, columna);
        if (!f.isEmparejada()) f.setVisible(false);
    }

    public void marcarEmparejadas(int f1, int c1, int f2, int c2) {
        obtenerFicha(f1, c1).setEmparejada(true);
        obtenerFicha(f2, c2).setEmparejada(true);
    }

    public boolean todasEmparejadas() {
        for (int r = 0; r < filas; r++) {
            for (int c = 0; c < columnas; c++) {
                if (!fichas[r][c].isEmparejada()) return false;
            }
        }
        return true;
    }

    public String mostrarEstado() {
        StringBuilder sb = new StringBuilder();
        sb.append("   ");
        for (int c = 0; c < columnas; c++) sb.append(String.format(" %2d ", c));
        sb.append("\n");
        for (int r = 0; r < filas; r++) {
            sb.append(String.format("%2d ", r));
            for (int c = 0; c < columnas; c++) {
                Ficha f = fichas[r][c];
                sb.append(String.format(" %2s ", f.toString()));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public int getFilas() { return filas; }
    public int getColumnas() { return columnas; }
}
