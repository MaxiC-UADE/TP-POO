import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.*;
import java.io.*;

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



    public String dniUsuarioLogueado = null;

    // METODOS
    public void abandonarEquipo(String emailUsuario) {
        Scanner sc = new Scanner(System.in);

        /// Paso 1: Buscar DNI en usuarios.txt a partir del email
        String dniUsuario = null;
        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linea;
            String emailGuardado = "";
            String dniGuardado = "";

            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("Email: ")) {
                    emailGuardado = linea.substring(7).trim();
                } else if (linea.startsWith("DNI: ")) {
                    dniGuardado = linea.substring(5).trim();
                } else if (linea.startsWith("--------------------------------------------------")) {
                    if (emailUsuario.equals(emailGuardado)) {
                        dniUsuario = dniGuardado;
                        break;
                    }
                    emailGuardado = "";
                    dniGuardado = "";
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer usuarios.txt");
            e.printStackTrace();
            return;
        }

        if (dniUsuario == null) {
            System.out.println("No se encontró un usuario con ese email o no está logueado.");
            return;
        }

        /// Paso 2: Buscar equipos donde está el DNI
        List<String> equipos = new ArrayList<>();
        List<String> equiposCompletos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("equipos.txt"))) {
            String linea;
            StringBuilder bloqueEquipo = new StringBuilder();
            boolean estaElDNI = false;

            while ((linea = br.readLine()) != null) {
                bloqueEquipo.append(linea).append("\n");

                if (linea.startsWith("Integrantes:")) {
                    while ((linea = br.readLine()) != null && !linea.startsWith("--------------------------------------------------")) {
                        bloqueEquipo.append(linea).append("\n");
                        if (linea.contains(dniUsuario)) {
                            estaElDNI = true;
                        }
                    }
                    bloqueEquipo.append("--------------------------------------------------\n");

                    if (estaElDNI) {
                        String bloqueStr = bloqueEquipo.toString();
                        String nombreEquipo = "";
                        for (String l : bloqueStr.split("\n")) {
                            if (l.startsWith("Nombre del equipo: ")) {
                                nombreEquipo = l.substring(18).trim();
                                break;
                            }
                        }
                        equipos.add(nombreEquipo);
                        equiposCompletos.add(bloqueStr);
                    }
                    bloqueEquipo.setLength(0);
                    estaElDNI = false;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer equipos.txt");
            e.printStackTrace();
            return;
        }

        if (equipos.isEmpty()) {
            System.out.println("No estás en ningún equipo actualmente.");
            return;
        }

        /// Paso 3: Mostrar equipos y pedir cuál abandonar
        System.out.println("Equipos en los que participas:");
        for (int i = 0; i < equipos.size(); i++) {
            System.out.println((i + 1) + ". " + equipos.get(i));
        }
        System.out.println("Ingrese el número del equipo que desea abandonar (0 para cancelar):");

        int opcion = -1;
        while (true) {
            if (sc.hasNextInt()) {
                opcion = sc.nextInt();
                sc.nextLine();
                if (opcion >= 0 && opcion <= equipos.size()) {
                    break;
                } else {
                    System.out.println("Número inválido. Intente nuevamente:");
                }
            } else {
                System.out.println("Por favor ingrese un número válido:");
                sc.nextLine();
            }
        }

        if (opcion == 0) {
            System.out.println("No abandonaste ningún equipo.");
            return;
        }

        /// Paso 4: Modificar equipos.txt quitando el DNI del equipo elegido
        String equipoABorrar = equiposCompletos.get(opcion - 1);
        StringBuilder nuevoContenidoEquipo = new StringBuilder();
        String[] lineas = equipoABorrar.split("\n");

        for (String l : lineas) {
            if (l.trim().matches("\\d+\\. DNI: " + dniUsuario + "( \\(Capitán\\))?")) {
                continue; // omitimos línea del DNI a eliminar
            }
            nuevoContenidoEquipo.append(l).append("\n");
        }

        /// Reenumerar integrantes
        String[] lineasFiltradas = nuevoContenidoEquipo.toString().split("\n");
        StringBuilder reenumerado = new StringBuilder();
        int contador = 0;
        for (String nl : lineasFiltradas) {
            if (nl.startsWith("  ") && nl.contains("DNI: ")) {
                contador++;
                String lineaRenumerada = nl.replaceFirst("\\s*\\d+\\.", "  " + contador + ".");
                reenumerado.append(lineaRenumerada).append("\n");
            } else {
                reenumerado.append(nl).append("\n");
            }
        }

        /// Reescribir equipos.txt modificado
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("equipos.txt"))) {
            for (int i = 0; i < equiposCompletos.size(); i++) {
                if (i == opcion - 1) {
                    bw.write(reenumerado.toString());
                } else {
                    bw.write(equiposCompletos.get(i));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al guardar equipos.txt");
            e.printStackTrace();
            return;
        }

        System.out.println("Has abandonado el equipo: " + equipos.get(opcion - 1));
    }


    public void registrar() {
        Scanner sc = new Scanner(System.in);
        sc.nextLine();

        System.out.println("Nombre:");
        String nombre = sc.nextLine();

        System.out.println("Apellido:");
        String apellido = sc.nextLine();

        int edad;
        while (true) {
            System.out.println("Edad:");

            if (sc.hasNextInt()) {
                edad = sc.nextInt();
                sc.nextLine(); // limpiar el buffer

                if (edad >= 18 && edad <= 99) {
                    break;
                } else {
                    System.out.println("La edad debe estar entre 18 y 99 años.");
                }
            } else {
                System.out.println("Por favor ingrese un número válido.");
                sc.nextLine(); // descartar entrada inválida
            }
        }

        String email;
        while (true) {
            System.out.println("Email:");
            email = sc.nextLine().trim();

            // Validación simple: debe contener '@' y un '.' después del '@'
            int atPos = email.indexOf('@');
            int dotPos = email.lastIndexOf('.');

            if (atPos > 0 && dotPos > atPos + 1 && dotPos < email.length() - 1) {
                break;
            } else {
                System.out.println("Email inválido. Por favor, ingresa un email válido.");
            }
        }

        System.out.println("Password:");
        String password = sc.nextLine();

        System.out.println("DNI:");
        int dni = sc.nextInt();
        sc.nextLine();

        /// Lista de posiciones válidas
        List<String> posicionesValidas = Arrays.asList("Delantero", "Mediocampista", "Defensor", "Arquero");

        String posicion;

        while (true) {
            System.out.println("Posición (Delantero, Mediocampista, Defensor, Arquero):");
            posicion = sc.nextLine().trim();

            /// Validar ignorando mayúsculas/minúsculas
            String finalPosicion = posicion;
            boolean posicionValida = posicionesValidas.stream()
                    .anyMatch(p -> p.equalsIgnoreCase(finalPosicion));

            if (posicionValida) {
                // igualar minus o mayus
                for (String p : posicionesValidas) {
                    if (p.equalsIgnoreCase(posicion)) {
                        posicion = p;
                        break;
                    }
                }
                break;
            } else {
                System.out.println("Posición inválida. Debes ingresar una de las siguientes: " + String.join(", ", posicionesValidas));
            }
        }


        try (BufferedWriter writer = new BufferedWriter(new FileWriter("usuarios.txt", true))) {
            writer.write("Email: " + email + "\n");
            writer.write("Password: " + password + "\n");
            writer.write("Nombre: " + nombre + "\n");
            writer.write("Apellido: " + apellido + "\n");
            writer.write("Edad: " + edad + "\n");
            writer.write("DNI: " + dni + "\n");
            writer.write("Posición: " + posicion + "\n");
            writer.write("--------------------------------------------------\n");
            System.out.println("Usuario guardado");
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo");
            e.printStackTrace();
        }
        sc.close();
    }

    public boolean login() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese su email: ");
        String emailIngresado = sc.nextLine().trim();
        System.out.print("Ingrese su password: ");
        String passwordIngresado = sc.nextLine().trim();

        boolean accesoConcedido = false;

        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linea;
            String emailGuardado = "";
            String passwordGuardado = "";

            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("Email: ")) {
                    emailGuardado = linea.substring(7).trim();
                } else if (linea.startsWith("Password: ")) {
                    passwordGuardado = linea.substring(10).trim();

                    if (emailIngresado.equals(emailGuardado) && passwordIngresado.equals(passwordGuardado)) {
                        accesoConcedido = true;
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
        }

        // Si se logueó correctamente, ahora buscamos el DNI con ese email
        if (accesoConcedido) {
            try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
                String linea;
                boolean emailEncontrado = false;

                while ((linea = br.readLine()) != null) {
                    if (linea.startsWith("Email: ")) {
                        String emailArchivo = linea.substring(7).trim();
                        emailEncontrado = emailArchivo.equals(emailIngresado);
                    } else if (emailEncontrado && linea.startsWith("DNI: ")) {
                        dniUsuarioLogueado = linea.substring(5).trim(); // guardamos el DNI
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println("Error al buscar el DNI.");
            }

            System.out.println("Bienvenido a FutInc");
        } else {
            System.out.println("Las credenciales son incorrectas, asegúrate de introducir bien tu contraseña");
        }

        return accesoConcedido;
    }

}
