public class Evento {
    private String encargado;
    private String nombreEvento;
    private String tipoEvento;
    private String fecha;
    private String horario;
    private boolean depositoPagado;
    private int salonAsignado;
    
    // Constructor
    public Evento(String encargado, String nombreEvento, String tipoEvento, 
                    String fecha, String horario) {
        this.encargado = encargado;
        this.nombreEvento = nombreEvento;
        this.tipoEvento = tipoEvento;
        this.fecha = fecha;
        this.horario = horario;
        this.depositoPagado = false;
        this.salonAsignado = -1; // -1 indica no asignado
    }
    
    // Getters
    public String getEncargado() {
        return encargado;
    }
    
    public String getNombreEvento() {
        return nombreEvento;
    }
    
    public String getTipoEvento() {
        return tipoEvento;
    }
    
    public String getFecha() {
        return fecha;
    }
    
    public String getHorario() {
        return horario;
    }
    
    public int getSalonAsignado() {
        return salonAsignado;
    }
    
    public void setSalonAsignado(int salonAsignado) {
        this.salonAsignado = salonAsignado;
    }
    
    // Métodos de negocio
    public boolean esVIP() {
        return tipoEvento.toLowerCase().contains("vip") || 
                tipoEvento.toLowerCase().contains("conferencia") || 
                tipoEvento.toLowerCase().contains("gala");
    }
    
    public boolean depositoRealizado() {
        return depositoPagado;
    }
    
    public void realizarDeposito() {
        this.depositoPagado = true;
    }
    
    @Override
    public String toString() {
        return "Evento: " + nombreEvento + " | Encargado: " + encargado + 
                " | Tipo: " + tipoEvento + " | Fecha: " + fecha + 
                " | Horario: " + horario + " | Depósito: " + 
                (depositoPagado ? "Pagado" : "Pendiente") +
                " | Salón: " + (salonAsignado == -1 ? "Sin asignar" : "#" + salonAsignado);
    }
}