package ar.edu.unq.po2.tp.grupal.muestra;

import ar.edu.unq.po2.tp.grupal.revision.Revision;

public class EstadoVerificada extends EstadoDeMuestra {
	
	private String opinionVerificada;

	public EstadoVerificada(String opinionVerificada) {
		super("Muestra verificada");
		this.setOpinionVerificada(opinionVerificada);
	}
	
	public String obtenerResultadoActual(Muestra muestra) {
		return (this.getOpinionVerificada() + " - " + this.getDescripcion());
	}
	
	public void recibirRevision(Revision revision, Muestra muestra) throws Exception {
		throw new Exception();
	}
	
	public String getOpinionVerificada() {
		return opinionVerificada;
	}

	private void setOpinionVerificada(String opinionVerificada) {
		this.opinionVerificada = opinionVerificada;
	}
}
