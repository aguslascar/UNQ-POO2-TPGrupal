package ar.edu.unq.po2.tp.grupal.zonaDeCobertura;

public class Ubicacion {
	private double x, y;

	public Ubicacion(double x, double y) {
		this.x = x;
		this.y = y;
	}

	// --------Getters y Setters-------------------------------------------------

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	// --------------------------------------------------------------------------------------------

	public static double distancia(Ubicacion a, Ubicacion b) { // sacar static
		double ct1 = a.x - b.x; // ct = cateto
		double ct2 = a.y - b.y;
		double hip = Math.sqrt(ct1 * ct1 + ct2 * ct2);
		return Math.round(hip);
	}

}
