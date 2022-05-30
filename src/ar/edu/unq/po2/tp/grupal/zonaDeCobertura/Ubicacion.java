package ar.edu.unq.po2.tp.grupal.zonaDeCobertura;

import java.util.ArrayList;

public class Ubicacion {
	private int x, y;

	public Ubicacion(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// --------Getters y Setters-------------------------------------------------

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	// --------------------------------------------------------------------------------------------

	public static int distanciaEntre(Ubicacion a, Ubicacion b) { // sacar static
		int ct1 = (int) (a.getX() - b.getX()); // ct = cateto
		int ct2 = (int) (a.getY() - b.getY());
		int hip = (int) Math.sqrt(ct1 * ct1 + ct2 * ct2);
		return hip;
	}

	public ArrayList<Ubicacion> seEncuentranAMenosDe(int n, ArrayList<Ubicacion> ubicaciones) {
		Ubicacion ubicacionActual = new Ubicacion(this.getX(), this.getY());
		ArrayList<Ubicacion> estabanAmenosDe = new ArrayList<Ubicacion>();
		for (Ubicacion ubicacion : ubicaciones) {
			if (distanciaEntre(ubicacionActual, ubicacion) < n) {
				estabanAmenosDe.add(ubicacion);
			}
		}
		return estabanAmenosDe;
	}

}
