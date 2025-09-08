/*
Clase: JuegoMemoriaGUI
Autor: Cristian Estuardo Orellana Dieguez - 25664
Descripción: Interfaz gráfica con Swing para el juego de memoria.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JuegoMemoriaGUI extends JFrame {
    private JuegoMemoria juego;
    private JButton[][] botones;
    private JLabel lblTurno;
    private JLabel lblPuntaje;
    private int[] primeraSeleccion = null;

    public JuegoMemoriaGUI(int filas, int columnas, String nombre1, String nombre2) throws Exception {
        juego = new JuegoMemoria(filas, columnas, nombre1, nombre2);
        botones = new JButton[filas][columnas];

        setTitle("Juego de Memoria - Swing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior con turno y puntajes
        JPanel panelInfo = new JPanel(new GridLayout(2,1));
        lblTurno = new JLabel("Turno de: " + juego.getTurnoActual().getNombre(), SwingConstants.CENTER);
        lblPuntaje = new JLabel(puntajesTexto(), SwingConstants.CENTER);
        panelInfo.add(lblTurno);
        panelInfo.add(lblPuntaje);
        add(panelInfo, BorderLayout.NORTH);

        // Panel central con tablero
        JPanel panelTablero = new JPanel(new GridLayout(filas, columnas));
        for (int r = 0; r < filas; r++) {
            for (int c = 0; c < columnas; c++) {
                JButton btn = new JButton("X");
                int fila = r, col = c;
                btn.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24));
                btn.addActionListener(e -> manejarClick(fila, col));
                botones[r][c] = btn;
                panelTablero.add(btn);
            }
        }
        add(panelTablero, BorderLayout.CENTER);

        setSize(600, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void manejarClick(int fila, int col) {
        try {
            Ficha ficha = juego.getTablero().obtenerFicha(fila, col);
            if (ficha.isEmparejada() || ficha.isVisible()) return;

            // Mostrar símbolo
            juego.getTablero().revelarFicha(fila, col);
            botones[fila][col].setText(ficha.getSimbolo());

            if (primeraSeleccion == null) {
                // Guardar primera selección
                primeraSeleccion = new int[]{fila, col};
            } else {
                // Segunda selección
                int r1 = primeraSeleccion[0], c1 = primeraSeleccion[1];
                int r2 = fila, c2 = col;
                primeraSeleccion = null;

                // Resolver turno después de un delay
                Timer timer = new Timer(1000, new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        String resultado = juego.jugarTurno(r1, c1, r2, c2);
                        actualizarTablero();
                        lblTurno.setText("Turno de: " + juego.getTurnoActual().getNombre());
                        lblPuntaje.setText(puntajesTexto());

                        if (juego.verificarFin()) {
                            JOptionPane.showMessageDialog(null, juego.mostrarResultados());
                            dispose(); // cerrar ventana
                        }
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void actualizarTablero() {
        for (int r = 0; r < botones.length; r++) {
            for (int c = 0; c < botones[0].length; c++) {
                Ficha f = juego.getTablero().obtenerFicha(r, c);
                if (f.isVisible() || f.isEmparejada()) {
                    botones[r][c].setText(f.getSimbolo());
                } else {
                    botones[r][c].setText("X");
                }
            }
        }
    }

    private String puntajesTexto() {
        return juego.getJugador1().getNombre() + ": " + juego.getJugador1().getPuntos() + "  |  " +
               juego.getJugador2().getNombre() + ": " + juego.getJugador2().getPuntos();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String n1 = JOptionPane.showInputDialog("Nombre jugador 1:");
            String n2 = JOptionPane.showInputDialog("Nombre jugador 2:");
            String filasStr = JOptionPane.showInputDialog("Número de filas (ej: 4):");
            String colsStr = JOptionPane.showInputDialog("Número de columnas (ej: 4):");
            try {
                int filas = Integer.parseInt(filasStr);
                int columnas = Integer.parseInt(colsStr);
                new JuegoMemoriaGUI(filas, columnas, n1, n2);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        });
    }
}
