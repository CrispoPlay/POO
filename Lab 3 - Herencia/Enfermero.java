/**
 * Clase Enfermero, considera bono nocturno si aplica.
 */
public class Enfermero extends TrabajadorMedico {
    private String turno; // "diurno" o "nocturno"
    private String nivelCertificacion;
    private double bonoNocturno;

    public Enfermero() {}

    public Enfermero(int idEmpleado, String nombre, String departamento, int aniosExperiencia,
                     double salarioBase, String turno, String nivelCertificacion, double bonoNocturno) {
        super(idEmpleado, nombre, departamento, aniosExperiencia, salarioBase);
        this.turno = turno;
        this.nivelCertificacion = nivelCertificacion;
        this.bonoNocturno = bonoNocturno;
    }

    public String getTurno() { return turno; }
    public void setTurno(String turno) { this.turno = turno; }

    public String getNivelCertificacion() { return nivelCertificacion; }
    public void setNivelCertificacion(String nivelCertificacion) { this.nivelCertificacion = nivelCertificacion; }

    public double getBonoNocturno() { return bonoNocturno; }
    public void setBonoNocturno(double bonoNocturno) { this.bonoNocturno = bonoNocturno; }

    @Override
    public double calcularSalario() {
        double bono = "nocturno".equalsIgnoreCase(turno) ? bonoNocturno : 0.0;
        return getSalarioBase() + bono;
    }

    /**
     * Representación simple y natural para enfermeros.
     */
    @Override
    public String toString() {
        return super.toString() + String.format(" | Enfermero | Turno:%s | Cert:%s | BonoNoct:%.2f",
                turno, nivelCertificacion, bonoNocturno);
    }
}
