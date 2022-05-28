package ar.edu.unq.po2.tp.grupal.filtros;

import java.util.List;


import ar.edu.unq.po2.tp.grupal.muestra.Muestra;

public class FiltroOR implements Filtro {
	
	/**
	 * @author aguslascar
	 * Es un filtro compuesto de 2 elementos de tipo Filtro. 
	 * Es un filtro del tipo OR, por ende al filtrar, se van a combinar los resultados de ambos filtros.
	 * Los dos filtros deben ser definidos al momento de la creacion.
	 */
	
	Filtro filtro1;
	Filtro filtro2;
	
	public FiltroOR(Filtro filtro1, Filtro filtro2) {
		this.filtro1 = filtro1;
		this.filtro2 = filtro2;
	}


	@Override
	public List<Muestra> filtrar(List<Muestra> muestras) {
		/**
		 * Filtra la lista de muestras segun los criterios de los dos filtros que contenga
		 * Y luego une sus resultados sin repetidos.
		 * Retorna una lista de muestras.
		 */
		/*
		 * Uso el mensaje combinarResultados dandole como parametro cada lista de muestras segun cada uno de los filtros
		 */
		return this.combinarResultados(filtro1.filtrar(muestras), filtro2.filtrar(muestras));
	}


	private List<Muestra> combinarResultados(List<Muestra> lista1, List<Muestra> lista2) {
		/*
		 * Chequeo si cada muestra de la segunda lista NO esta en la lista1, si no esta, lo agrego y retorno la lista1.
		 * El resultado es la lista1 con los elementos no repetidos de la lista2.
		 */
		for (Muestra m : lista2) {
			if(! lista1.contains(m)) {
				lista1.add(m);
			}
		}
		return lista1;
	}

}
