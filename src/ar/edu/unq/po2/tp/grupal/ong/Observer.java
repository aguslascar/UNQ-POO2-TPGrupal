package ar.edu.unq.po2.tp.grupal.ong;

import ar.edu.unq.po2.tp.grupal.muestra.Muestra;
import ar.edu.unq.po2.tp.grupal.zonaDeCobertura.ZonaDeCobertura;

/**
 * Interface hecha para que implemente la ong.
 * 
 * @author Leonardo Medici.
 *
 */
public interface Observer {

	/**
	 * Llama a la funcionalidad externa de muestra.
	 * @param zonaDeCobertura Zona de cobertura en la que se agrego una nueva muestra.
	 * @param muestra muestra agregada.
	 */
	void nuevaMuestra(ZonaDeCobertura zonaDeCobertura, Muestra muestra);


	/**
	 * Llama a la funcionalidad externa de validacion
	 * @param zonaDeCobertura Zona de cobertura en la que validó la muestra.
	 * @param muestra validada.
	 */
	public void nuevaValidacion(ZonaDeCobertura zona, Muestra muestra);




}
