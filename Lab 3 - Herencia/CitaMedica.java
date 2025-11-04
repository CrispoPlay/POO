import java.time.LocalDateTime;

/**
 * Representa una cita médica.
 */
public class CitaMedica {
    private int idCita;
    private String paciente;
    private TrabajadorMedico trabajadorAsignado;
    private LocalDateTime fechaHora;
    private String tipoCita;
    private EstadoCita estado;

    public CitaMedica() {}

    public CitaMedica(int idCita, String paciente, TrabajadorMedico trabajadorAsignado,
                      LocalDateTime fechaHora, String tipoCita, EstadoCita estado) {
        this.idCita = idCita;
        this.paciente = paciente;
        this.trabajadorAsignado = trabajadorAsignado;
        this.fechaHora = fechaHora;
        this.tipoCita = tipoCita;
        this.estado = estado;
    }

    public int getIdCita() { return idCita; }
    public void setIdCita(int idCita) { this.idCita = idCita; }

    public String getPaciente() { return paciente; }
    public void setPaciente(String paciente) { this.paciente = paciente; }

    public TrabajadorMedico getTrabajadorAsignado() { return trabajadorAsignado; }
    public void setTrabajadorAsignado(TrabajadorMedico trabajadorAsignado) { this.trabajadorAsignado = trabajadorAsignado; }

    public java.time.LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(java.time.LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public String getTipoCita() { return tipoCita; }
    public void setTipoCita(String tipoCita) { this.tipoCita = tipoCita; }

    public EstadoCita getEstado() { return estado; }
    public void setEstado(EstadoCita estado) { this.estado = estado; }

    @Override
    public String toString() {
        String trabajador = trabajadorAsignado == null ? "Sin asignar" : trabajadorAsignado.getNombre();
        return String.format("CitaID:%d | Paciente:%s | Médico:%s | Fecha:%s | Tipo:%s | Estado:%s",
                idCita, paciente, trabajador, fechaHora.toString(), tipoCita, estado.name());
    }
}
