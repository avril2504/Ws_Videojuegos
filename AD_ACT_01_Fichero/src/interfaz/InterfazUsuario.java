package interfaz;

import java.util.List;
import java.util.Scanner;

import modelo.entidad.Usuario;
import modelo.entidad.Videojuego;
import modelo.negocio.GestorUsuario;
import modelo.negocio.GestorVideojuego;

public class InterfazUsuario {

    private GestorUsuario gu = null;
    private GestorVideojuego gv = null;
    private Scanner scString = new Scanner(System.in); // Para leer texto
    private Scanner scInt = new Scanner(System.in);    // Para leer números

    public void mostrarInterfaz() {
        System.out.println("Bienvenidos a nuestra app :)");
        Usuario usuario = null;
        gu = new GestorUsuario();
        gv = new GestorVideojuego();
        int respuesta = 0;

        int contador = 0;
        boolean validado = false;

        while (contador < 3 && !validado) {
            usuario = pedirDatos();
            respuesta = gu.validar(usuario);
            switch (respuesta) {
            case 0:
                System.out.println("Usuario no existe");
                break;
            case 1:
                System.out.println("Usuario correcto, bienvenido a la app");
                validado = true;
                iniciarAplicacion(usuario);
                break;
            case 2:
                System.out.println("Usuario y/o password incorrectos");
                contador++;
                break;
            case 666:
                System.out.println("Error de acceso. Intentelo más tarde");
                break;
            }
        }

        System.out.println("Fin de la aplicación");
    }

    private void iniciarAplicacion(Usuario u) {
        System.out.println("--------------------------");
        System.out.println("Perfil de " + u.getNombre());
        System.out.println("--------------------------");
        int opcion = 0;
        do {
            opcion = menu();
            switch (opcion) {
            case 1:
                darAltaUsuario();
                break;
            case 2:
                agregarVideojuego();
                break;
            case 3:
                listarVideojuegos();
                break;
            case 0:
                System.out.println("Saliendo del programa...");
                break;
            default:
                System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void darAltaUsuario() {
        Usuario usuario = pedirDatos();
        int respuesta = gu.guardar(usuario);
        switch (respuesta) {
        case 1:
            System.out.println("Usuario en blanco o con solo espacios en blanco");
            break;
        case 2:
            System.out.println("Password en blanco o con solo espacios en blanco");
            break;
        case 3:
            System.out.println("Usuario guardado con éxito!! :) :)");
            break;
        case 666:
            System.out.println("Error de acceso. Inténtelo más tarde. Código 666");
            break;
        }
    }

    private void agregarVideojuego() {
        System.out.println("----- Agregar Videojuego -----");
        System.out.print("Introduzca el nombre del videojuego: ");
        String nombre = scString.nextLine();

        System.out.print("Introduzca la compañía del videojuego: ");
        String compania = scString.nextLine();

        System.out.print("Introduzca la nota del videojuego (0-100): ");
        int nota = scInt.nextInt();

        Videojuego v = new Videojuego(nombre, compania, nota);
        int resultado = gv.guardar(v);

        switch (resultado) {
            case 1:
                System.out.println("El nombre del videojuego es inválido (debe tener al menos 3 caracteres).");
                break;
            case 2:
                System.out.println("El nombre de la compañía es inválido (debe tener al menos 5 caracteres).");
                break;
            case 3:
                System.out.println("La nota del videojuego debe estar entre 0 y 100.");
                break;
            case 4:
                System.out.println("Videojuego agregado exitosamente.");
                break;
            case 666:
                System.out.println("Error al guardar el videojuego. Inténtelo más tarde.");
                break;
        }
    }

    private void listarVideojuegos() {
        System.out.println("----- Listar Videojuegos -----");
        List<Videojuego> lista = gv.listarVideojuegos();
        if (lista == null || lista.isEmpty()) {
            System.out.println("No hay videojuegos registrados o error al acceder.");
        } else {
            for (Videojuego v : lista) {
                System.out.println("Nombre: " + v.getNombre() + " - Compañía: " + v.getCompania() + " - Nota: " + v.getNota());
            }
        }
    }

    private int menu() {
        System.out.println("\nElija una opción: ");
        System.out.println("1 - Registrar usuario");
        System.out.println("2 - Agregar videojuego");
        System.out.println("3 - Listar videojuegos");
        System.out.println("0 - Salir del programa");
        System.out.print("Opción: ");
        return scInt.nextInt();
    }

    private Usuario pedirDatos() {
        System.out.println("Introduzca el nombre: ");
        String nombre = scString.nextLine();
        System.out.println("Introduzca el password: ");
        String pass = scString.nextLine();
        Usuario u = new Usuario();
        u.setNombre(nombre);
        u.setPassword(pass);
        return u;
    }
}
