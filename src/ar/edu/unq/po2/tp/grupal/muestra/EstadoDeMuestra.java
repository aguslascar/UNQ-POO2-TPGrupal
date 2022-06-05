package ar.edu.unq.po2.tp.grupal.muestra;

import ar.edu.unq.po2.tp.grupal.revision.Revision;

public abstract class EstadoDeMuestra {
	
	private String descripcion;
	
	public EstadoDeMuestra(String descripcion) {
		super();
		this.setDescripcion(descripcion);
	}
	
	public abstract String obtenerResultadoActual(Muestra muestra);
	
	public abstract void recibirRevision(Revision revision, Muestra muestra) throws Exception;
	
	public String getDescripcion() {
		return descripcion;
	}
	
	private void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
