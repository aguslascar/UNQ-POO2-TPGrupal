package ar.edu.unq.po2.tp.grupal.usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import ar.edu.unq.po2.tp.grupal.revision.*;
import ar.edu.unq.po2.tp.grupal.muestra.*;
import ar.edu.unq.po2.tp.grupal.aplicacion.*;
import ar.edu.unq.po2.tp.grupal.zonaDeCobertura.*;

public class Usuario {
	/**
	 * Esta clase representa un Usuario en el sistema.
	 * El usuario va a tener la instancia del sistema.
	 * Para instanciarlo se requiere un id de usuario y un nivel NivelDeUsuario.
	 * Va a contener una lista de revisiones que seran guardadas a medida que las haga.
	 * @author aguslascar
	 */
	private int idUsuario;
	private AplicacionWeb sistema;
	private NivelDeUsuario nivel;
	private List<Revision> revisiones;
	
	public Usuario(int idUsuario, AplicacionWeb sistema, NivelDeUsuario nivel) {
		super();
		this.idUsuario = idUsuario;
		this.sistema = sistema;
		this.nivel = nivel;
		this.revisiones = new ArrayList<Revision>();
	}

	public int getIdUsuario() {
		return idUsuario;
	}
	
	public NivelDeUsuario getNivel() {
		return nivel;
	}	
	
	public List<Revision> getRevisiones() {
		return revisiones;
	}
	
	public void agregarMuestra(Imagen imagen, String especie, Ubicacion ubicacion) {
		/**
		 * Este metodo va a registrar una nueva muestra que hace el usuario en el sistema.
		 * Se envia la fecha actual al momento de llamar a el metodo por lo que la muestra va a tener la
		 * fecha en la que se registro.
		 */
		sistema.registrarMuestra(imagen, especie, ubicacion, idUsuario, LocalDate.now());
	}
	
	public void agregarRevision(Muestra muestra, Opinion opinion) {
		/**
		 * Este metodo va a agregar una revision de la muestra dada con la opinion Opinion dada.
		 * Va a guardar esa revision en su lista de revisiones.
		 * Esa revision va a ser enviada al sistema para que sea registrada.
		 */
		Revision revision = new Revision(opinion, LocalDate.now(), nivel);
		revisiones.add(revision);
		sistema.agregarRevision(muestra, revision);
	}
}
