import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Capitan extends Usuario {
    public Capitan() {

    }

    public void crearEquipo() {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> rangoEdad = new ArrayList<>();

        sc.nextLine();

        System.out.println("Ingrese tu DNI:");
        int dniCapitan = sc.nextInt();
        sc.nextLine();

        System.out.println("Ingresa el nombre del equipo:");
        String nombreEquipo = sc.nextLine();

        System.out.println("Ingresa la cantidad cantidad integrantes:");
        int numeroIntegrantes = sc.nextInt();
        sc.nextLine();

        System.out.println("Ingrese tipo de equipo(Mixto, Masculino, Femenino):");
        String tipoEquipo = sc.nextLine();

        System.out.println("preferencias:");
        String preferencias = sc.nextLine();

        System.out.println("Integrantes:");
        int integrantes = sc.nextInt();
        sc.nextLine();

        /// Inicio creado de rango de edad (Minimo y Maximo)

        int edadMin, edadMax;

        while (true) {
            System.out.print("Ingrese la edad mínima (0-99): ");
            edadMin = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            System.out.print("Ingrese la edad máxima (0-99): ");
            edadMax = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            if (edadMin < 0 || edadMin > 99 || edadMax < 0 || edadMax > 99) {
                System.out.println("Las edades deben estar entre 0 y 99. Intente nuevamente.\n");
            } else if (edadMin > edadMax) {
                System.out.println("La edad mínima no puede ser mayor que la máxima. Intente nuevamente.\n");
            } else {
                break; // los datos son válidos, salimos del bucle
            }
        }

        rangoEdad.add(String.valueOf(edadMin));
        rangoEdad.add(String.valueOf(edadMax));

        System.out.println("Rango de edades guardado: " + rangoEdad);

    }

    public void borrarEquipo() {

    }

    public void inscripcion() {

    }
}
