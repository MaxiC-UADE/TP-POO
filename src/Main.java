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

        System.out.println("PRESIONE 1 PARA LOGIN, 2 PARA REGISTER:");
        int presion1 = sc.nextInt();
        sc.nextLine();

        Jugador jugador = new Jugador();

        if (presion1 == 1) {
            // Login
            boolean accesoConcedido = jugador.login(); // login debe devolver true si fue exitoso

            if (accesoConcedido) {
                // Menú en bucle
                while (true) {
                    System.out.println("\n--- MENÚ DE OPCIONES ---");
                    System.out.println("1. Abandonar un equipo");
                    System.out.println("2. Buscar equipo con cupo");
                    System.out.println("0. Salir");
                    System.out.print("Ingrese una opción: ");

                    int opcion = sc.nextInt();
                    sc.nextLine();

                    if (opcion == 1) {
                        System.out.print("Ingrese su email para abandonar el equipo: ");
                        String email = sc.nextLine().trim();
                        jugador.abandonarEquipo(email);
                    } else if (opcion == 2) {
                        jugador.buscarEquipo();
                    } else if (opcion == 0) {
                        System.out.println("Saliendo... ¡Hasta la próxima!");
                        break;
                    } else {
                        System.out.println("Opción inválida. Intente nuevamente.");
                    }
                }
            } else {
                System.out.println("No se pudo iniciar sesión. Reintente más tarde.");
            }

        } else if (presion1 == 2) {
            // Registro
            jugador.registrar();
            System.out.println("Registro exitoso. Reinicie para iniciar sesión.");
        } else {
            System.out.println("Opción inválida.");
        }

        sc.close();
    }
}

