/*
Nombre: Cristian Orellana
Carnet: 25664
Clase: DronRiego
Fecha de Creacion: 3/11/2025
 */
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Catalogo catalogo = new Catalogo();
        cargarInicial(catalogo);
        inicializarRegistros(catalogo);

        try (Scanner sc = new Scanner(System.in)) {
            int opcion = 0; // control del bucle SIN while(true)
            do {
                mostrarMenu();
                System.out.print("Seleccione una opción: ");
                String linea = sc.nextLine();
                try {
                    opcion = Integer.parseInt(linea.trim());
                } catch (NumberFormatException e) {
                    opcion = -1;
                }

                switch (opcion) {
                    case 1 -> listarTodos(catalogo);
                    case 2 -> buscarPorId(sc, catalogo);
                    case 3 -> buscarPorNombre(sc, catalogo);
                    case 4 -> ordenarPorConsumo(catalogo);
                    case 5 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opción inválida.");
                }
            } while (opcion != 5);
        }
    }

    private static void mostrarMenu() {
        System.out.println("""
                ====== Menú ======
                1. Listar todos los dispositivos
                2. Buscar por ID
                3. Buscar por nombre
                4. Ordenar por consumo
                5. Salir
                """);
    }

    private static void listarTodos(Catalogo catalogo) {
        System.out.println("=== Listado ===");
        for (Dispositivo d : catalogo.listarTodos()) {
            System.out.println(d);
            if (d instanceof Registrable r) {
                System.out.println("  (registros: " + r.getRegistros().size() + ")");
            }
        }
    }

    private static void buscarPorId(Scanner sc, Catalogo catalogo) {
        System.out.print("Ingrese ID: ");
        String id = sc.nextLine();
        Dispositivo d = catalogo.buscarPorId(id);
        if (d == null) {
            System.out.println("No encontrado.");
        } else {
            mostrarDispositivoDetallado(d);
        }
    }

    private static void buscarPorNombre(Scanner sc, Catalogo catalogo) {
        System.out.print("Ingrese nombre (parcial, case-insensitive): ");
        String nombre = sc.nextLine();
        List<Dispositivo> hallados = catalogo.buscarPorNombre(nombre);
        if (hallados.isEmpty()) {
            System.out.println("No se encontraron coincidencias.");
        } else {
            System.out.println("Coincidencias:");
            for (Dispositivo d : hallados) {
                mostrarDispositivoDetallado(d);
            }
        }
    }

    private static void ordenarPorConsumo(Catalogo catalogo) {
        catalogo.ordenarPorConsumo();
        System.out.println("Catálogo ordenado por consumo (ascendente).");
        listarTodos(catalogo);
    }

    private static void mostrarDispositivoDetallado(Dispositivo d) {
        System.out.println(d);
        if (d instanceof Registrable r) {
            System.out.println("  Registros:");
            for (String reg : r.getRegistros()) {
                System.out.println("    - " + reg);
            }
        }
        if (d instanceof Medible m) {
            System.out.println("  Medición actual: " + String.format(Locale.US, "%.3f", m.medir()));
        }
        if (d instanceof Accionable) {
            System.out.println("  (Accionable)");
        }
    }

    // === Solo datos de ejemplo y acciones iniciales ===
    private static void cargarInicial(Catalogo catalogo) {
        catalogo.agregarDispositivo(new SensorSuelo("S1", "Sensor Suelo Norte", 1.2, 35.5, 22.0));
        catalogo.agregarDispositivo(new SensorSuelo("S2", "Sensor Suelo Sur", 1.1, 41.0, 21.5));
        catalogo.agregarDispositivo(new SensorSuelo("S3", "Sensor Suelo Este", 1.0, 28.7, 20.2));

        catalogo.agregarDispositivo(new EstacionMeteorologica("E1", "Estación Central", 4.5, 23.3, 1008.2));
        catalogo.agregarDispositivo(new EstacionMeteorologica("E2", "Estación Auxiliar", 3.8, 24.1, 1006.7));

        catalogo.agregarDispositivo(new DronRiego("DR1", "Dron Riego A", 15.0, 12.0));
        catalogo.agregarDispositivo(new DronRiego("DR2", "Dron Riego B", 16.5, 10.0));

        catalogo.agregarDispositivo(new DronMonitoreo("DM1", "Dron Monitoreo Águila", 13.0, "4K"));
        catalogo.agregarDispositivo(new DronMonitoreo("DM2", "Dron Monitoreo Halcón", 12.0, "1080p"));
        catalogo.agregarDispositivo(new DronMonitoreo("DM3", "Dron Monitoreo Cóndor", 12.8, "5K"));
    }

    private static void inicializarRegistros(Catalogo catalogo) {
        Dispositivo dr1 = catalogo.buscarPorId("DR1");
        if (dr1 instanceof DronRiego r1) {
            r1.ejecutarAccion("regar");
            r1.ejecutarAccion("regar", 2.5);
            r1.generarRegistro();
        }
        Dispositivo dm1 = catalogo.buscarPorId("DM1");
        if (dm1 instanceof DronMonitoreo m1) {
            m1.ejecutarAccion("fotografiar");
            m1.ejecutarAccion("fotografiar", 30.0);
            m1.ejecutarAccion("ajustarIndice", 0.732);
            m1.generarRegistro();
        }
        Dispositivo s1 = catalogo.buscarPorId("S1");
        if (s1 instanceof SensorSuelo ss1) {
            ss1.generarRegistro();
        }
        Dispositivo e1 = catalogo.buscarPorId("E1");
        if (e1 instanceof EstacionMeteorologica em1) {
            em1.generarRegistro();
        }
    }
}
