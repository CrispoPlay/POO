/*
Clase: MainJuego
Autor: Cristian Estuardo Orellana Dieguez - 25664
Descripción: Clase principal que gestiona entrada y salida de datos con el usuario.
*/

import java.util.Scanner;

public class MainJuego {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean ejecutar = true;
        System.out.println("=== Juego de Memoria - UVG CC2008 ===");
        while (ejecutar) {
            System.out.println("\nMenú:\n1) Iniciar nueva partida\n2) Salir");
            System.out.print("Seleccione una opción: ");
            String opcion = sc.nextLine();
            if (opcion.equals("1")) {
                try {
                    System.out.print("Nombre jugador 1: ");
                    String n1 = sc.nextLine();
                    System.out.print("Nombre jugador 2: ");
                    String n2 = sc.nextLine();
                    System.out.print("Filas (ej: 4): ");
                    int filas = Integer.parseInt(sc.nextLine());
                    System.out.print("Columnas (ej: 4): ");
                    int columnas = Integer.parseInt(sc.nextLine());

                    JuegoMemoria juego = new JuegoMemoria(filas, columnas, n1, n2);
                    while (!juego.verificarFin()) {
                        System.out.println("\nTurno de: " + juego.getTurnoActual().getNombre());
                        System.out.println(juego.getTablero().mostrarEstado());

                        // Selección de primera casilla
                        System.out.print("Seleccione primera casilla (fila,columna): ");
                        String[] entrada1 = sc.nextLine().split(",");
                        int r1 = Integer.parseInt(entrada1[0].trim());
                        int c1 = Integer.parseInt(entrada1[1].trim());

                        try {
                            juego.getTablero().revelarFicha(r1, c1);
                            System.out.println("\nEstado del tablero (primera ficha revelada):");
                            System.out.println(juego.getTablero().mostrarEstado());
                        } catch (Exception ex) {
                            System.out.println("Error: " + ex.getMessage());
                            continue; // si hay error, vuelve a pedir jugada
                        }

                        // Selección de segunda casilla
                        System.out.print("Seleccione segunda casilla (fila,columna): ");
                        String[] entrada2 = sc.nextLine().split(",");
                        int r2 = Integer.parseInt(entrada2[0].trim());
                        int c2 = Integer.parseInt(entrada2[1].trim());

                        // Resolver el turno
                        String resultado = juego.jugarTurno(r1, c1, r2, c2);
                        System.out.println(resultado);
                    }
                } catch (Exception ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
            } else if (opcion.equals("2")) {
                ejecutar = false;
                System.out.println("Gracias por jugar. Hasta luego.");
            } else {
                System.out.println("Opción no válida.");
            }
        }
        sc.close();
    }
}
