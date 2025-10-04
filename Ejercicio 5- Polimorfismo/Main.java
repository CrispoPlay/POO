/*
Nombre: Cristian Estuardo Orellana Dieguez
Clase: Main
Descripcion: Clase Main que muestra el programa en consola
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Planificador planificador = new Planificador();
        int opcion;

        do {
            System.out.println("\n--- MENÚ DE PROCESOS ---");
            System.out.println("1. Agregar proceso");
            System.out.println("2. Eliminar proceso");
            System.out.println("3. Visualizar procesos");
            System.out.println("4. Ejecutar procesos");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese PID: ");
                    int pid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Ingrese nombre del proceso: ");
                    String nombre = sc.nextLine();
                    System.out.print("Tipo de proceso (CPU/IO/Daemon): ");
                    String tipo = sc.nextLine();

                    if (tipo.equalsIgnoreCase("CPU")) {
                        System.out.print("Ingrese uso de CPU (%): ");
                        double usoCPU = sc.nextDouble();
                        sc.nextLine();
                        planificador.agregarProceso(new ProcesoCPU(pid, nombre, usoCPU));
                    } else if (tipo.equalsIgnoreCase("IO")) {
                        System.out.print("¿Es de entrada o salida?: ");
                        String tipoIO = sc.nextLine();
                        String entradaTexto = "";
                        if (tipoIO.equalsIgnoreCase("entrada")) {
                            System.out.print("Ingrese texto de entrada: ");
                            entradaTexto = sc.nextLine();
                        }
                        planificador.agregarProceso(new ProcesoIO(pid, nombre, tipoIO, entradaTexto));
                    } else if (tipo.equalsIgnoreCase("Daemon")) {
                        System.out.print("Ingrese nombre del servicio: ");
                        String servicio = sc.nextLine();
                        planificador.agregarProceso(new Daemon(pid, nombre, servicio));
                    } else {
                        System.out.println("Tipo de proceso no válido.");
                    }
                    break;

                case 2:
                    System.out.print("Ingrese PID del proceso a eliminar: ");
                    int pidEliminar = sc.nextInt();
                    sc.nextLine();
                    boolean eliminado = planificador.eliminarProceso(pidEliminar);
                    if (eliminado) {
                        System.out.println("Proceso eliminado correctamente.");
                    } else {
                        System.out.println("No se encontró un proceso con ese PID.");
                    }
                    break;

                case 3:
                    System.out.println("\n--- Procesos registrados ---");
                    for (Proceso p : planificador.getProcesos()) {
                        System.out.println(p.toString());
                    }
                    break;

                case 4:
                    System.out.println("\n--- Ejecución de procesos ---");
                    for (String resultado : planificador.ejecutarTodos()) {
                        System.out.println(resultado);
                    }
                    break;

                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 5);

        sc.close();
    }
}