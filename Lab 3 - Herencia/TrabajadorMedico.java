/**
 * Clase base para trabajadores médicos.
 */
public abstract class TrabajadorMedico {
    private int idEmpleado;
    private String nombre;
    private String departamento;
    private int aniosExperiencia;
    private double salarioBase;

    public TrabajadorMedico() {}

    public TrabajadorMedico(int idEmpleado, String nombre, String departamento, int aniosExperiencia, double salarioBase) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.departamento = departamento;
        this.aniosExperiencia = aniosExperiencia;
        this.salarioBase = salarioBase;
    }

    public int getIdEmpleado() { return idEmpleado; }
    public void setIdEmpleado(int idEmpleado) { this.idEmpleado = idEmpleado; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }

    public int getAniosExperiencia() { return aniosExperiencia; }
    public void setAniosExperiencia(int aniosExperiencia) { this.aniosExperiencia = aniosExperiencia; }

    public double getSalarioBase() { return salarioBase; }
    public void setSalarioBase(double salarioBase) { this.salarioBase = salarioBase; }

    /**
     * Cada subclase define su forma de cálculo salarial.
     * @return salario mensual calculado
     */
    public abstract double calcularSalario();

    @Override
    public String toString() {
        return String.format("ID:%d | %s | Dept:%s | Exp:%dy | Base:%.2f",
                idEmpleado, nombre, departamento, aniosExperiencia, salarioBase);
    }
}
