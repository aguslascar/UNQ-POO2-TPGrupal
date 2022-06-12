package ar.edu.unq.po2.tp.grupal.ong;

import ar.edu.unq.po2.tp.grupal.zonaDeCobertura.Observer;

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
	 * Nombre del tipo string que representa el nombre de esta organización,
	 */
	private String nombre;
	/**
	 * Funcionalidad externa del tipo FuncionalidadExterna que es llamada cada vez
	 * que se añade una muestra.
	 */
	private FuncionalidadExterna muestra;
	/**
	 * Funcionalidad externa del tipo FuncionalidadExterna que es llamada cada vez
	 * que se valida una muestra.
	 */
	private FuncionalidadExterna validacion;

	/**
	 * Constructor de una nueva organización.
	 * 
	 * @param nombre El nombre de la organización.
	 */
	public Ong(String nombre, FuncionalidadExterna muestra, FuncionalidadExterna validacion) {
		this.nombre = nombre;
		this.setFuncionalidadMuestra(muestra);
		this.setFuncionalidadValidacion(validacion);
	}

	// ---------------Getters y Setters-------------------------------------

	public String getNombre() {
		return nombre;
	}

	public FuncionalidadExterna getFuncionalidadExternaMuestra() {
		return muestra;
	}

	public FuncionalidadExterna getFuncionalidadExternaValidacion() {
		return validacion;
	}

	public void setFuncionalidadMuestra(FuncionalidadExterna nuevaFuncionalidad) {
		muestra = nuevaFuncionalidad;
	}

	public void setFuncionalidadValidacion(FuncionalidadExterna nuevaFuncionalidad) {
		validacion = nuevaFuncionalidad;
	}

	// ------------------------------------------------------------------------------
	/**
	 * Llama a la funcionalidad externa de muestra.
	 */
	@Override
	public void update() {
		this.getFuncionalidadExternaMuestra();

	}

	/**
	 * Llama a la funcionalidad externa de validación.
	 */
	@Override
	public void updateValidacion() {
		this.getFuncionalidadExternaValidacion();

	}

}
