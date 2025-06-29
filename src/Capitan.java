import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;
import java.io.*;

public class Capitan extends Usuario {
    public Capitan() {

    }

    public void crearEquipo() {
        Scanner sc = new Scanner(System.in);

        /// Listas para el min y max de rango de edad y integrantes de cada grupo
        ArrayList<String> integrantes = new ArrayList<>();
        ArrayList<String> rangoEdad = new ArrayList<>();

        sc.nextLine();

        System.out.println("Ingresa tu DNI:");
        int dniCapitan = sc.nextInt();
        integrantes.add(String.valueOf(dniCapitan));
        sc.nextLine();

        System.out.println("Ingresa el nombre del equipo:");
        String nombreEquipo = sc.nextLine();


        /// Selección de formato (F5, F7, F8, F11)
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
        int cantidadEsperada = Integer.parseInt(numeroIntegrantes.substring(1)) - 1;

        /// Leer DNIS del archivo usuarios
        Set<String> usuariosRegistrados = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");

                if (partes.length >= 6) {
                    String dniu = partes[5].trim();
                    usuariosRegistrados.add(dniu); // Guardamos el DNI como
                } else {
                    System.out.println("Línea malformada en usuarios.txt: " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de usuarios.");
            e.printStackTrace();
            return;
        }

        // Pedir los DNI y validarlos contra el archivo
        for (int i = 0; i < cantidadEsperada; ) {
            System.out.print("Ingrese el DNI del integrante (Recuerda que tu ya eres uno de ellos.) #" + (i + 1) + ": ");
            String dni = sc.nextLine().trim();

            if (integrantes.contains(dni)) {
                System.out.println("Ese DNI ya fue ingresado como integrante. Por favor, ingrese uno diferente.");
            } else if (usuariosRegistrados.contains(dni)) {
                integrantes.add(dni);
                i++; // solo avanzar si es válido y no repetido
            } else {
                System.out.println("Lo siento, esa persona no se ha registrado en el sitio aun!");
            }
        }

        // Muestra final (opcional)
        System.out.println("Integrantes registrados correctamente:");
        for (String dni : integrantes) {
            System.out.println("- " + dni);
        }


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
