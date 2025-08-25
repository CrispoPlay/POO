import java.util.Scanner;

/**
 * Clase principal para ejecutar el sistema desde consola
 */
public class MainConsola {
    private static CentroSalones centro;
    private static Scanner scanner;
    
    public static void main(String[] args) {
        centro = new CentroSalones();
        scanner = new Scanner(System.in);
        
        System.out.println("=== SISTEMA DE GESTIÓN DE SALONES ===");
        System.out.println("Universidad del Valle de Guatemala");
        System.out.println("Bienvenido al Centro de Eventos\n");
        
        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            
            switch (opcion) {
                case 1:
                    nuevaSolicitudEvento();
                    break;
                case 2:
                    procesarReservas();
                    break;
                case 3:
                    mostrarSalones();
                    break;
                case 4:
                    mostrarEventos();
                    break;
                case 5:
                    mostrarListaEspera();
                    break;
                case 6:
                    liberarSalon();
                    break;
                case 7:
                    realizarDeposito();
                    break;
                case 8:
                    mostrarEstadisticas();
                    break;
                case 9:
                    System.out.println("¡Gracias por usar el sistema!");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 9);
        
        scanner.close();
    }
    
    private static void mostrarMenu() {
        System.out.println("\n=== MENÚ PRINCIPAL ===");
        System.out.println("1. Nueva solicitud de evento");
        System.out.println("2. Procesar reservas");
        System.out.println("3. Mostrar salones");
        System.out.println("4. Mostrar eventos registrados");
        System.out.println("5. Mostrar lista de espera");
        System.out.println("6. Liberar salón");
        System.out.println("7. Realizar depósito");
        System.out.println("8. Mostrar estadísticas");
        System.out.println("9. Salir");
        System.out.print("Seleccione una opción: ");
    }
    
    private static void nuevaSolicitudEvento() {
        System.out.println("\n=== NUEVA SOLICITUD DE EVENTO ===");
        
        System.out.print("Nombre del encargado: ");
        String encargado = scanner.nextLine();
        
        System.out.print("Nombre del evento: ");
        String nombreEvento = scanner.nextLine();
        
        System.out.print("Tipo de evento (VIP/Conferencia/Gala/Casual): ");
        String tipoEvento = scanner.nextLine();
        
        System.out.print("Fecha del evento (DD/MM/YYYY): ");
        String fecha = scanner.nextLine();
        
        System.out.print("Horario del evento: ");
        String horario = scanner.nextLine();
        
        Evento nuevoEvento = new Evento(encargado, nombreEvento, tipoEvento, fecha, horario);
        String resultado = centro.recibirSolicitud(nuevoEvento);
        System.out.println(resultado);
        
        System.out.println("Solicitud registrada exitosamente.");
        System.out.println("Recuerde realizar el depósito antes de procesar la reserva.");
        
        // Preguntar si quiere procesar la reserva inmediatamente
        System.out.print("¿Desea procesar esta reserva ahora? (s/n): ");
        String respuesta = scanner.nextLine();
        if (respuesta.toLowerCase().equals("s")) {
            // Preguntar por el depósito
            System.out.print("¿Ha realizado el depósito? (s/n): ");
            String deposito = scanner.nextLine();
            if (deposito.toLowerCase().equals("s")) {
                nuevoEvento.realizarDeposito();
            }
            
            String resultadoProceso = centro.procesarReserva(nuevoEvento);
            System.out.println(resultadoProceso);
        }
    }
    
    private static void procesarReservas() {
        System.out.println("\n=== PROCESAR RESERVAS ===");
        System.out.println("Esta función requiere que se implemente una cola de solicitudes.");
        System.out.println("Por simplicidad, puede crear un evento directamente:");
        
        nuevaSolicitudEvento();
        // Aquí se podría implementar la lógica para procesar automáticamente
    }
    
    private static void mostrarSalones() {
        System.out.println(centro.obtenerInfoSalones());
    }
    
    private static void mostrarEventos() {
        System.out.println(centro.getReservas().obtenerEventosRegistrados());
    }
    
    private static void mostrarListaEspera() {
        System.out.println(centro.getReservas().obtenerListaEspera());
    }
    
    private static void liberarSalon() {
        System.out.print("Ingrese el número del salón a liberar: ");
        int numeroSalon = scanner.nextInt();
        String resultado = centro.liberarSalon(numeroSalon);
        System.out.println(resultado);
    }
    
    private static void realizarDeposito() {
        System.out.println("Función de depósito - Se implementaría con sistema de pagos");
        System.out.println("Por ahora, los eventos se crean sin depósito pagado.");
        System.out.println("Use la opción 1 para crear eventos y marcar el depósito como pagado.");
    }
    
    private static void mostrarEstadisticas() {
        System.out.println(centro.obtenerEstadisticas());
    }
}