package ar.edu.unq.po2.tp.grupal.filtros;

import java.time.LocalDate;

import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.po2.tp.grupal.muestra.Muestra;

public class FiltroFechaUltimaVotacion implements Filtro {
	
	/**
	 * 
	 * @author aguslascar
	 * Esta clase implementa la interfaz Filtro.
	 * Tiene un comparador de fechas que puede ser mayor o menor que se defiine al momento de la creacion.
	 * Tambien se define una fecha a comparar con la fecha de la ultima votacion de cada muestra.
	 * 
	 *
	 */
	
	LocalDate fecha;
	ComparadorDeFechas comparador;
	
	public FiltroFechaUltimaVotacion(LocalDate fecha, ComparadorDeFechas comparador) {
		this.fecha = fecha;
		this.comparador = comparador;
	}
	
	@Override
	public List<Muestra> filtrar(List<Muestra> muestras) {
		/**
		 * Filtra una lista de muestras segun la fecha de la ultima votacion de cada muestra
		 *  y si son anteriores o posteriores a la fecha dada a filtrar.
		 *  Retorna una lista de muestras.
		 */
		return muestras.stream()
				.filter(m -> comparador.comparar(m.fechaUltimaVotacion(), fecha))
				.collect(Collectors.toList());
	}
	
//	private boolean comparar(Muestra m) {
//		/**
//		 * Compara la fecha definida en el filtro segun la fecha de la ultima votacion de la muestra
//		 * retorna un booleano que se define segun el tipo de operador.
//		 */
//		switch(operador) {
//		case MAYOR: 
//			return m.fechaUltimaVotacion().isAfter(fecha);
//		case MENOR:
//			return m.fechaUltimaVotacion().isBefore(fecha);
//		default: throw new IllegalArgumentException("No hay operador especificado");
//		}
//	}
}
