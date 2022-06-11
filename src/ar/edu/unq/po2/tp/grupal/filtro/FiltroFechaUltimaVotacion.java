package ar.edu.unq.po2.tp.grupal.filtro;

import java.time.LocalDate;

import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.po2.tp.grupal.muestra.Muestra;
/**
 * Esta clase implementa la interfaz Filtro.
 * Tiene un comparador de fechas que puede ser mayor o menor que se defiine al momento de la creacion.
 * Tambien se define una fecha a comparar con la fecha de la ultima votacion de cada muestra.
 * @author aguslascar
 *
 */
public class FiltroFechaUltimaVotacion implements Filtro {
	
	
	
	LocalDate fecha;
	ComparadorDeFechas comparador;
	
	/**
	 * Crea una instancia de un Filtro que va a filtrar por fecha de ultima votacion de la muestra
	 * @param un LocalDate(fecha a comparar) y un ComparadorDeFechas que va a indicar si compara por menor o mayor.
	 */
	public FiltroFechaUltimaVotacion(LocalDate fecha, ComparadorDeFechas comparador) {
		
		this.fecha = fecha;
		this.comparador = comparador;
	}
	
	/**
	 * Filtra una lista de muestras segun la fecha de la ultima votacion de cada muestra
	 *  y si son anteriores o posteriores a la fecha dada a filtrar.
	 *  @return una lista de muestras.
	 */
	@Override
	public List<Muestra> filtrar(List<Muestra> muestras) {
		
		return muestras.stream()
				.filter(m -> comparador.comparar(m.fechaUltimaVotacion(), fecha))
				.collect(Collectors.toList());
	}
	
}
