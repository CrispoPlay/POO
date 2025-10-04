/*
Nombre: Cristian Estuardo Orellana Dieguez
Clase: ProcesoCPU
Descripcion: Emula que un proceso esta gastando un porcentaje de CPU
 */
public class ProcesoCPU extends Proceso {
    private double usoCPU;

    //Constructor sin parámetros
    public ProcesoCPU() {
        super();
        usoCPU = 0;
    }

    //Constructor con Parámetros
    public ProcesoCPU(int pid, String nombre, double usoCPU) {
        super(pid, nombre);
        this.usoCPU = usoCPU;
    }

    public double getUsoCPU() {
        return usoCPU;
    }

    public void setUsoCPU(double usoCPU) {
        this.usoCPU = usoCPU;
    }

    // Simula ejecución intensiva de CPU
    @Override
    public String ejecutar() {
        return super.toString() + "Proceso CPU ejecutado. Uso de CPU procesado: " + usoCPU + "%";
    }
}