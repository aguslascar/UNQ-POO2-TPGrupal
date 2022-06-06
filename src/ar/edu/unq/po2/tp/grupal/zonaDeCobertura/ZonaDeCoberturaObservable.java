package ar.edu.unq.po2.tp.grupal.zonaDeCobertura;

public interface ZonaDeCoberturaObservable {

	public void registrar(Observer o);

	public void desuscribir(Observer o);

	public void notificar();

}
