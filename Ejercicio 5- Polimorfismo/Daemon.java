/*
Nombre: Cristian Estuardo Orellana Dieguez
Clase: Daemon
Descripcion: Clase que emula un uso en segundo plano en Deamon
 */
public class Daemon extends Proceso {
    private String servicio;

    //constructor sin parámetros
    public Daemon() {
        super();
        servicio = "";
    }
    //constructor con parámetros
    public Daemon(int pid, String nombre, String servicio) {
        super(pid, nombre);
        this.servicio = servicio;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    // Simula ejecución continua en segundo plano
    @Override
    public String ejecutar() {
        return super.toString() + "Daemon ejecutado. Servicio " + servicio + " activo en segundo plano.";
    }
}