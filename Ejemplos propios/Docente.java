import java.util.*;
public class Docente extends Trabajador {
    private int aniosAntiguedad;
    private String gradoCientifico;

    public Docente() {
        super();
        aniosAntiguedad = 0;
        gradoCientifico = "";
    }

    public Docente(String nombre, String nit, String direccion, double salarioBase,
                   int hAusensia, int aniosAntiguedad, String gradoCientifico) {
        super(nombre, nit, direccion, salarioBase, hAusensia);
        this.aniosAntiguedad = aniosAntiguedad;
        this.gradoCientifico = gradoCientifico;
    }

    public int getAniosAntiguedad() {
        return aniosAntiguedad;
    }

    public void setAniosAntiguedad(int aniosAnt) {
        this.aniosAntiguedad = aniosAnt;
    }

    public String getGradoCientifico() {
        return gradoCientifico;
    }

    public void setGradoCientifico(String gradoC) {
        this.gradoCientifico = gradoC;
    }

    /**
     * Calcular el plis por antiguedad del docente
     *
     * @return (double) con el plus de antiguedad
     */
    public double plusAntiguedad() {
        double plus = 0.0;
        plus = Math.floor((double) aniosAntiguedad / 5) * 20;
        return plus;
    }

    public int plusGradoCientifico() {
        int plus = 0;
        if (gradoCientifico.equals("Master")) {
            plus = 80;
        }
        if (gradoCientifico.equals("Doctor")){
            plus = 120;
        }
        return plus;
    }
    public double calcularSalario() {
        double sal = super.calcularSalario(); //Es el salario calculado en comun de clase trabajador
        sal = sal + this.plusAntiguedad() + plusGradoCientifico();
        return sal;
    }
    public String toString(){
        return super.toString() + "\nAños de Antiguedad: " + aniosAntiguedad +  "\nGrado Cientifico" + gradoCientifico;
    }
}
