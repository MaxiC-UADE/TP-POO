import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("PRESIONA 1 PARA LOGIN 2 PARA REGISTER:");
        int presion1 = sc.nextInt();

        if (presion1 == 1) {
            System.out.print("Ingrese su email: ");
            String email = sc.nextLine().trim();

            while (email.isEmpty()) {
                System.out.print("Debe ingresar un email válido: ");
                email = sc.nextLine().trim();
            }

            System.out.print("Ingrese su password: ");
            String password = sc.nextLine().trim();

            System.out.println("Email ingresado: " + email);
            System.out.println("Password ingresado: " + password);
        }

        sc.close();

    }
}