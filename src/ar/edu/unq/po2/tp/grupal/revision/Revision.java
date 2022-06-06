package ar.edu.unq.po2.tp.grupal.revision;

import java.time.LocalDate;

/**
 * Esta clase representa y guarda los datos de una revisi�n realizada por un usuario hacia otra muestra, o hacia su 
 * propia muestra, guardando una opinion, una fecha de realizaci�n y el nivel del usuario que realiz� la revisi�n.
 * @author Franco Marengo
 *
 */
public class Revision {

	/**
	 * Instancia de la clase Opinion que representa la opini�n del usuario que realiza la revisi�n.
	 * @see ar.edu.unq.po2.tp.grupal.revision.Opinion Opinion
	 */
	private Opinion opinion;
	/**
	 * Una fecha de tipo LocalDate que representa la fecha en la que se realiz� la revisi�n.
	 * @see LocalDate
	 */
	private LocalDate fecha;
	/**
	 * Instancia de la clase NivelDeUsuario que representa el nivel del usuario que realiz� la revisi�n.
	 * @see ar.edu.unq.po2.tp.grupal.revision.NivelDeUsuario NivelDeUsuario
	 */
	private NivelDeUsuario nivelDeUsuario;
	/**
	 * Un int que representa el n�mero identificatorio del usuario que realiz� la revisi�n.
	 */
	private int idUsuario;
	
	/**
	 * Constructor de la clase Revisi�n, recrea una nueva Revisi�n realizada.
	 * @param opinion Instancia de la clase Opinion que representa la opini�n del usuario que realiza la revisi�n.
	 * @param fecha Fecha de tipo LocalDate que representa la fecha en la que se realiz� la revisi�n.
	 * @param nivelDeUsuario Instancia de la clase NivelDeUsuario que representa el nivel del usuario que realiza la
	 *                       revisi�n.
	 * @param idUsuario Un valor de tipo int que representa el n�mero identificatorio del usuario que realiz� la 
	 *                  Revision.
	 * @see ar.edu.unq.po2.tp.grupal.revision.Opinion Opinion
	 * @see LocalDate
	 * @see ar.edu.unq.po2.tp.grupal.revision.NivelDeUsuario NivelDeUsuario
	 */
	public Revision(Opinion opinion, LocalDate fecha, NivelDeUsuario nivelDeUsuario, int idUsuario) {
		super();
		this.setOpinion(opinion);
		this.setFecha(fecha);
		this.setNivelDeUsuario(nivelDeUsuario);
		this.setIdUsuario(idUsuario);
	}

	/**
	 * M�todo que retorna la fecha en la que se realiz� la revisi�n.
	 * @return Valor de tipo LocalDate.
	 */
	public LocalDate getFecha() {
		return fecha;
	}

	/**
	 * Guarda la fecha pasada como par�metro.
	 * @param fecha Valor de tipo LocalDate que debe representar la fecha en la que se realiz� la revisi�n.
	 * @see LocalDate
	 */
	private void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	/**
	 * M�todo que retorna el nivel de usuario del usuario que realiz� la revisi�n.
	 * @return Una instancia de NivelDeUsuario.
	 * @see ar.edu.unq.po2.tp.grupal.revision.NivelDeUsuario NivelDeUsuario
	 */
	public NivelDeUsuario getNivelDeUsuario() {
		return nivelDeUsuario;
	}

	/**
	 * Guarda el nivel del usuario que realiz� la revisi�n.
	 * @param nivelDeUsuario Una instancia de NivelDeUsuario que representa el nivel del usuario que realiz� la
	 *                       revisi�n.
	 * @see ar.edu.unq.po2.tp.grupal.revision.NivelDeUsuario NivelDeUsuario
	 */
	private void setNivelDeUsuario(NivelDeUsuario nivelDeUsuario) {
		this.nivelDeUsuario = nivelDeUsuario;
	}

	/**
	 * Guarda la opini�n del usuario que realiz� la revisi�n.
	 * @param opinion Una instancia de Opini�n.
	 * @see ar.edu.unq.po2.tp.grupal.revision.Opinion Opinion
	 */
	private void setOpinion(Opinion opinion) {
		this.opinion = opinion;
	}

	/**
	 * M�todo que retorna la opini�n del usuario que realiz� la revisi�n.
	 * @return Una instancia de Opinion.
	 * @see ar.edu.unq.po2.tp.grupal.revision.Opinion Opinion
	 */
	public Opinion getOpinion() {
		return opinion;
	}
	
	/**
	 * M�todo que retorna el valor en la variable idUsuario.
	 * @return Un int que representa el n�mero identificatorio del usuario que realiz� la revisi�n.
	 */
	public int getIdUsuario() {
		return idUsuario;
	}

	/**
	 * Guarda en la variable de instancia idUsuario el valor pasado como par�metro.
	 * @param idUsuario Un int que representa el n�mero identificatorio del usuario que realiz� la revisi�n.
	 */
	private void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
}
