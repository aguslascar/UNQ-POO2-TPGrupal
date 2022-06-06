package ar.edu.unq.po2.tp.grupal.muestra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ar.edu.unq.po2.tp.grupal.revision.Revision;

/**
 * Un EstadoDeMuestra que representa cuando una Muestra ya fue opinada por al menos un experto y ningun usuario básico
 * puede opinar sobre ésta.
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
		// Copia la lista de revisiones interna de 'muestra'
				List<Revision> revisionesAVer = this.getRevisionesDeExpertos(); 
				// Crea un HashMap que va a guardar la descripcion de la opinion de cada revisión y
				// un Integer con la cantidad de ocurrencias de la descripcion de la opinion
				HashMap<String, Integer> mapa = new HashMap<>();
				// Si la descripcion de la opinion no se encuentra como clave entonces la agrega 
				// con valor 1 (primera ocurrencia), de lo contrario suma 1 al valor de la clave encontrada
				for (int x = 0; x < revisionesAVer.size(); x++) {        
					String opinionAver = revisionesAVer.get(x).getOpinion().getDescripcion();
						mapa.put(opinionAver, 1);
					}
				// Inicializa con un valor cualquiera que posteriormente se va a modificar
				String opinionMayorCantVotosHastaAhora = "No definido";
				// Inicializa el mayor numero de ocurrencias como 0 para que la primera key se guarde correctamente
				int mayor = 0;
				// Inicializa el segundo mayor en caso de empate entre opiniones
				int segundoMayor = 0;
				// Si el valor de la key en 'entry' es mayor a la variable 'mayor', entonces la variable 'mayor' y la
				// variable opinionMayorCantVotosHastaAhora se settean con el valor y la key del 'entry' analizado,
				// en caso de que el valor de la key en 'entry' sea igual al de la variable 'mayor' entonces la variable
				// 'segundoMayor' se settea con el valor de la key del 'entry' analizado
				for (HashMap.Entry<String, Integer> entry : mapa.entrySet()) {
					if (entry.getValue() > mayor) {
						mayor = entry.getValue();
						opinionMayorCantVotosHastaAhora = entry.getKey();
					} else if (entry.getValue() == mayor) {
						segundoMayor = entry.getValue();
					}
				}
				// Si finalmente las variables 'mayor' y 'segundoMayor' quedan como iguales después de analizar todos los
				// 'entry' del HashMap, entonces se retorna como resultado 'No definido', de lo contrario se retorna
				// la key con el mayor valor, significando que es la opinión que tuvo mayor cantidad de votos
				if (mayor == segundoMayor) {
					return ("No definido");
				} else {
					return (opinionMayorCantVotosHastaAhora);
				}
	}
	
	public void recibirRevision(Revision revision, Muestra muestra) throws Exception {
		// Si el usuario que realizo 'revision' es experto y la revisión coincide con al menos una revisión anterior
		// de otro experto, entonces el EstadoDeMuestra cambia a verificada y se agrega la 'revision' a 'muestra'
		if (revision.getNivelDeUsuario().esExperto() && coincideConOpinionDeExperto(revision)) {
			muestra.agregarRevision(revision);
			muestra.cambiarEstado(new EstadoVerificada(revision.getOpinion().getDescripcion()));
		// Si el usuario que realizo 'revision' solo es experto pero no coincide con al menos una revisión anterior de
	    // otro experto, entonces solo se agrega 'revision' a 'muestra' y a la variable 'revisionesDeExpertos'.
		} else if (revision.getNivelDeUsuario().esExperto()) {
		    muestra.agregarRevision(revision);
		    this.getRevisionesDeExpertos().add(revision);
		// Si el usuario que realizo 'revision' no es experto, entonces se lanza una excepción.
		} else if (!revision.getNivelDeUsuario().esExperto()) {
			throw new Exception();
		}
	}
	
	/**
	 * Un método que evalua si la Revisión 'rev' coincide con alguna revision realizada anteriormente
	 * por un usuario nivel experto.
	 * @param rev Una Revision realizada por un usuario nivel experto.
	 * @return Un valor Booleano que representa si 'rev' coincidió con al menos alguna otra revision de
	 *         un usuario nivel experto.
	 */
	private boolean coincideConOpinionDeExperto(Revision rev) {
		// Evalua para cada 'revision' en 'revisionesDeExpertos' si 'rev' coincide con esta.
		for(Revision revision:this.getRevisionesDeExpertos()) {
			if (revision.getOpinion().getDescripcion() == rev.getOpinion().getDescripcion()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Método que retorna las revisiones realizadas anteriormente por expertos.
	 * @return Una colección de tipo List de Revision que representa las anteriores revisiones realizadas por expertos.
	 */
	public List<Revision> getRevisionesDeExpertos() {
		return revisionesDeExpertos;
	}

	/**
	 * Guarda una colección de tipo List con revisiones de expertos.
	 * @param revisionesDeExpertos Una colección de tipo List que contiene revisiones realizadas por expertos.
	 */
	private void setRevisionesDeExpertos(List<Revision> revisionesDeExpertos) {
		this.revisionesDeExpertos = revisionesDeExpertos;
	}
}
