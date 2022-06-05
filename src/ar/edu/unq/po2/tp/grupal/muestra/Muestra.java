package ar.edu.unq.po2.tp.grupal.muestra;

import java.util.List;
import java.time.*;
import java.util.ArrayList;

import ar.edu.unq.po2.tp.grupal.revision.Opinion;
import ar.edu.unq.po2.tp.grupal.revision.Revision;

/**
 * Esta clase representa y guarda datos de una muestra tomada, como la fecha en que se registr� la muestra, el id del
 * usuario que envi� la muestra, la foto, la ubicaci�n y la opini�n del autor de la muestra.
 * @author Franco Marengo
 *
 */
public class Muestra {

	/**
	 * Numero de tipo primitivo int identificatorio del usuario que tom� la muestra.
	 * Valores v�lidos desde el 0 inclusive en adelante.
	 */
	private int idUsuario;
	/**
	 * Fecha de tipo LocalDate que representa la fecha en la que se tom� la muestra.
	 */
	private LocalDate fecha;
	/**
	 * Un EstadoDeMuestra que representa el estado por el que est� pasando la muestra actualmente.
	 */
	private EstadoDeMuestra estado;
	/**
	 * Colecci�n de tipo List que guarda todas las revisiones que posee la muestra.
	 */
	private List<Revision> revisiones;
	/**
	 * Un String que representa una foto tomada para documentar la muestra.
	 */
	private String foto;
	/**
	 * Una Ubicacion que representa la ubicaci�n en la que se tom� la muestra.
	 */
	private Ubicacion ubicacion;
	/**
	 * Un String que registra la opini�n del usuario que realiz� la toma de la muestra.
	 */
	private Opinion opinion;
	
	/**
	 * Constructor de una nueva muestra tomada.
	 * @param usuario Usuario autor de la muestra.
	 * @param fecha Fecha en la que se registr� la muestra.
	 * @param Foto Imagen adjunta que documenta la muestra.
	 * @param ubicacion Ubicaci�n en la que se gener� la muestra.
	 * @param opinion Opini�n del propio usuario que tom� la muestra.
	 */
	public Muestra(Usuario usuario, LocalDate fecha, String Foto, Ubicacion ubicacion, Opinion opinion) throws Exception {
		super();
		this.setIdUsuario(usuario.getIdUsuario());
		this.setFecha(fecha);
		this.setFoto(Foto);
		this.setUbicacion(ubicacion);
		this.setOpinion(opinion);
		this.setRevisiones(new ArrayList<Revision>());
		this.setEstado(new EstadoSinVerificar());
		this.agregarRevision(new Revision(opinion, fecha, usuario.getNivelDeUsuario()));
	}

	/**
	 * M�todo que retorna la opini�n que tenga mayor cantidad de votos en la lista de revisiones de la muestra.
	 * @return Un String que describe la opini�n que tuvo mayor cantidad de votos, o un String por defecto
	 *         ("No definido") en caso de que ninguna opini�n tenga mayor cantidad de votos que otra.
	 */
	public String getResultadoActual() {
		return (this.getEstado().obtenerResultadoActual(this));
	}
	
	/**
	 * Agrega una nueva Revision a la lista de revisiones interna de la muestra.
	 * @param revision Una Revision a guardar en la muestra a la que se hace referencia.
	 */
	protected void agregarRevision(Revision revision) throws Exception {
			this.getRevisiones().add(revision);
	}
	
	public void recibirRevision(Revision revision) throws Exception {
		this.getEstado().recibirRevision(revision, this);
	}
	
	protected void cambiarEstado(EstadoDeMuestra nuevoEstado) {
		this.setEstado(nuevoEstado);
	}

	/**
	 * M�todo que retorna el n�mero de identificaci�n del usuario que tom� la muestra.
	 * @return Valor de tipo primitivo int que representa un id de usuario.
	 */
	public int getIdUsuario() {
		return idUsuario;
	}

	/**
	 * Guarda en la variable interna privada idUsuario el valor pasado como par�metro.
	 * @param idUsuario Un id de usuario v�lido y existente.
	 */
	private void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * M�todo que retorna la fecha en la que se tomo la muestra.
	 * @return Un LocalDate que representa la fecha en la que se tom� la muestra.
	 * @see LocalDate
	 */
	public LocalDate getFecha() {
		return fecha;
	}

	/**
	 * Guarda en la variable interna privada fecha el valor pasado como par�metro.
	 * @param fecha Un LocalDate que debe representar la fecha en la que se tom� la muestra.
	 */
	private void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	/**
	 * M�todo que retorna el estado actual en el que se encuentra la muestra.
	 * @return Un EstadoDeMuestra que representa el estado actual de la muestra.
	 * @see ar.edu.unq.po2.tp.grupal.muestra.EstadoDeMuestra EstadoDeMuestra
	 */
	public EstadoDeMuestra getEstado() {
		return estado;
	}

	/**
	 * Guarda en la variable interna privada estado el EstadoDeMuestra pasado como par�metro.
	 * @param estado Un EstadoDeMuestra que debe representar el estado actual de la muestra.
	 */
	private void setEstado(EstadoDeMuestra estado) {
		this.estado = estado;
	}

	/**
	 * M�todo que retorna una colecci�n con todas las revisiones sobre la muestra.
	 * @return Una colecci�n de tipo List con todas las revisiones.
	 */
	public List<Revision> getRevisiones() {
		return revisiones;
	}

	/**
	 * Guarda en la variable interna privada la lista de revisiones pasada como par�metro.
	 * @param revisiones Una colecci�n de tipo List de revisiones, obligatoriamente debe estar vac�a.
	 */
	private void setRevisiones(List<Revision> revisiones) {
		this.revisiones = revisiones;
	}

	/**
	 * M�todo que retorna la foto que se registr� al momento de crear la muestra.
	 * @return Un String que representa la foto tomada al momento de registrar la muestra.
	 */
	public String getFoto() {
		return foto;
	}

	/**
	 * Guarda en la variable interna privada foto el valor pasado como par�metro.
	 * @param foto Un String que representa la foto tomada al momento de registrar la muestra.
	 */
	private void setFoto(String foto) {
		this.foto = foto;
	}

	/**
	 * 
	 * M�todo que retorna la ubicaci�n en la que se registr� la muestra.
	 * @return Una Ubicacion que representa la ubicaci�n en la que se tom� la muestra.
	 * @see ar.edu.unq.po2.tp.grupal.muestra.Ubicacion Ubicacion
	 */
	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	/**
	 * Guarda en la variable interna privada ubicacion la ubicacion en la que se tom� la muestra.
	 * @param ubicacion Valor de tipo Ubicacion que representa la ubicaci�n en la que se tom� la muestra.
	 */
	private void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	/**
	 * M�todo que retorna la opini�n del usuario que tom� la muestra.
	 * @return Un String que representa la opini�n del usuario que tom� la muestra.
	 */
	public Opinion getOpinion() {
		return opinion;
	}

	/**
	 * Guarda en la variable interna privada la opini�n del usuario que tom� la muestra.
	 * @param opinion Un String que representa la opini�n del usuario que tom� la muestra.
	 */
	private void setOpinion(Opinion opinion) {
		this.opinion = opinion;
	}
}
