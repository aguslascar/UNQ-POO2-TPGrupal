package ar.edu.unq.po2.tp.grupal.usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.po2.tp.grupal.revision.*;
import ar.edu.unq.po2.tp.grupal.aplicacion.*;
import ar.edu.unq.po2.tp.grupal.muestra.*;
import ar.edu.unq.po2.tp.grupal.zonaDeCobertura.*;

public class Usuario {
	/**
	 * Esta clase representa un Usuario en el sistema.
	 * El usuario va a tener la instancia del sistema.
	 * Para instanciarlo se requiere un id de usuario y un booleano que indique si tiene conocimiento validado.
	 * De no tener conocimiento validado su nivel sera basico por defecto.
	 * Va a contener una lista de revisiones que seran guardadas a medida que las haga.
	 * @author aguslascar
	 */
	private int idUsuario;
	private AplicacionWeb sistema;
	private NivelDeUsuario nivel;
	private List<Revision> revisiones;
	private boolean conocimientoValidado;
	
	public Usuario(int idUsuario, AplicacionWeb sistema, boolean conocimientoValidado) {
		super();
		this.idUsuario = idUsuario;
		this.sistema = sistema;
		this.conocimientoValidado = conocimientoValidado;
		this.revisiones = new ArrayList<Revision>();
		if(conocimientoValidado) {
			this.nivel = new Experto();
		}
		else {
			this.nivel = new Basico();
		}
		//De tener conocimiento validado, su nivel al crearse va a ser Experto.
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
	
	public void agregarRevision(Muestra muestra, Opinion opinion)  {
		/**
		 * Este metodo va a agregar una revision de la muestra dada con la opinion Opinion dada si y solo si
		 * el usuario es un usuario del sistema y la muestra existe en el sistema.
		 * Va a guardar esa revision en su lista de revisiones.
		 * Esa revision va a ser enviada al sistema para que sea registrada.
		 * 
		 */
		try {
			Revision revision = new Revision(opinion, LocalDate.now(), nivel);
			//Cuando a el sistema le envio en mensaje agregarRevision es cuando se puede generar la excepcion.
			sistema.agregarRevision(muestra, revision);
			//Si no hubo excepcion, la agrego a la lista de revisiones.
			revisiones.add(revision);
			} catch (Exception e) {
				System.out.println("Lo sentimos, no puede opinar sobre esta muestra.");
			}		
	}
	
	public List<Revision> revisionesUltimos30Dias() {
		/**
		 * Este metodo me retorna todas las revisiones del usuario que hizo en los ultimos 30 dias
		 * @return lista de Revision
		 */
		//Seteo una fecha a comparar que es la fecha actual menos 30 dias.
		LocalDate fechaAComparar = LocalDate.now().minusDays(30);
		//Filtro las revisiones que estan despues de esa fecha a comparar.
		return revisiones.stream()
					.filter(r -> r.getFecha().isAfter(fechaAComparar))
					.collect(Collectors.toList());
	}
	
	public void subirDeNivel() {
		/**
		 * Este metodo modifica el nivel del usuario y lo hace un usuario Experto.
		 */
		this.nivel = new Experto();
	}
	
	public void bajarDeNivel() {
		/**
		 * Este metodo modifica el nivel del usuario lo hace un usuario Basico.
		 * Chequeo que no tenga conocimiento validado, de tenerlo no se modifica su nivel
		 */
		if(!this.conocimientoValidado) {
			this.nivel = new Basico();
		}	
	}
}
