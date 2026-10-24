package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

import entidades.*;
import enums.*;

public class Main {

    // Variables estáticas
    static Scanner sc = new Scanner(System.in);
    static Sesion sesionActual = new Sesion("", "", Perfil.INVITADO);
    // Cargamos el archivo .properties
    static Properties properties = new Properties();

    public static void main(String[] args) {
        menuPrincipal();
    }

    private static void menuPrincipal() {
        System.out.println("Bienvenido, Invitado. Por favor, introduzca sus datos para iniciar sesión.");
        int opcion = 0;

        do {
            // Menú principal
            System.out.println("--- Bienvenido a la clínica ---");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Salir");
            System.out.println("-------------------------------");
            System.out.println("Introduce una opción: ");
            try {
                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1:
                        login();
                    case 2:
                        break;
                    default:
                        System.out.println("Por favor, introduce un número válido (1 - 2).");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Por favor, introduzca un número válido (1 - 2).");
                // Limpiamos el scanner para evitar un bucle infinito
                sc.nextLine();
            }


        } while (opcion != 2);
    }

    private static void login() {
        // Pedimos los datos de inicio de sesión
        System.out.print("Nombre de usuario: ");
        String user = sc.nextLine();
        System.out.print("Contraseña: ");
        String pass = sc.nextLine();

        // COMPROBACIONES
        // No está en blanco
        if (user.trim().isEmpty() || pass.trim().isEmpty()) {
            System.out.println("Error: no ha escrito el usuario o contraseña, inténtelo de nuevo.");
        }
        else {
            // Primero comprobamos el caso admin, que no está en credenciales pero sí en properties
            try {
                properties.load(new BufferedReader(new FileReader("application.properties")));
            } catch(Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            if (user.equals(properties.getProperty("user-admin")) && pass.equals(properties.getProperty("pass-admin"))) {
                System.out.println("Bienvenido/a, Administrador.");
                sesionActual.setPerfil(Perfil.ADMIN);
                subMenu();
            }

            // Si no es admin accedemos al archivo credenciales.txt
            Path ruta = FileSystems.getDefault().getPath("credenciales.txt");
            try (BufferedReader br = Files.newBufferedReader(ruta)) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] texto = linea.split("\\|");

                    // Si coinciden usuario [0] y contraseña [1] inicia sesión
                    if (user.equals(texto[0]) && pass.equals(texto[1])) {
                        System.out.println("Bienvenido/a, " + texto[2] + ", de " + texto[3] + ".");
                        if (texto[3].equals("enfermeria")) {
                            sesionActual.setPerfil(Perfil.ENFERMERIA);
                            subMenu();
                        } else if (texto[3].equals("secretariado")) {
                            sesionActual.setPerfil(Perfil.SECRETARIADO);
                            subMenu();
                        }
                    }
                }
                System.out.println("Algunas de sus credenciales son incorrectas, inténtelo de nuevo");
                menuPrincipal();
            } catch (IOException err) {
                System.out.println("Error de I/O: " + err.getMessage());
            }
        }
    }

    private static void subMenu() {
        // Dependiendo del perfil el usuario puede hacer una cosa u otra
        if (sesionActual.getPerfil() == Perfil.SECRETARIADO) {
            // Acciones de secretariado
            accionesSecretariado();
        }
        else if (sesionActual.getPerfil() == Perfil.ENFERMERIA) {
            accionesEnfermeria();
        }
        else if (sesionActual.getPerfil() == Perfil.ADMIN) {
            // El admin puede hacer los dos
            accionesSecretariado();
            accionesEnfermeria();
        }
    }

    private static void accionesSecretariado() {
        // Gestiona los datos de los empleados y las citas con los pacientes
        // Hacemos menú para que el usuario escoja qué quiere hacer
        int opcion = -1;
        do {
            System.out.println("--- SUBMENÚ SECRETARIADO ---");
            System.out.println("1. Dar de alta a un nuevo empleado");
            System.out.println("2. Salir de la sesión");
            System.out.println("----------------------------");
            System.out.print("Por favor, escriba una opción: ");
            try {
                opcion = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException err) {
                System.out.println("Por favor, introduzca una opción válida (1 - 2).");
            }

            switch (opcion) {
                case 1:
                    darAlta();
                    break;
                case 2:
                    logout();
                    break;
                default:
                    break;
            }
        } while (opcion != 0);
    }

    private static void accionesEnfermeria() {
        // Realiza anotaciones en las citas
    }

    private static void darAlta() {
    }
    
    private static void logout() {
        // Cambiamos la sesión y devolvemos al menú principal
        sesionActual.setPerfil(Perfil.INVITADO);
        menuPrincipal();
    }
}
