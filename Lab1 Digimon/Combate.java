import java.util.List;

public class Combate {
    private Entrenador entrenador1;
    private Entrenador entrenador2;

    public Combate(Entrenador e1, Entrenador e2) {
        this.entrenador1 = e1;
        this.entrenador2 = e2;
    }

public String iniciar() {
    StringBuilder resumen = new StringBuilder();

    List<Digimon> equipo1 = entrenador1.getEquipoDigimon();
    List<Digimon> equipo2 = entrenador2.getEquipoDigimon();

    int victoriasEntrenador1 = 0;
    int victoriasEntrenador2 = 0;

    for (int i = 0; i < 3; i++) {
        Digimon d1 = equipo1.get(i);
        Digimon d2 = equipo2.get(i);

        d1.intentarEvolucion();
        d2.intentarEvolucion();

        int poder1 = d1.calcularAtaqueTotal();
        int poder2 = d2.calcularDefensaTotal();

        resumen.append(entrenador1.getNombreEntrenador())
                .append(" usa a ")
                .append(d1.toString())
                .append("\n");

        resumen.append(entrenador2.getNombreEntrenador())
                .append(" usa a ")
                .append(d2.toString())
                .append("\n");

        if (poder1 > poder2) {
            resumen.append("Resultado: ").append(d1.getNombreDigimon()).append(" gana la ronda!\n\n");
            victoriasEntrenador1++;
        } else if (poder2 > poder1) {
            resumen.append("Resultado: ").append(d2.getNombreDigimon()).append(" gana la ronda!\n\n");
            victoriasEntrenador2++;
        } else {
            resumen.append("Resultado: Empate en la ronda!\n\n");
        }
    }

    resumen.append("===== RESULTADO FINAL =====\n");
    resumen.append(entrenador1.getNombreEntrenador()).append(" ganó ").append(victoriasEntrenador1).append(" rondas.\n");
    resumen.append(entrenador2.getNombreEntrenador()).append(" ganó ").append(victoriasEntrenador2).append(" rondas.\n");

    if (victoriasEntrenador1 > victoriasEntrenador2) {
        resumen.append("¡").append(entrenador1.getNombreEntrenador()).append(" es el ganador del combate!\n");
    } else if (victoriasEntrenador2 > victoriasEntrenador1) {
        resumen.append("¡").append(entrenador2.getNombreEntrenador()).append(" es el ganador del combate!\n");
    } else {
        resumen.append("El combate terminó en empate.\n");
    }

    return resumen.toString();
    }
    
}
