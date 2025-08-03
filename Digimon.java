public class Digimon {
    private String nombreDigimon;
    private String tipoElemento;
    private int poderAtaqueBase;
    private int poderDefensaBase;
    private Digievolucion digievolucion;
    private boolean evoluciono = false; 

    // Getters y setters
    public String getNombreDigimon() {
        return nombreDigimon;
    }

    public void setNombreDigimon(String nombreDigimon) {
        this.nombreDigimon = nombreDigimon;
    }

    public String getTipoElemento() {
        return tipoElemento;
    }

    public void setTipoElemento(String tipoElemento) {
        this.tipoElemento = tipoElemento;
    }

    public int getPoderAtaqueBase() {
        return poderAtaqueBase;
    }

    public void setPoderAtaqueBase(int poderAtaqueBase) {
        this.poderAtaqueBase = poderAtaqueBase;
    }

    public int getPoderDefensaBase() {
        return poderDefensaBase;
    }

    public void setPoderDefensaBase(int poderDefensaBase) {
        this.poderDefensaBase = poderDefensaBase;
    }

    public Digievolucion getDigievolucion() {
        return digievolucion;
    }

    public void setDigievolucion(Digievolucion digievolucion) {
        this.digievolucion = digievolucion;
    }

    public boolean isEvoluciono() {
        return evoluciono;
    }

    public void setEvoluciono(boolean evoluciono) {
        this.evoluciono = evoluciono;
    }

    // Método para calcular el ataque total considerando evolución
    public int calcularAtaqueTotal() {
        int ataqueTotal = poderAtaqueBase;
        if (evoluciono && digievolucion != null && "ataque".equalsIgnoreCase(digievolucion.getTipoEfecto())) {
            ataqueTotal += digievolucion.getValorEfecto();
        }
        return ataqueTotal;
    }

    // Método para calcular la defensa total considerando evolución
    public int calcularDefensaTotal() {
        int defensaTotal = poderDefensaBase;
        if (evoluciono && digievolucion != null && "defensa".equalsIgnoreCase(digievolucion.getTipoEfecto())) {
            defensaTotal += digievolucion.getValorEfecto();
        }
        return defensaTotal;
    }

    // Método para intentar la evolución basado en la probabilidad
    public void intentarEvolucion() {
        if (digievolucion == null || evoluciono) {
            // No tiene evolución o ya evolucionó
            return;
        }
        int probabilidad = digievolucion.getProbabilidadActivar();
        int azar = (int) (Math.random() * 100) + 1; // 1-100
        if (azar <= probabilidad) {
            evoluciono = true;
        }
    }

    @Override
    public String toString() {
        String evoTexto = evoluciono ? " (Evolucionó a " + digievolucion.getNombreFase() + ")" : "";
        return nombreDigimon + " - Tipo: " + tipoElemento +
                ", Ataque: " + calcularAtaqueTotal() +
                ", Defensa: " + calcularDefensaTotal() + evoTexto;
    }
}
