/*
Nombre: Cristian Estuardo Orellana Dieguez
Clase: Planificador
Descripcion: Clase control que se encarga de a gregar/eliminar y crear la lista de procesos
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Planificador {
    private List<Proceso> procesos;

    //Crea el Array
    public Planificador() {
        procesos = new ArrayList<>();
    }

    //Agrega procesos al Array
    public void agregarProceso(Proceso p) {
        procesos.add(p);
    }

    //Se encarga de eliminar los procesos tomando en cuenta el PID que será el identificador
    public boolean eliminarProceso(int pid) {
        Iterator<Proceso> it = procesos.iterator();
        while (it.hasNext()) {
            Proceso p = it.next();
            if (p.getPid() == pid) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public List<Proceso> getProcesos() {
        return procesos;
    }

    //Se encarga de ejecutar todos los procesos de la lista procesos y mostrando sus resultados
    public List<String> ejecutarTodos() {
        List<String> resultados = new ArrayList<>();
        for (Proceso p : procesos) {
            resultados.add(p.ejecutar());
        }
        return resultados;
    }
}