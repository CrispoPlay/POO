/**
 *
 */
public class Trabajador {
    private String nombre;
    private String nit;
    private String direccion;
    protected double salarioBase;
    private int hAusensia;

    public Trabajador() {
        nombre = "";
        nit = "";
        direccion = "";
        salarioBase = 0;
        hAusensia = 0;
    }
    public Trabajador(String nombre, String nit, String direccion, double salarioBase, int hAusensia) {
       this.nombre = nombre;
       this.nit = nit;
       this.direccion = direccion;
       this.salarioBase = salarioBase;
       this.hAusensia = hAusensia;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nom) {
        this.nombre = nom;
    }
    public String getNit() {
        return nit;
    }
    public void setNit(String ni) {
        this.nit = ni;
    }
    public String getDireccion(){
        return direccion;
    }
    public void setDireccion(String dir){
        this.direccion = dir;
    }
    public double getSalariobase(){
        return salarioBase;
    }
    public void setSalariobase(double SB){
        this.salarioBase = SB;
    }
    public int gethAusensia(){
        return hAusensia;
    }
    public void sethAusensia(int hA){
        this.hAusensia = hA;
    }

    /**
     * Calcula la tarifa horaria
     * @return (double)
     */
    public double tarifaHoraria(){
        double tarifa = salarioBase/192;
        return tarifa;
    }

    /**
     * Calcula el descuento por las Ausensias
     * @return (double) con el descuento por ausensias
     */
    public double descuentoPorAusensia(){
        double descuento = tarifaHoraria()*hAusensia;
        return descuento;
    }

    /**
     * Calcula el salario del trabajador
     * @return (double) con el salario del Trabajador
     */
    public double calcularSalario(){
        double salario = salarioBase -  descuentoPorAusensia();
        return salario;
    }

    public String toString(){
        String info = "\nNombre:" + nombre +"Nit: "+ nit+ "\nDireccion: " + direccion
                + "\nSalario Base: " + salarioBase + "\nHoras de Ausencia: " + hAusensia;
        return info;
    }

}
