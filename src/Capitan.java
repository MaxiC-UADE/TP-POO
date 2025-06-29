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

        ArrayList<String> integrantes = new ArrayList<>();
        ArrayList<String> rangoEdad = new ArrayList<>();

        sc.nextLine();

        System.out.println("Ingresa tu DNI:");
        int dniCapitan = sc.nextInt();
        integrantes.add(String.valueOf(dniCapitan));
        sc.nextLine();

        String nombreEquipo;

        while (true) {
            System.out.println("Ingresa el nombre del equipo:");
            nombreEquipo = sc.nextLine().trim();

            boolean nombreRepetido = false;

            try (BufferedReader br = new BufferedReader(new FileReader("equipos.txt"))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    if (linea.trim().equalsIgnoreCase("Nombre del equipo: " + nombreEquipo)) {
                        nombreRepetido = true;
                        break;
                    }
                }
            } catch (FileNotFoundException e) {
                // Si no existe el archivo, no hay problema, se asume que es el primer equipo
            } catch (IOException e) {
                System.out.println("Error al leer el archivo equipos.txt");
                e.printStackTrace();
                return;
            }

            if (nombreRepetido) {
                System.out.println("Ese nombre ya lo tiene otro equipo, deberás elegir otro:");
            } else {
                break;
            }
        }


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

        int cantidadEsperada = Integer.parseInt(numeroIntegrantes.substring(1)) - 1;

        Set<String> usuariosRegistrados = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length >= 6) {
                    String dniu = partes[5].trim();
                    usuariosRegistrados.add(dniu);
                } else {
                    System.out.println("Línea malformada en usuarios.txt: " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de usuarios.");
            e.printStackTrace();
            return;
        }

        for (int i = 0; i < cantidadEsperada; ) {
            System.out.print("Ingrese el DNI del integrante (Recuerda que tú ya eres uno de ellos) #" + (i + 1) + ": ");
            String dni = sc.nextLine().trim();

            if (integrantes.contains(dni)) {
                System.out.println("Ese DNI ya fue ingresado como integrante. Por favor, ingrese uno diferente.");
            } else if (usuariosRegistrados.contains(dni)) {
                integrantes.add(dni);
                i++;
            } else {
                System.out.println("Lo siento, esa persona no se ha registrado en el sitio aún.");
            }
        }

        System.out.println("Integrantes registrados correctamente:");
        for (String dni : integrantes) {
            System.out.println("- " + dni);
        }

        String tipoEquipo;
        while (true) {
            System.out.println("Ingrese tipo de equipo (Mixto, Masculino, Femenino):");
            tipoEquipo = sc.nextLine();
            String tipoEquipoLower = tipoEquipo.toLowerCase();

            if (tipoEquipoLower.equals("mixto") || tipoEquipoLower.equals("masculino") || tipoEquipoLower.equals("femenino")) {
                break;
            } else {
                System.out.println("Opción inválida. Por favor ingrese Mixto, Masculino o Femenino.");
            }
        }

        System.out.println("Preferencias:"); /// PREFERENCIAS HACER BETO O GONZA
        String preferencias = sc.nextLine();

        int edadMin, edadMax;
        while (true) {
            System.out.print("Ingrese la edad mínima (0-99): ");
            edadMin = sc.nextInt();
            sc.nextLine();

            System.out.print("Ingrese la edad máxima (0-99): ");
            edadMax = sc.nextInt();
            sc.nextLine();

            if (edadMin < 0 || edadMin > 99 || edadMax < 0 || edadMax > 99) {
                System.out.println("Las edades deben estar entre 0 y 99. Intente nuevamente.\n");
            } else if (edadMin > edadMax) {
                System.out.println("La edad mínima no puede ser mayor que la máxima. Intente nuevamente.\n");
            } else {
                break;
            }
        }

        rangoEdad.add(String.valueOf(edadMin));
        rangoEdad.add(String.valueOf(edadMax));

        System.out.println("Rango de edades guardado: " + rangoEdad);

        /// GUARDADO EN ARCHIVO
        String idEquipo = "E1"; // valor por defecto
        int contadorEquipos = 0;

        /// Calcular el número de equipo leyendo el archivo
        try (BufferedReader br = new BufferedReader(new FileReader("equipos.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("ID: E")) {
                    contadorEquipos++;
                }
            }
            idEquipo = "E" + (contadorEquipos + 1);
        } catch (FileNotFoundException e) {
            // Primer equipo, archivo aún no existe
            idEquipo = "E1";
        } catch (IOException e) {
            System.out.println("Error al leer el archivo equipos.txt para calcular el ID.");
            e.printStackTrace();
            return;
        }

        /// Guardar el equipo en el archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("equipos.txt", true))) {
            writer.write("ID: " + idEquipo + "\n");
            writer.write("Nombre del equipo: " + nombreEquipo + "\n");
            writer.write("Formato: " + numeroIntegrantes + " (" + integrantes.size() + " integrantes)\n");
            writer.write("Tipo de equipo: " + tipoEquipo + "\n");
            writer.write("Preferencias: " + preferencias + "\n");
            writer.write("Rango de edad: " + edadMin + "-" + edadMax + "\n");
            writer.write("Integrantes:\n");
            for (int i = 0; i < integrantes.size(); i++) {
                writer.write("  " + (i + 1) + ". DNI: " + integrantes.get(i) + (i == 0 ? " (Capitán)" : "") + "\n");
            }
            writer.write("--------------------------------------------------\n");
            System.out.println("¡Equipo guardado exitosamente en 'equipos.txt' como " + idEquipo + "!\n");
        } catch (IOException e) {
            System.out.println("Error al guardar el equipo en el archivo.");
            e.printStackTrace();
        }
    }


    public void borrarEquipo() {

    }

    public void inscripcion() {

    }
}
