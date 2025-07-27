public class Compradores {
    private String Nombre;
    private String Email;
    private int CantidadBoletos;
    private float Presupuesto;

    public Compradores(String nombre, String email, int cantidad, float presupuesto) {
        this.Nombre = nombre;
        this.Email = email;
        this.CantidadBoletos = cantidad;
        this.Presupuesto = presupuesto;
    }

    public String getNombre() { return Nombre; }
    public void setNombre(String nombre) { this.Nombre = nombre; }

    public String getEmail() { return Email; }
    public void setEmail(String email) { this.Email = email; }

    public int getCantidadBoletos() { return CantidadBoletos; }
    public void setCantidadBoletos(int cantidad) { this.CantidadBoletos = cantidad; }

    public float getPresupuesto() { return Presupuesto; }
    public void setPresupuesto(float presupuesto) { this.Presupuesto = presupuesto; }
} 