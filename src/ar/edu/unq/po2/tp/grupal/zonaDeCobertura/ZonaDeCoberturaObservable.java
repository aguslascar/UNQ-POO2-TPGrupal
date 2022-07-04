package ar.edu.unq.po2.tp.grupal.zonaDeCobertura;

import ar.edu.unq.po2.tp.grupal.muestra.Muestra;
import ar.edu.unq.po2.tp.grupal.ong.Observer;

/**
 * Interface hecha para que implemente zona de cobertura.
 * 
 * @author Leonardo Medici
 *
 */
public interface ZonaDeCoberturaObservable {

	/**
	 * Registra una ong que se esta registrando a una zona de cobertura.
	 * 
	 * @param o observer que es una ong.
	 */
	public void registrar(Observer o);

	/**
	 * Desuscribe a una ong que se esta desuscribiendo de una zona de cobertura.
	 * 
	 * @param o observer que es una ong.
	 */
	public void desuscribir(Observer o);

	/**
	 * Notifica a todas las organizaciones cuando se añade una nueva muestra.
	 */

	public void notificarNuevaMuestra(Muestra muestra);

	/**
	 * Notifica a todas las organizaciones cuando se valida una muestra.
	 */
	public void notificarValidacion(Muestra muestra);

}
