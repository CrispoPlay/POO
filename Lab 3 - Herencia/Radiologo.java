import java.util.List;

/**
 * Radiólogo que cobra por estudio.
 */
public class Radiologo extends TrabajadorMedico {
    private List<String> equiposCertificados;
    private double tarifaPorEstudio;
    private int estudiosRealizados;

    public Radiologo() {}

    public Radiologo(int idEmpleado, String nombre, String departamento, int aniosExperiencia,
                     double salarioBase, List<String> equiposCertificados, double tarifaPorEstudio, int estudiosRealizados) {
        super(idEmpleado, nombre, departamento, aniosExperiencia, salarioBase);
        this.equiposCertificados = equiposCertificados;
        this.tarifaPorEstudio = tarifaPorEstudio;
        this.estudiosRealizados = estudiosRealizados;
    }

    public List<String> getEquiposCertificados() { return equiposCertificados; }
    public void setEquiposCertificados(List<String> equiposCertificados) { this.equiposCertificados = equiposCertificados; }

    public double getTarifaPorEstudio() { return tarifaPorEstudio; }
    public void setTarifaPorEstudio(double tarifaPorEstudio) { this.tarifaPorEstudio = tarifaPorEstudio; }

    public int getEstudiosRealizados() { return estudiosRealizados; }
    public void setEstudiosRealizados(int estudiosRealizados) { this.estudiosRealizados = estudiosRealizados; }

    @Override
    public double calcularSalario() {
        double extra = tarifaPorEstudio * estudiosRealizados;
        return getSalarioBase() + extra;
    }

    /**
     * Cadena legible para radiólogos.
     */
    @Override
    public String toString() {
        return super.toString() + String.format(" | Radiólogo | Estudios:%d | TarifaEst:%.2f",
                estudiosRealizados, tarifaPorEstudio);
    }
}
