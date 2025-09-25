import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Trabajador> trabajadores = new ArrayList<>();
        int opcion;

        do {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. Registrar Docente");
            System.out.println("2. Registrar No Docente");
            System.out.println("3. Eliminar Docente");
            System.out.println("4. Eliminar No Docente");
            System.out.println("5. Visualizar Trabajadores");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero(sc);

            switch (opcion) {
                case 1:
                    // Registrar Docente
                    System.out.println("\n--- Registro de Docente ---");
                    System.out.print("Nombre: ");
                    String nombreD = sc.nextLine();
                    System.out.print("NIT: ");
                    String nitD = sc.nextLine();
                    System.out.print("Dirección: ");
                    String dirD = sc.nextLine();
                    System.out.print("Salario Base: ");
                    double salarioD = leerDouble(sc);
                    System.out.print("Horas de Ausencia: ");
                    int hAusD = leerEntero(sc);
                    System.out.print("Años de Antigüedad: ");
                    int anios = leerEntero(sc);
                    System.out.print("Grado Científico (Licenciado/Master/Doctor): ");
                    String grado = sc.nextLine();

                    Docente docente = new Docente(nombreD, nitD, dirD, salarioD, hAusD, anios, grado);
                    trabajadores.add(docente);
                    System.out.println("Docente registrado con éxito.");
                    break;

                case 2:
                    // Registrar No Docente
                    System.out.println("\n--- Registro de No Docente ---");
                    System.out.print("Nombre: ");
                    String nombreN = sc.nextLine();
                    System.out.print("NIT: ");
                    String nitN = sc.nextLine();
                    System.out.print("Dirección: ");
                    String dirN = sc.nextLine();
                    System.out.print("Salario Base: ");
                    double salarioN = leerDouble(sc);
                    System.out.print("Horas de Ausencia: ");
                    int hAusN = leerEntero(sc);
                    System.out.print("Feriados Trabajados: ");
                    int feriados = leerEntero(sc);

                    NoDocente noDocente = new NoDocente(nombreN, nitN, dirN, salarioN, hAusN, feriados);
                    trabajadores.add(noDocente);
                    System.out.println("No Docente registrado con éxito.");
                    break;

                case 3:
                    // Eliminar Docente
                    System.out.print("\nIngrese NIT del Docente a eliminar: ");
                    String nitEliminarD = sc.nextLine();
                    eliminarTrabajador(trabajadores, nitEliminarD, Docente.class);
                    break;

                case 4:
                    // Eliminar No Docente
                    System.out.print("\nIngrese NIT del No Docente a eliminar: ");
                    String nitEliminarN = sc.nextLine();
                    eliminarTrabajador(trabajadores, nitEliminarN, NoDocente.class);
                    break;

                case 5:
                    // Visualizar todos los trabajadores
                    System.out.println("\n--- LISTADO DE TRABAJADORES ---");
                    if (trabajadores.isEmpty()) {
                        System.out.println("No hay trabajadores registrados.");
                    } else {
                        for (Trabajador t : trabajadores) {
                            System.out.println("-------------------------");
                            System.out.println(t.toString());
                            System.out.println("Salario Calculado: " + t.calcularSalario());
                        }
                    }
                    break;

                case 6:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 6);

        sc.close();
    }

    // Método para eliminar trabajador según tipo y NIT
    public static void eliminarTrabajador(ArrayList<Trabajador> lista, String nit, Class<?> tipo) {
        Iterator<Trabajador> it = lista.iterator();
        boolean encontrado = false;

        while (it.hasNext()) {
            Trabajador t = it.next();
            if (t.getNit().equalsIgnoreCase(nit) && tipo.isInstance(t)) {
                it.remove();
                encontrado = true;
                System.out.println("Trabajador eliminado correctamente.");
                break;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontró un trabajador de ese tipo con el NIT especificado.");
        }
    }

    // Lectura segura de enteros
    private static int leerEntero(Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Intente nuevamente: ");
            }
        }
    }

    // Lectura segura de doubles
    private static double leerDouble(Scanner sc) {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Intente nuevamente: ");
            }
        }
    }
}
