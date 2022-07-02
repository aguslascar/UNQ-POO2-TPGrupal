package ar.edu.unq.po2.tp.grupal.zonaDeCobertura;

import java.util.ArrayList;
import ar.edu.unq.po2.tp.grupal.muestra.*;
import ar.edu.unq.po2.tp.grupal.ong.Observer;
import ar.edu.unq.po2.tp.grupal.ong.Ong;

/**
 * Esta clase representa a una zona de cobertura, que se encanga de guardar las
 * ubicaciones a partir de su epicentro, sabe cuando se solapa con otra zona,
 * guarda las muestras que suben los usuarios y también guarda organizaciones
 * que quieran saber noticias sobre una nueva muestra o si se valida una
 * muestra.
 * 
 * @author Leonardo Medici
 *
 */
public class ZonaDeCobertura implements ZonaDeCoberturaObservable {
	/**
	 * Nombre de tipo String que representa como se nombró esta zona de cobertura.
	 */
	private String nombre;
	/**
	 * Epicentro del tipo Ubicacion que representa el centro de la zona.
	 */
	private Ubicacion epicentro;
	/**
	 * Radio del tipo int que representa el radio de la zona.
	 */
	private int radio;
	/**
	 * Muestras del tipo ArrayList<Muestra> que representa todas las muestras
	 * registradas en esta zona.
	 */
	private ArrayList<Muestra> muestras = new ArrayList<Muestra>();
	/**
	 * Ubicaciones del tipo ArrayList<Ubicacion> que representa todas las
	 * ubicaciones dentro de la zona.
	 */
	private ArrayList<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
	/**
	 * OngsSubscriptas del tipo ArrayList<Ong> que representa todas las ong que se
	 * registraron a esta zona.
	 */
	private ArrayList<Ong> ongsSubscriptas = new ArrayList<Ong>();

	/**
	 * Constructor de una nueva zona de cobertura.
	 * 
	 * @param nombre          nombre de la zona de cobertura.
	 * @param epicentro       centro de la zona de cobertura.
	 * @param radio           radio del que se generó la zona de cobertura.
	 * @param muestras        muestras que han subido a la zona de cobertura.
	 * @param ubicaciones     que están dentro de la zona de cobertura.
	 * @param OngsSubscriptas las organizaciones que se registraron a esta zona de
	 *                        cobertura.
	 */
	public ZonaDeCobertura(String nombre, Ubicacion epicentro, int radio, ArrayList<Muestra> muestras,
			ArrayList<Ubicacion> ubicaciones, ArrayList<Ong> OngsSubscriptas) {
		this.nombre = nombre;
		this.epicentro = epicentro;
		this.radio = radio;
		this.muestras = muestras;
		this.ubicaciones = ubicaciones;
		this.ongsSubscriptas = OngsSubscriptas;
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
		return ongsSubscriptas;
	}

	// ------------------------------------------------------------------------------

	/**
	 * Calcula la distancia de la Zona de Cobertura con su radio y la devuelve en
	 * Kilometros.
	 * 
	 * @return
	 */
	public int distanciaDeLaZonaEnKM() {
		return (int) (2 * Math.PI * radio);
	}

	/**
	 * Agrega una nueva ubicacion a la lista de ubicaciones de la zona.
	 * 
	 * @param ubicacion Una nueva ubicación a guardar en la zona.
	 */
	public void agregarUbicacion(Ubicacion ubicacion) {
		ubicaciones.add(ubicacion);
	}

	/**
	 * Devuelve true si la zona a comparar se solapa con esta zona.
	 * 
	 * @param zona2 Zona a comparar con esta zona.
	 * @return Un Booleano que representa si esta zona se solapa o no con la zona a
	 *         comparar.
	 */
	public Boolean seSolapa(ZonaDeCobertura zona2) {
		Boolean esSolapada = false;
		ArrayList<Ubicacion> ubicacionesDeZona2 = zona2.getUbicaciones();
		for (Ubicacion ubicacionDeZona1 : this.getUbicaciones()) {
			if (ubicacionesDeZona2.contains(ubicacionDeZona1)) {
				esSolapada = true;
			}
		}
		return esSolapada;
	}

	/**
	 * Regresa de una lista de zonas, todas aquellas con la que se solapa esta zona.
	 * 
	 * @param zonas Una lista de zonas para comparar.
	 * @return Regresa una lista de Zonas de cobertura, con las que solapa esta
	 *         zona.
	 */
	public ArrayList<ZonaDeCobertura> zonasSolapadas(ArrayList<ZonaDeCobertura> zonas) {
		ArrayList<ZonaDeCobertura> seSolapaCon = new ArrayList<ZonaDeCobertura>();
		for (ZonaDeCobertura zona : zonas) {
			if (this.seSolapa(zona)) {
				seSolapaCon.add(zona);
			}
		}
		return seSolapaCon;
	}

	/**
	 * Agrega una muestra a la lista de muestras solo si la ubicacion de la muestra
	 * esta dentro de la lista de ubicaciones de la zona.
	 * 
	 * @param muestra Nueva muestra a agregar a la zona.
	 */
	public void agregarMuestra(Muestra muestra) {
		if (perteneceAZona(muestra.getUbicacion())) {
			muestras.add(muestra);
			this.notificarNuevaMuestra();
		}
	}

	/**
	 * Dice si una ubicación pertenece a la lista de ubicaciones de la zona.
	 * 
	 * @param ubicacion Ubicación a comparar.
	 * @return Un booleano que representa si la ubicación dada esta dentro de las
	 *         ubicaciones de zona.
	 */
	public boolean perteneceAZona(Ubicacion ubicacion) {
		return this.getUbicaciones().contains(ubicacion);
	}

	/**
	 * Dice si una muestra pertenece a la lista de muestras de la zona.
	 * 
	 * @param muestra Muestra a comparar.
	 * @returnUn booleano que representa si la muestra dada esta dentro de las
	 *           muestras de zona.
	 */
	public boolean esMuestraDeZona(Muestra muestra) {
		return this.getMuestras().contains(muestra);
	}

	/**
	 * Registra a una organización dentro de la lista de organizaciones.
	 */
	@Override
	public void registrar(Observer o) {
		this.getOngsSubscriptas().add((Ong) o);
		((Ong)o).registrarZona(this);
	}

	/**
	 * Saca a una organización de la lista de organizaciones suscriptas.
	 */
	@Override
	public void desuscribir(Observer o) {
		this.getOngsSubscriptas().remove((Ong)o);
		((Ong)o).desuscribirZona(this);
	}

	/**
	 * Notifica a todas las organizaciones cuando se añade una nueva muestra.
	 */
	@Override
	public void notificarNuevaMuestra() {
		for (Ong suscriptor : this.getOngsSubscriptas()) {
			suscriptor.nuevaMuestra();
		}
	}

	/**
	 * Notifica a todas las organizaciones cuando se valida una muestra.
	 */
	@Override
	public void notificarValidacion() {
		for (Ong suscriptor : this.getOngsSubscriptas()) {
			suscriptor.nuevaValidacion();
		}
	}

}
