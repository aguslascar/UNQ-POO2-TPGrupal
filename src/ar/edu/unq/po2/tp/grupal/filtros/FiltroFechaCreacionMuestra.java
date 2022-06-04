package ar.edu.unq.po2.tp.grupal.filtros;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.po2.tp.grupal.muestra.Muestra;

public class FiltroFechaCreacionMuestra implements Filtro {
	/**
	 * Esta clase implementa la interfaz Filtro.
	 * Tiene un comparador de fechas que puede ser mayor o menor que se defiine al momento de la creacion.
	 * Tambien se define una fecha a comparar con la fecha de la creacion de cada muestra.
	 * @author aguslascar
	 * 
	 *
	 */
	
	LocalDate fecha;
	ComparadorDeFechas comparador;
	
	public FiltroFechaCreacionMuestra(LocalDate fecha, ComparadorDeFechas comparador) {
		this.fecha = fecha;
		this.comparador = comparador;
	}
	
	@Override
	public List<Muestra> filtrar(List<Muestra> muestras) {
		/**
		 * Filtra una lista de muestras segun la fecha de creacion de cada muestra
		 *  y si son anteriores o posteriores a la fecha dada a filtrar.
		 *  Retorna una lista de muestras.
		 */
		return muestras.stream()
				.filter(m -> comparador.comparar(m.getFecha(), fecha))
				.collect(Collectors.toList());
	}
}
