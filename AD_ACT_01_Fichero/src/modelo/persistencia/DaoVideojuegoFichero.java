package modelo.persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Videojuego;

public class DaoVideojuegoFichero {

	private static final String VIDEOJUEGO_FICHERO = "Videojuegos.txt";
	
	
	/**
	 * Método que lee todos los videojuegos almacenados en el fichero "Videojuegos.txt"
	 * y lo agrega a una lista viegojuegos
	 *
	 * @return videojuego que contiene todos los videojuegos leídos del fichero.
	 * @throws Exception si ocurre algún problema en el acceso o la lectura del fichero.
	 */
	public List<Videojuego> listaViediojuego() throws Exception {
	    List<Videojuego> videojuegos = new ArrayList<>();

	    try (FileReader fr = new FileReader(VIDEOJUEGO_FICHERO);
	         BufferedReader br = new BufferedReader(fr)) {

	        String linea = br.readLine();
	        while (linea != null) {
	            String[] cadenaPartida = linea.split("_");

	            if (cadenaPartida.length != 3) {
	                System.err.println("Formato incorrecto en la línea: " + linea);
	                linea = br.readLine();
	                continue;
	            }

	            String nombreVideojuego = cadenaPartida[0];
	            String compania = cadenaPartida[1];
	            int nota;

	            try {
	                nota = Integer.parseInt(cadenaPartida[2]); 
	            } catch (NumberFormatException e) {
	                System.err.println("Nota inválida en la línea: " + linea);
	                linea = br.readLine();
	                continue;  
	            }

	            Videojuego v = new Videojuego();
	            v.setNombre(nombreVideojuego);
	            v.setCompania(compania);
	            v.setNota(nota);
	            videojuegos.add(v);

	            linea = br.readLine();
	        }

	    } catch (Exception e) {
	        throw e;
	    }

	    return videojuegos;  
	}
	
	/**
	 * Método que dado un nombre pasado por parametro busca su coincidencia
	 * en el fichero "Videojuegos.txt" y en caso de que lo encuentre lo devuelve
	 * junto con su compania y nota
	 * @param nombre el nombre a buscar en el fichero
	 * @return videojuego en caso de que este en en fichero, null en caso contrario
	 * @throws Exception, en caso de que haya algún problema en el fichero de 
	 * entrada salida
	 */
	public Videojuego getByVideojuego(String nombre) throws Exception{
		Videojuego videojuego = null;
		
		try(FileReader fr = new FileReader(VIDEOJUEGO_FICHERO);
			BufferedReader br = new BufferedReader(fr)){
			String cadena = br.readLine();
			while(cadena != null) {
				String[] cadenaPartida = cadena.split("_");
				String nombreVideojuego = cadenaPartida[0];
				String compania = cadenaPartida[1];
				int nota = Integer.parseInt(cadenaPartida[2]);
				if(nombre.equals(nombreVideojuego)) {
					videojuego = new Videojuego();
					videojuego.setNombre(nombreVideojuego);
					videojuego.setCompania(compania);
					videojuego.setNota(nota);
					return videojuego;
				}
				cadena = br.readLine();
			}
		} catch (Exception e) {
			throw e;
		}
		
		return null;
		
	}
	
	/**
	 * Método que dado un videojuego lo persista en el fichero "Videojuegos.txt". Se añadirá
	 * a la última línea. Se persistirá en formato "NOMBRE_COMPAÑIA_NOTA"
	 * @param v es el videojuego que queremos persistir
	 * @throws Exception, en caso de que haya algún problema en el fichero de 
	 * entrada salida
	 */
	public void registrarVideojuego(Videojuego v) throws Exception{
		File f = new File(VIDEOJUEGO_FICHERO);
		if(!f.exists()) {
			throw new Exception("Fichero NO existe!");
		}
		try(FileWriter fw = new FileWriter(VIDEOJUEGO_FICHERO,true);
			BufferedWriter bw = new BufferedWriter(fw)){
			bw.write(v.toString());
			bw.write("\n");
		}catch(Exception e) {
			throw e;
		}
	}
}
