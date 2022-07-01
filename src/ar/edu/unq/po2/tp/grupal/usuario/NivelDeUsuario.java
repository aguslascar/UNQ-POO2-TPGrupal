package ar.edu.unq.po2.tp.grupal.usuario;

/**
 * Esta clase abstracta representa los niveles de usuario.
 * Contiene un metodo abstracto que retorna un booleano diciendo si es experto o no.
 * @author aguslascar
 */
public abstract class NivelDeUsuario {

	public abstract boolean esExperto();
	public abstract String getDescripcion();
}
