package ar.edu.unq.po2.tp.grupal.usuario;
/**
 * Es una subclase de NivelDeUsuario que representa el nivel experto.
 * @author aguslascar
 */
public class Experto extends NivelDeUsuario {
	
	/**
	 * Un metodo para saber si el usuario es o no experto.
	 * @return un booleano.
	 */
	@Override
	public boolean esExperto() {
		
		return true;
	}
	
	@Override
	public String getDescripcion() {
		return "Nivel de usuario experto";
	}

}
