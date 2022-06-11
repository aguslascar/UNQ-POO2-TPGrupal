package ar.edu.unq.po2.tp.grupal.usuario;
/**
 * Es una subclase de NivelDeUsuario que representa el nivel basico.
 * @author aguslascar
 */
public class Basico extends NivelDeUsuario {
	
	/**
	 * Un metodo para saber si el usuario es o no experto.
	 * @return un booleano.
	 */
	@Override
	public boolean esExperto() {
		
		return false;
	}

}
