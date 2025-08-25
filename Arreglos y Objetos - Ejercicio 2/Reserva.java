public class Reserva {
    private Salon[] listaSalones;
    private Evento[] listaEventos;
    private Evento[] listaEspera;
    private int contadorEventos;
    private int contadorEspera;
    
    // Constructor
    public Reserva(Salon[] salones) {
        this.listaSalones = salones;
        this.listaEventos = new Evento[100]; // Capacidad máxima
        this.listaEspera = new Evento[50];   // Lista de espera
        this.contadorEventos = 0;
        this.contadorEspera = 0;
    }
    
    /**
     * Asigna un salón disponible al evento según las reglas
     */
    public boolean asignarSalon(Evento evento) {
        // Buscar salón disponible que cumpla las reglas
        for (Salon salon : listaSalones) {
            if (salon.estaDisponible() && puedeAsignarse(evento, salon)) {
                salon.reservar();
                evento.setSalonAsignado(salon.getNumero());
                listaEventos[contadorEventos++] = evento;
                return true;
            }
        }
        return false;
    }
    
    /**
     * Verifica si un evento puede asignarse a un salón específico
     */
    private boolean puedeAsignarse(Evento evento, Salon salon) {
        // Regla: Salones grandes solo para eventos VIP
        if (salon.getTipo().toLowerCase().contains("grande")) {
            return evento.esVIP();
        }
        // Salones pequeños y medianos para cualquier evento
        return true;
    }
    
    /**
     * Agrega evento a lista de espera
     */
    public void agregarAListaEspera(Evento evento) {
        if (contadorEspera < listaEspera.length) {
            listaEspera[contadorEspera++] = evento;
        }
    }
    
    /**
     * Muestra eventos en lista de espera
     */
    public String obtenerListaEspera() {
        if (contadorEspera == 0) {
            return "No hay eventos en lista de espera.";
        }
        
        StringBuilder lista = new StringBuilder();
        lista.append("=== LISTA DE ESPERA ===\n");
        for (int i = 0; i < contadorEspera; i++) {
            lista.append((i + 1) + ". " + listaEspera[i] + "\n");
        }
        return lista.toString();
    }
    
    /**
     * Obtiene todos los eventos registrados
     */
    public String obtenerEventosRegistrados() {
        if (contadorEventos == 0) {
            return "No hay eventos registrados.";
        }
        
        StringBuilder lista = new StringBuilder();
        lista.append("=== EVENTOS REGISTRADOS ===\n");
        for (int i = 0; i < contadorEventos; i++) {
            lista.append((i + 1) + ". " + listaEventos[i] + "\n");
        }
        return lista.toString();
    }
    
    /**
     * Libera un salón cuando termina un evento
     */
    public void liberarSalon(int numeroSalon) {
        for (Salon salon : listaSalones) {
            if (salon.getNumero() == numeroSalon) {
                salon.liberar();
                break;
            }
        }
    }
    
    // Getters
    public Evento[] getListaEventos() {
        return listaEventos;
    }
    
    public int getContadorEventos() {
        return contadorEventos;
    }
}