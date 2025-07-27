import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SistemaDeVenta sistema = new SistemaDeVenta();
        int opcion;

        do {
            System.out.println("\n1. Nuevo comprador");
            System.out.println("2. Nueva solicitud de boletos");
            System.out.println("3. Consultar disponibilidad total");
            System.out.println("4. Consultar disponibilidad individual");
            System.out.println("5. Reporte de caja");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch(opcion) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Cantidad de boletos: ");
                    int cantidad = sc.nextInt();
                    System.out.print("Presupuesto: ");
                    float presupuesto = sc.nextFloat();
                    sc.nextLine();
                    sistema.setCompradorActual(new Compradores(nombre, email, cantidad, presupuesto));
                    break;
                case 2:
                    sistema.realizarSolicitud();
                    break;
                case 3:
                    sistema.mostrarDisponibilidadTotal();
                    break;
                case 4:
                    System.out.print("Ingrese la zona (Zona 1, Zona 5, Zona 10): ");
                    String zona = sc.nextLine();
                    sistema.mostrarDisponibilidadLocal(zona);
                    break;
                case 5:
                    sistema.reporteCaja();
                    break;
                case 6:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while(opcion != 6);

        sc.close();
    }
}