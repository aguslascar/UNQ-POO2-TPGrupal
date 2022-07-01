package ar.edu.unq.po2.tp.grupal.muestra;

import ar.edu.unq.po2.tp.grupal.revision.Revision;

/**
 * Un EstadoDeMuestra que representa cuando una muestra fue opinada por expertos y en alg�n momento dos expertos
 * coincidieron en su revisi�n.
 * @author Franco Marengo
 *
 */
public class EstadoVerificada extends EstadoDeMuestra {
	
	/**
	 * Un String que representa la descripci�n de la opini�n que finalmente fue verificada.
	 */
	private String opinionVerificada;

	/**
	 * Constructor de EstadoDeMuestra EstadoVerificada.
	 * @param opinionVerificada Un String que representa la descripcion de la opinion verificada.
	 */
	public EstadoVerificada(String opinionVerificada) {
		super("Muestra verificada");
		this.setOpinionVerificada(opinionVerificada);
	}
	
	public String obtenerResultadoActual(Muestra muestra) {
		return opinionVerificada;
	}
	
	public void recibirRevision(Revision revision, Muestra muestra) throws Exception {
		throw new Exception();
	}
	
	/**
	 * M�todo que retorna la opini�n verificada en la que coincidieron dos expertos.
	 * @return Un String que representa la opini�n en la que coincidieron dos expertos.
	 */
	public String getOpinionVerificada() {
		return opinionVerificada;
	}

	/**
	 * Guarda la opini�n en la que coincidieron dos expertos, en la variable opinionVerificada
	 * @param opinionVerificada Un String que representa la descripci�n de la opini�n en la que coincidieron dos
	 *                          expertos.
	 */
	private void setOpinionVerificada(String opinionVerificada) {
		this.opinionVerificada = opinionVerificada;
	}
}
