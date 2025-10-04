/*
Nombre: Cristian Estuardo Orellana Dieguez
Clase: ProcesoIO
Descripcion: Clase de procesos IO qu puede ser de entrada o salida de datos y lo emula
 */
public class ProcesoIO extends Proceso {
    private String tipoIO;
    private String entradaTexto;

    public ProcesoIO() {
        super();
        tipoIO = "";
        entradaTexto = "";
    }
// Constructor con parametros
    public ProcesoIO(int pid, String nombre, String tipoIO, String entradaTexto) {
        super(pid, nombre);
        this.tipoIO = tipoIO;
        this.entradaTexto = entradaTexto;
    }

    public String getTipoIO() {
        return tipoIO;
    }

    public void setTipoIO(String tipoIO) {
        this.tipoIO = tipoIO;
    }

    public String getEntradaTexto() {
        return entradaTexto;
    }
//si es entrada de Texto aqui recibe
    public void setEntradaTexto(String entradaTexto) {
        this.entradaTexto = entradaTexto;
    }

    // Simula ejecución de entrada/salida
    @Override
    public String ejecutar() {
        if (tipoIO.equalsIgnoreCase("entrada")) {
            return super.toString() + "Proceso IO de entrada ejecutado. Texto recibido: " + entradaTexto;
        } else if (tipoIO.equalsIgnoreCase("salida")) {
            return super.toString() + "Proceso IO de salida ejecutado. Datos enviados correctamente.";
        } else {
            return "Tipo de IO no reconocido.";
        }
    }
}