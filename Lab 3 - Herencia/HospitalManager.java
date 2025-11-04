import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase que centraliza la lógica del hospital.
 * No realiza impresiones ni lecturas; devuelve datos para que el main los muestre.
 */
public class HospitalManager {
    private List<TrabajadorMedico> trabajadores;
    private List<CitaMedica> citas;
    private List<HistorialReagendamiento> historialReagendamientos;

    public HospitalManager() {
        trabajadores = new ArrayList<>();
        citas = new ArrayList<>();
        historialReagendamientos = new ArrayList<>();
    }

    public List<TrabajadorMedico> getTrabajadores() { return trabajadores; }
    public void setTrabajadores(List<TrabajadorMedico> trabajadores) { this.trabajadores = trabajadores; }

    public List<CitaMedica> getCitas() { return citas; }
    public void setCitas(List<CitaMedica> citas) { this.citas = citas; }

    public List<HistorialReagendamiento> getHistorialReagendamientos() { return historialReagendamientos; }

    public void agregarTrabajador(TrabajadorMedico t) { trabajadores.add(t); }

    public void agregarCita(CitaMedica c) { citas.add(c); }

    /**
     * Asigna un trabajador disponible según clase y que no tenga conflicto en la fecha indicada.
     * Se busca por tipo de clase (nombre simple) como "DoctorGeneral", "Cirujano", etc.
     * @param tipoClase nombre simple de la clase buscada
     * @param fecha hora deseada
     * @return trabajador asignado o null si no se encuentra
     */
    public TrabajadorMedico asignarPersonal(String tipoClase, LocalDateTime fecha) {
        for (TrabajadorMedico t : trabajadores) {
            if (t.getClass().getSimpleName().equals(tipoClase)) {
                boolean ocupado = false;
                for (CitaMedica c : citas) {
                    if (c.getTrabajadorAsignado() != null && c.getTrabajadorAsignado().getIdEmpleado() == t.getIdEmpleado()
                        && c.getFechaHora().equals(fecha)) {
                        ocupado = true;
                        break;
                    }
                }
                if (!ocupado) return t;
            }
        }
        return null;
    }

    /**
     * Reagenda una cita y registra el cambio.
     * No imprime, solo actualiza estructuras.
     */
    public boolean reagendarCita(int idCita, LocalDateTime nuevaFecha, String motivo) {
        for (CitaMedica c : citas) {
            if (c.getIdCita() == idCita) {
                // verificar conflicto con otros pacientes para el mismo trabajador
                TrabajadorMedico t = c.getTrabajadorAsignado();
                if (t != null) {
                    for (CitaMedica otro : citas) {
                        if (otro != c && otro.getTrabajadorAsignado() != null &&
                            otro.getTrabajadorAsignado().getIdEmpleado() == t.getIdEmpleado() &&
                            otro.getFechaHora().equals(nuevaFecha)) {
                            // conflicto, no se puede reagendar así
                            return false;
                        }
                    }
                }
                HistorialReagendamiento h = new HistorialReagendamiento(c, nuevaFecha, motivo);
                historialReagendamientos.add(h);
                c.setFechaHora(nuevaFecha);
                c.setEstado(EstadoCita.REAGENDADA);
                return true;
            }
        }
        return false;
    }

    /**
     * Calcula la nómina total por departamento usando polimorfismo.
     * @return mapa departamento -> total nómina
     */
    public Map<String, Double> calcularNominaPorDepartamento() {
        Map<String, Double> mapa = new HashMap<>();
        for (TrabajadorMedico t : trabajadores) {
            String dept = t.getDepartamento();
            double salario = t.calcularSalario();
            mapa.put(dept, mapa.getOrDefault(dept, 0.0) + salario);
        }
        return mapa;
    }

    /**
     * Resuelve conflictos simples: si hay dos citas con mismo trabajador y hora, intenta reasignar la segunda.
     * @return lista de cambios realizados (strings) para que main los muestre
     */
    public List<String> resolverConflictos() {
        List<String> cambios = new ArrayList<>();
        for (int i = 0; i < citas.size(); i++) {
            CitaMedica a = citas.get(i);
            if (a.getTrabajadorAsignado() == null) continue;
            for (int j = i+1; j < citas.size(); j++) {
                CitaMedica b = citas.get(j);
                if (b.getTrabajadorAsignado() == null) continue;
                if (a.getTrabajadorAsignado().getIdEmpleado() == b.getTrabajadorAsignado().getIdEmpleado()
                        && a.getFechaHora().equals(b.getFechaHora())) {
                    // intentar reasignar b
                    TrabajadorMedico otro = asignarPersonal(b.getTrabajadorAsignado().getClass().getSimpleName(), b.getFechaHora().plusHours(1));
                    if (otro != null) {
                        b.setTrabajadorAsignado(otro);
                        b.setEstado(EstadoCita.REAGENDADA);
                        String msg = String.format("Cita %d reasignada a %s para %s", b.getIdCita(), otro.getNombre(), b.getFechaHora().toString());
                        cambios.add(msg);
                        historialReagendamientos.add(new HistorialReagendamiento(b, b.getFechaHora(), "Resolución conflicto"));
                    } else {
                        cambios.add(String.format("No se pudo reasignar cita %d (conflicto)", b.getIdCita()));
                    }
                }
            }
        }
        return cambios;
    }

    public List<CitaMedica> listarCitasPorEstado(EstadoCita estado) {
        List<CitaMedica> salida = new ArrayList<>();
        for (CitaMedica c : citas) {
            if (c.getEstado() == estado) salida.add(c);
        }
        return salida;
    }

    public List<CitaMedica> listarCitasPorTrabajador(int idEmpleado) {
        List<CitaMedica> salida = new ArrayList<>();
        for (CitaMedica c : citas) {
            if (c.getTrabajadorAsignado() != null && c.getTrabajadorAsignado().getIdEmpleado() == idEmpleado) salida.add(c);
        }
        return salida;
    }
}
