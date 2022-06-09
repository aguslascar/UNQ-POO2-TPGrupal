package ar.edu.unq.po2.tp.grupal.zonaDeCobertura;

import java.util.ArrayList;

public class ZonaDeCobertura implements ZonaDeCoberturaObservable {
	String nombre;
	Ubicacion epicentro;
	int radio;

	ArrayList<Muestra> muestras = new ArrayList<Muestra>();
	ArrayList<Ubicacion> ubicaciones = new ArrayList<Ubicacion>(); // Usar radio

	private ArrayList<Ong> OngsSubscriptas = new ArrayList<Ong>();

	public ZonaDeCobertura(String nombre, Ubicacion epicentro, int radio, ArrayList<Muestra> muestras,
			ArrayList<Ubicacion> ubicaciones, ArrayList<Ong> OngsSubscriptas) {
		this.nombre = nombre;
		this.epicentro = epicentro;
		this.radio = radio;
		this.muestras = muestras;
		this.ubicaciones = ubicaciones;
		this.OngsSubscriptas = OngsSubscriptas;
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

	public ArrayList<Ong> getOngsSubscriptas() {
		return OngsSubscriptas;
	}

	// ------------------------------------------------------------------------------

	public Boolean seSolapa(ZonaDeCobertura zona1, ZonaDeCobertura zona2) {
		Boolean esSolapada = false;
		ArrayList<Ubicacion> ubicacionesDeZona2 = zona2.getUbicaciones();
		for (Ubicacion ubicacionDeZona1 : zona1.getUbicaciones()) {
			if (ubicacionesDeZona2.contains(ubicacionDeZona1)) {
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

	public void agregarMuestra(Muestra muestra) {
		muestras.add(muestra);
		this.notificar();
	}

	@Override
	public void registrar(Observer o) {
		this.getOngsSubscriptas().add((Ong) o);
	}

	@Override
	public void desuscribir(Observer o) {
		this.getOngsSubscriptas().remove(o);
	}

	@Override
	public void notificar() {
		for (Ong suscriptor : this.getOngsSubscriptas()) {
			suscriptor.update();
		}

	}

}
