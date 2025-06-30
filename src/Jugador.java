import java.util.*;
import javax.swing.JOptionPane;
import java.io.*;

public class Jugador extends Usuario{

    public Jugador() {
    }

    public void buscarEquipo() {
        List<String> equiposFaltos = new ArrayList<>();
        List<String> nombresEquipos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("equipos.txt"))) {
            String linea;
            StringBuilder bloqueEquipo = new StringBuilder();

            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("ID: ")) {
                    if (bloqueEquipo.length() > 0) {
                        String[] lineas = bloqueEquipo.toString().split("\n");
                        String nombreEquipo = "";
                        int requeridos = 0;
                        int actuales = 0;
                        boolean contandoIntegrantes = false;

                        for (String l : lineas) {
                            if (l.startsWith("Nombre del equipo: ")) {
                                nombreEquipo = l.substring(18).trim();
                            } else if (l.startsWith("Formato: ")) {
                                String formato = l.substring(9).trim();
                                if (formato.length() >= 2 && formato.charAt(0) == 'F') {
                                    try {
                                        requeridos = Integer.parseInt(formato.substring(1, 2));
                                    } catch (NumberFormatException e) {
                                        requeridos = 0;
                                    }
                                }
                            } else if (l.startsWith("Integrantes:")) {
                                contandoIntegrantes = true;
                            } else if (contandoIntegrantes) {
                                if (l.trim().isEmpty() || l.startsWith("ID: ")) {
                                    contandoIntegrantes = false;
                                } else {
                                    actuales++;
                                }
                            }
                        }

                        if (actuales < requeridos && !nombreEquipo.isEmpty()) {
                            equiposFaltos.add(nombreEquipo + " (" + actuales + "/" + requeridos + ")");
                            nombresEquipos.add(nombreEquipo);
                        }

                        bloqueEquipo.setLength(0);
                    }
                }

                bloqueEquipo.append(linea).append("\n");
            }

            // Último bloque
            if (bloqueEquipo.length() > 0) {
                String[] lineas = bloqueEquipo.toString().split("\n");
                String nombreEquipo = "";
                int requeridos = 0;
                int actuales = 0;
                boolean contandoIntegrantes = false;

                for (String l : lineas) {
                    if (l.startsWith("Nombre del equipo: ")) {
                        nombreEquipo = l.substring(18).trim();
                    } else if (l.startsWith("Formato: ")) {
                        String formato = l.substring(9).trim();
                        if (formato.length() >= 2 && formato.charAt(0) == 'F') {
                            try {
                                requeridos = Integer.parseInt(formato.substring(1, 2));
                            } catch (NumberFormatException e) {
                                requeridos = 0;
                            }
                        }
                    } else if (l.startsWith("Integrantes:")) {
                        contandoIntegrantes = true;
                    } else if (contandoIntegrantes) {
                        if (l.trim().isEmpty()) {
                            contandoIntegrantes = false;
                        } else {
                            actuales++;
                        }
                    }
                }

                if (actuales < requeridos && !nombreEquipo.isEmpty()) {
                    equiposFaltos.add(nombreEquipo + " (" + actuales + "/" + requeridos + ")");
                    nombresEquipos.add(nombreEquipo);
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer equipos.txt");
            e.printStackTrace();
        }

        if (equiposFaltos.isEmpty()) {
            System.out.println("No hay equipos con lugares disponibles.");
        } else {
            System.out.println("Equipos con lugares disponibles:");
            for (int i = 0; i < equiposFaltos.size(); i++) {
                System.out.println((i + 1) + ". " + equiposFaltos.get(i));
            }

            Scanner sc = new Scanner(System.in);
            System.out.print("Seleccione un número para unirse a un equipo: ");
            if (sc.hasNextInt()) {
                int opcion = sc.nextInt();
                sc.nextLine(); // limpiar buffer

                if (opcion >= 1 && opcion <= nombresEquipos.size()) {
                    String equipoSeleccionado = nombresEquipos.get(opcion - 1);
                    unirseEquipo(equipoSeleccionado); //
                } else {
                    System.out.println("Opción inválida.");
                }
            } else {
                System.out.println("Entrada inválida.");
            }
        }
    }


    public void unirseEquipo(String nombreEquipo) {
        if (dniUsuarioLogueado == null) {
            System.out.println("Primero debes iniciar sesión.");
            return;
        }

        String dni = dniUsuarioLogueado;

        File archivoOriginal = new File("equipos.txt");
        File archivoTemp = new File("equipos_temp.txt");

        boolean yaInscripto = false;
        boolean agregado = false;

        try (
                BufferedReader br = new BufferedReader(new FileReader(archivoOriginal));
                PrintWriter pw = new PrintWriter(new FileWriter(archivoTemp))
        ) {
            String linea;
            StringBuilder bloqueEquipo = new StringBuilder();

            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("ID: ")) {
                    if (bloqueEquipo.length() > 0) {
                        String bloque = bloqueEquipo.toString();
                        if (bloque.contains("Nombre del equipo: " + nombreEquipo)) {
                            if (bloque.contains(dni)) {
                                System.out.println("Ya estás registrado en este equipo.");
                                yaInscripto = true;
                                pw.print(bloque);
                            } else {
                                StringBuilder nuevoBloque = new StringBuilder();
                                boolean insertado = false;

                                for (String l : bloque.split("\n")) {
                                    nuevoBloque.append(l).append("\n");
                                    if (l.trim().equals("Integrantes:") && !insertado) {
                                        nuevoBloque.append(dni).append("\n");
                                        insertado = true;
                                    }
                                }

                                pw.print(nuevoBloque.toString());
                                System.out.println("Te has unido correctamente al equipo.");
                                agregado = true;
                            }
                        } else {
                            pw.print(bloque);
                        }
                        bloqueEquipo.setLength(0);
                    }
                }
                bloqueEquipo.append(linea).append("\n");
            }

            // Último bloque
            if (bloqueEquipo.length() > 0) {
                String bloque = bloqueEquipo.toString();
                if (bloque.contains("Nombre del equipo: " + nombreEquipo)) {
                    if (bloque.contains(dni)) {
                        System.out.println("Ya estás registrado en este equipo.");
                        yaInscripto = true;
                        pw.print(bloque);
                    } else {
                        StringBuilder nuevoBloque = new StringBuilder();
                        boolean insertado = false;

                        for (String l : bloque.split("\n")) {
                            nuevoBloque.append(l).append("\n");
                            if (l.trim().equals("Integrantes:") && !insertado) {
                                nuevoBloque.append(dni).append("\n");
                                insertado = true;
                            }
                        }

                        pw.print(nuevoBloque.toString());
                        System.out.println("Te has unido correctamente al equipo.");
                        agregado = true;
                    }
                } else {
                    pw.print(bloque);
                }
            }

            if (archivoOriginal.delete()) {
                archivoTemp.renameTo(archivoOriginal);
            }

        } catch (IOException e) {
            System.out.println("Error al modificar el archivo: " + e.getMessage());
        }
    }
}
