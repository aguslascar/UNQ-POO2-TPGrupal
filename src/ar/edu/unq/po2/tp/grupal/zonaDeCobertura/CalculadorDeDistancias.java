package ar.edu.unq.po2.tp.grupal.zonaDeCobertura;

import java.util.ArrayList;

public class CalculadorDeDistancias {
	
	private AplicacionWeb  aplicacion;

	public CalculadorDeDistancias() {

	}
	
	public CalculadorDeDistancias(AplicacionWeb aplicacion) {
		this.aplicacion = aplicacion;
	}

	public AplicacionWeb getAplicacion() {
		return aplicacion;
	}

	// Devuelve la distancia de 2 ubicaciones.
	public int distanciaEntre(Ubicacion a, Ubicacion b) { // sacar static
		int ct1 = (int) (a.getLongitud() - b.getLongitud()); // ct = cateto
		int ct2 = (int) (a.getLatitud() - b.getLatitud());
		int hip = (int) Math.sqrt(ct1 * ct1 + ct2 * ct2);
		return hip;
	}

	// Conoce, a partir de una lista de ubicaciones, aquellas que se encuentran a
	// menos de x metros, o kilómetros.
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

	// Dado una muestra, conocer todas las muestras obtenidas a menos de x metros o
	// kilómetros.
	/**
	 * 
	 * @param muestra
	 * @param n
	 * @return
	 */
	public ArrayList<Muestra> muestrasObtenidasA(Muestra m, int n) {
		Ubicacion ubicacionActualMuestra = m.getUbicacion();
		ArrayList<Muestra> muestrasObtenidas = new ArrayList<Muestra>();
		for (Muestra muestra : this.getAplicacion().getMuestras()) {
			if (distanciaEntre(ubicacionActualMuestra, muestra.getUbicacion()) < n) {
				muestrasObtenidas.add(muestra);
			}
		}
		return muestrasObtenidas;
	}

}
