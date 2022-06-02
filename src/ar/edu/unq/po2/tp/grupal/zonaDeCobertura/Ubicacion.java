package ar.edu.unq.po2.tp.grupal.zonaDeCobertura;

public class Ubicacion {
	
	private int x, y;
	
	

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
