package interfaz;

import java.util.Scanner;

import modelo.entidad.Usuario;
import modelo.negocio.GestorUsuario;

public class InterfazUsuario {

	private GestorUsuario gu = null;
	private Scanner scString = new Scanner(System.in);
	private Scanner sc = new Scanner(System.in);

	public void mostrarInterfaz() {
		System.out.println("Bienvenidos a nuestra app :)");
		Usuario usuario = null;
		gu = new GestorUsuario();
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
				System.out.println("Error de acceso. Intentelo mas tarde");
				break;
			}

		}

		System.out.println("Fin de la aplicación");


	}

	private void iniciarAplicacion(Usuario u) {
		System.out.println("--------------------------");
		System.out.println("Perfil de " + u.getNombre());
		System.out.println("--------------------------");
		int opcion = menu();
	}

	private int menu() {
		System.out.println("1 - Registrar usuario");
		System.out.println("2 - Salir del programa");
		
		
		return 0;
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
