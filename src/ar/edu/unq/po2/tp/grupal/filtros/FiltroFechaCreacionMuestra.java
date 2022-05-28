package ar.edu.unq.po2.tp.grupal.filtros;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.po2.tp.grupal.muestra.Muestra;

public class FiltroFechaCreacionMuestra implements Filtro {
	/**
	 * 
	 * @author aguslascar
	 * Esta clase implementa la interfaz Filtro.
	 * Tiene un operador que puede ser mayor o menor que se defiine al momento de la creacion.
	 * Tambien se define una fecha a comparar con la fecha de la creacion de cada muestra.
	 *
	 */
	
	public enum Operador {
		/**
		 * Este operador representa los dos operadores posibles para este filtro, mayor o menor.
		 */
		MAYOR, MENOR
		
	}
	LocalDate fecha;
	Operador operador;
	
	
	@Override
	public List<Muestra> filtrar(List<Muestra> muestras) {
		/**
		 * Filtra una lista de muestras segun la fecha de creacion de cada muestra
		 *  y si son anteriores o posteriores a la fecha dada a filtrar.
		 *  Retorna una lista de muestras.
		 */
		return muestras.stream()
				.filter(m -> this.comparar(m))
				.collect(Collectors.toList());
	}
	private boolean comparar(Muestra m) {
		/**
		 * Compara la fecha definida en el filtro segun la fecha de la creacion de la muestra
		 * retorna un booleano que se define segun el tipo de operador.
		 */
		switch(operador) {
		case MAYOR: 
			return m.getFecha().isAfter(fecha);
		case MENOR:
			return m.getFecha().isBefore(fecha);
		default: throw new IllegalArgumentException("No hay operador especificado");
		}
	}
}
