package ar.edu.unq.po2.tp.grupal.usuario;

public class Basico extends NivelDeUsuario {
	/**
	 * Es una subclase de NivelDeUsuario que representa el nivel basico.
	 * @author aguslascar
	 */
	@Override
	public boolean esExperto() {
		/**
		 * Un metodo para saber si el usuario es o no experto.
		 * @return un booleano.
		 */
		return false;
	}

}
