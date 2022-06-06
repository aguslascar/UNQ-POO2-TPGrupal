package ar.edu.unq.po2.tp.grupal.filtros;


import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.po2.tp.grupal.muestra.Muestra;

public class FiltroTipoDeInsecto implements Filtro {
	/**
	 * Esta clase implementa la interfaz Filtro.
	 * Tiene que tener un criterio que tiene que ser un tipo de insecto.
	 * @author aguslascar
	 */
	String criterio;
	
	public FiltroTipoDeInsecto(String criterio) {
		super();
		this.criterio = criterio;
	}
	
	@Override
	public List<Muestra> filtrar(List<Muestra> muestras) {
		/**
		 * Filtra una lista de muestras y retorna una lista de aquellas muestras que sean del tipo 
		 * que se especifico anteriormente.
		 */
		return muestras.stream()
				.filter(m -> m.tipo() == criterio)
				.collect(Collectors.toList());
	}

}
