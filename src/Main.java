import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("PRESIONE 1 PARA LOGIN 2 PARA REGISTER:");
        int presion1 = sc.nextInt();
        sc.nextLine();

        if (presion1 == 1) {
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
        } else if(presion1 == 2){
            System.out.println("Nombre:");
            String nombre = sc.nextLine();

            System.out.println("Apellido:");
            String apellido = sc.nextLine();

            System.out.println("Edad:");
            String edad = sc.nextLine();

            System.out.println("Email:");
            String email = sc.nextLine();

            System.out.println("Password:");
            String password = sc.nextLine();

            System.out.println("DNI:");
            String dni = sc.nextLine();

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
        }
        sc.close();

    }
}
