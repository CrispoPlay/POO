// Clase: Main
// Autor: Cristian Estuardo Orellana Dieguez
// Carnet: 25664
// Fecha de creación: 30/08/2025
// Descripción: Programa principal para ejecutar el sistema del gimnasio con menú y entrada de datos desde teclado.
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        //creamos
        Scanner sc = new Scanner(System.in); //Cre el teclado
        Gimnasio gimnasio = new Gimnasio(); //se crea gimnasio dentro de la clase

        //Menu de opciones disponibles
        int opcion;
        do {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Registrar entrenadores");
            System.out.println("2. Registrar rutinas");
            System.out.println("3. Registrar miembros");
            System.out.println("4. Mostrar reportes generales");
            System.out.println("5. Ver miembros por rutina");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Evitar el problema del Scanner

//Podemos elegir las opciones en el menu
            switch (opcion) {
                //Registro del entrenador
                case 1:
                    System.out.print("¿Cuántos entrenadores desea registrar? ");
                    int cantEntrenadores = sc.nextInt();
                    sc.nextLine();

                    for (int i = 0; i < cantEntrenadores; i++) {
                        System.out.print("Nombre del entrenador " + (i+1) + ": ");
                        String nombre = sc.nextLine();
                        System.out.print("ID del entrenador " + (i+1) + ": ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        Entrenador e = new Entrenador(nombre, id);
                        gimnasio.agregarEntrenador(e);
                    }
                    break;

                    //Registro de las rutinas
                case 2:
                    System.out.print("¿Cuántas rutinas desea registrar? ");
                    int cantRutinas = sc.nextInt();
                    sc.nextLine();

                    for (int i = 0; i < cantRutinas; i++) {
                        System.out.print("Nombre de la rutina " + (i+1) + ": ");
                        String nombre = sc.nextLine();
                        System.out.print("ID de la rutina " + (i+1) + ": ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        Rutina r = new Rutina(nombre, id);
                        gimnasio.agregarRutina(r);
                    }
                    break;
                    //Registro de los miembros
                case 3:
                    System.out.print("¿Cuántos miembros desea registrar? ");
                    int cantMiembros = sc.nextInt();
                    sc.nextLine();

                    for (int i = 0; i < cantMiembros; i++) {
                        System.out.print("Nombre del miembro " + (i+1) + ": ");
                        String nombre = sc.nextLine();
                        System.out.print("ID del miembro " + (i+1) + ": ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        Miembro m = new Miembro(nombre, id);

                        // Asignar entrenador
                        if (gimnasio.getEntrenadores().isEmpty()) {
                            System.out.println("No hay entrenadores registrados.");
                        } else {
                            System.out.println("Seleccione el ID del entrenador para " + nombre + ":");
                            for (Entrenador e : gimnasio.getEntrenadores()) {
                                System.out.println(" - " + e.getId() + " | " + e.getNombre());
                            }
                            int idEntrenador = sc.nextInt();
                            sc.nextLine();
                            Entrenador elegido = null;
                            for (Entrenador e : gimnasio.getEntrenadores()) {
                                if (e.getId() == idEntrenador) {
                                    elegido = e;
                                }
                            }
                            if (elegido != null) {
                                m.setEntrenador(elegido);
                                elegido.agregarAlumno(m);
                            }
                        }

                        // Asignar rutina
                        if (gimnasio.getRutinas().isEmpty()) {
                            System.out.println("No hay rutinas registradas.");
                        } else {
                            System.out.println("Seleccione el ID de la rutina para " + nombre + ":");
                            for (Rutina r : gimnasio.getRutinas()) {
                                System.out.println(" - " + r.getId() + " | " + r.getNombre());
                            }
                            int idRutina = sc.nextInt();
                            sc.nextLine();
                            Rutina rutinaElegida = null;
                            for (Rutina r : gimnasio.getRutinas()) {
                                if (r.getId() == idRutina) {
                                    rutinaElegida = r;
                                }
                            }
                            if (rutinaElegida != null) {
                                m.setRutina(rutinaElegida);
                                rutinaElegida.agregarMiembro(m);

                                if (m.getEntrenador() != null) {
                                    m.getEntrenador().agregarRutina(rutinaElegida);
                                }
                            }
                        }

                        gimnasio.agregarMiembro(m);
                    }
                    break;
                    //Resumen del gimnasio en general
                case 4:
                    System.out.println("\n===== RESUMEN DEL GIMNASIO =====");
                    System.out.println(gimnasio.mostrarResumen());

                    if (gimnasio.rutinaMasPopular() != null) {
                        System.out.println("Rutina más popular: " + gimnasio.rutinaMasPopular().getNombre());
                    }
                    if (gimnasio.entrenadorConMasAlumnos() != null) {
                        System.out.println("Entrenador con más alumnos: " + gimnasio.entrenadorConMasAlumnos().getNombre());
                    }
                    System.out.println("Total de rutinas activas: " + gimnasio.totalRutinasActivas());
                    break;
                    //Muestra los miembros por rutina y un resumen mas detallado
                case 5:
                    System.out.println("\n===== MIEMBROS POR RUTINA =====");
                    if (gimnasio.getRutinas().isEmpty()) {
                        System.out.println("No hay rutinas registradas.");
                    } else {
                        for (Rutina r : gimnasio.getRutinas()) {
                            System.out.println("\nRutina: " + r.getNombre() + " (ID: " + r.getId() + ")");
                            if (r.getMiembros().isEmpty()) {
                                System.out.println("  No hay miembros en esta rutina.");
                            } else {
                                for (Miembro m : r.getMiembros()) {
                                    String entrenador = (m.getEntrenador() != null) ? m.getEntrenador().getNombre() : "Sin entrenador";
                                    System.out.println("  - " + m.getNombre() + " (ID: " + m.getId() + ") | Entrenador: " + entrenador);
                                }
                            }
                        }
                    }
                    break;
                    //Exit
                case 6:
                    System.out.println("Saliendo del sistema...");
                    System.out.println("Gracias por visitarnos amiguito");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 6);
        sc.close();
    }
}
