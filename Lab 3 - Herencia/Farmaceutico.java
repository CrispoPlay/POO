/**
 * Farmacéutico con límite de prescripciones y posible manejo de sustancias controladas.
 */
public class Farmaceutico extends TrabajadorMedico {
    private int limitePrescripcionesPorDia;
    private boolean licenciaSustanciasControladas;

    public Farmaceutico() {}

    public Farmaceutico(int idEmpleado, String nombre, String departamento, int aniosExperiencia,
                        double salarioBase, int limitePrescripcionesPorDia, boolean licenciaSustanciasControladas) {
        super(idEmpleado, nombre, departamento, aniosExperiencia, salarioBase);
        this.limitePrescripcionesPorDia = limitePrescripcionesPorDia;
        this.licenciaSustanciasControladas = licenciaSustanciasControladas;
    }

    public int getLimitePrescripcionesPorDia() { return limitePrescripcionesPorDia; }
    public void setLimitePrescripcionesPorDia(int limite) { this.limitePrescripcionesPorDia = limite; }

    public boolean isLicenciaSustanciasControladas() { return licenciaSustanciasControladas; }
    public void setLicenciaSustanciasControladas(boolean licencia) { this.licenciaSustanciasControladas = licencia; }

    @Override
    public double calcularSalario() {
        // Comisión simbólica: sueldo base + 0.5 * limitePrescripcionesPorDia
        return getSalarioBase() + 0.5 * limitePrescripcionesPorDia;
    }

    /**
     * toString natural para farmacéuticos.
     */
    @Override
    public String toString() {
        return super.toString() + String.format(" | Farmacéutico | LímitePresc:%d | Licencia:%b",
                limitePrescripcionesPorDia, licenciaSustanciasControladas);
    }
}
