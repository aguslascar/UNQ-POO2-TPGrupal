package ar.edu.unq.po2.tp.grupal.revision1;

import java.time.LocalDate;
import ar.edu.unq.po2.tp.grupal.usuario.*;

public class Revision {

	private Opinion opinion;
	private LocalDate fecha;
	private NivelDeUsuario nivel;
	private int idUsuario;
	
	public Revision(Opinion opinion, LocalDate fecha, NivelDeUsuario nivel, int idUsuario) {
		super();
		this.opinion = opinion;
		this.fecha = fecha;
		this.nivel = nivel;
		this.idUsuario = idUsuario;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}

	public int getid() {
		// TODO Auto-generated method stub
		return idUsuario;
	}
}
