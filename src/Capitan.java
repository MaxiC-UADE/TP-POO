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

        System.out.println("Ingresa tu DNI:");
        int dniCapitan = sc.nextInt();
        sc.nextLine();

        System.out.println("Ingresa el nombre del equipo:");
        String nombreEquipo = sc.nextLine();


        // Selección de formato (F5, F7, F8, F11)
        String numeroIntegrantes;
        while (true) {
            System.out.println("Ingresa la cantidad de integrantes (F5, F7, F8, F11):");
            numeroIntegrantes = sc.nextLine().toUpperCase();

            if (numeroIntegrantes.equals("F5") || numeroIntegrantes.equals("F7") ||
                    numeroIntegrantes.equals("F8") || numeroIntegrantes.equals("F11")) {
                break;
            } else {
                System.out.println("Opción inválida. Solo se permite: F5, F7, F8, F11.");
            }
        }

        // Extraer número del formato (ej: de "F5" obtener 5)
        int cantidadEsperada = Integer.parseInt(numeroIntegrantes.substring(1));

        // Validar que la cantidad de integrantes coincida
        int integrantes;
        while (true) {
            System.out.println("Integrantes:");
            if (sc.hasNextInt()) {
                integrantes = sc.nextInt();
                sc.nextLine();

                if (integrantes == cantidadEsperada) {
                    break;
                } else {
                    System.out.println("Debes ingresar exactamente " + cantidadEsperada + " integrantes.");
                }
            } else {
                System.out.println("Por favor, ingresa un número válido.");
                sc.nextLine();
            }
        }

        System.out.println("Formato elegido: " + numeroIntegrantes);
        System.out.println("Integrantes ingresados correctamente: " + integrantes);


        /// Inicio Tipo de equipo
        String tipoEquipo;

        while (true) {
            System.out.println("Ingrese tipo de equipo (Mixto, Masculino, Femenino):");
            tipoEquipo = sc.nextLine();

            /// Convertimos a minúsculas para comparar sin importar mayúsculas/minúsculas
            String tipoEquipoLower = tipoEquipo.toLowerCase();

            if (tipoEquipoLower.equals("mixto") || tipoEquipoLower.equals("masculino") || tipoEquipoLower.equals("femenino")) {
                break; // Salir del bucle si es válido
            } else {
                System.out.println("Opción inválida. Por favor ingrese Mixto, Masculino o Femenino.");
            }
        }
        /// Fin Tipo de equipo



        System.out.println("preferencias:");
        String preferencias = sc.nextLine();
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
        /// Fin creado de rango de edad (Minimo y Maximo)

    }

    public void borrarEquipo() {

    }

    public void inscripcion() {

    }
}
