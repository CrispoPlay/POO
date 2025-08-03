public class Digievolucion {
    private String nombreFase;
    private String tipoEfecto; 
    private int valorEfecto;
    private int probabilidadActivar; 

    // Getters y setters
    public String getNombreFase() {
        return nombreFase;
    }

    public void setNombreFase(String nombreFase) {
        this.nombreFase = nombreFase;
    }

    public String getTipoEfecto() {
        return tipoEfecto;
    }

    public void setTipoEfecto(String tipoEfecto) {
        this.tipoEfecto = tipoEfecto;
    }

    public int getValorEfecto() {
        return valorEfecto;
    }

    public void setValorEfecto(int valorEfecto) {
        this.valorEfecto = valorEfecto;
    }

    public int getProbabilidadActivar() {
        return probabilidadActivar;
    }

    public void setProbabilidadActivar(int probabilidadActivar) {
        this.probabilidadActivar = probabilidadActivar;
    }
}
