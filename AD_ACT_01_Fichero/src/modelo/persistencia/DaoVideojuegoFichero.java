package modelo.persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import modelo.entidad.Videojuego;

public class DaoVideojuegoFichero {

	private static final String VIDEOJUEGO_FICHERO = "Videojuegos.txt";
	
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
		}catch(Exception e) {
			throw e;
		}
	}
}
