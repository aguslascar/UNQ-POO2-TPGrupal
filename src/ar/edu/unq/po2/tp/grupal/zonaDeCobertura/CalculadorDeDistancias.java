package ar.edu.unq.po2.tp.grupal.zonaDeCobertura;

import java.util.ArrayList;

import ar.edu.unq.po2.tp.grupal.muestra.*;

/**
 * Esta clase representa a un calculador de distancias, que se encanga de
 * calcular la distancia entre dos ubicaciones, de devolver las ubicaciones
 * menos a cierta distancia y de devolver todas las muestras a menos de cierta
 * distancia.
 * 
 * @author Leonardo Medici
 *
 */
public class CalculadorDeDistancias {

	/**
	 * Inicializa un calculador de distancias.
	 */
	public CalculadorDeDistancias() {
		super();
	}

	/**
	 * Devuelve la distancia de 2 ubicaciones.
	 * 
	 * @param a Es el primer ubicación a comparar.
	 * @param b Es la segunda ubicación a comparar.
	 * @return Un int que dice la distancia entre las 2 ubicaciones.
	 */
	public int distanciaEntre(Ubicacion a, Ubicacion b) { // sacar static
		int ct1 = (int) (a.getLongitud() - b.getLongitud()); // ct = cateto
		int ct2 = (int) (a.getLatitud() - b.getLatitud());
		int hip = (int) Math.sqrt(ct1 * ct1 + ct2 * ct2);
		return hip;
	}

	/**
	 * Conoce, a partir de una lista de ubicaciones, aquellas que se encuentran a
	 * menos de x metros, o kilómetros.
	 * 
	 * @param puntoInicial Ubicación desde la que se pide calcular.
	 * @param n            Los kilometros o metros que se pide cubrir.
	 * @param ubicaciones  La lista de ubicaciones a buscar.
	 * @return Las ubicaciones a las que se encontró a menos de la distancia dada.
	 */
	public ArrayList<Ubicacion> seEncuentranAMenosDe(Ubicacion puntoInicial, int n, ArrayList<Ubicacion> ubicaciones) {
		Ubicacion ubicacionActual = puntoInicial;
		ArrayList<Ubicacion> estabanAmenosDe = new ArrayList<Ubicacion>();
		for (Ubicacion ubicacion : ubicaciones) {
			if (distanciaEntre(ubicacionActual, ubicacion) < n) {
				estabanAmenosDe.add(ubicacion);
			}
		}
		return estabanAmenosDe;
	}

	// Dado una muestra, conoce todas las muestras obtenidas a menos de x metros o
	// kilómetros.
	/**
	 * 
	 * @param m Una muestra con la que se pide las demás muestras de su alrededor a
	 *          menos de x distancia.
	 * @param n La distancia que pide cubrir la busqueda de muestras a partir de la
	 *          muestra dada.
	 * @return Todas las muestras que estaban a n metros o kilómetros de la muestra
	 *         dada.
	 */
	public ArrayList<Muestra> muestrasObtenidasA(Muestra m, int n, ArrayList<Muestra> muestras) {
		Ubicacion ubicacionActualMuestra = m.getUbicacion();
		ArrayList<Muestra> muestrasObtenidas = new ArrayList<Muestra>();
		for (Muestra muestra : muestras) {
			if (distanciaEntre(ubicacionActualMuestra, muestra.getUbicacion()) < n) {
				muestrasObtenidas.add(muestra);
			}
		}
		return muestrasObtenidas;
	}

}
