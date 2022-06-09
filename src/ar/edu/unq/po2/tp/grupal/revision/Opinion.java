package ar.edu.unq.po2.tp.grupal.revision;

/**
 * Esta clase enumerativa representa las distintas opiniones que pueden realizar los usuarios sobre alguna instancia
 * de Muestra.
 * @author Franco Marengo
 *
 */
public enum Opinion {

	VINCHUCAINFESTANS("Vinchuca Infestans"),
	VINCHUCASORDIDA("Vinchuca Sordida"),
	VINCHUCAGUASAYANA("Vinchuca Guasayana"),
	CHINCHEFOLIADA("Chinche Foliada"),
	PHTIACHINCHE("Phtia-Chinche"),
	NINGUNA("Ninguna"),
	IMAGENPOCOCLARA("Imagen poco clara");
	
	/**
	 * Un String que representa la descripción del nombre de la instancia de Opinion.
	 */
	private String descripcion;
	
	/**
	 * Constructor de la clase Opinion que permite representar una nueva opinion de parte de un usuario.
	 * @param descripcion Un String que representa la descripción del nombre de una instancia de Opinion.
	 */
	private Opinion(String descripcion) {
		this.descripcion = descripcion;    // No se utiliza un setter debido a que es un enumerativo.
	}
	
	/**
	 * Método que retorna la descripción del nombre de la instancia de Opinion.
	 * @return Un String representando el nombre de la instancia.
	 */
	public String getDescripcion() {
		return descripcion;
	}
}
