import java.util.Random;

public class Ticket {
    private int NumeroTicket;
    private boolean randomA;
    private boolean randomB;
    private int a;
    private int b;

    public void generar() {
        Random rand = new Random();
        NumeroTicket = rand.nextInt(15000) + 1;
        a = rand.nextInt(15000) + 1;
        b = rand.nextInt(15000) + 1;
        randomA = true;
        randomB = true;
    }

    public boolean esValido() {
        int min = Math.min(a, b);
        int max = Math.max(a, b);
        return NumeroTicket >= min && NumeroTicket <= max;
    }

    public int getNumeroTicket() { return NumeroTicket; }
    public boolean getRandomA() { return randomA; }
    public boolean getRandomB() { return randomB; }
}