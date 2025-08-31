// Clase: Miembro
// Autor: Cristian Estuardo Orellana Dieguez
// Carnet: 25664
// Fecha de creación: 30/08/2025
// Descripción: La clase más importante donde se desarrolla la lógica y se unen las demas clases
import java.util.ArrayList;
public class Gimnasio {
    //Atributos y arreglos de la clase
    private ArrayList<Miembro> miembros;
    private ArrayList<Entrenador> entrenadores;
    private ArrayList<Rutina> rutinas;

    //constructor de la clase
    public Gimnasio() {
        this.miembros = new ArrayList<>();
        this.entrenadores = new ArrayList<>();
        this.rutinas = new ArrayList<>();
    }

    // Métodos Sets/gets y otros metodos para agregar miembors, rutinas, etcc.
    public void agregarMiembro(Miembro miembro) {
        miembros.add(miembro);
    }

    public void agregarEntrenador(Entrenador entrenador) {
        entrenadores.add(entrenador);
    }

    public void agregarRutina(Rutina rutina) {
        rutinas.add(rutina);
    }

    // Métodos de consulta
    public ArrayList<Entrenador> getEntrenadores() {
        return entrenadores;
    }

    public ArrayList<Rutina> getRutinas() {
        return rutinas;
    }

    public ArrayList<Miembro> getMiembros() {
        return miembros;
    }

    //Nos devolverá la rutina más popular con sus miembros
    public Rutina rutinaMasPopular() {
        Rutina popular = null;
        int max = -1;
        for (Rutina r : rutinas) {
            if (r.getCantidadMiembros() > max) {
                popular = r;
                max = r.getCantidadMiembros();
            }
        }
        return popular;
    }

    //Nos dará al entrenador al que se le asignaron mas miembros a supervisar
    public Entrenador entrenadorConMasAlumnos() {
        Entrenador top = null;
        int max = -1;
        for (Entrenador e : entrenadores) {
            if (e.getCantidadAlumnos() > max) {
                top = e;
                max = e.getCantidadAlumnos();
            }
        }
        return top;
    }
    //Devuelve el valor de la lista Rutinas
    public int totalRutinasActivas() {
        return rutinas.size();
    }
//ToString para devolver el resumen en esta clase
    public String mostrarResumen() {
        return "Miembros registrados: " + miembros.size() + "\n"
                + "Entrenadores registrados: " + entrenadores.size() + "\n"
                + "Rutinas activas: " + rutinas.size() + "\n";
    }
}
