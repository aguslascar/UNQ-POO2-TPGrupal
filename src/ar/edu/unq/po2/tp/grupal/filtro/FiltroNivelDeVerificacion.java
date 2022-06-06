package ar.edu.unq.po2.tp.grupal.filtros;

import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.po2.tp.grupal.muestra.Muestra;

public class FiltroNivelDeVerificacion implements Filtro {
	/**
	 * Esta clase implementa la interfaz Filtro.
	 * Filtra muestras segun el nivel que se le indique al ser creado.
	 * @author aguslascar
	 */
	String nivelAFiltrar;
	
	public FiltroNivelDeVerificacion(String nivelAFiltrar) {
		this.nivelAFiltrar = nivelAFiltrar;
	}
	@Override
	public List<Muestra> filtrar(List<Muestra> muestras) {
		/**
		 * Filtra una lista de muestras y retorna una lista de muestras segun el nivel solicitado.
		 */
		return muestras.stream()
		.filter(m -> m.getResultadoActual() == nivelAFiltrar)
		.collect(Collectors.toList());
	}

}
