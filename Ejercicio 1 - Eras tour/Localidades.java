import java.util.*;

public class Localidades {
    private String Zona;
    private float Precio;
    private int CapacidadBoletos;
    private int BoletosVendidos;

    public Localidades(String zona, float precio, int capacidad) {
        this.Zona = zona;
        this.Precio = precio;
        this.CapacidadBoletos = capacidad;
        this.BoletosVendidos = 0;
    }

    public String getZona() { return Zona; }
    public void setZona(String zona) { this.Zona = zona; }

    public float getPrecio() { return Precio; }
    public void setPrecio(float precio) { this.Precio = precio; }

    public int getCapacidadBoletos() { return CapacidadBoletos; }

    public int getBoletosVendidos() { return BoletosVendidos; }
    public void setBoletosVendidos(int vendidos) { this.BoletosVendidos = vendidos; }

    public boolean hayEspacio(int cantidad) {
        return (CapacidadBoletos - BoletosVendidos) >= cantidad;
    }

    public void venderBoletos(int cantidad) {
        this.BoletosVendidos += cantidad;
    }
}
