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
        System.out.println("¿Como se llamara su equipo?");
        String nombreEquipoIngresado = sc.nextLine();

        String generoEquipo;
        while (true) {
            System.out.println("¿Que genero le corresponde?");
            System.out.println("1. Masculino");
            System.out.println("2. Femenino");
            System.out.println("Elija una opcion: ");
            int opcionGenero = sc.nextInt();
            sc.nextLine();
            if (opcionGenero == 1) {
                generoEquipo = "Masculino";
                break;
            }
            else if (opcionGenero == 2) {
                generoEquipo = "Femenino";
                break;
            }
            else {
                System.out.println("Opcion invalida. Por favor ingrese una opcion valida.");
            }
        }

        System.out.println("¿Cual sera la edad minima?");
        String edadMinima = sc.nextLine();
        System.out.println("¿Cual sera la edad maxima?");
        String edadMaxima = sc.nextLine();
        String rangoEdad = edadMinima + "," + edadMaxima;

        String dnisIntegrantesString = this.dni;
        while (true) {
            System.out.println("¿Desea completar el equipo? Ingrese 1 para SI o 2 para NO.");
            int opcionCompletarEquipo = sc.nextInt();
            sc.nextLine();

            if (opcionCompletarEquipo == 1) {
                int i = 4;
                List<String> dnisIntegrantesLista = new ArrayList<>();
                dnisIntegrantesLista.add(this.dni);

                while (i!=0){
                    System.out.println("Ingrese un dni:");
                    String dniIntegrante = sc.nextLine();
                    dnisIntegrantesLista.add(dniIntegrante);
                    i -= 1;
                }
                dnisIntegrantesString = String.join(",", dnisIntegrantesLista);
                System.out.println("Accion exitosa. Cargaste los dnis de los integrantes de tu equipo.");
            } else if (opcionCompletarEquipo == 2) {
                break;
            }
            else {
                System.out.println("Opcion invalida. Por favor ingrese una opcion valida.");
            }
        }
        System.out.println("¿Cual es el horario minimo (en horas puntuales)?");
        String horarioMinima = sc.nextLine();
        System.out.println("¿Cual es el horario maximo (en horas puntuales)?");
        String horarioMaxima = sc.nextLine();
        String rangoHorario = horarioMinima + "," + horarioMaxima;
        System.out.println("¿En que zona desea jugar?");
        String zona = sc.nextLine();
        System.out.println("¿En que dia desea jugar?");
        String dia = sc.nextLine();

        String preferencias = rangoHorario + "," + zona + "," + dia;


        String nuevaLinea = this.dni + ";" + nombreEquipoIngresado + ";" + generoEquipo + ";" + rangoEdad + ";" + dnisIntegrantesString + preferencias;
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("equipos.txt", true))){
            writer.write(nuevaLinea);
            writer.newLine();
            System.out.println("Equipo creado correctamente.");
        } catch (IOException e){
            System.out.println("Ocurrió un error al escribir el archivo: " + e.getMessage());
        }
        sc.close();

    }

    public void borrarEquipo(String dniCapitan) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el nombre del equipo:");
        String nombreEquipoIngresado = sc.nextLine();

        String archivo = "equipos.txt";

        try {
            File inputFile = new File(archivo);
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            boolean equipoBorrado = false;

            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");
                String dniCapitanGuardado = partes[0];
                String nombreEquipoGuardado  = partes[1];

                if (dniCapitan.equals(dniCapitanGuardado) && nombreEquipoIngresado.equals(nombreEquipoGuardado)) {
                    equipoBorrado = true;
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
            if(equipoBorrado){
                System.out.println("¡Enhorabuena! borraste el equipo.");
            }
            else{
                System.out.println(" Accion denegada: desafortunadamente el equipo no existe o no sos el capitan del equipo.");
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo original o escribir en el temporal.");
        }


    }

    public void inscripcion() {

    }
}
