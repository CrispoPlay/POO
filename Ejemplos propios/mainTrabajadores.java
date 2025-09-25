public class mainTrabajadores {
    public static void main(String[] args) {
        Docente profesor = new Docente("Juan","123456","Ciudad de Guatemala", 3000, 3, 20, "Master");
        NoDocente limpieza = new NoDocente("Gabriel","25444", "Fraijanes", 2000,2,5);
        System.out.println("Datos del Profesor: ");
        System.out.println(profesor);
        System.out.println("Datos del Limpieza: ");
        System.out.println(limpieza);

    }
}