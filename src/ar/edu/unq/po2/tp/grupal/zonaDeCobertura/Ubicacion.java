package ar.edu.unq.po2.tp.grupal.zonaDeCobertura;

/**
 * Esta clase representa a una ubicacion, que contiene una latitud y una
 * longitud.
 * 
 * @author Leonardo Medici.
 *
 */
public class Ubicacion {

	/**
	 * Latitud y longitud de una ubicacion.
	 */
	private int x, y;

	/**
	 * Constructor de una nueva ubicación.
	 * 
	 * @param Longitud
	 * @param Latitud
	 */
	public Ubicacion(int x, int y) {
		this.x = x; // longitud
		this.y = y; // latitud
	}

	// --------Getters-------------------------------------------------

	public int getLongitud() {
		return x;
	}

	public int getLatitud() {
		return y;
	}

}
