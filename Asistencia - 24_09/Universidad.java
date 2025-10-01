import java.util.*;

public class Universidad{
    private String nombre;
    private String direccion;
    private String telefono;
    private ArrayList<Trabajador> listaEmpleados;

    public Universidad(){
        nombre = "";
        direccion = "";
        telefono = "";
    }
    public Universidad(String nombre, String direccion, String telefono){
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.listaEmpleados = new ArrayList<Trabajador>();
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public ArrayList<Trabajador> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(ArrayList<Trabajador> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    /**
     *
     * @param nombre
     * @param nit
     * @param direccion
     * @param salarioBase
     * @param hAusensia
     * @param aniosAntiguedad
     * @param gradoCientifico
     * @param feriadosTrabajados
     * @param tipoTrabajador: 1 es Docente, 2 noDocente
     */
    public void agregarEmpleados(String nombre, String nit, String direccion, double salarioBase,
                                int hAusensia, int aniosAntiguedad, String gradoCientifico, int feriadosTrabajados,
                                int tipoTrabajador){
        Trabajador nuevoEmpleado = null;
        if (tipoTrabajador == 0){
            nuevoEmpleado = new Docente(nombre,nit,direccion,salarioBase, hAusensia,aniosAntiguedad,gradoCientifico);
        }
        if (tipoTrabajador == 1){
            nuevoEmpleado = new NoDocente(nombre,nit,direccion,salarioBase,hAusensia,feriadosTrabajados);
        }
        if (nuevoEmpleado != null){
            listaEmpleados.add(nuevoEmpleado);
        }
    }

    public double calcularNomina(){
        double total =0;
        for (Trabajador t: listaEmpleados)
            total += t.calcularSalario();
            return total;
    }

    public double salarioTotalDocente(){
        double total = 0;
        for(Trabajador t: listaEmpleados){
            if(t instanceof Docente){
                total = + t.calcularSalario();
            }
        }
        return total;
    }

        public double salarioTotalNoDocente(){
        double total = 0;
        for(Trabajador t: listaEmpleados){
            if(t instanceof NoDocente){
                total = + t.calcularSalario();
            }
        }
        return total;
    }
}