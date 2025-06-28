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

        while(true){
        System.out.println("Ingrese 1 para logearse o 2 para registrarse.");
        int opcionLoginRegistro = sc.nextInt();
        sc.nextLine();

        if (opcionLoginRegistro == 1) {
            if (opcionCrearEquipo == 1) {
                Usuario persona = new Jugador();
                persona.login();
            }else{
                Usuario persona = new Capitan();
                persona.login();
                }
            break;

        } else if(opcionLoginRegistro == 2){
            if (opcionCrearEquipo == 1) {
                Usuario persona = new Jugador();
                persona.registrar();
            }else{
                Usuario persona = new Capitan();
                persona.registrar();
            }
            break;

        }else{
            System.out.println("Opcion invalida. Por favor ingrese una opcion valida.");
        }

        }

        Jugador jugador = new Jugador();
        jugador.elegirRol();

        sc.close();

    }
}
