package ar.edu.unq.po2.tp.grupal.filtro;


import java.util.List;
import ar.edu.unq.po2.tp.grupal.muestra.Muestra;

public interface Filtro {
	/**
	 * Una interface Filtro que va a tener clases que la implementen para representar los filtros de busqueda.
	 * En un patron composite donde los "leaf" son los criterios de busqueda y los "composite" filtros combinables con 
	 * AND y OR
	 *@author aguslascar
	 */
	public List<Muestra> filtrar(List<Muestra> muestras)
	/**
	 * Este metodo va a filtrar una lista de muestras segun el criterio de busqueda. Retorna una lista de muestras.
	 */;
	
}