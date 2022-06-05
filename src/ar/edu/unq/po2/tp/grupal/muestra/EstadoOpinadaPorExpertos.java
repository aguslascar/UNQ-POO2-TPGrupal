package ar.edu.unq.po2.tp.grupal.muestra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ar.edu.unq.po2.tp.grupal.revision.Revision;

public class EstadoOpinadaPorExpertos extends EstadoDeMuestra {

	private List<Revision> revisionesDeExpertos;

	public EstadoOpinadaPorExpertos(Revision revision) {
		super("Muestra opinada por expertos");
		this.setRevisionesDeExpertos(new ArrayList<Revision>());
		this.getRevisionesDeExpertos().add(revision);
	}
	
	public String obtenerResultadoActual(Muestra muestra) {
		        // Copia la lista de revisiones interna de 'muestra'
				List<Revision> revisionesAVer = this.getRevisionesDeExpertos(); 
				// Crea un HashMap que va a guardar la descripcion de la opinion de cada revisi�n y
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
				// Si finalmente las variables 'mayor' y 'segundoMayor' quedan como iguales despu�s de analizar todos los
				// 'entry' del HashMap, entonces se retorna como resultado 'No definido', de lo contrario se retorna
				// la key con el mayor valor, significando que es la opini�n que tuvo mayor cantidad de votos
				if (mayor == segundoMayor) {
					return ("No definido" + " - " + this.getDescripcion());
				} else {
					return (opinionMayorCantVotosHastaAhora + " - " + this.getDescripcion());
				}
	}
	
	public void recibirRevision(Revision revision, Muestra muestra) throws Exception {
		if (this.coincideConOpinionDeExperto(revision)) {
			muestra.agregarRevision(revision);
			muestra.cambiarEstado(new EstadoVerificada(revision.getOpinion().getDescripcion()));
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