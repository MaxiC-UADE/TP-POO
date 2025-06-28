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
        int opcionCrearEquipo;
        // bucle que determina si el usuario quiere ser capitan o un simple jugador.
        while (true) {
            System.out.println("¿Desea crear un equipo? Ingrese 1 para aceptar o 2 para rechazar.");
            opcionCrearEquipo = sc.nextInt(); // lee solo el numero, no el salto de linea que queda en el buffer de entrada
            sc.nextLine(); // consume el salto de linea, es decir, limpia el buffer de entrada.

            if (opcionCrearEquipo == 1 || opcionCrearEquipo == 2){
                System.out.println("Opcion tomada.");
                break;
            }else{
                System.out.println("Opcion invalida. Por favor ingrese una opcion valida.");
            }

        }

        // bucle que pregunta al usuario si quiere loguearse o registrarse y actua en consecuencia.
        Usuario persona;
        while(true){
        System.out.println("Ingrese 1 para logearse o 2 para registrarse.");
        int opcionLoginRegistro = sc.nextInt();
        sc.nextLine();

        if (opcionLoginRegistro == 1) {
            if (opcionCrearEquipo == 1) {
                persona = new Jugador();
                persona.login();
            }else{
                persona = new Capitan();
                persona.login();
                }
            break;

        } else if(opcionLoginRegistro == 2){
            if (opcionCrearEquipo == 1) {
                persona = new Jugador();
                persona.registrar();
            }else{
                persona = new Capitan();
                persona.registrar();
            }
            break;

        }else{
            System.out.println("Opcion invalida. Por favor ingrese una opcion valida.");
        }

        }
        // flujos del programa segun si la persona es capitan o un jugador simple.
        int opcionMenu;
        if (persona instanceof Capitan) {
            System.out.println("Eres un capitán.");
            System.out.println("1. Crear equipo.");
            System.out.println("2. Borrar equipo.");
            System.out.println("3. Inscribir tu equipo.");

            opcionMenu = sc.nextInt();
            sc.nextLine();


        } else if (persona instanceof Jugador) {
            System.out.println("Eres un jugador.");
            System.out.println("¿QUE DESEA HACER?");
            System.out.println("1. Abandonar equipo.");
            System.out.println("2. Buscar equipo.");

            opcionMenu = sc.nextInt();
            sc.nextLine();


        }


        sc.close();

    }
}
