package main;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Stream;

import entidades.*;
import enums.*;

public class Main {

    // Variables estáticas
    static Scanner sc = new Scanner(System.in);
    static Sesion sesionActual = new Sesion("", "", Perfil.INVITADO);
    static final Path RUTA_CREDENCIALES = FileSystems.getDefault().getPath("credenciales.txt");
    static final Path RUTA_ALERGIAS = FileSystems.getDefault().getPath("alergias/");
    // Cargamos el archivo .properties
    static Properties properties = new Properties();

    public static void main(String[] args) {
        // Cargamos las properties para tenerlas a mano
        cargarProperties();
        menuPrincipal();

    }

    private static void cargarProperties() {
        try {
            properties.load(new BufferedReader(new FileReader("application.properties")));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void menuPrincipal() {
        System.out.println("TEST: " + sesionActual.getPerfil());
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
                        break;
                    case 2:
                        System.out.println("Saliendo del programa...");
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
        String user = sc.nextLine().trim();
        System.out.print("Contraseña: ");
        String pass = sc.nextLine().trim();

        // COMPROBACIONES
        // No está en blanco
        if (user.isEmpty() || pass.isEmpty()) {
            System.out.println("Error: no ha escrito el usuario o contraseña, inténtelo de nuevo.");
        } else {
            // Primero comprobamos el caso admin, que no está en credenciales pero sí en properties
            if (user.equals(properties.getProperty("user-admin")) && pass.equals(properties.getProperty("pass-admin"))) {
                System.out.println("Bienvenido/a, Administrador.");
                sesionActual.setPerfil(Perfil.ADMIN);
                subMenu();
            }

            // Si no es admin accedemos al archivo credenciales.txt
            try (BufferedReader br = Files.newBufferedReader(RUTA_CREDENCIALES)) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] texto = linea.split("\\|");

                    // Si coinciden usuario [0] y contraseña [1] inicia sesión
                    if (user.equals(texto[0]) && pass.equals(texto[1])) {
                        System.out.println("Bienvenido/a, " + texto[2] + ", de " + texto[3] + ".");
                        if (texto[3].equals("enfermeria")) {
                            sesionActual.setPerfil(Perfil.ENFERMERIA);
                            // Si ya lo encontramos rompemos el bucle
                            break;
                        } else if (texto[3].equals("secretariado")) {
                            sesionActual.setPerfil(Perfil.SECRETARIADO);
                            break;
                        }
                    }
                }
            } catch (IOException err) {
                System.out.println("Error de I/O: " + err.getMessage());
            }

            // Dependiendo de la sesión vemos qué hacer
            if (sesionActual.getPerfil() == Perfil.INVITADO) {
                // Si ha fallado volvemos al menú principal
                System.out.println("Algunas de sus credenciales son incorrectas, inténtelo de nuevo");
            } else if (sesionActual.getPerfil() != Perfil.INVITADO) {
                // Si ya se ha iniciado sesión lanzamos el submenú
                subMenu();
            }
        }
    }

    private static void subMenu() {
        // Dependiendo del perfil el usuario puede hacer una cosa u otra
        if (sesionActual.getPerfil() == Perfil.SECRETARIADO) {
            // Acciones de secretariado
            accionesSecretariado();
        } else if (sesionActual.getPerfil() == Perfil.ENFERMERIA) {
            accionesEnfermeria();
        } else if (sesionActual.getPerfil() == Perfil.ADMIN) {
            // El admin puede hacer los dos
            System.out.println("1. Acciones de Secretariado");
            System.out.println("2. Acciones de Enfermería");
            System.out.println("3. Cerrar sesión");
            System.out.println("Por favor, introduzca un número para escoger la acción:");
            int opcion = -1;
            try {
                opcion = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Por favor, introduzca un número válido (1 - 3).");
                // Limpiamos el scanner para evitar un bucle infinito
                sc.nextLine();
            }
            switch (opcion) {
                case 1:
                    accionesSecretariado();
                    break;
                case 2:
                    accionesEnfermeria();
                    break;
                case 3:
                    logout();
                    break;
                default:
                    System.out.println("Introduzca un número válido (1 - 2)");
                    break;
            }
        }
    }

    private static void accionesSecretariado() {
        // Gestiona los datos de los empleados y las citas con los pacientes
        // Hacemos menú para que el usuario escoja qué quiere hacer
        int opcion = -1;
        do {
            System.out.println("--- MENÚ SECRETARIADO ---");
            System.out.println("1. Dar de alta a un nuevo empleado");
            System.out.println("2. Cerrar sesión");
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
                    System.out.println("Por favor, introduce un número válido (1 - 2).");
                    break;
            }
        } while (opcion != 2);
    }

    private static void accionesEnfermeria() {
        // Menú enfermería
        int opcion = 0;
        do {
            System.out.println("--- MENÚ ENFERMERÍA ---");
            System.out.println("1. Ver alergias");
            System.out.println("2. Nueva alergia a medicamento");
            System.out.println("3. Editar alergia");
            System.out.println("4. Cerrar sesión");
            System.out.println("Introduzca la opción a elegir (1 - 4):");
            try {
                opcion = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Por favor, introduzca un número válido (1 - 4)");
            }
            switch (opcion) {
                case 1:
                    verAlergias();
                    break;
                case 2:
                    nuevaAlergia();
                    break;
                case 3:
                    // Pedimos al usuario el ID a cambiar
                    System.out.println("Introduzca el ID a cambiar:");
                    int id = sc.nextInt();
                    sc.nextLine();
                    editarAlergia(id);
                    break;
                case 4:
                    logout();
                    break;
                default:
                    System.out.println("Introduzca un número válido (1 - 4).");
                    break;
            }
        } while (opcion != 4);
    }

    private static void darAlta() {
        // Primero pedimos los datos comunes
        System.out.println("--- NUEVO ALTA ---");
        System.out.println("Introduzca el ID:");
        Long id = null;
        try {
            id = sc.nextLong();
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("Por favor, introduzca un número.");
        }
        System.out.println("Introduzca el nombre:");
        String nombre = sc.nextLine();
        System.out.println("Introduzca los apellidos:");
        String apellidos = sc.nextLine();
        System.out.println("Introduzca el teléfono:");
        String telefono = sc.nextLine();
        System.out.println("Introduzca el email:");
        String email = sc.nextLine();
        System.out.println("Introduzca el NIF / NIE:");
        String nifNie = sc.nextLine();
        System.out.println("Introduzca la dirección:");
        String direccion = sc.nextLine();

        // Pedimos al usuario el tipo de Empleado
        System.out.println("--------------");
        System.out.println("1. Cirujano");
        System.out.println("2. Secretariado");
        System.out.println("3. Enfermeria");
        System.out.println("Por favor, introduzca el tipo de empleado (1 - 3):");
        int opcion = 0;
        try {
            opcion = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Por favor, introduzca un número válido (1 - 3");
        }
        switch (opcion) {
            case 1:
                // CIRUJANO
                Set<Especialidad> lista = seleccionarEspecialidades();
                // Creamos el cirujano
                Cirujano cirujano = new Cirujano(
                        id, nombre, apellidos, telefono, email, nifNie, direccion, lista);
                // Creamos las credenciales
                crearCredenciales(cirujano);
                break;
            case 2:
                // SECRETARIADO
                // Pedimos únicamente los años de experiencia
                System.out.println("Introduzca los años de experiencia:");
                int aniosExperiencia = 0;
                try {
                    aniosExperiencia = sc.nextInt();
                    sc.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Por favor, introduzca un número válido");
                }
                // Creamos el secretariado
                Secretariado secretariado = new Secretariado(
                        id, nombre, apellidos, telefono, email, nifNie, direccion, aniosExperiencia);
                // Creamos las credenciales
                crearCredenciales(secretariado);
                break;
            case 3:
                // ENFERMERÍA
                // Pedimos la categoría (A, B, C) manualmente, solo una
                System.out.println("1. A");
                System.out.println("2. B");
                System.out.println("3. C");
                System.out.println("Introduzca la categoría de la enfermería (por defecto: A):");
                Categoria cat;
                int opcionCat = 0;
                try {
                    opcionCat = sc.nextInt();
                    sc.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Por favor, introduzca un número válido (1 - 3)");
                }
                cat = switch (opcionCat) {
                    case 1 -> Categoria.A;
                    case 2 -> Categoria.B;
                    case 3 -> Categoria.C;
                    default -> {
                        System.out.println("Por favor, introduzca una opción válida.");
                        yield Categoria.A;
                    }
                };

                // Creamos el objeto
                Enfermeria enfermeria = new Enfermeria(
                        id, nombre, apellidos, telefono, email, nifNie, direccion, cat
                );
                crearCredenciales(enfermeria);
                break;
        }
    }

    private static Set<Especialidad> seleccionarEspecialidades() {
        // Pedimos al usuario las distintas especialidades de cirujano
        // Mínimo 1
        int opcion = -1;
        // Lista a devolver
        LinkedHashSet<Especialidad> lista = new LinkedHashSet<>();
        do {
            System.out.println("Lista de especialidades:");
            // Con .values() accedemos al enum entero
            for (Especialidad esp : Especialidad.values()) {
                // Ordinal devuelve la posición, + 1 porque empieza en 0
                System.out.println((esp.ordinal() + 1) + ". " + esp);
            }
            System.out.println("---------");
            System.out.println("Introduzca un número para añadir la especialidad (min 1, 0 para confirmar):");

            try {
                opcion = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Por favor, introduzca un número válido.");
            }

            // Si ha escogido 0 comprobamos que la lista no está vacía primero
            if (opcion == 0) {
                if (lista.isEmpty()) {
                    System.out.println("Debes introducir una especialidad como mínimo.");
                } else {
                    return lista;
                }
            }

            // Dependiendo de qué ha escogido sumamos a la lista
            // Recorremos la lista y añadimos la que coincida con la opción
            for (Especialidad esp : Especialidad.values()) {
                if ((esp.ordinal() + 1) == opcion) {
                    lista.add(esp);
                    break;
                }
            }

            // Imprimimos la lista
            System.out.println("Lista de especialidades añadidas:");
            for (Especialidad esp : lista) {
                System.out.println(esp);
            }
        } while (opcion != 0);
        return lista;
    }

    private static void crearCredenciales(Empleado empleado) {
        // Forma: usuario|contraseña|nombre apellidos|categoria
        // Aquí validamos usr y pass y luego la escribimos en credenciales.txt
        System.out.println("Introduzca un usuario para el registro:");
        String usuario = sc.nextLine().trim();
        System.out.println("Introduzca una contraseña:");
        String pass = sc.nextLine().trim();

        // Si alguno está en blanco paramos el proceso y devolvemos
        // al usuario al menú de secretariado
        if (usuario.isEmpty() || pass.isEmpty()) {
            System.out.println("Error: alguno de los parámetros está en blanco.");
            return;
        }

        // Registramos al usuario en credenciales.txt
        // Utilizamos nio que es más moderno que BufferedWriter
        String categoria;
        if (empleado instanceof Cirujano || empleado instanceof Enfermeria) {
            categoria = "enfermeria";
        } else {
            categoria = "secretariado";
        }
        String texto = "\n" + usuario + "|" + pass + "|" + empleado.getNombre() + " " + empleado.getApellidos() + "|" + categoria;
        try {
            Files.writeString(
                    RUTA_CREDENCIALES, // primero la ruta
                    texto, // el texto a escribir
                    StandardOpenOption.CREATE, // crea el archivo si no existe
                    StandardOpenOption.APPEND // si ya existe, escribe al final
            );
            System.out.println("Usuario registrado correctamente.");
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error de entrada / salida");
        }
    }

    private static void verAlergias() {
        // Leemos todos los ficheros Alergia en alergias/
        // Para ello hacemos un Stream de Files para recorrer cada archivo
        try (Stream<Path> streamArchivos = Files.list(RUTA_ALERGIAS)) {
            if (streamArchivos.findAny().isEmpty()) {
                System.out.println("No hay ninguna Alergia guardada.");
            } else {
                streamArchivos.forEach(path -> {
                    // Dentro de cada archivo hacemos un try con el OIS para cada uno
                    try (var is = Files.newInputStream(path);
                         var ois = new ObjectInputStream(is)) {
                        // Leemos el objeto Alergia (y el objeto asociado Medicamento)
                        Alergia alergia = (Alergia) ois.readObject();
                        System.out.println(alergia.toString());
                        System.out.println("----------");
                    } catch (IOException e) {
                        System.out.println("Error de entrada / salida.");
                    } catch (ClassNotFoundException e) {
                        System.out.println("Directorio no encontrado.");
                    }
                });
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

        private static void nuevaAlergia () {
            // Pedimos los datos al usuario de Alergia y Medicamento
            System.out.println("Introduzca el nombre de la alergia:");
            String nombreAlergia = sc.nextLine();
            System.out.println("Introduzca el nombre del medicamento:");
            String nombreMedicamento = sc.nextLine();
            System.out.println("Introduzca el principio activo:");
            String principioActivo = sc.nextLine();
            System.out.println("Introduzca la dosis máxima (en miligramos):");
            int dosisMaxima = 0;
            try {
                dosisMaxima = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Por favor, introduzca un valor numérico.");
            }

            // Para autodeterminar el ID podemos contar los archivos que hay en alergias/
            // E.g. si no hay ninguno el ID será 1
            long numAlergias = 0;
            try (var stream = Files.list(RUTA_ALERGIAS)) {
                numAlergias = stream.count();
            } catch (IOException e) {
                System.out.println("Error de entrada / salida.");
            }
            int id = (int) numAlergias + 1;

            // Creamos el objeto
            Alergia alergia = new Alergia(
                    id, nombreAlergia, new Medicamento(id, nombreMedicamento, principioActivo, dosisMaxima));

            // Escribimos la Alergia y su Medicamento en un nuevo fichero binario en alergias/
            Path rutaEscritura = FileSystems.getDefault().getPath(RUTA_ALERGIAS+ "/Alergia_" + id);
            try (FileOutputStream fos = new FileOutputStream(rutaEscritura.toFile());
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                // Escribimos
                oos.writeObject(alergia);
                System.out.println("La alergia con ID " + id + " se ha escrito correctamente.");
            } catch (IOException e) {
                System.out.println("Error de entrada / salida.");
            }
        }

        private static void editarAlergia (int id) {
            // Primero tomamos el archivo con el ID pedido
            // Buscamos en la carpeta de forma similar a verAlergias
            try (Stream<Path> streamArchivos = Files.list(RUTA_ALERGIAS)) {
                streamArchivos.forEach(path -> {
                    // Podemos hacer un try con lector y escritor a la vez
                    try (var is = Files.newInputStream(path);
                         var os = Files.newOutputStream(path);
                         var ois = new ObjectInputStream(is);
                         var oos = new ObjectOutputStream(os)){
                        // Leemos el objeto Alergia (y el objeto asociado Medicamento)
                        Alergia alergia = (Alergia) ois.readObject();
                        // Comprobamos si tiene el ID pedido
                        if (alergia.getId() == id) {
                            // Si son iguales pedimos al usuario qué quiere cambiar
                            int opcion = -1;
                            do {
                                // Hacemos menú por si quiere cambiar más de una cosa
                                System.out.println("1. Nombre de la alergia");
                                System.out.println("2. Nombre del medicamento ");
                                System.out.println("3. Principio activo del medicamento");
                                System.out.println("4. Dosis máxima (en mg) del medicamento");
                                System.out.println("Introduzca qué variable quiere cambiar (0 para guardar cambios:");
                                try {
                                    opcion = sc.nextInt();
                                    sc.nextLine();
                                } catch (InputMismatchException e) {
                                    System.out.println("Por favor, introduzca un número (1 - 4, 0 para salir y guardar cambios).");
                                }
                                switch (opcion) {
                                    case 1:
                                        System.out.println("Introduzca el nuevo nombre de la alergia:");
                                        String nuevoNombreAlergia = sc.nextLine();
                                        alergia.setNombre(nuevoNombreAlergia);
                                        break;
                                    case 2:
                                        System.out.println("Introduzca el nuevo nombre del medicamento:");
                                        String nuevoNombreMedicamento = sc.nextLine();
                                        alergia.getMedicamento().setNombre(nuevoNombreMedicamento);
                                        break;
                                    case 3:
                                        System.out.println("Introduzca el nuevo principio activo:");
                                        String nuevoPrincipioActivo = sc.nextLine();
                                        alergia.getMedicamento().setPrincipioActivo(nuevoPrincipioActivo);
                                        break;
                                    case 4:
                                        System.out.println("Introduzca la nueva dosis máxima (en miligramos):");
                                        int nuevaDosisMaxima = 0;
                                        try {
                                            nuevaDosisMaxima = sc.nextInt();
                                            sc.nextLine();
                                        } catch (InputMismatchException e) {
                                            System.out.println("Por favor, introduzca un número válido.");
                                        }
                                        alergia.getMedicamento().setDosisMax(nuevaDosisMaxima);
                                        break;
                                }
                            } while (opcion != 0);

                            // Una vez hechos los cambios, escribimos el nuevo objeto Alergia
                            Path rutaEscritura = FileSystems.getDefault().getPath(RUTA_ALERGIAS+ "/Alergia_" + id);
                            oos.writeObject(alergia);
                            System.out.println("Alergia con ID " + id + " cambiado y guardado correctamente.");
                        }
                    } catch (IOException e) {
                        System.out.println("Error de entrada / salida.");
                    } catch (ClassNotFoundException e) {
                        System.out.println("Directorio no encontrado.");
                    }
                });
            } catch (IOException e) {
                System.out.println("Error de entrada / salida");
                e.printStackTrace();
            }
        }

        private static void logout () {
            // Cambiamos la sesión a Invitado
            sesionActual.setPerfil(Perfil.INVITADO);
        }
    }
