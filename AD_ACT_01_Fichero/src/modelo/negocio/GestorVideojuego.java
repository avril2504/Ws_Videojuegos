package modelo.negocio;

import modelo.entidad.Videojuego;

import modelo.persistencia.DaoVideojuegoFichero;

public class GestorVideojuego {

	private DaoVideojuegoFichero dvf;

	/**
	 * Método que valida un usuario pasado por parametro contra un usuario guardado
	 * en la persistencia. Un usuario esta validado cuando el nombre y el password
	 * guardado coincide con el usuario pasado por parametro
	 * 
	 * @param u usuario a validar/comparar
	 * @return <b>0</b> el usuario no existe, <b>1</b> el usuario existe y es
	 *         valido, <b>2</b> el usuario existe pero no es valido y <b>666</b> en
	 *         caso de que haya algún problema en el de entrada salida
	 */
	public int validar(Videojuego v) {
		dvf = new DaoVideojuegoFichero();
		try {
			Videojuego uFichero = dvf.getByVideojuego(v.getNombre());
			if (uFichero == null) {
				return 0;
			}

			if (uFichero.equals(v)) {
				return 1;
			} else {
				return 2;
			}
		} catch (Exception e) {
			return 666;
		}
	}

	/**
	 * Método que guarda un videojuego pasado por parámetro validando sus atributos.
	 * 
	 * @param v El videojuego a guardar. Debe tener un nombre, compañía, y nota válidos.
	 * @return <b>0</b> si el videojuego pasado por parámetro es nulo.
	 *         <b>1</b> si el nombre está vacío, solo tiene espacios en blanco o tiene menos de 3 caracteres.
	 *         <b>2</b> si el nombre de la compañía está vacío, solo tiene espacios en blanco o tiene menos de 5 caracteres.
	 *         <b>3</b> si la nota está fuera del rango permitido (menor a 0 o mayor a 100).
	 *         <b>4</b> si el videojuego se ha guardado correctamente.
	 *         <b>666</b> en caso de que ocurra una excepción de entrada/salida o cualquier otro error inesperado.
	 */
	public int guardar(Videojuego v) {
		if (v == null) {
			return 0;
		}

		dvf = new DaoVideojuegoFichero();
		try {

			if (v.getNombre() == null && v.getNombre().isBlank() && v.getNombre().length() < 3) {
				return 1;
			}

			else if (v.getCompania() == null && v.getCompania().isBlank() && v.getCompania().length() < 5) {
				return 2;
			}

			else if (v.getNota() < 0 && v.getNota() > 100) {
				return 3;
			} else {
				dvf.registrarVideojuego(v);
				return 4;
			}
		} catch (Exception e) {
			return 666;
		}
	}

}
