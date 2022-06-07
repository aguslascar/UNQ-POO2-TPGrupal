package ar.edu.unq.po2.tp.grupal.usuario;

public class Experto extends NivelDeUsuario {
	/**
	 * Es una subclase de NivelDeUsuario que representa el nivel experto.
	 * @author aguslascar
	 */
	@Override
	boolean esExperto() {
		/**
		 * Un metodo para saber si el usuario es o no experto.
		 * @return un booleano.
		 */
		return true;
	}

}
