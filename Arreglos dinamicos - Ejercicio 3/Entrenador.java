// Clase: Entrenador
// Autor: Cristian Estuardo Orellana Dieguez
// Carnet: 25664
// Fecha de creación: 30/08/2025
// Descripción: En esta clase creamos a los entrenadores, donde definimos sus caracteristicas y los arreglos a los que estan relacionados
import java.util.ArrayList;
public class Entrenador {
    //Atributos y arreglos
    private String nombre;
    private int id;
    private ArrayList<Miembro> alumnos; //Arreglo para alumnos
    private ArrayList<Rutina> rutinas; //Arreglo para rutinas

    // Constructor
    public Entrenador(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
        this.alumnos = new ArrayList<>();
        this.rutinas = new ArrayList<>();
    }

    //aumenta el tamaño de la lista Miembro
    public void agregarAlumno(Miembro miembro) {
        alumnos.add(miembro);
    }

    //sets y gets    
    public ArrayList<Miembro> getAlumnos() {
        return alumnos;
    }

    public int getCantidadAlumnos() {
        return alumnos.size();
    }

    //Aumenta la cantidad de rutinas media vez no hayan duplicados
    public void agregarRutina(Rutina rutina) {
        if (!rutinas.contains(rutina)) { // evitar duplicados
            rutinas.add(rutina);
        }
    }
    //devuelve elementos de Rutinas
    public ArrayList<Rutina> getRutinas() {
        return rutinas;
    }

    // Getters básicos
    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }
}
