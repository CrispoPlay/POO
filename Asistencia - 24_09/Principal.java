import java.util.Scanner;
public class Principal {
    public static void main(String[] args){
        Scanner teclado = new Scanner(System.in);
        Universidad uvg = new Universidad();
        
        System.out.println("Ingrese el nombre de la universidad: ");
        uvg.setNombre(teclado.nextLine());  
        System.out.println("Ingrese la direccion de la universidad: ");
        uvg.setDireccion(teclado.nextLine());
        System.out.println("Ingrese el telefono de la universidad: ");  
        uvg.setTelefono(teclado.nextLine());

        int aniosAntiguedad = 0;
        int gradocientifico = 0;
        int feriadosTrabajados = 0;
        String gc = "Ninguno";
        
        String rpta = "s";
        while (rpta.equals("s")){
            System.out.println("Ingrese el nombre del empleado: ");
            String nombre = teclado.nextLine();
            System.out.println("Ingrese el NIT del empleado: ");
            String nit = teclado.nextLine();
            System.out.println("Ingrese la direccion del empleado: ");
            String direccion = teclado.nextLine();
            System.out.println("Ingrese el salario base del empleado: ");
            double salarioBase = Double.parseDouble(teclado.nextLine());
            System.out.println();
            System.out.println("Ingrese las horas de ausencia del empleado: ");
            int horasAusencia = Integer.parseInt(teclado.nextLine());
            System.out.println();
            System.out.println("Ingrese el tipo de trabajador (1 para Docente, 2 para NoDocente): ");
            int tipoTrabajador = Integer.parseInt(teclado.nextLine());
            System.out.println();
            if (tipoTrabajador == 1){
                System.out.println("Ingrese los años de antiguedad del empleado: ");
                aniosAntiguedad = Integer.parseInt(teclado.nextLine());
                System.out.println();
                System.out.println("Ingrese el grado cientifico del empleado (1: Ninguno,2: Maestria, 3: Doctorado): ");
                gradocientifico = Integer.parseInt(teclado.nextLine());
                System.out.println();
                switch (gradocientifico) {
                    case 1:
                        gc = "Ninguno";
                        break;
                    case 2:
                        gc = "Master";
                        break;
                    case 3:
                        gc = "Doctor";
                        break;
                }
            }
            else
                if (tipoTrabajador == 2){
                    System.out.println("Ingrese los feriados trabajados del empleado: ");
                    feriadosTrabajados = Integer.parseInt(teclado.nextLine());
                    System.out.println();
                }
                else{
                    System.out.println("Tipo de trabajador no valido. Intente de nuevo.");
                }
            
            if(tipoTrabajador == 1 || tipoTrabajador == 2)
                uvg.agregarEmpleados(nombre, nit, direccion, salarioBase, horasAusencia, aniosAntiguedad, gc, feriadosTrabajados, tipoTrabajador);
            
            System.out.println("¿Desea agregar otro empleado? (s/n): ");
            rpta = teclado.nextLine();  
        }
    double nominaTotal = uvg.calcularNomina();
    double nominaDocente = uvg.salarioTotalDocente();
    double nominaNoDocente = uvg.salarioTotalNoDocente();

    System.out.println("La nomina total de la universidad es: " + nominaTotal);
    System.out.println("La nomina total de los docentes es: " + nominaDocente);
    System.out.println("La nomina total de los no docentes es: " + nominaNoDocente);
    }
}
