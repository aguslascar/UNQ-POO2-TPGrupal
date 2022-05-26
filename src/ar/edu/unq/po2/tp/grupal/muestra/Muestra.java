package ar.edu.unq.po2.tp.grupal.muestra;

import java.util.List;
import java.time.*;
import java.util.ArrayList;

import ar.edu.unq.po2.tp.grupal.revision.Revision;

public class Muestra {

	private int idUsuario;
	private LocalDate fecha;
	private EstadoDeMuestra estado;
	private List<Revision> revisiones;
	private int foto;
	private Ubicacion ubicacion;
	private String opinion;
	
	public Muestra(int idUsuario, LocalDate fecha, int Foto, Ubicacion ubicacion, String opinion) {
		super();
		this.setIdUsuario(idUsuario);
		this.setFecha(fecha);
		this.setFoto(Foto);
		this.setUbicacion(ubicacion);
		this.setOpinion(opinion);
		this.setRevisiones(new ArrayList<Revision>());
		this.setEstado(new MuestraSinVerificar());
	}
	
	public void agregarRevision(Revision revision) {
		this.getRevisiones().add(revision);
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	private void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	private void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public EstadoDeMuestra getEstado() {
		return estado;
	}

	private void setEstado(EstadoDeMuestra estado) {
		this.estado = estado;
	}

	public List<Revision> getRevisiones() {
		return revisiones;
	}

	private void setRevisiones(List<Revision> revisiones) {
		this.revisiones = revisiones;
	}

	public int getFoto() {
		return foto;
	}

	private void setFoto(int foto) {
		this.foto = foto;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	private void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getOpinion() {
		return opinion;
	}

	private void setOpinion(String opinion) {
		this.opinion = opinion;
	}
}
