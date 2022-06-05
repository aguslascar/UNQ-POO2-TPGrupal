package ar.edu.unq.po2.tp.grupal.zonaDeCobertura;

import java.util.ArrayList;

public class ZonaDeCobertura {
	String nombre;
	Ubicacion epicentro;
	int radio;

	ArrayList<Muestra> muestras = new ArrayList<Muestra>();
	ArrayList<Ubicacion> ubicaciones = new ArrayList<Ubicacion>(); // Usar radio

	public ZonaDeCobertura(String nombre, Ubicacion epicentro, int radio, ArrayList<Muestra> muestras,
			ArrayList<Ubicacion> ubicaciones) {
		this.nombre = nombre;
		this.epicentro = epicentro;
		this.radio = radio;
		this.muestras = muestras;
		this.ubicaciones = ubicaciones;
	}

	// --------Getters-------------------------------------------------

	public String getNombre() {
		return nombre;
	}

	public Ubicacion getEpicentro() {
		return epicentro;
	}

	public int getRadio() {
		return radio;
	}

	public ArrayList<Muestra> getMuestras() {
		return muestras;
	}

	public ArrayList<Ubicacion> getUbicaciones() {
		return ubicaciones;
	}

	// ------------------------------------------------------------------------------

	public Boolean perteneceAUbicaciones(Ubicacion ubicacion, ArrayList<Ubicacion> ubicaciones) {
		Boolean seSolapa = false;
		for (Ubicacion ubicacionDeLista : ubicaciones) {
			if (ubicacion == ubicacionDeLista) {
				seSolapa = true;
			}
		}
		return seSolapa;
	}

	public Boolean seSolapa(ZonaDeCobertura zona1, ZonaDeCobertura zona2) {
		Boolean esSolapada = false;
		ArrayList<Ubicacion> ubicacionesDeZona2 = zona2.getUbicaciones();
		for (Ubicacion ubicacionDeZona1 : zona1.getUbicaciones()) {
			if (this.perteneceAUbicaciones(ubicacionDeZona1, ubicacionesDeZona2)) {
				esSolapada = true;
			}
		}
		return esSolapada;
	}

	public ArrayList<ZonaDeCobertura> zonasSolapadas(ArrayList<ZonaDeCobertura> zonas) {
		ArrayList<ZonaDeCobertura> seSolapaCon = new ArrayList<ZonaDeCobertura>();
		for (ZonaDeCobertura zona : zonas) {
			if (this.seSolapa(this, zona)) {
				seSolapaCon.add(zona);
			}
		}
		return seSolapaCon;
	}

}
