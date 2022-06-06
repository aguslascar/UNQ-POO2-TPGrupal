package ar.edu.unq.po2.tp.grupal.filtro;

import java.time.LocalDate;

import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.po2.tp.grupal.muestra.Muestra;

public class FiltroFechaUltimaVotacion implements Filtro {
	
	/**
	 * Esta clase implementa la interfaz Filtro.
	 * Tiene un comparador de fechas que puede ser mayor o menor que se defiine al momento de la creacion.
	 * Tambien se define una fecha a comparar con la fecha de la ultima votacion de cada muestra.
	 * @author aguslascar
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
		 *  @return una lista de muestras.
		 */
		return muestras.stream()
				.filter(m -> comparador.comparar(m.fechaUltimaVotacion(), fecha))
				.collect(Collectors.toList());
	}
	
}
