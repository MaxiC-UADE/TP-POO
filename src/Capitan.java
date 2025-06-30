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

        // --- Aquí pedimos la zona y validamos el horario ---
        System.out.println("Ingrese la zona donde quieren jugar habitualmente:");
        String zona = sc.nextLine().trim();

        String horario;
        while (true) {
            System.out.println("Ingrese un horario cómodo para el equipo (formato 24h HH:mm, ejemplo 18:30):");
            horario = sc.nextLine().trim();

            if (horario.matches("^([01]\\d|2[0-3]):([0-5]\\d)$")) {
                break;
            } else {
                System.out.println("Formato inválido. Por favor ingrese el horario en formato HH:mm (ejemplo: 18:30).");
            }
        }

        String preferencias = "Zona: " + zona + "; Horario: " + horario;

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
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el nombre del equipo que desea borrar: ");
        String nombreEquipo = sc.nextLine().trim();

        System.out.print("Ingrese su DNI para verificar si es el capitán: ");
        String dniUsuario = sc.nextLine().trim();

        File archivoOriginal = new File("equipos.txt");
        File archivoTemporal = new File("equipos_temp.txt");

        boolean equipoEncontrado = false;
        boolean usuarioEsCapitan = false;

        try (
                BufferedReader br = new BufferedReader(new FileReader(archivoOriginal));
                PrintWriter pw = new PrintWriter(new FileWriter(archivoTemporal))
        ) {
            String linea;
            StringBuilder bloqueEquipo = new StringBuilder();

            while ((linea = br.readLine()) != null) {
                // Si detecta un nuevo equipo por la línea que comienza con ID:
                if (linea.startsWith("ID:")) {
                    // Evaluar bloque anterior
                    if (bloqueEquipo.length() > 0) {
                        String bloqueStr = bloqueEquipo.toString();
                        if (bloqueStr.contains("Nombre del equipo: " + nombreEquipo)) {
                            equipoEncontrado = true;
                            // Verificar si el DNI aparece como capitán
                            if (bloqueStr.contains(dniUsuario + " (capitan)")) {
                                usuarioEsCapitan = true;
                                // No se escribe este bloque (se elimina)
                            } else {
                                // No es el capitán, no eliminar
                                pw.print(bloqueStr);
                            }
                        } else {
                            pw.print(bloqueStr);
                        }
                        bloqueEquipo.setLength(0);
                    }
                }
                bloqueEquipo.append(linea).append(System.lineSeparator());
            }

            // Evaluar último bloque
            if (bloqueEquipo.length() > 0) {
                String bloqueStr = bloqueEquipo.toString();
                if (bloqueStr.contains("Nombre del equipo: " + nombreEquipo)) {
                    equipoEncontrado = true;
                    if (bloqueStr.contains(dniUsuario + " (capitan)")) {
                        usuarioEsCapitan = true;
                        // No escribir el bloque
                    } else {
                        pw.print(bloqueStr);
                    }
                } else {
                    pw.print(bloqueStr);
                }
            }

            // Reemplazar archivo original si corresponde
            if (archivoOriginal.delete()) {
                archivoTemporal.renameTo(archivoOriginal);
            }

            if (!equipoEncontrado) {
                System.out.println("No se encontró un equipo con ese nombre.");
            } else if (equipoEncontrado && usuarioEsCapitan) {
                System.out.println("El equipo \"" + nombreEquipo + "\" fue eliminado exitosamente.");
            } else {
                System.out.println("Solo el capitán puede eliminar este equipo.");
            }

        } catch (IOException e) {
            System.out.println("Ocurrió un error al procesar el archivo: " + e.getMessage());
        }
    }

    public void inscripcion() {
        Scanner sc = new Scanner(System.in);
        List<List<String>> torneos = new ArrayList<>();

        // Leer torneos.txt y separar en bloques
        try (BufferedReader br = new BufferedReader(new FileReader("torneos.txt"))) {
            String linea;
            List<String> bloqueActual = new ArrayList<>();

            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("-----------------------------")) {
                    torneos.add(new ArrayList<>(bloqueActual));
                    bloqueActual.clear();
                } else {
                    bloqueActual.add(linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer torneos.txt: " + e.getMessage());
            return;
        }

        if (torneos.isEmpty()) {
            System.out.println("No hay torneos disponibles.");
            return;
        }

        // Mostrar torneos
        System.out.println("Torneos disponibles:");
        for (int i = 0; i < torneos.size(); i++) {
            String nombre = torneos.get(i).stream()
                    .filter(l -> l.startsWith("NombreTorneo: "))
                    .findFirst()
                    .map(l -> l.substring(14))
                    .orElse("Torneo sin nombre");
            System.out.println((i + 1) + ") " + nombre);
        }

        // Elegir torneo
        System.out.print("Seleccione un torneo por número: ");
        int opcion = sc.nextInt();
        sc.nextLine();

        if (opcion < 1 || opcion > torneos.size()) {
            System.out.println("Opción inválida.");
            return;
        }

        int torneoIndex = opcion - 1;
        List<String> torneoSeleccionado = torneos.get(torneoIndex);

        System.out.println("\n--- Detalles del Torneo ---");
        for (String linea : torneoSeleccionado) {
            System.out.println(linea);
        }

        // Validar cupo
        int equiposActuales = 0;
        int maxEquipos = 0;

        for (String linea : torneoSeleccionado) {
            if (linea.startsWith("EquipoInscripto: ")) {
                equiposActuales++;
            } else if (linea.startsWith("MaxEquipos: ")) {
                try {
                    maxEquipos = Integer.parseInt(linea.substring(12).trim());
                } catch (NumberFormatException e) {
                    System.out.println("Error: MaxEquipos inválido en el torneo.");
                    return;
                }
            }
        }

        if (equiposActuales >= maxEquipos) {
            System.out.println("Este torneo ya alcanzó el máximo de equipos inscriptos (" + maxEquipos + ").");
            return;
        }

        // Opciones
        System.out.println("\n1) Confirmar inscripción");
        System.out.println("2) Volver atrás");
        System.out.print("Seleccione una opción: ");
        int accion = sc.nextInt();
        sc.nextLine();

        if (accion == 1) {
            System.out.print("Ingrese el nombre del equipo: ");
            String nombreEquipo = sc.nextLine().trim();

            // Validar que no esté repetido
            boolean yaInscripto = torneoSeleccionado.stream()
                    .anyMatch(l -> l.toLowerCase().equals("equipoincripto: " + nombreEquipo.toLowerCase()));

            if (yaInscripto) {
                System.out.println("Ese equipo ya está inscripto en este torneo.");
                return;
            }

            // Agregar equipo
            torneoSeleccionado.add("EquipoInscripto: " + nombreEquipo);

            // Guardar archivo completo
            try (FileWriter fw = new FileWriter("torneos.txt", false)) {
                for (List<String> torneo : torneos) {
                    for (String linea : torneo) {
                        fw.write(linea + "\n");
                    }
                    fw.write("-----------------------------\n");
                }
                System.out.println("Inscripción confirmada y guardada.");
            } catch (IOException e) {
                System.out.println("Error al guardar la inscripción: " + e.getMessage());
            }

        } else if (accion == 2) {
            System.out.println("Volviendo al menú principal...");
        } else {
            System.out.println("Opción no válida.");
        }
    }
}
