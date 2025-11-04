import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.List;
import java.util.Locale;

public class MainGUI {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("Ejercicio 6 – Interfaces (Swing)");
            f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            f.setSize(1000, 650);
            f.setLocationRelativeTo(null);

            Catalogo catalogo = new Catalogo();
            cargarInicial(catalogo);
            inicializarRegistros(catalogo);

            DeviceTableModel model = new DeviceTableModel(catalogo.listarTodos());
            JTable table = new JTable(model);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane tableScroll = new JScrollPane(table);

            JTextArea details = new JTextArea();
            details.setEditable(false);
            details.setLineWrap(true);
            details.setWrapStyleWord(true);
            JScrollPane detailsScroll = new JScrollPane(details);

            JPanel right = new JPanel(new BorderLayout(8,8));
            right.add(new JLabel("Detalles / Registros / Acciones"), BorderLayout.NORTH);
            right.add(detailsScroll, BorderLayout.CENTER);

            JPanel controls = new JPanel(new GridLayout(0,1,6,6));

            JButton btnListar = new JButton("Listar todos");
            btnListar.addActionListener(e -> model.setData(catalogo.listarTodos()));
            controls.add(btnListar);

            JPanel pId = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JTextField tfId = new JTextField(12);
            JButton btnBuscarId = new JButton("Buscar por ID");
            btnBuscarId.addActionListener(e -> {
                Dispositivo d = catalogo.buscarPorId(tfId.getText().trim());
                model.setData(d == null ? List.of() : List.of(d));
            });
            pId.add(new JLabel("ID:")); pId.add(tfId); pId.add(btnBuscarId);
            controls.add(pId);

            JPanel pNom = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JTextField tfNom = new JTextField(12);
            JButton btnBuscarNom = new JButton("Buscar por nombre");
            btnBuscarNom.addActionListener(e -> model.setData(catalogo.buscarPorNombre(tfNom.getText().trim())));
            pNom.add(new JLabel("Nombre:")); pNom.add(tfNom); pNom.add(btnBuscarNom);
            controls.add(pNom);

            JButton btnOrdenar = new JButton("Ordenar por consumo (asc)");
            btnOrdenar.addActionListener(e -> model.setData(catalogo.ordenarPorConsumo()));
            controls.add(btnOrdenar);

            JPanel pAcc = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JTextField tfAccion = new JTextField(10);
            JTextField tfParam = new JTextField(6);
            JButton btnAccion = new JButton("Ejecutar acción");
            btnAccion.addActionListener(e -> {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    Dispositivo d = model.getAt(row);
                    if (d instanceof Accionable a) {
                        String acc = tfAccion.getText().trim();
                        String p = tfParam.getText().trim();
                        if (!acc.isEmpty() && !p.isEmpty()) {
                            try {
                                double val = Double.parseDouble(p);
                                a.ejecutarAccion(acc, val);
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(f, "Parámetro numérico inválido", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        } else if (!acc.isEmpty()) {
                            a.ejecutarAccion(acc);
                        }
                        if (d instanceof Registrable r) r.generarRegistro();
                        showDetails(details, d);
                    } else {
                        JOptionPane.showMessageDialog(f, "El dispositivo no es Accionable.", "Info", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });
            pAcc.add(new JLabel("Acción:")); pAcc.add(tfAccion);
            pAcc.add(new JLabel("Param:")); pAcc.add(tfParam);
            pAcc.add(btnAccion);
            controls.add(pAcc);

            table.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
                if (!e.getValueIsAdjusting()) {
                    int row = table.getSelectedRow();
                    if (row >= 0) showDetails(details, model.getAt(row));
                    else details.setText("");
                }
            });

            JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(controls), tableScroll);
            split.setDividerLocation(320);
            JSplitPane split2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, split, right);
            split2.setDividerLocation(700);

