package ar.edu.unq.po2.tp.grupal.revision;

import java.time.LocalDate;

/**
 * Esta clase representa y guarda los datos de una revisión realizada por un usuario hacia otra muestra, o hacia su 
 * propia muestra, guardando una opinion, una fecha de realización y el nivel del usuario que realizó la revisión.
 * @author Franco Marengo
 *
 */
public class Revision {

	/**
	 * Instancia de la clase Opinion que representa la opinión del usuario que realiza la revisión.
	 * @see ar.edu.unq.po2.tp.grupal.revision.Opinion Opinion
	 */
	private Opinion opinion;
	/**
	 * Una fecha de tipo LocalDate que representa la fecha en la que se realizó la revisión.
	 * @see LocalDate
	 */
	private LocalDate fecha;
	/**
	 * Instancia de la clase NivelDeUsuario que representa el nivel del usuario que realizó la revisión.
	 * @see ar.edu.unq.po2.tp.grupal.revision.NivelDeUsuario NivelDeUsuario
	 */
	private NivelDeUsuario nivelDeUsuario;
	/**
	 * Un int que representa el número identificatorio del usuario que realizó la revisión.
	 */
	private int idUsuario;
	
	/**
	 * Constructor de la clase Revisión, recrea una nueva Revisión realizada.
	 * @param opinion Instancia de la clase Opinion que representa la opinión del usuario que realiza la revisión.
	 * @param fecha Fecha de tipo LocalDate que representa la fecha en la que se realizó la revisión.
	 * @param nivelDeUsuario Instancia de la clase NivelDeUsuario que representa el nivel del usuario que realiza la
	 *                       revisión.
	 * @param idUsuario Un valor de tipo int que representa el número identificatorio del usuario que realizó la 
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
	 * Método que retorna la fecha en la que se realizó la revisión.
	 * @return Valor de tipo LocalDate.
	 */
	public LocalDate getFecha() {
		return fecha;
	}

	/**
	 * Guarda la fecha pasada como parámetro.
	 * @param fecha Valor de tipo LocalDate que debe representar la fecha en la que se realizó la revisión.
	 * @see LocalDate
	 */
	private void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	/**
	 * Método que retorna el nivel de usuario del usuario que realizó la revisión.
	 * @return Una instancia de NivelDeUsuario.
	 * @see ar.edu.unq.po2.tp.grupal.revision.NivelDeUsuario NivelDeUsuario
	 */
	public NivelDeUsuario getNivelDeUsuario() {
		return nivelDeUsuario;
	}

	/**
	 * Guarda el nivel del usuario que realizó la revisión.
	 * @param nivelDeUsuario Una instancia de NivelDeUsuario que representa el nivel del usuario que realizó la
	 *                       revisión.
	 * @see ar.edu.unq.po2.tp.grupal.revision.NivelDeUsuario NivelDeUsuario
	 */
	private void setNivelDeUsuario(NivelDeUsuario nivelDeUsuario) {
		this.nivelDeUsuario = nivelDeUsuario;
	}

	/**
	 * Guarda la opinión del usuario que realizó la revisión.
	 * @param opinion Una instancia de Opinión.
	 * @see ar.edu.unq.po2.tp.grupal.revision.Opinion Opinion
	 */
	private void setOpinion(Opinion opinion) {
		this.opinion = opinion;
	}

	/**
	 * Método que retorna la opinión del usuario que realizó la revisión.
	 * @return Una instancia de Opinion.
	 * @see ar.edu.unq.po2.tp.grupal.revision.Opinion Opinion
	 */
	public Opinion getOpinion() {
		return opinion;
	}
	
	/**
	 * Método que retorna el valor en la variable idUsuario.
	 * @return Un int que representa el número identificatorio del usuario que realizó la revisión.
	 */
	public int getIdUsuario() {
		return idUsuario;
	}

	/**
	 * Guarda en la variable de instancia idUsuario el valor pasado como parámetro.
	 * @param idUsuario Un int que representa el número identificatorio del usuario que realizó la revisión.
	 */
	private void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
}
