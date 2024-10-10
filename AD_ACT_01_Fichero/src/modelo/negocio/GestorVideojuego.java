package modelo.negocio;

import java.util.List;

import modelo.entidad.Videojuego;
import modelo.persistencia.DaoVideojuegoFichero;

public class GestorVideojuego {

    private DaoVideojuegoFichero dvf;

    /**
     * Método que valida un videojuego pasado por parámetro contra un videojuego
     * guardado en la persistencia. Un videojuego está validado cuando el nombre y
     * los atributos guardados coinciden con el videojuego pasado por parámetro.
     * 
     * @param v videojuego a validar/comparar
     * @return <b>0</b> si el videojuego no existe, <b>1</b> si el videojuego
     *         existe y es válido, <b>2</b> si el videojuego existe pero no es
     *         válido, y <b>666</b> en caso de que ocurra algún problema de entrada/salida.
     */
    public int validar(Videojuego v) {
        dvf = new DaoVideojuegoFichero();
        try {
            Videojuego vFichero = dvf.getByVideojuego(v.getNombre());
            if (vFichero == null) {
                return 0; 
            }

            if (vFichero.equals(v)) {
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

            if (v.getCompania() == null && v.getCompania().isBlank() && v.getCompania().length() < 5) {
                return 2; 
            }

            if (v.getNota() < 0 && v.getNota() > 100) {
                return 3; 
            }

            dvf.registrarVideojuego(v); 
            return 4; 
        } catch (Exception e) {
            return 666; 
        }
    }
    
    /**
     * Método que lista todos los videojuegos desde la persistencia (fichero).
     * 
     * @return una lista de videojuegos, o <b>null</b> si hay un problema con la persistencia.
     */
    public List<Videojuego> listarVideojuegos() {
        try {
            return dvf.listaViediojuego();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    
    
    
}
