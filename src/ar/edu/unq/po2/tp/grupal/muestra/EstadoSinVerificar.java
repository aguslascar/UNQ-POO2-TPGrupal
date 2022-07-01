package ar.edu.unq.po2.tp.grupal.muestra;

import java.util.Collections;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import ar.edu.unq.po2.tp.grupal.revision.Revision;

/**
 * Un EstadoDeMuestra que representa cuando una Muestra fue opinada por usuarios nivel básico o solo opinada por el
 * autor de esta Muestra.
 * @author Franco Marengo
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
	
	public String obtenerResultadoActual(Muestra muestra) {
		// Copia la lista de revisiones interna de 'muestra'
				List<Revision> revisionesAVer = muestra.getRevisiones(); 
				// Crea un HashMap que va a guardar la descripcion de la opinion de cada revisión y
				// un Integer con la cantidad de ocurrencias de la descripcion de la opinion
				HashMap<String, Integer> map = new HashMap<>();
				// Si la descripcion de la opinion no se encuentra como clave entonces la agrega 
				// con valor 1 (primera ocurrencia), de lo contrario suma 1 al valor de la clave encontrada
				for (int x = 0; x < revisionesAVer.size(); x++) {        
					String opinionAver = revisionesAVer.get(x).getOpinion().getDescripcion();
					if (map.containsKey(opinionAver)) {
						map.put(opinionAver, map.get(opinionAver) + 1);
					} else {
						map.put(opinionAver, 1);
					}
				}
				Entry<String, Integer> masVotada = Collections.max(map.entrySet(), Map.Entry.comparingByValue());
				map.remove(masVotada.getKey());
				Optional<Entry<String, Integer>> segundaMasVotada = map.entrySet()
			                                                            .stream()
			                                                            .max(Map.Entry.comparingByValue());
				if (segundaMasVotada.isPresent() && segundaMasVotada.get().getValue() == masVotada.getValue()) {
					return "No definido";
				} else {
					return masVotada.getKey();
				}
	}
	
	public void recibirRevision(Revision revision, Muestra muestra) throws Exception {
		// Valida si el usuario es nivel experto, si es asi, se cambia el estado de 'muestra' y se guarda la revision
		if (revision.getNivelDeUsuario().esExperto()) {
			muestra.cambiarEstado(new EstadoOpinadaPorExpertos(revision));
		// Si el usuario no es experto, solo se agrega la revisión en 'muestra'.
		}
	    muestra.agregarRevision(revision);
	}
}
