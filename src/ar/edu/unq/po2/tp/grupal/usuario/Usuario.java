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
	private List<Muestra> muestras;
	private List<Revision> revisiones;
	private boolean conocimientoValidado;
	
	public Usuario(AplicacionWeb sistema, boolean conocimientoValidado) {
		/**
		 * Se crea una instancia de usuario. Si tiene conocimiento validado
		 * es experto, de no tenerlo, es basico por defecto.
		 */
		super();
		this.sistema = sistema;
		this.conocimientoValidado = conocimientoValidado;
		this.muestras = new ArrayList<Muestra>();
		this.revisiones = new ArrayList<Revision>();
		if(conocimientoValidado) {
			this.nivel = new Experto();
		}
		else {
			this.nivel = new Basico();
		}
		//De tener conocimiento validado, su nivel al crearse va a ser Experto.
	}

	public int getidUsuario() {
		return idUsuario;
	}	
	
	public List<Revision> getRevisiones() {
		return revisiones;
	}
	

	public NivelDeUsuario getNivel() {
		return nivel;
	}
	
	public void setidUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	

	public boolean tieneConocimientoValidado() {
		/**
		 * Indica si el usuario tiene conocimiento validado externamente
		 * @return un booleano
		 */
		return conocimientoValidado;
	}

	public boolean esExperto() {
		/**
		 * Indica si el usuario tiene nivel Experto
		 * @return un booleano
		 */
		return nivel.esExperto();
	}
	
	public void agregarMuestra(Muestra muestra) {
		/**
		 * Este metodo se encarga de agregar muestras que haya hecho el usuario.
		 * Este metodo va a ser llamado solo por el sistema.
		 * @param una Muestra
		 */
		muestras.add(muestra);
	}
	
	public void agregarRevision(Muestra muestra, Opinion opinion)  {
		/**
		 * Este metodo va a agregar una revision de la muestra dada con la opinion Opinion dada si y solo si
		 * el usuario es un usuario del sistema y la muestra existe en el sistema.
		 * Va a guardar esa revision en su lista de revisiones.
		 * Esa revision va a ser enviada al sistema para que sea registrada.
		 * @param una Muestra a agregar revision y una Opinion que es la opinion de la revision a agregar a la muestra
		 * 
		 */
		try {
			Revision revision = new Revision(opinion, LocalDate.now(), nivel, idUsuario);
			//Cuando a el sistema le envio el mensaje agregarRevision es cuando se puede generar la excepcion.
			sistema.agregarRevision(muestra, revision);
			//Si no hubo excepcion, la agrego a la lista de revisiones.
			revisiones.add(revision);
			} catch (Exception e) {
				System.out.println("Lo sentimos, no puede opinar sobre esta muestra.");
			}		
	}
	
	public int cantidadRevisionesUltimos30Dias() {
		/**
		 * Este metodo retorna la cantidad de revisiones de los ultimos 30 dias.
		 * @return un int
		 */
		return this.revisionesUltimos30Dias().size();
	}
	
	public int cantidadEnviosUltimos30Dias() {
		/**
		 * Este metodo retorna la cantidad de envios que realizo el usuario en los ultimos
		 * 30 dias
		 * @return un int
		 */
		return this.enviosUltimos30dias().size();
	}
	
	private List<Muestra> enviosUltimos30dias() {
		/**
		 * De los envios del usuario, filtra los envios de muestras de los ultimos 30 dias.
		 * @return un int que representa la cantidad de envios de los ultimos 30 dias
		 */
		//Creo una fecha a comparar que es la fecha actual menos 30 dias
		LocalDate fechaAComparar = LocalDate.now().minusDays(30);
		//Tomo la lista de envios de muestras en sistema que haya realizado el usuario
		//A esas muestras, filtro solo las que hayan sido realizadas los ultimos 30 dias.
		return muestras = muestras.stream()
							.filter(m -> m.getFecha().isAfter(fechaAComparar))
							.collect(Collectors.toList());
		
	}
	
	private List<Revision> revisionesUltimos30Dias() {
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
		 * No puede llamarse si el usuario es un usuario validado externamente.
		 */
			this.nivel = new Basico();	
	}


}
