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
            /// Login
            Jugador jugador = new Jugador();
            jugador.login();


        } else if(presion1 == 2){
            /// Register
            Jugador jugador = new Jugador();
            jugador.registrar(); /// Llama metodo de usuario y guarda la info en un txt.

        }

        Jugador jugador = new Jugador();
        jugador.elegirRol();

        sc.close();

    }
}
