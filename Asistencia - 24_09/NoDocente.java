public class NoDocente extends Trabajador {
private int feriadosTrabajados;

    public NoDocente() {
        super();
        feriadosTrabajados = 0;
    }
    public NoDocente(String nombre, String nit, String direccion, double salarioBase, int hAusensia,
                int feriadosTrabajados) {
        super(nombre, nit, direccion, salarioBase, hAusensia);
        this.feriadosTrabajados = feriadosTrabajados;
    }
    public int getFeriadosTrabajados() {
        return feriadosTrabajados;
    }
    public void setFeriadosTrabajados(int fT) {
        this.feriadosTrabajados = fT;
    }

    public double extraPorFeriados(){
        double plus = 0.0;
        double tarifaDiaria = salarioBase/24;
        plus = tarifaDiaria*2*feriadosTrabajados;
        return plus;
    }
    public double calcularSalario() {
        double salario = super.calcularSalario(); //Es el salario calculado en comun de clase trabajador
        salario = salario + extraPorFeriados();
        return salario;
    }

    public String toString(){
        return super.toString() + "\nFeriados Trabajados: " + feriadosTrabajados;
    }
}