            f.setLayout(new BorderLayout());
            f.add(split2, BorderLayout.CENTER);
            f.setVisible(true);
        });
    }

    private static void showDetails(JTextArea details, Dispositivo d) {
        StringBuilder sb = new StringBuilder();
        sb.append(d.toString()).append("\n");
        if (d instanceof Medible m) {
            sb.append("Medición actual: ").append(String.format(java.util.Locale.US, "%.3f", m.medir())).append("\n");
        }
        if (d instanceof Registrable r) {
            sb.append("\nRegistros:\n");
            for (String reg : r.getRegistros()) sb.append(" • ").append(reg).append("\n");
        }
        details.setText(sb.toString());
        details.setCaretPosition(0);
    }

    // Carga inicial (>=10 dispositivos)
    private static void cargarInicial(Catalogo catalogo) {
        catalogo.agregarDispositivo(new SensorSuelo("S1", "Sensor Suelo Norte", 1.2, 35.5, 22.0));
        catalogo.agregarDispositivo(new SensorSuelo("S2", "Sensor Suelo Sur", 1.1, 41.0, 21.5));
        catalogo.agregarDispositivo(new SensorSuelo("S3", "Sensor Suelo Este", 1.0, 28.7, 20.2));

        catalogo.agregarDispositivo(new EstacionMeteorologica("E1", "Estación Central", 4.5, 23.3, 1008.2));
        catalogo.agregarDispositivo(new EstacionMeteorologica("E2", "Estación Auxiliar", 3.8, 24.1, 1006.7));

        catalogo.agregarDispositivo(new DronRiego("DR1", "Dron Riego A", 15.0, 12.0));
        catalogo.agregarDispositivo(new DronRiego("DR2", "Dron Riego B", 16.5, 10.0));

        catalogo.agregarDispositivo(new DronMonitoreo("DM1", "Dron Monitoreo Águila", 13.0, "4K"));
        catalogo.agregarDispositivo(new DronMonitoreo("DM2", "Dron Monitoreo Halcón", 12.0, "1080p"));
        catalogo.agregarDispositivo(new DronMonitoreo("DM3", "Dron Monitoreo Cóndor", 12.8, "5K"));
    }

    // Acciones iniciales para registros coherentes
    private static void inicializarRegistros(Catalogo catalogo) {
        Dispositivo dr1 = catalogo.buscarPorId("DR1");
        if (dr1 instanceof DronRiego r1) {
            r1.ejecutarAccion("regar");
            r1.ejecutarAccion("regar", 2.5);
            r1.generarRegistro();
        }
        Dispositivo dm1 = catalogo.buscarPorId("DM1");
        if (dm1 instanceof DronMonitoreo m1) {
            m1.ejecutarAccion("fotografiar");
            m1.ejecutarAccion("fotografiar", 30.0);
            m1.ejecutarAccion("ajustarIndice", 0.732);
            m1.generarRegistro();
        }
        Dispositivo s1 = catalogo.buscarPorId("S1");
        if (s1 instanceof SensorSuelo ss1) ss1.generarRegistro();
        Dispositivo e1 = catalogo.buscarPorId("E1");
        if (e1 instanceof EstacionMeteorologica em1) em1.generarRegistro();
    }

    /** Swing-only: table model embebido para no contaminar las clases de dominio. */
    private static class DeviceTableModel extends AbstractTableModel {
        private final String[] cols = {"ID", "Nombre", "Consumo (W)", "Tipo", "Medible", "Accionable", "Registrable"};
        private List<Dispositivo> data;

        public DeviceTableModel(List<Dispositivo> data) { this.data = data; }
        public void setData(List<Dispositivo> data) { this.data = data; fireTableDataChanged(); }
        public Dispositivo getAt(int row) { return (row < 0 || row >= data.size()) ? null : data.get(row); }

        @Override public int getRowCount() { return data == null ? 0 : data.size(); }
        @Override public int getColumnCount() { return cols.length; }
        @Override public String getColumnName(int c) { return cols[c]; }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Dispositivo d = data.get(rowIndex);
            return switch (columnIndex) {
                case 0 -> d.getId();
                case 1 -> d.getNombre();
                case 2 -> d.getConsumoElectrico();
                case 3 -> d.getClass().getSimpleName();
                case 4 -> (d instanceof Medible);
                case 5 -> (d instanceof Accionable);
                case 6 -> (d instanceof Registrable);
                default -> "";
            };
        }

        @Override public Class<?> getColumnClass(int columnIndex) {
            return switch (columnIndex) {
                case 2 -> Double.class;
                case 4,5,6 -> Boolean.class;
                default -> String.class;
            };
        }
    }
}
