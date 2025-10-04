/*
Nombre: Cristian Estuardo Orellana Dieguez
Clase: Proceso
Descripcion: Clase abstracta de proceso que servirá como plantilla para las demas clases heredadas
 */
public abstract class Proceso {
    private int pid;
    private String nombre;

    // Constructor sin parámetros
    public Proceso() {
        pid = 0;
        nombre = "";
    }

    // Constructor con parámetros
    public Proceso(int pid, String nombre) {
        this.pid = pid;
        this.nombre = nombre;
    }

    // Getters y setters
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Método abstracto que será implementado por las subclases
    public abstract String ejecutar();

    // Representación textual del proceso
    @Override
    public String toString() {
        return "PID: " + pid + ", Nombre: " + nombre + " - ";
    }
}