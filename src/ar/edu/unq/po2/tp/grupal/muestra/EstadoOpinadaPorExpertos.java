package ar.edu.unq.po2.tp.grupal.muestra;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.tp.grupal.revision.Revision;

/**
 * Un EstadoDeMuestra que representa cuando una Muestra ya fue opinada por al menos un experto y ningun usuario b�sico
 * puede opinar sobre �sta.
 * @author Franco Marengo 
 */
public class EstadoOpinadaPorExpertos extends EstadoDeMuestra {

	/**
	 * Una lista de revisiones realizadas por expertos.
	 */
	private List<Revision> revisionesDeExpertos;

	/**
	 * Constructor del EstadoDeMuestra EstadoOpinadaPorExpertos.
	 * @param revision
	 * @see ar.edu.unq.po2.tp.grupal.EstadoDeMuestra EstadoDeMuestra
	 */
	public EstadoOpinadaPorExpertos(Revision revision) {
		super("Muestra opinada por expertos");
		this.setRevisionesDeExpertos(new ArrayList<Revision>());
		this.getRevisionesDeExpertos().add(revision);
	}
	
	public String obtenerResultadoActual(Muestra muestra) {
		if (this.getRevisionesDeExpertos().size() > 1) {
			return "No definido";
		} else {
			return this.getRevisionesDeExpertos().get(0).getOpinion().getDescripcion();
		}
	}
	
	public void recibirRevision(Revision revision, Muestra muestra) throws Exception {
		// Si el usuario que realizo 'revision' es experto y la revisi�n coincide con al menos una revisi�n anterior
		// de otro experto, entonces el EstadoDeMuestra cambia a verificada y se agrega la 'revision' a 'muestra'
		if (revision.getNivelDeUsuario().esExperto() && coincideConOpinionDeExperto(revision)) {
			muestra.agregarRevision(revision);
			muestra.cambiarEstado(new EstadoVerificada(revision.getOpinion().getDescripcion()));
			muestra.notificarVerificacion();
		// Si el usuario que realizo 'revision' solo es experto pero no coincide con al menos una revisi�n anterior de
	    // otro experto, entonces solo se agrega 'revision' a 'muestra' y a la variable 'revisionesDeExpertos'.
		} else if (revision.getNivelDeUsuario().esExperto()) {
		    muestra.agregarRevision(revision);
		    this.getRevisionesDeExpertos().add(revision);
		// Si el usuario que realizo 'revision' no es experto, entonces se lanza una excepci�n.
		} else if (!revision.getNivelDeUsuario().esExperto()) {
			throw new Exception();
		}
	}
	
	/**
	 * Un m�todo que evalua si la Revisi�n 'rev' coincide con alguna revision realizada anteriormente
	 * por un usuario nivel experto.
	 * @param rev Una Revision realizada por un usuario nivel experto.
	 * @return Un valor Booleano que representa si 'rev' coincidi� con al menos alguna otra revision de
	 *         un usuario nivel experto.
	 */
	private boolean coincideConOpinionDeExperto(Revision rev) {
		// Evalua para cada 'revision' en 'revisionesDeExpertos' si 'rev' coincide con esta.
		for(Revision revision:this.getRevisionesDeExpertos()) {
			if (revision.getOpinion().getDescripcion().equals(rev.getOpinion().getDescripcion())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * M�todo que retorna las revisiones realizadas anteriormente por expertos.
	 * @return Una colecci�n de tipo List de Revision que representa las anteriores revisiones realizadas por expertos.
	 */
	public List<Revision> getRevisionesDeExpertos() {
		return revisionesDeExpertos;
	}

	/**
	 * Guarda una colecci�n de tipo List con revisiones de expertos.
	 * @param revisionesDeExpertos Una colecci�n de tipo List que contiene revisiones realizadas por expertos.
	 */
	private void setRevisionesDeExpertos(List<Revision> revisionesDeExpertos) {
		this.revisionesDeExpertos = revisionesDeExpertos;
	}
}
