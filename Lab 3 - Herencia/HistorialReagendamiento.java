import java.time.LocalDateTime;

/**
 * Registro de cambios de fecha de cita.
 */
public class HistorialReagendamiento {
    private CitaMedica citaOriginal;
    private LocalDateTime nuevaFecha;
    private String motivo;

    public HistorialReagendamiento() {}

    public HistorialReagendamiento(CitaMedica citaOriginal, LocalDateTime nuevaFecha, String motivo) {
        this.citaOriginal = citaOriginal;
        this.nuevaFecha = nuevaFecha;
        this.motivo = motivo;
    }

    public CitaMedica getCitaOriginal() { return citaOriginal; }
    public void setCitaOriginal(CitaMedica citaOriginal) { this.citaOriginal = citaOriginal; }

    public LocalDateTime getNuevaFecha() { return nuevaFecha; }
    public void setNuevaFecha(LocalDateTime nuevaFecha) { this.nuevaFecha = nuevaFecha; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    @Override
    public String toString() {
        return String.format("Cita:%d | NuevaFecha:%s | Motivo:%s",
                citaOriginal.getIdCita(), nuevaFecha.toString(), motivo);
    }
}
