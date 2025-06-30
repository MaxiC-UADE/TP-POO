import java.io.*;
import java.util.Scanner;

public class Administrador {
    public String emailAdmin;
    public String contraseñaAdmin;

    public Administrador(String emailAdmin, String contraseñaAdmin) {
        this.emailAdmin = emailAdmin;
        this.contraseñaAdmin = contraseñaAdmin;
    }

    // GETS Y SETS

    public String getEmailAdmin() {
        return emailAdmin;
    }
    public void setEmailAdmin(String emailAdmin) {
        this.emailAdmin = emailAdmin;
    }

    public String getContraseñaAdmin() {
        return contraseñaAdmin;
    }
    public void setContraseñaAdmin(String contraseñaAdmin) {
        this.contraseñaAdmin = contraseñaAdmin;
    }

    // METODOS
    public void crearTorneo() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese el nombre del torneo: ");
        String nombreTorneo = sc.nextLine();

        System.out.print("Ingrese el formato del torneo: ");
        String formato = sc.nextLine();

        System.out.print("Ingrese la cantidad máxima de equipos: ");
        int maxEquipos = sc.nextInt();
        sc.nextLine();

        System.out.print("Ingrese el nombre de la cancha: ");
        String nombreCanchaIngresado = sc.nextLine().trim();

        // Buscar la zona correspondiente a la cancha en canchas.txt
        String zonaEncontrada = null;

        try (BufferedReader br = new BufferedReader(new FileReader("canchas.txt"))) {
            String linea;
            String nombre = null;
            String zona = null;

            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("Nombre: ")) {
                    nombre = linea.substring(8).trim();
                } else if (linea.startsWith("Zona: ")) {
                    zona = linea.substring(6).trim();
                } else if (linea.startsWith("--------------------------")) {
                    if (nombre != null && nombre.equalsIgnoreCase(nombreCanchaIngresado)) {
                        zonaEncontrada = zona;
                        break;
                    }
                    // reset para siguiente bloque
                    nombre = null;
                    zona = null;
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo canchas.txt: " + e.getMessage());
            return;
        }

        if (zonaEncontrada == null) {
            System.out.println("No se encontró una cancha con ese nombre.");
            return;
        }

        // Guardar torneo en archivo torneos.txt
        try (FileWriter fw = new FileWriter("torneos.txt", true)) {
            fw.write("NombreTorneo: " + nombreTorneo + "\n");
            fw.write("Formato: " + formato + "\n");
            fw.write("MaxEquipos: " + maxEquipos + "\n");
            fw.write("Cancha: " + nombreCanchaIngresado + "\n");
            fw.write("Zona: " + zonaEncontrada + "\n");
            fw.write("-----------------------------\n");
            System.out.println("Torneo guardado correctamente en torneos.txt");
        } catch (IOException e) {
            System.out.println("Error al guardar el torneo: " + e.getMessage());
        }
    }
}
