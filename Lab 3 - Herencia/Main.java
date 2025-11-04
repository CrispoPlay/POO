import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Driver program con menú por consola.
 * Aquí están permitidos Scanner y prints (cumpliendo la restricción del laboratorio).
 */
public class Main {
    public static void main(String[] args) {
        HospitalManager manager = new HospitalManager();
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        int opcion = -1;
        do {
            System.out.println("=== Sistema Hospitalario ===");
            System.out.println("1) Agregar trabajador");
            System.out.println("2) Agregar cita");
            System.out.println("3) Reagendar cita");
            System.out.println("4) Resolver conflictos (intento automático)");
            System.out.println("5) Calcular nómina por departamento");
            System.out.println("6) Listar trabajadores");
            System.out.println("7) Listar citas");
            System.out.println("0) Salir");
            System.out.print("Ingrese opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    System.out.print("Tipo (DoctorGeneral/Cirujano/Enfermero/Radiologo/Farmaceutico/Terapeuta): ");
                    String tipo = sc.nextLine().trim();
                    System.out.print("ID: ");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Departamento: ");
                    String dept = sc.nextLine();
                    System.out.print("Años experiencia: ");
                    int anios = Integer.parseInt(sc.nextLine());
                    System.out.print("Salario base: ");
                    double base = Double.parseDouble(sc.nextLine());

                    switch (tipo) {
                        case "DoctorGeneral":
                            System.out.print("Especialización: ");
                            String esp = sc.nextLine();
                            System.out.print("Pacientes por día: ");
                            int ppd = Integer.parseInt(sc.nextLine());
                            System.out.print("Tarifa por consulta: ");
                            double tarifa = Double.parseDouble(sc.nextLine());
                            manager.agregarTrabajador(new DoctorGeneral(id,nombre,dept,anios,base,esp,ppd,tarifa));
                            break;
                        case "Cirujano":
                            System.out.print("Horas de cirugía: ");
                            int horas = Integer.parseInt(sc.nextLine());
                            System.out.print("Bono de riesgo: ");
                            double bono = Double.parseDouble(sc.nextLine());
                            System.out.print("Tarifa por hora: ");
                            double tarifaH = Double.parseDouble(sc.nextLine());
                            manager.agregarTrabajador(new Cirujano(id,nombre,dept,anios,base,null,horas,bono,tarifaH));
                            break;
                        case "Enfermero":
                            System.out.print("Turno (diurno/nocturno): ");
                            String turno = sc.nextLine();
                            System.out.print("Nivel de certificación: ");
                            String cert = sc.nextLine();
                            System.out.print("Bono nocturno: ");
                            double bNoct = Double.parseDouble(sc.nextLine());
                            manager.agregarTrabajador(new Enfermero(id,nombre,dept,anios,base,turno,cert,bNoct));
                            break;
                        case "Radiologo":
                            System.out.print("Tarifa por estudio: ");
                            double tarifaE = Double.parseDouble(sc.nextLine());
                            System.out.print("Estudios realizados: ");
                            int estudios = Integer.parseInt(sc.nextLine());
                            manager.agregarTrabajador(new Radiologo(id,nombre,dept,anios,base,null,tarifaE,estudios));
                            break;
                        case "Farmaceutico":
                            System.out.print("Límite prescripciones por día: ");
                            int limite = Integer.parseInt(sc.nextLine());
                            System.out.print("Licencia sustancias controladas (true/false): ");
                            boolean licencia = Boolean.parseBoolean(sc.nextLine());
                            manager.agregarTrabajador(new Farmaceutico(id,nombre,dept,anios,base,limite,licencia));
                            break;
                        case "Terapeuta":
                            System.out.print("Tipo de terapia: ");
                            String tt = sc.nextLine();
                            System.out.print("Duración promedio sesión (min): ");
                            int dur = Integer.parseInt(sc.nextLine());
                            manager.agregarTrabajador(new Terapeuta(id,nombre,dept,anios,base,tt,dur));
                            break;
                        default:
                            System.out.println("Tipo no reconocido.");
                    }
                    System.out.println("Trabajador agregado.");
                    break;
                case 2:
                    System.out.print("ID cita: ");
                    int idC = Integer.parseInt(sc.nextLine());
                    System.out.print("Paciente: ");
                    String paciente = sc.nextLine();
                    System.out.print("Tipo de cita: ");
                    String tipoC = sc.nextLine();
                    System.out.print("Fecha y hora (yyyy-MM-dd HH:mm): ");
                    String fechaStr = sc.nextLine();
                    LocalDateTime fecha = LocalDateTime.parse(fechaStr, fmt);
                    System.out.print("Tipo de trabajador requerido (DoctorGeneral, Cirujano, ...): ");
                    String tipoReq = sc.nextLine();
                    TrabajadorMedico asignado = manager.asignarPersonal(tipoReq, fecha);
                    CitaMedica cita = new CitaMedica(idC, paciente, asignado, fecha, tipoC, EstadoCita.PROGRAMADA);
                    manager.agregarCita(cita);
                    System.out.println("Cita creada. Asignado: " + (asignado==null? "Nadie": asignado.getNombre()));
                    break;
                case 3:
                    System.out.print("ID cita a reagendar: ");
                    int idRe = Integer.parseInt(sc.nextLine());
                    System.out.print("Nueva fecha y hora (yyyy-MM-dd HH:mm): ");
                    String f2 = sc.nextLine();
                    LocalDateTime nf = LocalDateTime.parse(f2, fmt);
                    System.out.print("Motivo: ");
                    String motivo = sc.nextLine();
                    boolean ok = manager.reagendarCita(idRe, nf, motivo);
                    System.out.println("Reagendamiento " + (ok? "exitoso":"fallido (conflicto/no existente)"));
                    break;
                case 4:
                    List<String> cambios = manager.resolverConflictos();
                    System.out.println("Resultados resolución:");
                    for (String s : cambios) System.out.println("- " + s);
                    break;
                case 5:
                    Map<String, Double> nomina = manager.calcularNominaPorDepartamento();
                    System.out.println("Nómina por departamento:");
                    for (String d : nomina.keySet()) {
                        System.out.println(d + " -> " + String.format("%.2f", nomina.get(d)));
                    }
                    break;
                case 6:
                    System.out.println("Trabajadores registrados:");
                    for (TrabajadorMedico t : manager.getTrabajadores()) {
                        System.out.println(t.toString());
                    }
                    break;
                case 7:
                    System.out.println("Citas registradas:");
                    for (CitaMedica c : manager.getCitas()) {
                        System.out.println(c.toString());
                    }
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
            System.out.println();
        } while (opcion != 0);

        sc.close();
    }
}
