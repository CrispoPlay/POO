import java.util.List;

/**
 * Cirujano con horas de cirugía y bono por riesgo.
 */
public class Cirujano extends TrabajadorMedico {
    private List<String> tiposOperaciones;
    private int horasCirugia;
    private double bonoRiesgo;
    private double tarifaHora;

    public Cirujano() {}

    public Cirujano(int idEmpleado, String nombre, String departamento, int aniosExperiencia,
                    double salarioBase, List<String> tiposOperaciones, int horasCirugia,
                    double bonoRiesgo, double tarifaHora) {
        super(idEmpleado, nombre, departamento, aniosExperiencia, salarioBase);
        this.tiposOperaciones = tiposOperaciones;
        this.horasCirugia = horasCirugia;
        this.bonoRiesgo = bonoRiesgo;
        this.tarifaHora = tarifaHora;
    }

    public List<String> getTiposOperaciones() { return tiposOperaciones; }
    public void setTiposOperaciones(List<String> tiposOperaciones) { this.tiposOperaciones = tiposOperaciones; }

    public int getHorasCirugia() { return horasCirugia; }
    public void setHorasCirugia(int horasCirugia) { this.horasCirugia = horasCirugia; }

    public double getBonoRiesgo() { return bonoRiesgo; }
    public void setBonoRiesgo(double bonoRiesgo) { this.bonoRiesgo = bonoRiesgo; }

    public double getTarifaHora() { return tarifaHora; }
    public void setTarifaHora(double tarifaHora) { this.tarifaHora = tarifaHora; }

    @Override
    public double calcularSalario() {
        double extra = horasCirugia * tarifaHora + bonoRiesgo;
        return getSalarioBase() + extra;
    }

    /**
     * toString sencillo y natural para cirujanos.
     */
    @Override
    public String toString() {
        return super.toString() + String.format(" | Cirujano | Horas cirugía:%d | Bono:%.2f | TarifaH:%.2f",
                horasCirugia, bonoRiesgo, tarifaHora);
    }
}
