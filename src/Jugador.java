import java.io.*;

public class Jugador extends Usuario{

    public Jugador() {
    }

    public void buscarEquipo() {

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
