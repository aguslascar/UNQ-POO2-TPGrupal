package ar.edu.unq.po2.tp.grupal.filtro;

import java.util.List;


import ar.edu.unq.po2.tp.grupal.muestra.Muestra;

/**
 * Es un filtro compuesto de 2 elementos de tipo Filtro. 
 * Es un filtro del tipo OR, por ende al filtrar, se van a combinar los resultados de ambos filtros.
 * Los dos filtros deben ser definidos al momento de la creacion.
 * @author aguslascar
 */
public class FiltroOR implements Filtro {
	
	
	
	Filtro filtro1;
	Filtro filtro2;
	
	public FiltroOR(Filtro filtro1, Filtro filtro2) {
		this.filtro1 = filtro1;
		this.filtro2 = filtro2;
	}


	/**
	 * Filtra la lista de muestras segun los criterios de los dos filtros que contenga
	 * Y luego une sus resultados sin repetidos.
	 * @param una lista de Muestra a filtrar
	 * @return una lista de Muestra.
	 */
	@Override
	public List<Muestra> filtrar(List<Muestra> muestras) {
		
		/*
		 * Uso el mensaje combinarResultados dandole como parametro cada lista de muestras segun cada uno de los filtros
		 */
		return this.combinarResultados(filtro1.filtrar(muestras), filtro2.filtrar(muestras));
	}


	/**
	 * Chequea si cada muestra de la segunda lista NO esta en la lista1, si no esta, lo agrego y retorno la lista1.
	 * El resultado es la lista1 con los elementos no repetidos de la lista2.
	 * @param lista1 y lista2 son una lista de Muestra que van a combinarse
	 * @return una lista de Muestra que contenga los resultados de lista1 y lista2.
	 */
	private List<Muestra> combinarResultados(List<Muestra> lista1, List<Muestra> lista2) {
		
		for (Muestra m : lista2) {
			if(! lista1.contains(m)) {
				lista1.add(m);
			}
		}
		return lista1;
	}

}
