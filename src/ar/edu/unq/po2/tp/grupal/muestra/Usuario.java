package ar.edu.unq.po2.tp.grupal.muestra;

import java.time.LocalDate;

import ar.edu.unq.po2.tp.grupal.revision.NivelDeUsuario;
import ar.edu.unq.po2.tp.grupal.revision.Opinion;
import ar.edu.unq.po2.tp.grupal.revision.Revision;

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
	
	public void recibirMensaje(String s) {
		System.out.println(s);
	}
	
	public void agregarOpinion(Muestra muestra, Opinion opinion) {
		try {
		muestra.agregarRevision(new Revision(opinion, LocalDate.now(), this.getNivelDeUsuario()));
		} catch (Exception e) {
			System.out.println("Lo sentimos, no estas habilitado para opinar sobre esta muestra.");
		}
	}
	// Esta es una clase falsa para testear el funcionamiento de Muestra
	
}
