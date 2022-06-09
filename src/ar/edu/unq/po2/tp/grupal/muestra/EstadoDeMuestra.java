package ar.edu.unq.po2.tp.grupal.muestra;

import ar.edu.unq.po2.tp.grupal.revision.Revision;

/**
 * Clase que representa el estado de una muestra.
 * @author Franco Marengo
 *
 */
public abstract class EstadoDeMuestra {
	
	/**
	 * String que representa la descripci�n del nombre del EstadoDeMuestra.
	 */
	private String descripcion;
	
	/**
	 * Constructor de EstadoDeMuestra
	 * @param descripcion Un String que representa la descripci�n del nombre del estado de muestra.
	 */
	public EstadoDeMuestra(String descripcion) {
		super();
		this.setDescripcion(descripcion);
	}
	
	/**
	 * M�todo que permite calcular la opini�n con mayor cantidad de votos en com�n de la lista de revisiones
	 * de 'muestra'.
	 * @param muestra Una Muestra para poder analizar.
	 * @return Un String que representa la descripci�n de la opini�n con la mayor cantidad de votos en com�n.
	 * @see ar.edu.unq.po2.tp.grupal.muestra.Muestra Muestra
	 */
	public abstract String obtenerResultadoActual(Muestra muestra);
	
	/**
	 * M�todo que analiza si 'revision' esta habilitada para opinar de 'muestra' seg�n el estado de muestra y el nivel
	 * del usuario que realiz� la 'revision'.
	 * @param revision Una Revision a analizar.
	 * @param muestra Una Muestra a analizar.
	 * @throws Exception Si el usuario no esta habilitado para opinar segun su nivel y el estado de la muestra.
	 * @see ar.edu.unq.po2.tp.grupal.muestra.Muestra Muestra
	 * @see ar.edu.unq.po2.tp.grupal.revision.Revision Revision
	 */
	public abstract void recibirRevision(Revision revision, Muestra muestra) throws Exception;
	
	/**
	 * M�todo que retorna la descripci�n del nombre del EstadoDeMuestra.
	 * @return Un String que representa la descripci�n del nombre de EstadoDeMuestra.
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * Guarda la descripci�n del nombre de EstadoDeMuestra.
	 * @param descripcion Un String que representa la descripci�n del nombre del EstadoDeMuestra.
	 */
	private void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
