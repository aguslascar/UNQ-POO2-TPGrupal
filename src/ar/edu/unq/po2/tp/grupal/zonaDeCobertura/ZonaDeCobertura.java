package ar.edu.unq.po2.tp.grupal.zonaDeCobertura;

import java.util.ArrayList;

public class ZonaDeCobertura {
	String nombre;
	Ubicacion epicentro;
	int radio;
	
	ArrayList<Muestra> muestras = new ArrayList<Muestra>();
	ArrayList<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();

	public ZonaDeCobertura(String nombre, Ubicacion epicentro, int radio, ArrayList<Muestra> muestras, ArrayList<Ubicacion> ubicaciones) {
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

   //------------------------------------------------------------------------------

	
}
