package ar.edu.unq.po2.tp.grupal.ong;

import java.util.ArrayList;


import ar.edu.unq.po2.tp.grupal.muestra.Muestra;
import ar.edu.unq.po2.tp.grupal.zonaDeCobertura.Ubicacion;
import ar.edu.unq.po2.tp.grupal.zonaDeCobertura.ZonaDeCobertura;

/**
 * Esta clase representa a una organización, que se encarga de realizar una
 * funcionalidad externa cada vez que se añada una muestra o se valide una
 * muestra.
 * 
 * @author Leonardo Medici
 *
 */
public class Ong implements Observer {

	/**
	 * ZonasConocidas de tipo ZonaDeCobertura que denota las zonas a las que están
	 * subscriptas.
	 */
	private ArrayList<ZonaDeCobertura> zonasSubscriptas = new ArrayList<ZonaDeCobertura>();
	/**
	 * Ubicacion del tipo Ubicacion que denota en donde está ubicada la
	 * organizacion.
	 */
	private Ubicacion ubicacion;

	/**
	 * Tipo del tipo TipoDeOrg que denota el tipo de la organizacion.
	 */
	private TipoDeOrg tipo;

	/**
	 * Trabajadores del tipo Int denota la cantidad de trabajadores de la
	 * organización.
	 */
	private int trabajadores;
	/**
	 * Funcionalidad externa del tipo FuncionalidadExterna que es llamada cada vez
	 * que se añade una muestra.
	 */

	private FuncionalidadExterna funcionalidadMuestra;

	/**
	 * Funcionalidad externa del tipo FuncionalidadExterna que es llamada cada vez
	 * que se valida una muestra.
	 */
	private FuncionalidadExterna funcionalidadValidacion;

	/**
	 * Constructor de una nueva organización.
	 * 
	 * @param nombre El nombre de la organización.
	 */
	public Ong(Ubicacion ubicacion, TipoDeOrg tipo, int trabajadores, FuncionalidadExterna muestra,
			FuncionalidadExterna validacion) {
		this.ubicacion = ubicacion;
		this.tipo = tipo;
		this.trabajadores = trabajadores;
		this.setFuncionalidadMuestra(muestra);
		this.setFuncionalidadValidacion(validacion);
	}

	// ---------------Getters y Setters-------------------------------------

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public TipoDeOrg getTipoDeOrg() {
		return tipo;
	}

	public int getCantTrabajadores() {
		return trabajadores;
	}


	public FuncionalidadExterna getFuncionalidadExternaMuestra() {
		return funcionalidadMuestra;

	}

	public FuncionalidadExterna getFuncionalidadExternaValidacion() {
		return funcionalidadValidacion;
	}


	public void setFuncionalidadMuestra(FuncionalidadExterna nuevaFuncionalidad) {
		funcionalidadMuestra = nuevaFuncionalidad;

	}

	public void setFuncionalidadValidacion(FuncionalidadExterna nuevaFuncionalidad) {
		funcionalidadValidacion = nuevaFuncionalidad;
	}
	
	public ArrayList<ZonaDeCobertura> getZonasSubscriptas() {
		return zonasSubscriptas;
	}

	// ------------------------------------------------------------------------------
	/**
	 * Llama a la funcionalidad externa de muestra.
	 * @param zonaDeCobertura 
	 * @param muestra2 
	 */
	@Override
	public void nuevaMuestra(ZonaDeCobertura zona, Muestra muestra) {
		funcionalidadMuestra.nuevoEvento(this, zona, muestra);

	}

	/**
	 * Llama a la funcionalidad externa de validación.
	 */
	@Override
	public void nuevaValidacion(ZonaDeCobertura zona, Muestra muestra) {
		funcionalidadValidacion.nuevoEvento(this, zona, muestra);

	}

	/**
	 * Agrega una nueva Zona de cobertura a la lista de zonas subscriptas.
	 * 
	 * @param zonaDeCobertura zona de cobertura a agregar.
	 */
	public void registrarZona(ZonaDeCobertura zonaDeCobertura) {
		zonasSubscriptas.add(zonaDeCobertura);

	}

	/**
	 * Remueve de la lista de zonas subscriptas una zona a la que estaba subscripta.
	 * 
	 * @param zonaDeCobertura zona a remover.
	 */
	public void desuscribirZona(ZonaDeCobertura zonaDeCobertura) {
		zonasSubscriptas.remove(zonaDeCobertura);

	}

}
