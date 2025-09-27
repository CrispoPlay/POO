/*
Nombre: Cristian Estuardo Orellana Dieguez
Clase: JuegoBatallaMain
Descripcion: Esta clase es el main donde se realiza el ingreso y salida de datos
 */
import java.util.*;

public class JuegoBatallaMain {
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new JuegoBatallaMain().ejecutar();
    }

    public void ejecutar() {
        System.out.println("=== Bienvenido al simulador de batalla por turnos ===");
        System.out.print("Ingresa el nombre del jugador: ");
        String nombre = scanner.nextLine().trim();
        System.out.println("Elige rol: 1) Guerrero  2) Explorador");
        int rol = leerEntero("Selecciona 1 o 2: ", 1, 2);

        Jugador jugador;
        if (rol == 1) jugador = new Guerrero(nombre);
        else jugador = new Explorador(nombre);

        // Asignar ítems según rol
        if (jugador instanceof Guerrero) {
            jugador.agregarItem(new Item("Poción Pequeña", "cura", 30, 1));
            jugador.agregarItem(new Item("Brebaje de Fuerza", "buff", 3, 1));
        } else {
            jugador.agregarItem(new Item("Poción Pequeña", "cura", 30, 2));
            jugador.agregarItem(new Item("Poción Grande", "cura", 60, 1));
            jugador.agregarItem(new Item("Brebaje de Fuerza", "buff", 3, 2));
        }

        // Generar enemigos aleatorios
        Random rnd = new Random();
        int nEnemigos = rnd.nextInt(3) + 1;
        List<Enemigo> enemigos = new ArrayList<>();
        for (int i = 0; i < nEnemigos; i++) {
            boolean tipoEsqueleto = rnd.nextBoolean();
            boolean esJefe = rnd.nextDouble() < 0.25;
            if (tipoEsqueleto) {
                Enemigo e = esJefe ? new JefeEsqueleto("Jefe Esqueleto " + (i+1)) : new Esqueleto("Esqueleto " + (i+1));
                enemigos.add(e);
            } else {
                Enemigo e = esJefe ? new JefeDuende("Jefe Duende " + (i+1)) : new Duende("Duende " + (i+1));
                enemigos.add(e);
            }
        }

        Tablero tablero = new Tablero(jugador, enemigos);

        // Mostrar mensajes de inicio
        System.out.println("\n=== Comienza la batalla ===");
        System.out.println(jugador.mensajeInicio());
        for (Enemigo e : enemigos) {
            System.out.println(e.mensajeInicio());
        }

        boolean salir = false;
        while (!tablero.batallaFinalizada() && !salir) {
            mostrarEstado(tablero);

            if (tablero.jugadorEstaVivo()) {
                salir = turnoJugadorInteractivo(tablero);
                if (salir) break;
            }

            // Turno de enemigos
            for (Enemigo e : tablero.getEnemigos()) {
                if (!e.estaVivo()) continue;
                String accion = e.tomarTurno(tablero);
                System.out.println(accion);

                // Si atacó a jugador, mostrar detalle
                if (accion.contains("ataca") || accion.contains("ataque")) {
                    System.out.println("➡ " + accion);
                }

                // Verificar muerte del jugador tras el ataque
                if (!tablero.jugadorEstaVivo()) {
                    System.out.println(tablero.getJugador().mensajeFinal());
                    break;
                }
            }

            // Revisar muertes de enemigos en este turno
            for (Enemigo e : tablero.getEnemigos()) {
                if (!e.estaVivo()) {
                    System.out.println(e.mensajeFinal());
                }
            }
        }

        System.out.println("\n=== Resultado de la batalla ===");
        if (tablero.jugadorEstaVivo()) {
            System.out.println(jugador.mensajeFinal());
        }
        for (Enemigo e : enemigos) {
            System.out.println(e.mensajeFinal());
        }
        System.out.println("Gracias por jugar. ¡Fin!");
    }

    private void mostrarEstado(Tablero tablero) {
        System.out.println("\n--- Estado del combate ---");
        System.out.println("Jugador: " + tablero.getJugador());
        System.out.println("Inventario:\n" + tablero.getJugador().mostrarInventario());
        System.out.println("Enemigos:");
        int idx = 1;
        for (Enemigo e : tablero.getEnemigos()) {
            System.out.println(String.format("%d) %s", idx++, e));
        }
        System.out.println("--------------------------");
    }

    private boolean turnoJugadorInteractivo(Tablero tablero) {
        Jugador jugador = tablero.getJugador();
        System.out.println("Elige acción: 1) Atacar  2) Usar ítem  3) Pasar  4) Salir");
        int opcion = leerEntero("Selecciona 1-4: ", 1, 4);
        if (opcion == 4) return true;

        if (opcion == 3) {
            System.out.println(jugador.getNombre() + " decide pasar el turno.");
            return false;
        } else if (opcion == 2) {
            System.out.println("Inventario:\n" + jugador.mostrarInventario());
            System.out.print("Ingrese índice de ítem a usar: ");
            int idx = Integer.parseInt(scanner.nextLine().trim());
            System.out.println("Aplicar sobre: 1) Yo mismo  2) Enemigo");
            int tar = leerEntero("Selecciona 1-2: ", 1, 2);

            List<Combatiente> objetivos = new ArrayList<>();
            if (tar == 1) objetivos.add(jugador);
            else {
                List<Enemigo> vivos = new ArrayList<>();
                for (Enemigo e : tablero.getEnemigos()) if (e.estaVivo()) vivos.add(e);
                if (!vivos.isEmpty()) {
                    System.out.println("Elige objetivo:");
                    for (int i = 0; i < vivos.size(); i++) {
                        System.out.println(String.format("%d) %s", i, vivos.get(i)));
                    }
                    int choice = leerEntero("Índice objetivo: ", 0, vivos.size()-1);
                    objetivos.add(vivos.get(choice));
                }
            }
            String resultado = jugador.usarItem(idx, objetivos);
            System.out.println(resultado);
            return false;
        } else {
            List<Enemigo> vivos = new ArrayList<>();
            for (Enemigo e : tablero.getEnemigos()) if (e.estaVivo()) vivos.add(e);
            if (vivos.isEmpty()) return false;

            System.out.println("Elige enemigo a atacar:");
            for (int i = 0; i < vivos.size(); i++) {
                System.out.println(String.format("%d) %s", i, vivos.get(i)));
            }
            int choice = leerEntero("Índice enemigo: ", 0, vivos.size()-1);
            Enemigo objetivo = vivos.get(choice);

            boolean esquiva = false;
            if (objetivo instanceof Duende) esquiva = ((Duende) objetivo).intentoEsquivar();
            else if (objetivo instanceof JefeDuende) esquiva = ((JefeDuende) objetivo).intentoEsquivar();

            if (esquiva) {
                System.out.println(jugador.getNombre() + " ataca a " + objetivo.getNombre() + " pero este esquiva el ataque.");
            } else {
                System.out.println(jugador.getNombre() + " ataca a " + objetivo.getNombre());
                System.out.println(objetivo.recibirAtaque(jugador.getAtaque()));
                if (!objetivo.estaVivo()) {
                    System.out.println(objetivo.mensajeFinal());
                }
            }
            return false;
        }
    }

    private int leerEntero(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                int v = Integer.parseInt(line);
                if (v < min || v > max) {
                    System.out.println("Valor fuera de rango.");
                    continue;
                }
                return v;
            } catch (Exception e) {
                System.out.println("Entrada inválida.");
            }
        }
    }
}
