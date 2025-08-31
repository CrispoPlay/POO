// Clase: Rutina
// Autor: Cristian Estuardo Orellana Dieguez
// Carnet: 25664
// Fecha de creación: 30/08/2025
// Descripción: En esta clase creamos las rutinas donde definimos caracteristicas principales
import java.util.ArrayList;
public class Rutina {

    // Atributos de la clase
    private String nombre;
    private int id;
    private ArrayList<Miembro> activos;

    //constructor de la clase
    public Rutina(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
        this.activos = new ArrayList<>();
    }

    //aumenta la cantidad de elementos en la lista Miembro
    public void agregarMiembro(Miembro miembro) {
        activos.add(miembro);
    }

    // Métodos sets/gets y agregar miembros
    public ArrayList<Miembro> getMiembros() {
        return activos;
    }

    public int getCantidadMiembros() {
        return activos.size();
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }
}
