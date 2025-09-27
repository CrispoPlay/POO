import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class JuegoBatallaGUI extends JFrame {
    private Tablero tablero;
    private JTextArea logArea;
    private JPanel panelEnemigos;
    private JLabel lblJugador;
    private JProgressBar barraVidaJugador;

    public JuegoBatallaGUI() {
        setTitle("Batalla por Turnos ⚔️");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        inicializarInterfaz();   // ✅ primero la interfaz (crea logArea)
        inicializarJuego();      // ✅ luego el juego (ya puede usar agregarLog)
        actualizarEstado();
    }

    private void inicializarJuego() {
        String nombre = JOptionPane.showInputDialog(this, "Ingresa el nombre del jugador:");
        if (nombre == null || nombre.isEmpty()) nombre = "Jugador";

        String[] opciones = {"Guerrero 🛡️", "Explorador 🏹"};
        int rol = JOptionPane.showOptionDialog(this, "Elige tu clase:", "Clase",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, opciones, opciones[0]);

        Jugador jugador = (rol == 0) ? new Guerrero(nombre + " 🛡️") : new Explorador(nombre + " 🏹");

        // Inventario inicial
        if (jugador instanceof Guerrero) {
            jugador.agregarItem(new Item("Poción Pequeña 💊", "cura", 30, 1));
            jugador.agregarItem(new Item("Brebaje de Fuerza 💪", "buff", 3, 1));
        } else {
            jugador.agregarItem(new Item("Poción Pequeña 💊", "cura", 30, 2));
            jugador.agregarItem(new Item("Poción Grande 💖", "cura", 60, 1));
            jugador.agregarItem(new Item("Brebaje de Fuerza 💪", "buff", 3, 2));
        }

        // Generar enemigos aleatorios
        Random rnd = new Random();
        int nEnemigos = rnd.nextInt(3) + 1;
        java.util.List<Enemigo> enemigos = new ArrayList<>();
        for (int i = 0; i < nEnemigos; i++) {
            boolean tipoEsqueleto = rnd.nextBoolean();
            boolean esJefe = rnd.nextDouble() < 0.25;
            if (tipoEsqueleto) {
                Enemigo e = esJefe ? new JefeEsqueleto("👑💀 Jefe Esqueleto " + (i+1)) : new Esqueleto("💀 Esqueleto " + (i+1));
                enemigos.add(e);
            } else {
                Enemigo e = esJefe ? new JefeDuende("👑👺 Jefe Duende " + (i+1)) : new Duende("👺 Duende " + (i+1));
                enemigos.add(e);
            }
        }

        tablero = new Tablero(jugador, enemigos);

        // Log de inicio
        agregarLog(jugador.mensajeInicio());
        for (Enemigo e : enemigos) agregarLog(e.mensajeInicio());
    }

    private void inicializarInterfaz() {
        // Panel jugador
        JPanel panelJugador = new JPanel(new BorderLayout());
        lblJugador = new JLabel();
        barraVidaJugador = new JProgressBar();
        barraVidaJugador.setStringPainted(true);
        panelJugador.add(lblJugador, BorderLayout.NORTH);
        panelJugador.add(barraVidaJugador, BorderLayout.CENTER);
        add(panelJugador, BorderLayout.WEST);

        // Panel enemigos
        panelEnemigos = new JPanel();
        panelEnemigos.setLayout(new BoxLayout(panelEnemigos, BoxLayout.Y_AXIS));
        add(new JScrollPane(panelEnemigos), BorderLayout.EAST);

        // Área de log
        logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(logArea);
        add(scrollLog, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        JButton btnAtacar = new JButton("Atacar ⚔️");
        JButton btnItem = new JButton("Usar Ítem 💊");
        JButton btnPasar = new JButton("Pasar ⏭️");
        JButton btnSalir = new JButton("Salir 🚪");

        panelBotones.add(btnAtacar);
        panelBotones.add(btnItem);
        panelBotones.add(btnPasar);
        panelBotones.add(btnSalir);
        add(panelBotones, BorderLayout.SOUTH);

        // Acciones
        btnAtacar.addActionListener(e -> accionAtacar());
        btnItem.addActionListener(e -> accionUsarItem());
        btnPasar.addActionListener(e -> pasarTurno());
        btnSalir.addActionListener(e -> System.exit(0));
    }

    private void accionAtacar() {
        java.util.List<Enemigo> vivos = new ArrayList<>();
        for (Enemigo en : tablero.getEnemigos()) if (en.estaVivo()) vivos.add(en);
        if (vivos.isEmpty()) return;

        String[] opciones = vivos.stream().map(Combatiente::getNombre).toArray(String[]::new);
        String elegido = (String) JOptionPane.showInputDialog(this, "Elige enemigo a atacar:", "Atacar",
                JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        if (elegido == null) return;

        Enemigo objetivo = vivos.stream().filter(en -> en.getNombre().equals(elegido)).findFirst().orElse(null);
        if (objetivo == null) return;

        Jugador jugador = tablero.getJugador();
        boolean esquiva = (objetivo instanceof Duende && ((Duende) objetivo).intentoEsquivar())
                || (objetivo instanceof JefeDuende && ((JefeDuende) objetivo).intentoEsquivar());

        if (esquiva) {
            agregarLog(jugador.getNombre() + " ataca a " + objetivo.getNombre() + " pero esquiva el ataque.");
        } else {
            agregarLog(jugador.getNombre() + " ataca a " + objetivo.getNombre());
            agregarLog(objetivo.recibirAtaque(jugador.getAtaque()));
            if (!objetivo.estaVivo()) agregarLog(objetivo.mensajeFinal());
        }
        turnoEnemigos();
        actualizarEstado();
    }

    private void accionUsarItem() {
        Jugador jugador = tablero.getJugador();
        if (jugador.getInventario().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No tienes ítems disponibles.");
            return;
        }

        String[] opciones = jugador.getInventario().stream()
                .map(Item::toString).toArray(String[]::new);
        int idx = JOptionPane.showOptionDialog(this, "Elige un ítem:", "Inventario",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, opciones, opciones[0]);
        if (idx < 0) return;

        java.util.List<Combatiente> objetivos = new ArrayList<>();
        objetivos.add(jugador);

        agregarLog(jugador.usarItem(idx, objetivos));
        turnoEnemigos();
        actualizarEstado();
    }

    private void pasarTurno() {
        agregarLog(tablero.getJugador().getNombre() + " decide pasar el turno.");
        turnoEnemigos();
        actualizarEstado();
    }

    private void turnoEnemigos() {
        for (Enemigo e : tablero.getEnemigos()) {
            if (!e.estaVivo()) continue;
            String accion = e.tomarTurno(tablero);
            agregarLog(accion);
            if (!tablero.getJugador().estaVivo()) {
                agregarLog(tablero.getJugador().mensajeFinal());
                JOptionPane.showMessageDialog(this, "¡Has sido derrotado! ❌");
                System.exit(0);
            }
        }
    }

    private void actualizarEstado() {
        Jugador jugador = tablero.getJugador();
        lblJugador.setText(jugador.getNombre() + " | Vida: " + jugador.getVida());
        barraVidaJugador.setMaximum(150);
        barraVidaJugador.setValue(jugador.getVida());

        panelEnemigos.removeAll();
        for (Enemigo e : tablero.getEnemigos()) {
            JProgressBar barra = new JProgressBar(0, 120);
            barra.setValue(e.getVida());
            barra.setStringPainted(true);
            barra.setString(e.getNombre() + " (" + e.getVida() + " vida)");
            panelEnemigos.add(barra);
        }
        panelEnemigos.revalidate();
        panelEnemigos.repaint();
    }

    private void agregarLog(String texto) {
        logArea.append(texto + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JuegoBatallaGUI().setVisible(true));
    }
}