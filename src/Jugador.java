import java.util.*;
import javax.swing.JOptionPane;
import java.io.*;

public class Jugador extends Usuario{

    public Jugador() {
    }

    public void buscarEquipo() {
        List<String> equiposFaltos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("equipos.txt"))) {
            String linea;
            StringBuilder bloqueEquipo = new StringBuilder();

            while ((linea = br.readLine()) != null) {
                bloqueEquipo.append(linea).append("\n");

                if (linea.startsWith("--------------------------------------------------")) {
                    String bloque = bloqueEquipo.toString();
                    String nombreEquipo = "";
                    String formatoLinea = "";
                    int actuales = 0;
                    int requeridos = 0;

                    for (String l : bloque.split("\n")) {
                        if (l.startsWith("Nombre del equipo: ")) {
                            nombreEquipo = l.substring(18).trim();
                        } else if (l.startsWith("Formato: ")) {
                            formatoLinea = l.substring(9).trim(); // Ej: "F5 (3 integrantes)"
                            String[] partes = formatoLinea.split(" ");
                            requeridos = Integer.parseInt(partes[0].substring(1)); // F5 -> 5

                            // Extraer número actual de integrantes desde los paréntesis
                            int parAbre = formatoLinea.indexOf('(');
                            int parCierra = formatoLinea.indexOf(')');
                            if (parAbre != -1 && parCierra != -1) {
                                String contenido = formatoLinea.substring(parAbre + 1, parCierra); // ej: "3 integrantes"
                                String[] tokens = contenido.split(" ");
                                for (String t : tokens) {
                                    try {
                                        actuales = Integer.parseInt(t);
                                        break;
                                    } catch (NumberFormatException e) {
                                        // continuar
                                    }
                                }
                            }
                        }
                    }

                    if (actuales < requeridos) {
                        equiposFaltos.add(nombreEquipo + " (" + actuales + "/" + requeridos + ")");
                    }

                    bloqueEquipo.setLength(0);
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
        }
    }


    public void unirseEquipo() {

    }
}
