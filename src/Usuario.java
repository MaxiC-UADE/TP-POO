import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public abstract class Usuario {
    public String email;
    public String password;
    public String nombre;
    public String apellido;
    public int dni;
    public int edad;
    public String posicion;

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

    public int getDni() {
        return dni;
    }
    public void setDni(int dni) {
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
    public void abandonarEquipo(String nombreEquipo) {

    }

    public void registrar() {
        Scanner sc = new Scanner(System.in);
        sc.nextLine();

        System.out.println("Nombre:");
        String nombre = sc.nextLine();

        System.out.println("Apellido:");
        String apellido = sc.nextLine();

        System.out.println("Edad:");
        int edad = sc.nextInt();
        sc.nextLine();

        System.out.println("Email:");
        String email = sc.nextLine();

        System.out.println("Password:");
        String password = sc.nextLine();

        System.out.println("DNI:");
        int dni = sc.nextInt();
        sc.nextLine();

        System.out.println("Posicion:");
        String posicion = sc.nextLine();

        try{
            FileWriter archivo = new FileWriter("usuarios.txt", true);
            archivo.write(email + ";" + password + ";" + nombre + ";" + apellido + ";" + edad + ";" + dni + ";" + posicion + "\n");
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
                    break;
                }
            }
            br.close();

        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
        }
        if (accesoConcedido) {
            System.out.println("bienvenido");
        } else {
            System.out.println("acceso denegado");

        }
    }

    public void elegirRol() {
        Scanner sc = new Scanner(System.in);

        System.out.println("PRESIONE 1 PARA CREAR UN EQUIPO, O PRESIONE 2 PARA UNIRSE UN EQUIPO:");
        int presion1 = sc.nextInt();
        sc.nextLine();

        if (presion1 == 1) {

        }
        else if(presion1 == 2){

        }
        sc.close();
    }

}
