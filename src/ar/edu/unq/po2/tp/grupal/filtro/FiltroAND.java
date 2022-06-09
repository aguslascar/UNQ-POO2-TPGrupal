package ar.edu.unq.po2.tp.grupal.filtro;

import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.po2.tp.grupal.muestra.Muestra;

public class FiltroAND implements Filtro {
	
	/**
	 * Es un filtro compuesto de 2 elementos del tipo Filtro.
	 * Es un filtro del tipo AND, por lo que al filtrar retorna el resultado de los elementos en comun entre
	 * los dos filtros especificados.
	 * Los dos filtros deben ser definidos al momento de la creacion.
	 * @author aguslascar
	 */
	
	Filtro filtro1;
	Filtro filtro2;
	
	public FiltroAND(Filtro filtro1, Filtro filtro2) {
		/**
		 * Crea una nueva instancia de un Filtro que va a tener dos filtros con un operador AND
		 * @param dos Filtro que van a ser los filtros a operar.
		 */
		this.filtro1 = filtro1;
		this.filtro2 = filtro2;
	}

	@Override
	public List<Muestra> filtrar(List<Muestra> muestras) {
		/**
		 * Filtra la lista de muestras segun los criterios de los dos filtros que contenga
		 * Y luego se intersectan sus resultados sin repetidos.
		 * @param una lista de Muestra a filtrar
		 * @return una lista de Muestra
		 */
		return filtro2.filtrar(muestras)
				.stream()
				.filter(m -> filtro1.filtrar(muestras).contains(m))
				.collect(Collectors.toList());
		
	}

}
