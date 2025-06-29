import java.io.*;
import java.util.Scanner;

public class Jugador extends Usuario{

    public Jugador() {
    }

    public void buscarEquipo() {
        Scanner sc = new Scanner(System.in);

        boolean equiposDisponibles = false;

        try {
            BufferedReader br = new BufferedReader(new FileReader("equipos.txt"));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                String cantidadIntegrantesGuardado = partes[7];
                int cantidadIntegrantesGuardadoInt = Integer.parseInt(cantidadIntegrantesGuardado);

                String generoEquipo = partes[2];
                String[] edadesRango = partes[3].split(",");
                int edadMinima = Integer.parseInt(edadesRango[0]);
                int edadMaxima = Integer.parseInt(edadesRango[1]);

                if (cantidadIntegrantesGuardadoInt < 5 && this.genero.equals(generoEquipo) && edadMinima <= this.edad && this.edad <= edadMaxima) {
                    System.out.println("Equipo: " + partes[1]);
                    System.out.println("Cantidad de integrantes actualmente: " + partes[7]);
                    String[] preferencias = partes[5].split(",");
                    System.out.println("Rango horario: " + preferencias[0]);
                    System.out.println("Zona: " + preferencias[1]);
                    System.out.println("Dia: " + preferencias[2] + "\\n");

                    equiposDisponibles = true;


                }
            }
            br.close();

        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
        }
        if (!equiposDisponibles) {
            System.out.println("No se encontraron equipos disponibles para usted.");
        }
    }

    public void unirseEquipo(String nombreEquipoInput) {
        String archivo = "equipos.txt";
        try {
            File inputFile = new File(archivo);
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            boolean integranteAñadido = false;

            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");
                String nombreEquipoGuardado  = partes[1];

                if (nombreEquipoInput.equals(nombreEquipoGuardado)) {
                    partes[4] += "," + this.dni;
                    int cantidadIntegrantesGuardado = Integer.parseInt(partes[7]);
                    cantidadIntegrantesGuardado += 1;
                    String nuevaCantidadIntegrantes = Integer.toString(cantidadIntegrantesGuardado);
                    partes[7] = nuevaCantidadIntegrantes;
                    writer.write(String.join(",", partes));
                    integranteAñadido = true;
                    continue; // saltar esta línea

                }
                writer.write(linea);
                writer.newLine();
            }

            writer.close();
            reader.close();

            if (inputFile.delete()) {
                tempFile.renameTo(inputFile);
            }
            if(integranteAñadido){
                System.out.println("¡Enhorabuena! Te uniste al equipo.");
            }
            else{
                System.out.println(" Error: el equipo no existe.");
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo original o escribir en el temporal.");
        }
    }
}
