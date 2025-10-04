/*
Nombre: Cristian Estuardo Orellana Dieguez
Clase: MainGUI
Descripcion: Clase del Main que se encarga de mostrar de manera gráfica el programa
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class MainGUI extends JFrame {
    private Planificador planificador;
    private JTextArea outputArea;

    public MainGUI() {
        planificador = new Planificador();
        setTitle("🎛 Simulador de Procesos del Sistema Operativo");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Estilo general
        Font font = new Font("Segoe UI", Font.PLAIN, 14);
        UIManager.put("Button.font", font);
        UIManager.put("Label.font", font);
        UIManager.put("TextField.font", font);
        UIManager.put("TextArea.font", font);

        // Área de salida con estilo
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setBackground(new Color(245, 245, 255));
        outputArea.setForeground(new Color(40, 40, 60));
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // Panel de botones con color
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(220, 230, 250));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton agregarBtn = new JButton("➕ Agregar Proceso");
        JButton ejecutarBtn = new JButton("▶ Ejecutar Procesos");
        JButton visualizarBtn = new JButton("📋 Visualizar Procesos");

        agregarBtn.setBackground(new Color(100, 180, 255));
        ejecutarBtn.setBackground(new Color(120, 220, 160));
        visualizarBtn.setBackground(new Color(255, 200, 120));

        buttonPanel.add(agregarBtn);
        buttonPanel.add(ejecutarBtn);
        buttonPanel.add(visualizarBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        // Acción para agregar proceso
        agregarBtn.addActionListener(e -> agregarProceso());

        // Acción para ejecutar procesos
        ejecutarBtn.addActionListener(e -> {
            outputArea.append("\n🔧 Ejecutando procesos...\n");
            List<String> resultados = planificador.ejecutarTodos();
            for (String r : resultados) {
                outputArea.append("• " + r + "\n");
            }
        });

        // Acción para visualizar procesos
        visualizarBtn.addActionListener(e -> {
            outputArea.append("\n📋 Procesos registrados:\n");
            for (Proceso p : planificador.getProcesos()) {
                outputArea.append("• " + p.toString() + "\n");
            }
        });
    }

    private void agregarProceso() {
        String[] tipos = {"CPU", "IO", "Daemon"};
        String tipo = (String) JOptionPane.showInputDialog(this, "Tipo de proceso:", "Agregar Proceso",
                JOptionPane.QUESTION_MESSAGE, null, tipos, tipos[0]);

        if (tipo == null) return;

        int pid = Integer.parseInt(JOptionPane.showInputDialog(this, "PID:"));
        String nombre = JOptionPane.showInputDialog(this, "Nombre del proceso:");

        switch (tipo) {
            case "CPU":
                double usoCPU = Double.parseDouble(JOptionPane.showInputDialog(this, "Uso de CPU (%):"));
                planificador.agregarProceso(new ProcesoCPU(pid, nombre, usoCPU));
                break;
            case "IO":
                String[] modos = {"entrada", "salida"};
                String modo = (String) JOptionPane.showInputDialog(this, "Modo de IO:", "Tipo IO",
                        JOptionPane.QUESTION_MESSAGE, null, modos, modos[0]);
                String entradaTexto = "";
                if ("entrada".equalsIgnoreCase(modo)) {
                    entradaTexto = JOptionPane.showInputDialog(this, "Texto de entrada:");
                }
                planificador.agregarProceso(new ProcesoIO(pid, nombre, modo, entradaTexto));
                break;
            case "Daemon":
                String servicio = JOptionPane.showInputDialog(this, "Nombre del servicio:");
                planificador.agregarProceso(new Daemon(pid, nombre, servicio));
                break;
        }

        outputArea.append("✅ Proceso agregado correctamente.\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainGUI().setVisible(true));
    }
}