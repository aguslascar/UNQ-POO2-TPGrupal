package ar.edu.unq.po2.tp.grupal.ong;

/**
 * Esta clase enumerativa representa los distintas tipos que pueden tener las
 * orgnizaciones-
 * 
 * @author Leonardo
 *
 */
public enum TipoDeOrg {

	SALUD("Salud"), EDUCATIVA("Educativa"), CULTURAL("Cultural"), ASISTENCIA("Asistencia");

	/**
	 * Un String que representa la descripción del nombre de la instancia de
	 * TipoDeOrg.
	 */
	private String descripcion;

	/**
	 * Constructor de la clase TipoDeOrg que permite representar un nuevo TipoDeOrg
	 * de parte de una organizacion.
	 * 
	 * @param descripcion Un String que representa la descripción del nombre de una
	 *                    instancia de TipoDeOrg.
	 */
	private TipoDeOrg(String descripcion) {
		this.descripcion = descripcion; // No se utiliza un setter debido a que es un enumerativo.
	}

	/**
	 * Método que retorna la descripción del nombre de la instancia de TipoDeOrg.
	 * 
	 * @return Un String representando el nombre de la instancia.
	 */
	public String getDescripcion() {
		return descripcion;
	}

}
