public class CentroSalones {
    private Salon[] salones;
    private Reserva reservas;
    private int contadorSalones;
    
    // Constructor
    public CentroSalones() {
        this.salones = new Salon[20]; // Capacidad máxima de salones
        this.contadorSalones = 0;
        inicializarSalones(); // Crear los 4 salones iniciales
        this.reservas = new Reserva(salones);
    }
    
    /**
     * Inicializa los salones por defecto del sistema
     */
    private void inicializarSalones() {
        registrarSalon(new Salon(101, "Pequeño", 50, 150.0f));
        registrarSalon(new Salon(201, "Mediano", 100, 250.0f));
        registrarSalon(new Salon(301, "Grande", 200, 400.0f));
        registrarSalon(new Salon(302, "Grande", 250, 500.0f));
    }
    
    /**
     * Registra un nuevo salón en el sistema
     */
    public boolean registrarSalon(Salon salon) {
        if (contadorSalones < salones.length) {
            salones[contadorSalones++] = salon;
            return true;
        }
        return false;
    }
    
    /**
     * Recibe una solicitud de reserva
     */
    public String recibirSolicitud(Evento evento) {
        return "Solicitud recibida: " + evento.getNombreEvento();
    }
    
    /**
     * Procesa todas las solicitudes de reserva
     */
    public String procesarReserva(Evento evento) {
        // Verificar reglas de negocio
        String validacion = validarEvento(evento);
        if (!validacion.equals("VALIDO")) {
            return validacion;
        }
        
        // Intentar asignar salón
        if (reservas.asignarSalon(evento)) {
            return "EXITO: Salón asignado exitosamente al evento: " + evento.getNombreEvento();
        } else {
            // Agregar a lista de espera
            reservas.agregarAListaEspera(evento);
            return "ESPERA: No hay salones disponibles. Evento agregado a lista de espera.";
        }
    }
    
    /**
     * Valida las reglas de negocio del evento
     */
    private String validarEvento(Evento evento) {
        // Regla 1: Debe tener depósito pagado
        if (!evento.depositoRealizado()) {
            return "ERROR: Debe realizar el depósito para reservar.";
        }
        
        // Regla 2: Nombre del evento no puede estar vacío
        if (evento.getNombreEvento() == null || evento.getNombreEvento().trim().isEmpty()) {
            return "ERROR: El evento debe tener un nombre.";
        }
        
        // Regla 3: Debe tener encargado
        if (evento.getEncargado() == null || evento.getEncargado().trim().isEmpty()) {
            return "ERROR: El evento debe tener un encargado.";
        }
        
        return "VALIDO";
    }
    
    /**
     * Obtiene información de todos los salones
     */
    public String obtenerInfoSalones() {
        StringBuilder info = new StringBuilder();
        info.append("=== SALONES DISPONIBLES ===\n");
        for (int i = 0; i < contadorSalones; i++) {
            info.append((i + 1) + ". " + salones[i] + "\n");
        }
        return info.toString();
    }
    
    /**
     * Libera un salón específico
     */
    public String liberarSalon(int numeroSalon) {
        reservas.liberarSalon(numeroSalon);
        return "Salón #" + numeroSalon + " liberado exitosamente.";
    }
    
    /**
     * Obtiene estadísticas del sistema
     */
    public String obtenerEstadisticas() {
        StringBuilder stats = new StringBuilder();
        stats.append("=== ESTADÍSTICAS DEL CENTRO ===\n");
        stats.append("Total de salones: " + contadorSalones + "\n");
        stats.append("Eventos procesados: " + reservas.getContadorEventos() + "\n");
        
        // Calcular ingresos estimados
        float ingresos = 0;
        for (int i = 0; i < reservas.getContadorEventos(); i++) {
            Evento evento = reservas.getListaEventos()[i];
            if (evento != null && evento.getSalonAsignado() != -1) {
                // Buscar el salón y sumar su costo (asumiendo 4 horas promedio)
                for (int j = 0; j < contadorSalones; j++) {
                    if (salones[j].getNumero() == evento.getSalonAsignado()) {
                        ingresos += salones[j].getCostoPorHora() * 4;
                        break;
                    }
                }
            }
        }
        stats.append("Ingresos estimados: Q" + ingresos + "\n");
        return stats.toString();
    }
    
    // Getters
    public Reserva getReservas() {
        return reservas;
    }
}