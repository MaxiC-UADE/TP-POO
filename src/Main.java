import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("PRESIONE 1 PARA LOGIN 2 PARA REGISTER:");
        int presion1 = sc.nextInt();

        if (presion1 == 1) {
            System.out.print("Ingrese su email: ");
            String emailIngresado = sc.nextLine();
            System.out.print("Ingrese su password: ");
            String passwordIngresado = sc.nextLine();
            sc.close();

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

            } else {

            }
        }


    }
}
