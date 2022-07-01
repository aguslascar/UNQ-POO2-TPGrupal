package ar.edu.unq.po2.tp.grupal.zonaDeCobertura;

import ar.edu.unq.po2.tp.grupal.ong.Observer;

/**
 * Interface hecha para que implemente zona de cobertura.
 * 
 * @author Leonardo Medici
 *
 */
public interface ZonaDeCoberturaObservable {

	public void registrar(Observer o);

	public void desuscribir(Observer o);

	public void notificarNuevaMuestra();

	public void notificarValidacion();

}
