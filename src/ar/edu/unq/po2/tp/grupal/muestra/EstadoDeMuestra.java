package ar.edu.unq.po2.tp.grupal.muestra;

import ar.edu.unq.po2.tp.grupal.revision.Revision;

/**
 * Clase que representa el estado de una muestra.
 * @author Franco Marengo
 *
 */
public abstract class EstadoDeMuestra {
	
	/**
	 * String que representa la descripción del nombre del EstadoDeMuestra.
	 */
	private String descripcion;
	
	/**
	 * Constructor de EstadoDeMuestra
	 * @param descripcion Un String que representa la descripción del nombre del estado de muestra.
	 */
	public EstadoDeMuestra(String descripcion) {
		super();
		this.setDescripcion(descripcion);
	}
	
	/**
	 * Método que permite calcular la opinión con mayor cantidad de votos en común de la lista de revisiones
	 * de 'muestra'.
	 * @param muestra Una Muestra para poder analizar.
	 * @return Un String que representa la descripción de la opinión con la mayor cantidad de votos en común.
	 * @see ar.edu.unq.po2.tp.grupal.muestra.Muestra Muestra
	 */
	public abstract String obtenerResultadoActual(Muestra muestra);
	
	/**
	 * Método que analiza si 'revision' esta habilitada para opinar de 'muestra' según el estado de muestra y el nivel
	 * del usuario que realizó la 'revision'.
	 * @param revision Una Revision a analizar.
	 * @param muestra Una Muestra a analizar.
	 * @throws Exception Si el usuario no esta habilitado para opinar segun su nivel y el estado de la muestra.
	 * @see ar.edu.unq.po2.tp.grupal.muestra.Muestra Muestra
	 * @see ar.edu.unq.po2.tp.grupal.revision.Revision Revision
	 */
	public abstract void recibirRevision(Revision revision, Muestra muestra) throws Exception;
	
	/**
	 * Método que retorna la descripción del nombre del EstadoDeMuestra.
	 * @return Un String que representa la descripción del nombre de EstadoDeMuestra.
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * Guarda la descripción del nombre de EstadoDeMuestra.
	 * @param descripcion Un String que representa la descripción del nombre del EstadoDeMuestra.
	 */
	private void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
