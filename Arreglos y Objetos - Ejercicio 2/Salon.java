public class Salon {
    private int numero;
    private String tipo;
    private int capacidad;
    private float costoPorHora;
    private boolean disponible;
    
    // Constructor
    public Salon(int numero, String tipo, int capacidad, float costoPorHora) {
        this.numero = numero;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.costoPorHora = costoPorHora;
        this.disponible = true; // Por defecto disponible
    }
    
    // Getters
    public int getNumero() {
        return numero;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public int getCapacidad() {
        return capacidad;
    }
    
    public float getCostoPorHora() {
        return costoPorHora;
    }
    
    public boolean estaDisponible() {
        return disponible;
    }
    
    // Métodos de negocio
    public void reservar() {
        this.disponible = false;
    }
    
    public void liberar() {
        this.disponible = true;
    }
    
    @Override
    public String toString() {
        return "Salón #" + numero + " (" + tipo + ") - Capacidad: " + capacidad + 
                " - Costo: Q" + costoPorHora + "/hora - " + 
                (disponible ? "Disponible" : "Ocupado");
    }
}