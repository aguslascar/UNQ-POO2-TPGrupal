package ar.edu.unq.po2.tp.grupal.muestra;

import ar.edu.unq.po2.tp.grupal.revision.Revision;

public class EstadoSinVerificar extends EstadoDeMuestra {

	public EstadoSinVerificar() {
		super("Muestra sin verificar");
	}
	
	public void recibirRevision(Revision revision, Muestra muestra) throws Exception {
		if (revision.getNivelDeUsuario().esExperto()) {
			muestra.cambiarEstado(new EstadoOpinadaPorExpertos(revision));
			muestra.agregarRevision(revision);
		} else {
			muestra.agregarRevision(revision);
		}
	}
}
