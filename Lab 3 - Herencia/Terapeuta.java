/**
 * Terapeuta con duración promedio de sesión.
 */
public class Terapeuta extends TrabajadorMedico {
    private String tipoTerapia;
    private int duracionPromedioSesion; // en minutos

    public Terapeuta() {}

    public Terapeuta(int idEmpleado, String nombre, String departamento, int aniosExperiencia,
                     double salarioBase, String tipoTerapia, int duracionPromedioSesion) {
        super(idEmpleado, nombre, departamento, aniosExperiencia, salarioBase);
        this.tipoTerapia = tipoTerapia;
        this.duracionPromedioSesion = duracionPromedioSesion;
    }

    public String getTipoTerapia() { return tipoTerapia; }
    public void setTipoTerapia(String tipoTerapia) { this.tipoTerapia = tipoTerapia; }

    public int getDuracionPromedioSesion() { return duracionPromedioSesion; }
    public void setDuracionPromedioSesion(int duracionPromedioSesion) { this.duracionPromedioSesion = duracionPromedioSesion; }

    @Override
    public double calcularSalario() {
        // Comisión simple: salario base + (duración en horas * 10)
        double horas = duracionPromedioSesion / 60.0;
        return getSalarioBase() + horas * 10.0;
    }

    /**
     * Representación simple y conversacional para terapeutas.
     */
    @Override
    public String toString() {
        return super.toString() + String.format(" | Terapeuta | Tipo:%s | DuraciónPromedio:%dmin",
                tipoTerapia, duracionPromedioSesion);
    }
}
