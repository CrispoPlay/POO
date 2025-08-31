// Clase: Miembro
// Autor: Cristian Estuardo Orellana Dieguez
// Carnet: 25664
// Fecha de creación: 30/08/2025
// Descripción: En esta clase creamos a los miembros, donde definimos sus caracteristicas
public class Miembro {
    //Atributos
    private String nombre;
    private int id;
    private Rutina rutina;
    private Entrenador entrenador;

    //Constructor de la clase Miembro
    public Miembro(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
    }

    // Métodos sets y gets
    public void setRutina(Rutina rutina) {
        this.rutina = rutina;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public Rutina getRutina() {
        return rutina;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }
}
