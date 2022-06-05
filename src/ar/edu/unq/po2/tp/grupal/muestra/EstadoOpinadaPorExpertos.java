package ar.edu.unq.po2.tp.grupal.muestra;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.tp.grupal.revision.Revision;

public class EstadoOpinadaPorExpertos extends EstadoDeMuestra {

	private List<Revision> revisionesDeExpertos;

	public EstadoOpinadaPorExpertos(Revision revision) {
		super("Muestra opinada por expertos");
		this.setRevisionesDeExpertos(new ArrayList<Revision>());
		this.getRevisionesDeExpertos().add(revision);
	}
	
	public void recibirRevision(Revision revision, Muestra muestra) throws Exception {
		if (revision.getNivelDeUsuario().esExperto() && coincideConOpinionDeExperto(revision)) {
			muestra.agregarRevision(revision);
			muestra.cambiarEstado(new EstadoVerificada(revision.getOpinion().getDescripcion()));
		} else if (revision.getNivelDeUsuario().esExperto()) {
		    muestra.agregarRevision(revision);
		    this.getRevisionesDeExpertos().add(revision);
		} else if (!revision.getNivelDeUsuario().esExperto()) {
			throw new Exception();
		} else {
			muestra.agregarRevision(revision);
		}
	}
	
	private boolean coincideConOpinionDeExperto(Revision rev) {
		for(Revision revision:this.getRevisionesDeExpertos()) {
			if (revision.getOpinion().getDescripcion() == rev.getOpinion().getDescripcion()) {
				return true;
			}
		}
		return false;
	}
	
	public List<Revision> getRevisionesDeExpertos() {
		return revisionesDeExpertos;
	}

	private void setRevisionesDeExpertos(List<Revision> revisionesDeExpertos) {
		this.revisionesDeExpertos = revisionesDeExpertos;
	}
}
