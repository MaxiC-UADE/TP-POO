import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public abstract class Usuario {
    public String email;
    public String password;
    public String nombre;
    public String apellido;
    public String dni;
    public int edad;
    public String posicion;
    public String genero;

    // gets y sets
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getpassword() {
        return password;
    }
    public void setpassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }


    // METODOS
    public void abandonarEquipo(String nombreEquipoInput) {
        String archivo = "equipos.txt";

        try {
            File inputFile = new File(archivo);
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            boolean integranteRemovido = false;

            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");
                String nombreEquipoGuardado  = partes[1];

                if (nombreEquipoInput.equals(nombreEquipoGuardado)) {
                    String[] dnisIntegrantesArray = partes[4].split(",");
                    List<String> dnisIntegrantesList = new ArrayList<>(Arrays.asList(dnisIntegrantesArray));
                    dnisIntegrantesList.remove(this.dni);
                    String dnisActualizados = String.join(",", dnisIntegrantesList);
                    partes[4] = dnisActualizados;
                    String nuevaLinea = String.join(";", partes);
                    writer.write(nuevaLinea);
                    integranteRemovido = true;
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
            if(integranteRemovido){
                System.out.println("¡Enhorabuena! fuiste removido del equipo.");
            }
            else{
                System.out.println(" Lamentablemente no pudimos remover tu estadia en el equipo: el equipo no existe o no fromas parte de este.");
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo original o escribir en el temporal.");
        }
    }

    public void registrar() {
        Scanner sc = new Scanner(System.in);
        sc.nextLine();

        System.out.println("Nombre:");
        String nombre = sc.nextLine();
        this.nombre = nombre;

        System.out.println("Apellido:");
        String apellido = sc.nextLine();
        this.apellido = apellido;

        System.out.println("Edad:");
        int edad = sc.nextInt();
        sc.nextLine();
        this.edad = edad;

        String genero;
        while (true) {
            System.out.println("Ingrese 1 para Masculino o 2 para Femenino.");
            int opcionGenero = sc.nextInt();
            sc.nextLine();

            if (opcionGenero == 1){
                genero = "Maculino";
                break;
            }
            else if (opcionGenero == 2){
                genero = "Femenino";
                break;
            }
            else {
                System.out.println("Opcion invalida. Por favor ingrese una opcion valida.");
            }
        }
        this.genero = genero;

        System.out.println("Email:");
        String email = sc.nextLine();
        this.email = email;

        System.out.println("Password:");
        String password = sc.nextLine();
        this.password = password;

        System.out.println("DNI:");
        String dni = sc.nextLine();
        this.dni = dni;

        System.out.println("Posicion:");
        String posicion = sc.nextLine();
        this.posicion = posicion;

        try{
            FileWriter archivo = new FileWriter("usuarios.txt", true);
            archivo.write(email + ";" + password + ";" + nombre + ";" + apellido + ";" + edad + ";" + dni + ";" + posicion + ";" + genero + "\\n");
            archivo.close();
            System.out.println("Usuario guardado");
        } catch (IOException e){
            System.out.println("Error al guardar el archivo");
        }
        sc.close();
    }

    public void login() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese su email: ");
        String emailIngresado = sc.nextLine();
        System.out.print("Ingrese su password: ");
        String passwordIngresado = sc.nextLine();


        boolean accesoConcedido = false;

        try {
            BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                String emailGuardado = partes[0];
                String passwordGuardado = partes[1];


                if (emailIngresado.equals(emailGuardado) && passwordIngresado.equals(passwordGuardado)) {
                    accesoConcedido = true;
                    this.nombre = partes[2];
                    this.apellido = partes[3];
                    this.edad = Integer.parseInt(partes[4]);
                    this.dni = partes[5];
                    this.posicion = partes[6];
                    break;
                }
            }
            br.close();

        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
        }
        if (accesoConcedido) {
            System.out.println("Bienvenido a FutInc");
        } else {
            System.out.println("Las credenciales son incorrectas, asegurate de introducir bien tu contraseña");

        }
    }
}


