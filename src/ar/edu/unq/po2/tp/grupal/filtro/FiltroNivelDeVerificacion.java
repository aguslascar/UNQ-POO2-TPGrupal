package ar.edu.unq.po2.tp.grupal.filtro;

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
		/**
		 * Crea una instancia de un Filtro que va a filtrar por nivel de verificacion de la muestra
		 * @param un String que va a ser el nivel a buscar. Solo pueden ser: "Muestra verificada" o "Muestra sin verificar"
		 */
		this.nivelAFiltrar = nivelAFiltrar;
	}
	@Override
	public List<Muestra> filtrar(List<Muestra> muestras) {
		/**
		 * Filtra una lista de muestras y retorna una lista de muestras segun el nivel solicitado.
		 * @param una lista de Muestra a filtrar
		 * @return una lista de Muestra
		 */
		return muestras.stream()
		.filter(m -> m.getResultadoActual() == nivelAFiltrar)
		.collect(Collectors.toList());
	}

}
