package ar.edu.unq.po2.tp.grupal.aplicacion;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.po2.tp.grupal.muestra.Muestra;
import ar.edu.unq.po2.tp.grupal.revision.Revision;
import ar.edu.unq.po2.tp.grupal.zonaDeCobertura.Ubicacion;
import ar.edu.unq.po2.tp.grupal.filtro.Filtro;
import ar.edu.unq.po2.tp.grupal.muestra.*;
import ar.edu.unq.po2.tp.grupal.usuario.*;
import ar.edu.unq.po2.tp.grupal.revision.*;

public class AplicacionWeb {
	
	/**
	 * Esta clase representa a la aplicacion web la cual se encargara de
	 * registrar usuarios, muestras y revisiones.
	 * Guarda una lista de Muestra y una lista de Usuario.
	 * Tiene un contador para saber el ultimo id del ultimo usuario registrado.
	 * 
	 * @author aguslascar
	 */
	
	private int ultimoidUsuario;
	private List<Usuario> usuarios;
	private List<Muestra> muestras;
	
	
	public AplicacionWeb() {
		ultimoidUsuario = 0;
		usuarios = new ArrayList<Usuario>();
		muestras = new ArrayList<Muestra>();
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	
	public List<Muestra> getMuestras() {
		return muestras;
	}
	
	public int getUltimoidUsuario() {
		return ultimoidUsuario;
	}
	
	public void registrarNuevoUsuario(boolean conocimientoValidado) {
		/**
		 * Este metodo crea un nuevo usuario segun los parametros dados
		 * y lo agrega a la lista de usuarios.
		 * Por cada usuario nuevo se suma 1 al ultimo id del usuario.
		 */
		ultimoidUsuario += 1;
		Usuario usuario = new Usuario(ultimoidUsuario, this, conocimientoValidado);
		usuarios.add(usuario);
	}
	
	public void agregarRevision(Muestra muestra, Revision revision, int idUsuario) throws Exception {
		/**
		 * Este metodo agrega una revision a la muestra si y solo si,
		 * el usuario es usuario del sistema y la muestra esta en la lista de muestras
		 */
		if(this.esUsuario(idUsuario)) {
			//Primero filtro que la muestra dada por parametro este en el sistema.
			//Utilizo equals para comprobar si alguna de las muestras del sistema
			//es igual a la muestra dada por parametro
			muestras.stream()
					.filter(m -> m.equals(muestra))
					.findFirst().ifPresent(m -> m.agregarRevision(revision));
		//Me fijo si la muestra esta en la lista de muestras.
		//Si esta, agrego la revision.
		}
		else {
			throw new Exception();
		}
		
	}

	public void registrarMuestra(int idUsuario, LocalDate fecha, Imagen imagen, Ubicacion ubicacion, Opinion opinion) {
		/**
		 * Este metodo crea una nueva muestra con los parametros dados y la registra
		 * en la lista de muestras.
		 * Si el idUsuario esta dentro de los usuarios, la crea, sino no hace nada
		 */
		//Chequeo que el idUsuario que paso como parametro sea un id que este dentro de mis usuarios
		if(this.esUsuario(idUsuario)) {
			Muestra muestra = new Muestra(idUsuario, LocalDate.now(), imagen, ubicacion, opinion);
			muestras.add(muestra);
			}
	}
	
	public boolean esUsuario(int id) {
		/**
		 * Este metodo retorna si el id esta dentro de la lista de usuarios.
		 */
		return usuarios.stream().anyMatch(u -> u.getidUsuario() == id);
	}

	public List<Muestra> filtrarMuestras(Filtro filtro) {
		return filtro.filtrar(muestras);
	}
	
	public void revisarNivelesDeUsuario() {
		/**
		 * Este metodo es el algoritmo para subir o bajar de categoria de experto.
		 * Si los usuarios tienen conocimiento validado, no se les puede modificar el nivel.
		 */
		usuarios.stream().forEach(u -> this.recategorizar(u));
	}

	private void recategorizar(Usuario usuario) {
		/**
		 * Este metodo, dado un usuario lo recategoriza segun su rendimiento.
		 * Si el usuario tiene conocimiento validado, no se modifica su nivel.
		 * Si es basico y tiene el rendimiento esperado, sube de categoria.
		 * Si es experto y no cumple con el rendimiento esperado, baja de categoria.
		 */
		//es un usuario basico pero tiene el rendimiento esperado
		if(!usuario.getNivel().esExperto() && this.tieneRendimientoEsperado(usuario)) {
			usuario.subirDeNivel();
		}
		//no tiene conocimiento validado y es experto o basico pero no tiene el rendimiento esperado.
		else if(!usuario.tieneConocimientoValidado() && ! this.tieneRendimientoEsperado(usuario)) {
			usuario.bajarDeNivel();
		}
}

	private boolean tieneRendimientoEsperado(Usuario usuario) {
		/**
		 * Este metodo chequea que el usuario haya realizado mas de 10 envios y
		 * mas de 20 revisiones que se hayan subido 30 dias anteriores a la fecha actual. 
		 * @return un booleano 
		 */
		return this.enviosUltimos30dias(usuario) > 10 && this.revisionesUltimos30dias(usuario) > 20;
	}

	private int revisionesUltimos30dias(Usuario usuario) {
		/**
		 * Retorna la cantidad de revisiones de los ultimos 30 dias del usuario dado por parametro
		 * @return un int.
		 */
		return usuario.revisionesUltimos30dias().size();
	}

	private int enviosUltimos30dias(Usuario usuario) {
		/**
		 * De los envios del usuario, filtra los envios de muestras de los ultimos 30 dias.
		 * @return un int que representa la cantidad de envios de los ultimos 30 dias
		 */
		//Creo una fecha a comparar que es la fecha actual menos 30 dias
		LocalDate fechaAComparar = LocalDate.now().minusDays(30);
		//Tomo la lista de envios de muestras en sistema que haya realizado el usuario
		List<Muestra> muestras = this.enviosDeUsuario(usuario);
		//A esas muestras, filtro solo las que hayan sido realizadas los ultimos 30 dias.
		muestras = muestras.stream()
							.filter(m -> m.getFecha().isAfter(fechaAComparar))
							.collect(Collectors.toList());
		//Retorno la cantidad de muestras de los ultimos 30 dias.
		return muestras.size();
	}

	private List<Muestra> enviosDeUsuario(Usuario usuario) {
		/**
		 * Este metodo devuelve una lista de Muestra que son los envios que realizo el usuario especificado 
		 * en el sistema
		 * @return una lista de Muestra
		 */
		//Filtro las muestras del sistema segun tengan el mismo id que el del usuario
		//dado por parametro.
		return muestras.stream()
				.filter(m -> m.getidUsuario() == usuario.getidUsuario())
				.collect(Collectors.toList());
	}
}