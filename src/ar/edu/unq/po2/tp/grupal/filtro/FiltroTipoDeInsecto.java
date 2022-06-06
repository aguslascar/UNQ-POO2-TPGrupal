package ar.edu.unq.po2.tp.grupal.filtro;


import java.util.List;
import ar.edu.unq.po2.tp.grupal.revision.*;
import java.util.stream.Collectors;

import ar.edu.unq.po2.tp.grupal.muestra.Muestra;

public class FiltroTipoDeInsecto implements Filtro {
	/**
	 * Esta clase implementa la interfaz Filtro.
	 * Tiene que tener una Opinion que tiene que ser un tipo de insecto.
	 * @author aguslascar
	 */
	Opinion tipoDeInsecto; //Utilizamos esto, ya que en la clase muestra, el tipo de insecto el cual se declaro
	                       //al subir la muestra, se define como una opinion del usuario que la realizo.
	
	public FiltroTipoDeInsecto(Opinion tipoDeInsecto) {
		/**
		 * Constructor del filtro tipo de insecto que va a recibir una Opinion representando el tipo de insecto
		 * a filtrar
		 * @param una Opinion que representa un tipo de insecto a buscar.
		 */
		super();
		this.tipoDeInsecto = tipoDeInsecto;
	}
	
	@Override
	public List<Muestra> filtrar(List<Muestra> muestras) {
		/**
		 * Filtra una lista de muestras y retorna una lista de aquellas muestras que sean del tipo 
		 * que se especifico anteriormente.
		 * @param una lista de Muestra
		 * @return una lista de Muestra
		 */
		return muestras.stream()
				.filter(m -> m.getOpinion() == tipoDeInsecto)
				.collect(Collectors.toList());
	}

}
