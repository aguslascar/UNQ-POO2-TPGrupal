package ar.edu.unq.po2.tp.grupal.muestra;

import ar.edu.unq.po2.tp.grupal.revision.NivelDeUsuario;

public class Usuario {

	private int idUsuario;
	private NivelDeUsuario nivelDeUsuario;
	
	public Usuario(int idUsuario, NivelDeUsuario nivel) {
		super();
		this.idUsuario = idUsuario;
		this.nivelDeUsuario = nivel;
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}
	
	public NivelDeUsuario getNivelDeUsuario() {
		return nivelDeUsuario;
	}
	// Esta es una clase falsa para testear el funcionamiento de Muestra
	
}
