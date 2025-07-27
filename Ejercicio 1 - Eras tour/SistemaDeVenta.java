import java.util.*;

public class SistemaDeVenta {
    private Compradores compradorActual;
    private List<Localidades> localidades;
    private int totalVendidos;

    public SistemaDeVenta() {
        localidades = new ArrayList<>();
        localidades.add(new Localidades("Zona 1", 100f, 20));
        localidades.add(new Localidades("Zona 5", 500f, 20));
        localidades.add(new Localidades("Zona 10", 1000f, 20));
    }

    public void setCompradorActual(Compradores comprador) {
        this.compradorActual = comprador;
    }

    public Compradores getCompradorActual() {
        return compradorActual;
    }

    public void realizarSolicitud() {
        Ticket ticket = new Ticket();
        ticket.generar();

        if (!ticket.esValido()) {
            System.out.println("Ticket no válido para comprar boletos.");
            return;
        }

        Random rand = new Random();
        Localidades seleccionada = localidades.get(rand.nextInt(localidades.size()));

        int cantidadDeseada = compradorActual.getCantidadBoletos();
        float presupuesto = compradorActual.getPresupuesto();

        if (!seleccionada.hayEspacio(1)) {
            System.out.println("No hay espacio en la zona seleccionada.");
            return;
        }

        int disponibles = seleccionada.getCapacidadBoletos() - seleccionada.getBoletosVendidos();
        int aVender = Math.min(disponibles, cantidadDeseada);

        if (seleccionada.getPrecio() > presupuesto) {
            System.out.println("Presupuesto insuficiente para esta zona.");
            return;
        }

        seleccionada.venderBoletos(aVender);
        totalVendidos += aVender;

        System.out.println("Compra exitosa: " + aVender + " boletos en " + seleccionada.getZona());
    }

    public void mostrarDisponibilidadTotal() {
        for (Localidades loc : localidades) {
            int disponibles = loc.getCapacidadBoletos() - loc.getBoletosVendidos();
            System.out.println(loc.getZona() + ": " + disponibles + " disponibles.");
        }
    }

    public void mostrarDisponibilidadLocal(String zona) {
        for (Localidades loc : localidades) {
            if (loc.getZona().equalsIgnoreCase(zona)) {
                int disponibles = loc.getCapacidadBoletos() - loc.getBoletosVendidos();
                System.out.println(loc.getZona() + ": " + disponibles + " disponibles.");
                return;
            }
        }
        System.out.println("Zona no encontrada.");
    }

    public void reporteCaja() {
        float total = 0f;
        for (Localidades loc : localidades) {
            total += loc.getBoletosVendidos() * loc.getPrecio();
        }
        System.out.println("Total recaudado: $" + total);
    }
}
