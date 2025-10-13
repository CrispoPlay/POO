import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class HospitalGUI extends JFrame {
    private HospitalManager manager;
    private JTabbedPane tabbedPane;
    private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    
    // Arrays para las listas desplegables
    private String[] tiposTrabajador = {"DoctorGeneral", "Cirujano", "Enfermero", "Radiologo", "Farmaceutico", "Terapeuta"};
    private String[] turnosEnfermero = {"diurno", "nocturno"};
    private String[] estadosCita = {"PROGRAMADA", "CONFIRMADA", "EN_PROGRESO", "COMPLETADA", "CANCELADA", "REAGENDADA"};

    public HospitalGUI() {
        manager = new HospitalManager();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Sistema Hospitalario - Gestión Integral");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        // Panel principal con pestañas
        tabbedPane = new JTabbedPane();
        
        // Crear las diferentes pestañas
        tabbedPane.addTab("Trabajadores", createTrabajadoresPanel());
        tabbedPane.addTab("Citas Médicas", createCitasPanel());
        tabbedPane.addTab("Reagendar Citas", createReagendarPanel());
        tabbedPane.addTab("Gestión", createGestionPanel());
        tabbedPane.addTab("Reportes", createReportesPanel());

        add(tabbedPane);
    }

    private JPanel createTrabajadoresPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Panel de formulario
        JPanel formPanel = createTrabajadorFormPanel();
        panel.add(formPanel, BorderLayout.NORTH);

        // Tabla de trabajadores
        String[] columnNames = {"ID", "Nombre", "Departamento", "Experiencia", "Salario Base", "Tipo", "Detalles"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Botón para actualizar tabla
        JButton btnActualizar = new JButton("Actualizar Lista");
        btnActualizar.addActionListener(e -> actualizarTablaTrabajadores(tableModel));
        panel.add(btnActualizar, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createTrabajadorFormPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Agregar Nuevo Trabajador"));

        // Campos comunes
        JComboBox<String> cmbTipo = new JComboBox<>(tiposTrabajador);
        JTextField txtId = new JTextField();
        JTextField txtNombre = new JTextField();
        JTextField txtDept = new JTextField();
        JTextField txtAnios = new JTextField();
        JTextField txtSalarioBase = new JTextField();

        // Campos específicos (inicialmente ocultos)
        JPanel camposEspecificos = new JPanel(new CardLayout());
        
        // Doctor General
        JPanel panelDoctor = new JPanel(new GridLayout(0, 2, 5, 5));
        panelDoctor.add(new JLabel("Especialización:"));
        JTextField txtEspecializacion = new JTextField();
        panelDoctor.add(txtEspecializacion);
        panelDoctor.add(new JLabel("Pacientes por día:"));
        JTextField txtPacientesDia = new JTextField();
        panelDoctor.add(txtPacientesDia);
        panelDoctor.add(new JLabel("Tarifa consulta:"));
        JTextField txtTarifaConsulta = new JTextField();
        panelDoctor.add(txtTarifaConsulta);
        
        // Cirujano
        JPanel panelCirujano = new JPanel(new GridLayout(0, 2, 5, 5));
        panelCirujano.add(new JLabel("Horas cirugía:"));
        JTextField txtHorasCirugia = new JTextField();
        panelCirujano.add(txtHorasCirugia);
        panelCirujano.add(new JLabel("Bono riesgo:"));
        JTextField txtBonoRiesgo = new JTextField();
        panelCirujano.add(txtBonoRiesgo);
        panelCirujano.add(new JLabel("Tarifa hora:"));
        JTextField txtTarifaHora = new JTextField();
        panelCirujano.add(txtTarifaHora);
        
        // Enfermero
        JPanel panelEnfermero = new JPanel(new GridLayout(0, 2, 5, 5));
        panelEnfermero.add(new JLabel("Turno:"));
        JComboBox<String> cmbTurno = new JComboBox<>(turnosEnfermero);
        panelEnfermero.add(cmbTurno);
        panelEnfermero.add(new JLabel("Certificación:"));
        JTextField txtCertificacion = new JTextField();
        panelEnfermero.add(txtCertificacion);
        panelEnfermero.add(new JLabel("Bono nocturno:"));
        JTextField txtBonoNocturno = new JTextField();
        panelEnfermero.add(txtBonoNocturno);

        // Radiólogo
        JPanel panelRadiologo = new JPanel(new GridLayout(0, 2, 5, 5));
        panelRadiologo.add(new JLabel("Tarifa por estudio:"));
        JTextField txtTarifaEstudio = new JTextField();
        panelRadiologo.add(txtTarifaEstudio);
        panelRadiologo.add(new JLabel("Estudios realizados:"));
        JTextField txtEstudiosRealizados = new JTextField();
        panelRadiologo.add(txtEstudiosRealizados);

        // Farmacéutico
        JPanel panelFarmaceutico = new JPanel(new GridLayout(0, 2, 5, 5));
        panelFarmaceutico.add(new JLabel("Límite prescripciones:"));
        JTextField txtLimitePrescripciones = new JTextField();
        panelFarmaceutico.add(txtLimitePrescripciones);
        panelFarmaceutico.add(new JLabel("Licencia sustancias controladas:"));
        JComboBox<String> cmbLicencia = new JComboBox<>(new String[]{"true", "false"});
        panelFarmaceutico.add(cmbLicencia);

        // Terapeuta
        JPanel panelTerapeuta = new JPanel(new GridLayout(0, 2, 5, 5));
        panelTerapeuta.add(new JLabel("Tipo de terapia:"));
        JTextField txtTipoTerapia = new JTextField();
        panelTerapeuta.add(txtTipoTerapia);
        panelTerapeuta.add(new JLabel("Duración sesión (min):"));
        JTextField txtDuracionSesion = new JTextField();
        panelTerapeuta.add(txtDuracionSesion);

        // Agregar todos los paneles al CardLayout
        camposEspecificos.add(panelDoctor, "DoctorGeneral");
        camposEspecificos.add(panelCirujano, "Cirujano");
        camposEspecificos.add(panelEnfermero, "Enfermero");
        camposEspecificos.add(panelRadiologo, "Radiologo");
        camposEspecificos.add(panelFarmaceutico, "Farmaceutico");
        camposEspecificos.add(panelTerapeuta, "Terapeuta");

        // Campos comunes en el formulario principal
        panel.add(new JLabel("Tipo:"));
        panel.add(cmbTipo);
        panel.add(new JLabel("ID:"));
        panel.add(txtId);
        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Departamento:"));
        panel.add(txtDept);
        panel.add(new JLabel("Años experiencia:"));
        panel.add(txtAnios);
        panel.add(new JLabel("Salario base:"));
        panel.add(txtSalarioBase);
        
        panel.add(new JLabel("Detalles específicos:"));
        panel.add(camposEspecificos);

        // Listener para cambiar los campos específicos
        cmbTipo.addActionListener(e -> {
            CardLayout cl = (CardLayout)(camposEspecificos.getLayout());
            cl.show(camposEspecificos, (String)cmbTipo.getSelectedItem());
        });

        // Botón de agregar
        JButton btnAgregar = new JButton("Agregar Trabajador");
        btnAgregar.addActionListener(e -> {
            try {
                String tipo = (String) cmbTipo.getSelectedItem();
                int id = Integer.parseInt(txtId.getText());
                String nombre = txtNombre.getText();
                String dept = txtDept.getText();
                int anios = Integer.parseInt(txtAnios.getText());
                double base = Double.parseDouble(txtSalarioBase.getText());

                switch (tipo) {
                    case "DoctorGeneral":
                        String esp = txtEspecializacion.getText();
                        int ppd = Integer.parseInt(txtPacientesDia.getText());
                        double tarifa = Double.parseDouble(txtTarifaConsulta.getText());
                        manager.agregarTrabajador(new DoctorGeneral(id, nombre, dept, anios, base, esp, ppd, tarifa));
                        break;
                    case "Cirujano":
                        int horas = Integer.parseInt(txtHorasCirugia.getText());
                        double bono = Double.parseDouble(txtBonoRiesgo.getText());
                        double tarifaH = Double.parseDouble(txtTarifaHora.getText());
                        manager.agregarTrabajador(new Cirujano(id, nombre, dept, anios, base, null, horas, bono, tarifaH));
                        break;
                    case "Enfermero":
                        String turno = (String) cmbTurno.getSelectedItem();
                        String cert = txtCertificacion.getText();
                        double bNoct = Double.parseDouble(txtBonoNocturno.getText());
                        manager.agregarTrabajador(new Enfermero(id, nombre, dept, anios, base, turno, cert, bNoct));
                        break;
                    case "Radiologo":
                        double tarifaEst = Double.parseDouble(txtTarifaEstudio.getText());
                        int estudios = Integer.parseInt(txtEstudiosRealizados.getText());
                        manager.agregarTrabajador(new Radiologo(id, nombre, dept, anios, base, null, tarifaEst, estudios));
                        break;
                    case "Farmaceutico":
                        int limite = Integer.parseInt(txtLimitePrescripciones.getText());
                        boolean licencia = Boolean.parseBoolean((String) cmbLicencia.getSelectedItem());
                        manager.agregarTrabajador(new Farmaceutico(id, nombre, dept, anios, base, limite, licencia));
                        break;
                    case "Terapeuta":
                        String tipoTerapia = txtTipoTerapia.getText();
                        int duracion = Integer.parseInt(txtDuracionSesion.getText());
                        manager.agregarTrabajador(new Terapeuta(id, nombre, dept, anios, base, tipoTerapia, duracion));
                        break;
                }
                
                JOptionPane.showMessageDialog(this, "Trabajador agregado exitosamente");
                limpiarCampos(panel);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        panel.add(btnAgregar);

        return panel;
    }

    private JPanel createCitasPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Formulario de citas
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Agendar Nueva Cita"));

        JTextField txtIdCita = new JTextField();
        JTextField txtPaciente = new JTextField();
        JTextField txtTipoCita = new JTextField();
        JTextField txtFecha = new JTextField();
        JComboBox<String> cmbTipoTrabajador = new JComboBox<>(tiposTrabajador);
        JComboBox<String> cmbEstadoCita = new JComboBox<>(estadosCita);

        formPanel.add(new JLabel("ID Cita:"));
        formPanel.add(txtIdCita);
        formPanel.add(new JLabel("Paciente:"));
        formPanel.add(txtPaciente);
        formPanel.add(new JLabel("Tipo de Cita:"));
        formPanel.add(txtTipoCita);
        formPanel.add(new JLabel("Fecha (yyyy-MM-dd HH:mm):"));
        formPanel.add(txtFecha);
        formPanel.add(new JLabel("Tipo Trabajador:"));
        formPanel.add(cmbTipoTrabajador);
        formPanel.add(new JLabel("Estado:"));
        formPanel.add(cmbEstadoCita);

        JButton btnAgendar = new JButton("Agendar Cita");
        btnAgendar.addActionListener(e -> {
            try {
                int idCita = Integer.parseInt(txtIdCita.getText());
                String paciente = txtPaciente.getText();
                String tipoCita = txtTipoCita.getText();
                LocalDateTime fecha = LocalDateTime.parse(txtFecha.getText(), fmt);
                String tipoReq = (String) cmbTipoTrabajador.getSelectedItem();
                EstadoCita estado = EstadoCita.valueOf((String) cmbEstadoCita.getSelectedItem());

                TrabajadorMedico asignado = manager.asignarPersonal(tipoReq, fecha);
                CitaMedica cita = new CitaMedica(idCita, paciente, asignado, fecha, tipoCita, estado);
                manager.agregarCita(cita);

                String mensaje = "Cita creada exitosamente.\nAsignado: " + 
                    (asignado == null ? "Ningún trabajador disponible" : asignado.getNombre());
                JOptionPane.showMessageDialog(this, mensaje);
                
                limpiarCampos(formPanel);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        formPanel.add(btnAgendar);
        panel.add(formPanel, BorderLayout.NORTH);

        // Tabla de citas
        String[] columnNames = {"ID", "Paciente", "Médico", "Fecha", "Tipo", "Estado"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Botón para actualizar tabla
        JButton btnActualizar = new JButton("Actualizar Citas");
        btnActualizar.addActionListener(e -> actualizarTablaCitas(tableModel));
        panel.add(btnActualizar, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createReagendarPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Reagendar Cita"));

        JTextField txtIdCitaReagendar = new JTextField();
        JTextField txtNuevaFecha = new JTextField();
        JTextField txtMotivo = new JTextField();

        formPanel.add(new JLabel("ID Cita a reagendar:"));
        formPanel.add(txtIdCitaReagendar);
        formPanel.add(new JLabel("Nueva fecha (yyyy-MM-dd HH:mm):"));
        formPanel.add(txtNuevaFecha);
        formPanel.add(new JLabel("Motivo:"));
        formPanel.add(txtMotivo);

        JButton btnReagendar = new JButton("Reagendar Cita");
        btnReagendar.addActionListener(e -> {
            try {
                int idCita = Integer.parseInt(txtIdCitaReagendar.getText());
                LocalDateTime nuevaFecha = LocalDateTime.parse(txtNuevaFecha.getText(), fmt);
                String motivo = txtMotivo.getText();

                boolean ok = manager.reagendarCita(idCita, nuevaFecha, motivo);
                if (ok) {
                    JOptionPane.showMessageDialog(this, "Cita reagendada exitosamente");
                    limpiarCampos(formPanel);
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo reagendar la cita (conflicto o no existe)", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        formPanel.add(btnReagendar);
        panel.add(formPanel, BorderLayout.NORTH);

        return panel;
    }

    private JPanel createGestionPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Botón para resolver conflictos
        JButton btnConflictos = new JButton("Resolver Conflictos de Citas");
        btnConflictos.addActionListener(e -> {
            List<String> cambios = manager.resolverConflictos();
            if (cambios.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontraron conflictos");
            } else {
                StringBuilder sb = new StringBuilder("Cambios realizados:\n");
                for (String cambio : cambios) {
                    sb.append("• ").append(cambio).append("\n");
                }
                JOptionPane.showMessageDialog(this, sb.toString(), "Conflictos Resueltos", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Botón para listar citas por estado
        JButton btnCitasEstado = new JButton("Listar Citas por Estado");
        btnCitasEstado.addActionListener(e -> {
            String estado = (String) JOptionPane.showInputDialog(this, 
                "Seleccione estado:", "Listar Citas por Estado", 
                JOptionPane.QUESTION_MESSAGE, null, estadosCita, estadosCita[0]);
            
            if (estado != null) {
                EstadoCita estadoCita = EstadoCita.valueOf(estado);
                List<CitaMedica> citas = manager.listarCitasPorEstado(estadoCita);
                
                StringBuilder sb = new StringBuilder("Citas con estado " + estado + ":\n\n");
                for (CitaMedica cita : citas) {
                    sb.append(cita.toString()).append("\n\n");
                }
                
                JTextArea textArea = new JTextArea(sb.toString(), 15, 50);
                JScrollPane scrollPane = new JScrollPane(textArea);
                textArea.setEditable(false);
                JOptionPane.showMessageDialog(this, scrollPane, "Citas - " + estado, JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Botón para listar citas por trabajador
        JButton btnCitasTrabajador = new JButton("Listar Citas por Trabajador");
        btnCitasTrabajador.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(this, "Ingrese ID del trabajador:");
            if (idStr != null && !idStr.trim().isEmpty()) {
                try {
                    int idEmpleado = Integer.parseInt(idStr);
                    List<CitaMedica> citas = manager.listarCitasPorTrabajador(idEmpleado);
                    
                    StringBuilder sb = new StringBuilder("Citas del trabajador ID " + idEmpleado + ":\n\n");
                    for (CitaMedica cita : citas) {
                        sb.append(cita.toString()).append("\n\n");
                    }
                    
                    JTextArea textArea = new JTextArea(sb.toString(), 15, 50);
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    textArea.setEditable(false);
                    JOptionPane.showMessageDialog(this, scrollPane, "Citas del Trabajador", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "ID inválido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(btnConflictos);
        panel.add(btnCitasEstado);
        panel.add(btnCitasTrabajador);

        return panel;
    }

    private JPanel createReportesPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JButton btnNomina = new JButton("Calcular Nómina por Departamento");
        btnNomina.addActionListener(e -> {
            Map<String, Double> nomina = manager.calcularNominaPorDepartamento();
            
            StringBuilder sb = new StringBuilder("Nómina por Departamento:\n\n");
            for (String dept : nomina.keySet()) {
                sb.append(String.format("%s -> $%,.2f\n", dept, nomina.get(dept)));
            }
            
            JOptionPane.showMessageDialog(this, sb.toString(), "Reporte de Nómina", JOptionPane.INFORMATION_MESSAGE);
        });

        JButton btnReporteTrabajadores = new JButton("Reporte de Trabajadores");
        btnReporteTrabajadores.addActionListener(e -> {
            StringBuilder sb = new StringBuilder("Trabajadores Registrados:\n\n");
            for (TrabajadorMedico t : manager.getTrabajadores()) {
                sb.append(t.toString()).append("\n\n");
            }
            
            JTextArea textArea = new JTextArea(sb.toString(), 20, 60);
            JScrollPane scrollPane = new JScrollPane(textArea);
            textArea.setEditable(false);
            JOptionPane.showMessageDialog(this, scrollPane, "Reporte de Trabajadores", JOptionPane.INFORMATION_MESSAGE);
        });

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        buttonPanel.add(btnNomina);
        buttonPanel.add(btnReporteTrabajadores);

        panel.add(buttonPanel, BorderLayout.NORTH);

        return panel;
    }

    private void actualizarTablaTrabajadores(DefaultTableModel model) {
        model.setRowCount(0);
        for (TrabajadorMedico t : manager.getTrabajadores()) {
            model.addRow(new Object[]{
                t.getIdEmpleado(),
                t.getNombre(),
                t.getDepartamento(),
                t.getAniosExperiencia(),
                t.getSalarioBase(),
                t.getClass().getSimpleName(),
                t.toString()
            });
        }
    }

    private void actualizarTablaCitas(DefaultTableModel model) {
        model.setRowCount(0);
        for (CitaMedica c : manager.getCitas()) {
            String trabajador = c.getTrabajadorAsignado() == null ? 
                "Sin asignar" : c.getTrabajadorAsignado().getNombre();
            
            model.addRow(new Object[]{
                c.getIdCita(),
                c.getPaciente(),
                trabajador,
                c.getFechaHora().format(fmt),
                c.getTipoCita(),
                c.getEstado().name()
            });
        }
    }

    private void limpiarCampos(Container container) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JTextField) {
                ((JTextField) comp).setText("");
            } else if (comp instanceof Container) {
                limpiarCampos((Container) comp);
            }
        }
    }

    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        try {
            // Este método sí existe en todas las versiones de Java
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        HospitalGUI gui = new HospitalGUI();
        gui.setVisible(true);
    });
}
}