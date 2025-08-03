import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainJuego {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Entrenador entrenador1 = new Entrenador();
        Entrenador entrenador2 = new Entrenador();

        List<Digimon> digimonsDisponibles = crearDigimons();

        boolean nombresSeteados = false;
        boolean equipo1Listo = false;
        boolean equipo2Listo = false;

        int opcion;

        do {
            System.out.println("\n--- MENÚ DE JUEGO ---");
            System.out.println("1. Ingresar nombres de entrenadores");
            System.out.println("2. Elegir Digimon para entrenador 1");
            System.out.println("3. Elegir Digimon para entrenador 2");
            System.out.println("4. Mostrar resumen de selección");
            System.out.println("5. Iniciar combate");
            System.out.println("6. Salir");
            System.out.print("Elija una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();  

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre del Entrenador 1: ");
                    String nombre1 = scanner.nextLine();
                    entrenador1.setNombreEntrenador(nombre1);

                    System.out.print("Ingrese el nombre del Entrenador 2: ");
                    String nombre2 = scanner.nextLine();
                    entrenador2.setNombreEntrenador(nombre2);

                    nombresSeteados = true;
                    System.out.println("Nombres guardados");
                    break;

                case 2:
                    if (!nombresSeteados) {
                        System.out.println("Por favor ingrese los nombres de los entrenadores (opción 1).");
                        break;
                    }
                    entrenador1.getEquipoDigimon().clear();
                    System.out.println("\nListado de Digimon disponibles:");
                    for (int i = 0; i < digimonsDisponibles.size(); i++) {
                        Digimon d = digimonsDisponibles.get(i);
                        System.out.printf("%d. %s | Tipo: %s | Ataque: %d | Defensa: %d | Evolución: %s\n",
                                i,
                                d.getNombreDigimon(),
                                d.getTipoElemento(),
                                d.getPoderAtaqueBase(),
                                d.getPoderDefensaBase(),
                                d.getDigievolucion().getNombreFase());
                    }
                    System.out.println(entrenador1.getNombreEntrenador() + ", elige tus 3 Digimon ingresando los números correspondientes:");
                    for (int i = 0; i < 3; i++) {
                        System.out.print("Digimon #" + (i + 1) + ": ");
                        int opcionDig = scanner.nextInt();
                        if (opcionDig < 0 || opcionDig >= digimonsDisponibles.size()) {
                            System.out.println("Opción inválida. Intenta de nuevo.");
                            i--;
                        } else {
                            entrenador1.agregarDigimon(digimonsDisponibles.get(opcionDig));
                        }
                    }
                    equipo1Listo = true;
                    System.out.println("Equipo del entrenador 1 guardado.");
                    break;

                case 3:
                    if (!nombresSeteados) {
                        System.out.println("Primero ingrese los nombres de los entrenadores (opción 1).");
                        break;
                    }
                    entrenador2.getEquipoDigimon().clear();
                    System.out.println("\nListado de Digimon disponibles:");
                    for (int i = 0; i < digimonsDisponibles.size(); i++) {
                        Digimon d = digimonsDisponibles.get(i);
                        System.out.printf("%d. %s | Tipo: %s | Ataque: %d | Defensa: %d | Evolución: %s\n",
                                i,
                                d.getNombreDigimon(),
                                d.getTipoElemento(),
                                d.getPoderAtaqueBase(),
                                d.getPoderDefensaBase(),
                                d.getDigievolucion().getNombreFase());
                    }
                    System.out.println(entrenador2.getNombreEntrenador() + ", elige tus 3 Digimon ingresando los números correspondientes:");
                    for (int i = 0; i < 3; i++) {
                        System.out.print("Digimon #" + (i + 1) + ": ");
                        int opcionDig = scanner.nextInt();
                        if (opcionDig < 0 || opcionDig >= digimonsDisponibles.size()) {
                            System.out.println("Opción inválida. Intenta de nuevo.");
                            i--;
                        } else {
                            entrenador2.agregarDigimon(digimonsDisponibles.get(opcionDig));
                        }
                    }
                    equipo2Listo = true;
                    System.out.println("Equipo del entrenador 2 guardado.");
                    break;

                case 4:
                    if (!equipo1Listo || !equipo2Listo) {
                        System.out.println("Ambos entrenadores deben seleccionar sus Digimon primero (opciones 2 y 3).");
                        break;
                    }
                    System.out.println("\nResumen de selección:");

                    System.out.println(entrenador1.getNombreEntrenador() + " eligió:");
                    for (Digimon d : entrenador1.getEquipoDigimon()) {
                        System.out.printf("- %s | Tipo: %s | Ataque: %d | Defensa: %d | Evolución: %s\n",
                            d.getNombreDigimon(),
                            d.getTipoElemento(),
                            d.getPoderAtaqueBase(),
                            d.getPoderDefensaBase(),
                            d.getDigievolucion().getNombreFase());
                    }

                    System.out.println(entrenador2.getNombreEntrenador() + " eligió:");
                    for (Digimon d : entrenador2.getEquipoDigimon()) {
                        System.out.printf("- %s | Tipo: %s | Ataque: %d | Defensa: %d | Evolución: %s\n",
                            d.getNombreDigimon(),
                            d.getTipoElemento(),
                            d.getPoderAtaqueBase(),
                            d.getPoderDefensaBase(),
                            d.getDigievolucion().getNombreFase());
                    }
                    break;

                case 5:
                    if (!equipo1Listo || !equipo2Listo) {
                        System.out.println("Ambos entrenadores deben seleccionar sus Digimon primero (opciones 2 y 3).");
                        break;
                    }
                    Combate combate = new Combate(entrenador1, entrenador2);
                    String resumen = combate.iniciar();
                    System.out.println("=================================================");
                    System.out.println("\n=====          RESUMEN DEL COMBATE          =====\n" + resumen);
                    System.out.println("=================================================");
                    break;

                case 6:
                    System.out.println("¡Gracias por jugar!");
                    break;

                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }

        } while (opcion != 6);

        scanner.close();
    }

    private static List<Digimon> crearDigimons() {
        List<Digimon> lista = new ArrayList<>();

        Digievolucion d1evo = new Digievolucion();
        d1evo.setNombreFase("Adult");
        d1evo.setTipoEfecto("ataque");
        d1evo.setValorEfecto(15);
        d1evo.setProbabilidadActivar(30);
        Digimon d1 = new Digimon();
        d1.setNombreDigimon("Br Br Patapin");
        d1.setTipoElemento("Fuego");
        d1.setPoderAtaqueBase(10);
        d1.setPoderDefensaBase(5);
        d1.setDigievolucion(d1evo);
        lista.add(d1);

        Digievolucion d2evo = new Digievolucion();
        d2evo.setNombreFase("Mega");
        d2evo.setTipoEfecto("defensa");
        d2evo.setValorEfecto(20);
        d2evo.setProbabilidadActivar(25);
        Digimon d2 = new Digimon();
        d2.setNombreDigimon("Tum Tum Sahur");
        d2.setTipoElemento("Fuego");
        d2.setPoderAtaqueBase(9);
        d2.setPoderDefensaBase(6);
        d2.setDigievolucion(d2evo);
        lista.add(d2);

        Digievolucion d3evo = new Digievolucion();
        d3evo.setNombreFase("Ultimate");
        d3evo.setTipoEfecto("daño");
        d3evo.setValorEfecto(10);
        d3evo.setProbabilidadActivar(20);
        Digimon d3 = new Digimon();
        d3.setNombreDigimon("Bombardilo");
        d3.setTipoElemento("Agua");
        d3.setPoderAtaqueBase(6);
        d3.setPoderDefensaBase(9);
        d3.setDigievolucion(d3evo);
        lista.add(d3);

        Digievolucion d4evo = new Digievolucion();
        d4evo.setNombreFase("Adult");
        d4evo.setTipoEfecto("ataque");
        d4evo.setValorEfecto(15);
        d4evo.setProbabilidadActivar(30);
        Digimon d4 = new Digimon();
        d4.setNombreDigimon("Liri Liri Lira");
        d4.setTipoElemento("Planta");
        d4.setPoderAtaqueBase(8);
        d4.setPoderDefensaBase(8);
        d4.setDigievolucion(d4evo);
        lista.add(d4);

        Digievolucion d5evo = new Digievolucion();
        d5evo.setNombreFase("Mega");
        d5evo.setTipoEfecto("defensa");
        d5evo.setValorEfecto(20);
        d5evo.setProbabilidadActivar(25);
        Digimon d5 = new Digimon();
        d5.setNombreDigimon("Capibalero Cococini");
        d5.setTipoElemento("Planta");
        d5.setPoderAtaqueBase(7);
        d5.setPoderDefensaBase(9);
        d5.setDigievolucion(d5evo);
        lista.add(d5);

        Digievolucion d6evo = new Digievolucion();
        d6evo.setNombreFase("Ultimate");
        d6evo.setTipoEfecto("daño");
        d6evo.setValorEfecto(10);
        d6evo.setProbabilidadActivar(20);
        Digimon d6 = new Digimon();
        d6.setNombreDigimon("3.14 k chu");
        d6.setTipoElemento("Eléctrico");
        d6.setPoderAtaqueBase(12);
        d6.setPoderDefensaBase(4);
        d6.setDigievolucion(d6evo);
        lista.add(d6);

        Digievolucion d7evo = new Digievolucion();
        d7evo.setNombreFase("Mega");
        d7evo.setTipoEfecto("defensa");
        d7evo.setValorEfecto(20);
        d7evo.setProbabilidadActivar(25);
        Digimon d7 = new Digimon();
        d7.setNombreDigimon("Tripi Tropi");
        d7.setTipoElemento("Fuego");
        d7.setPoderAtaqueBase(11);
        d7.setPoderDefensaBase(6);
        d7.setDigievolucion(d7evo);
        lista.add(d7);

        Digievolucion d8evo = new Digievolucion();
        d8evo.setNombreFase("Adult");
        d8evo.setTipoEfecto("ataque");
        d8evo.setValorEfecto(15);
        d8evo.setProbabilidadActivar(30);
        Digimon d8 = new Digimon();
        d8.setNombreDigimon("Saturno Saturnita");
        d8.setTipoElemento("Eléctrico");
        d8.setPoderAtaqueBase(10);
        d8.setPoderDefensaBase(7);
        d8.setDigievolucion(d8evo);
        lista.add(d8);

        Digievolucion d9evo = new Digievolucion();
        d9evo.setNombreFase("Ultimate");
        d9evo.setTipoEfecto("daño");
        d9evo.setValorEfecto(10);
        d9evo.setProbabilidadActivar(20);
        Digimon d9 = new Digimon();
        d9.setNombreDigimon("Tralalero Tralala");
        d9.setTipoElemento("Agua");
        d9.setPoderAtaqueBase(7);
        d9.setPoderDefensaBase(8);
        d9.setDigievolucion(d9evo);
        lista.add(d9);

        Digievolucion d10evo = new Digievolucion();
        d10evo.setNombreFase("Mega");
        d10evo.setTipoEfecto("defensa");
        d10evo.setValorEfecto(20);
        d10evo.setProbabilidadActivar(25);
        Digimon d10 = new Digimon();
        d10.setNombreDigimon("Wormmon");
        d10.setTipoElemento("Planta");
        d10.setPoderAtaqueBase(9);
        d10.setPoderDefensaBase(7);
        d10.setDigievolucion(d10evo);
        lista.add(d10);

        return lista;
    }
}
