package ar.edu.unq.po2.tp.grupal.muestra;

import ar.edu.unq.po2.tp.grupal.revision.Revision;

/**
 * Un EstadoDeMuestra que representa cuando una Muestra fue opinada por usuarios nivel básico o solo opinada por el
 * autor de esta Muestra.
 * @author Franco Marego
 *
 */
public class EstadoSinVerificar extends EstadoDeMuestra {

	/**
	 * Constructor del EstadoDeMuestra EstadoSinVerificar.
	 * @see ar.edu.unq.po2.tp.grupal.EstadoDeMuestra EstadoDeMuestra
	 */
	public EstadoSinVerificar() {
		super("Muestra sin verificar");
	}
	
	public void recibirRevision(Revision revision, Muestra muestra) throws Exception {
		// Valida si el usuario es nivel experto, si es asi, se cambia el estado de 'muestra' y se guarda la revision
		if (revision.getNivelDeUsuario().esExperto()) {
			muestra.cambiarEstado(new EstadoOpinadaPorExpertos(revision));
			muestra.agregarRevision(revision);
		// Si el usuario no es experto, solo se agrega la revisión en 'muestra'.
		} else {
			muestra.agregarRevision(revision);
		}
	}
}
