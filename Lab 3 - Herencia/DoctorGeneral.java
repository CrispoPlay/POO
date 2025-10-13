import java.util.Objects;

/**
 * Doctor general — calcula salario en base a consultas.
 */
public class DoctorGeneral extends TrabajadorMedico {
    private String especializacion;
    private int pacientesPorDia;
    private double tarifaConsulta;

    public DoctorGeneral() {}

    public DoctorGeneral(int idEmpleado, String nombre, String departamento, int aniosExperiencia,
                         double salarioBase, String especializacion, int pacientesPorDia, double tarifaConsulta) {
        super(idEmpleado, nombre, departamento, aniosExperiencia, salarioBase);
        this.especializacion = especializacion;
        this.pacientesPorDia = pacientesPorDia;
        this.tarifaConsulta = tarifaConsulta;
    }

    public String getEspecializacion() { return especializacion; }
    public void setEspecializacion(String especializacion) { this.especializacion = especializacion; }

    public int getPacientesPorDia() { return pacientesPorDia; }
    public void setPacientesPorDia(int pacientesPorDia) { this.pacientesPorDia = pacientesPorDia; }

    public double getTarifaConsulta() { return tarifaConsulta; }
    public void setTarifaConsulta(double tarifaConsulta) { this.tarifaConsulta = tarifaConsulta; }

    @Override
    public double calcularSalario() {
        // Suponemos 20 días hábiles al mes
        int dias = 20;
        double ingresos = pacientesPorDia * tarifaConsulta * dias;
        return getSalarioBase() + ingresos;
    }

    /**
     * Representación amigable del doctor.
     * Natural y breve, no muy "maquinada".
     * @return texto representativo del doctor
     */
    @Override
    public String toString() {
        return super.toString() + String.format(" | Doctor(%s) | Pacientes/día:%d | Tarifa:%.2f",
                especializacion, pacientesPorDia, tarifaConsulta);
    }
}
