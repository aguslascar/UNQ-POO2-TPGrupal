package ar.edu.unq.po2.tp.grupal.aplicacion;

import java.time.LocalDate;


import java.util.ArrayList;
import java.util.List;
import ar.edu.unq.po2.tp.grupal.revision.*;
import ar.edu.unq.po2.tp.grupal.zonaDeCobertura.*;
import ar.edu.unq.po2.tp.grupal.filtro.*;
import ar.edu.unq.po2.tp.grupal.muestra.*;
import ar.edu.unq.po2.tp.grupal.usuario.*;

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
		/**
		 * Inicializa una aplicacion web con todos sus valores de cero.
		 */
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
	
	public void registrarNuevoUsuario(Usuario usuario) {
		/**
		 * Este metodo agrega un nuevo usuario recibido por parametro
		 * y lo agrega a la lista de usuarios.
		 * Por cada usuario nuevo se suma 1 al ultimo id del usuario y se le asigna ese id.
		 * @param un Usuario que es un usuario a cargar en el sistema.
		 */
		ultimoidUsuario +=1;
		usuario = this.asignaridUsuario(usuario, ultimoidUsuario);
		usuarios.add(usuario);
	}
	
	private Usuario asignaridUsuario(Usuario usuario, int id) {
		/**
		 * Este metodo le asigna un id de usuario al usuario recibido por parametro.
		 * @param un Usuario y un int que va a ser el id del usuario.
		 * @return Usuario
		 */
		usuario.setidUsuario(id);
		return usuario;
	}

	public void agregarRevision(Muestra muestra, Revision revision) throws Exception{
		/**
		 * Este metodo agrega una revision a la muestra si y solo si,
		 * el usuario que esta haciendo la revision es usuario del sistema y 
		 * la muestra esta en la lista de muestras.
		 * No podra hacer una revision sobre su propia muestra ni tampoco hacer una revision
		 * si ya lo hizo anteriormente sobre esa muestra.
		 * @throws Exception si la muestra no admite revisiones
		 * @param 	una Muestra que se le va a agregar una revision
		 * 			una Revision que es la revision ya hecha
		 */
		int idUsuario = revision.getid();
		//Tiene que existir la muestra en el sistema
		//tiene que existir el usuario en el sistema
		//No tiene que haber subido el mismo la muestra o ya haber opinado
		if(this.muestraExisteEnElSistema(muestra)
				&&   this.esUsuario(idUsuario)
				&& ! this.yaOpino(muestra, idUsuario)) {
					//Si se cumplen todas esas condiciones, envio a la muestra la revision
					//Aca se puede generar una excepcion ya que la muestra puede no aceptar mas revisiones
					// o no puede aceptar revisiones de usuarios basicos
					muestra.recibirRevision(revision);
		   	}
			else {
				//Este es el caso en el que no cumpla las condiciones pedidas
				throw new Exception("No se puede opinar porque no es usuario del sistema, la muestra no esta en sistema o ya opino");
			}
		}
		
private boolean muestraExisteEnElSistema(Muestra muestra) {
		/**
		 * Este metodo retorna un booleano si la muestra dada por parametro esta en la lista de muestras
		 * @param una Muestra
		 * @return un booleano indicando si la muestra esta en el sistema
		 */
		return muestras.contains(muestra);
	}
		
	private boolean yaOpino(Muestra muestra, int idUsuario) {
		/**
		 * Este metodo retorna true si el usuario ya hizo una revision de esa muestra
		 * o bien subio el la muestra. false en otro caso
		 * Precondicion: la lista de revisiones de la muestra no puede ser vacia.
		 * @param 	una Muestra en la cual voy a buscar si el usuario hizo una revision
		 * 			un int que representa el id a buscar en las revisiones 
		 * @return un booleano
		 */
		/*
		 * Como cuando un usuario sube una muestra, realiza una revision diciendo que tipo de insecto
		 * cree que es, sin tener que preguntarle a muestra el id del usuario, 
		 * puedo saber que si ese usuario realizo una revision de la muestra,
		 * o bien es el que la subio o bien ya realizo una revision.
		 * Considero esto para la resolucion. Si un usuario sube una muestra, existe 
		 * una revision que contenga su id.
		 */
		return muestra.getRevisiones()
						.stream()
						.anyMatch(r -> r.getid() == idUsuario);
	}


	public void registrarMuestra(Usuario usuario, LocalDate fecha, Imagen imagen, Ubicacion ubicacion, Opinion opinion) {
		/**
		 * Este metodo crea una nueva muestra con los parametros dados y la registra
		 * en la lista de muestras.
		 * Si el idUsuario esta dentro de los usuarios, la crea, sino no hace nada
		 * @param 	un Usuario que es el usuario que sube la muestra.
		 * 			un LocalDate que representa la fecha de subida
		 * 			una Imagen que representa la imagen de la muestra tomada
		 * 			una Ubicacion que representa la ubicacion donde fue tomada la muestra
		 * 			una Opinion que representa que tipo de insecto le parecio al usuario que subio la muestra
		 */
		int id = usuario.getidUsuario();
		//Chequeo que el idUsuario que paso como parametro sea un id que este dentro de mis usuarios
		if(this.esUsuario(id)) {
			Muestra muestra = new Muestra(usuario, LocalDate.now(), imagen, ubicacion, opinion);
			muestras.add(muestra);
			}
	}
	
	public boolean esUsuario(int id) {
		/**
		 * Este metodo retorna si el id esta dentro de la lista de usuarios.
		 * @param un int que representa el id del usuario
		 * @return un boolean
		 */
		return usuarios.stream().anyMatch(u -> u.getidUsuario() == id);
	}

	public List<Muestra> filtrarMuestras(Filtro filtro) {
		/**
		 * Este metodo filtra las muestras segun el criterio dado por el filtro que se utilice.
		 * @param un Filtro que puede ser cualquiera de las implementaciones de la clase Filtro
		 * @return una lista de Muestra.
		 */
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
		 * @param un Usuario a recategorizar.
		 */
		//es un usuario basico pero tiene el rendimiento esperado
		if(!usuario.esExperto() 
				&& this.tieneRendimientoEsperado(usuario)) {
			usuario.subirDeNivel();
		}
		//es experto no validado pero no tiene el rendimiento esperado.
		else if(!usuario.tieneConocimientoValidado() 
				&& usuario.esExperto() 
				&& ! this.tieneRendimientoEsperado(usuario)) {
			usuario.bajarDeNivel();
		}
}

	private boolean tieneRendimientoEsperado(Usuario usuario) {
		/**
		 * Este metodo chequea que el usuario haya realizado mas de 10 envios y
		 * mas de 20 revisiones que se hayan subido 30 dias anteriores a la fecha actual. 
		 * @param un Usuario el cual se va a ver si tiene el rendimiento esperado
		 * @return un booleano 
		 */
		return this.enviosUltimos30dias(usuario) > 10 && this.revisionesUltimos30dias(usuario) > 20;
	}

	private int enviosUltimos30dias(Usuario usuario) {
		/**
		 * Retorna la cantidad de envios de los ultimos 30 dias del usuario dado por parametro
		 * @param un Usuario del cual quiero saber la cantidad de envios
		 * @return un int.
		 */
		return usuario.cantidadEnviosUltimos30Dias();
	}

	private int revisionesUltimos30dias(Usuario usuario) {
		/**
		 * Retorna la cantidad de revisiones de los ultimos 30 dias del usuario dado por parametro
		 * @param un Usuario del cual quiero saber la cantidad de revisiones
		 * @return un int.
		 */
		return usuario.cantidadRevisionesUltimos30Dias();
	}

}