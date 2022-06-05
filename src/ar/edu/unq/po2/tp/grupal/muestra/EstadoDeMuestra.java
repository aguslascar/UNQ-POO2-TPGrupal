package ar.edu.unq.po2.tp.grupal.muestra;

import java.util.HashMap;
import java.util.List;

import ar.edu.unq.po2.tp.grupal.revision.Revision;

/**
 * Clase que representa el estado de una muestra.
 * @author Franco Marego
 *
 */
public abstract class EstadoDeMuestra {
	
	/**
	 * String que representa la descripción del nombre del EstadoDeMuestra.
	 */
	private String descripcion;
	
	/**
	 * Constructor de EstadoDeMuestra
	 * @param descripcion Un String que representa la descripción del nombre del estado de muestra.
	 */
	public EstadoDeMuestra(String descripcion) {
		super();
		this.setDescripcion(descripcion);
	}
	
	/**
	 * Método que permite calcular la opinión con mayor cantidad de votos en común de la lista de revisiones
	 * de 'muestra'.
	 * @param muestra Una Muestra para poder analizar.
	 * @return Un String que representa la descripción de la opinión con la mayor cantidad de votos en común.
	 * @see ar.edu.unq.po2.tp.grupal.muestra.Muestra Muestra
	 */
	public String obtenerResultadoActual(Muestra muestra) {
		// Copia la lista de revisiones interna de 'muestra'
		List<Revision> revisionesAVer = muestra.getRevisiones(); 
		// Crea un HashMap que va a guardar la descripcion de la opinion de cada revisión y
		// un Integer con la cantidad de ocurrencias de la descripcion de la opinion
		HashMap<String, Integer> mapa = new HashMap<>();
		// Si la descripcion de la opinion no se encuentra como clave entonces la agrega 
		// con valor 1 (primera ocurrencia), de lo contrario suma 1 al valor de la clave encontrada
		for (int x = 0; x < revisionesAVer.size(); x++) {        
			String opinionAver = revisionesAVer.get(x).getOpinion().getDescripcion();
			if (mapa.containsKey(opinionAver)) {
				mapa.put(opinionAver, mapa.get(opinionAver) + 1);
			} else {
				mapa.put(opinionAver, 1);
			}
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
			return ("No definido" + " - " + this.getDescripcion());
		} else {
			return (opinionMayorCantVotosHastaAhora + " - " + this.getDescripcion());
		}
	}
	
	/**
	 * Método que analiza si 'revision' esta habilitada para opinar de 'muestra' según el estado de muestra y el nivel
	 * del usuario que realizó la 'revision'.
	 * @param revision Una Revision a analizar.
	 * @param muestra Una Muestra a analizar.
	 * @throws Exception Si el usuario no esta habilitado para opinar segun su nivel y el estado de la muestra.
	 * @see ar.edu.unq.po2.tp.grupal.muestra.Muestra Muestra
	 * @see ar.edu.unq.po2.tp.grupal.revision.Revision Revision
	 */
	public abstract void recibirRevision(Revision revision, Muestra muestra) throws Exception;
	
	/**
	 * Método que retorna la descripción del nombre del EstadoDeMuestra.
	 * @return Un String que representa la descripción del nombre de EstadoDeMuestra.
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * Guarda la descripción del nombre de EstadoDeMuestra.
	 * @param descripcion Un String que representa la descripción del nombre del EstadoDeMuestra.
	 */
	private void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
