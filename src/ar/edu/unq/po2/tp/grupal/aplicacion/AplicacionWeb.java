package ar.edu.unq.po2.tp.grupal.aplicacion;

import java.time.LocalDate;





import java.util.ArrayList;
import java.util.List;
import ar.edu.unq.po2.tp.grupal.revision.*;
import ar.edu.unq.po2.tp.grupal.usuario.Usuario;
import ar.edu.unq.po2.tp.grupal.zonaDeCobertura.*;
import ar.edu.unq.po2.tp.grupal.filtro.Filtro;
import ar.edu.unq.po2.tp.grupal.muestra.*;
import ar.edu.unq.po2.tp.grupal.ong.*;
/**
 * Esta clase representa a la aplicacion web la cual se encargara de
 * registrar usuarios, muestras y revisiones.
 * Guarda una lista de Muestra, una lista de Usuario, una lista de ZonaDeCobertura y una lista de Ong.
 * Tiene un contador para saber el ultimo id del ultimo usuario registrado.
 * 
 * @author aguslascar
 */
public class AplicacionWeb {
	
	
	
	private int ultimoidUsuario;
	private List<Usuario> usuarios;
	private List<Muestra> muestras;
	private List<ZonaDeCobertura> zonas;
	private List<Ong> organizaciones;
	
	/**
	 * Inicializa una aplicacion web con todos sus valores de cero.
	 */
	public AplicacionWeb() {
		
		ultimoidUsuario = 0;
		usuarios = new ArrayList<Usuario>();
		muestras = new ArrayList<Muestra>();
		zonas = new ArrayList<ZonaDeCobertura>();
		organizaciones = new ArrayList<Ong>();
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	
	public List<Muestra> getMuestras() {
		return muestras;
	}
	
	public List<ZonaDeCobertura> getZonas() {
		return zonas;
	}

	public List<Ong> getOrganizaciones() {
		return organizaciones;
	}

	public int getUltimoidUsuario() {
		return ultimoidUsuario;
	}
	
	/**
	 * Este metodo agrega un nuevo usuario recibido por parametro
	 * y lo agrega a la lista de usuarios.
	 * Por cada usuario nuevo se suma 1 al ultimo id del usuario y se le asigna ese id.
	 * @param un Usuario que es un usuario a cargar en el sistema.
	 */
	public void registrarNuevoUsuario(Usuario usuario) {
		
		ultimoidUsuario +=1;
		usuario = this.asignaridUsuario(usuario, ultimoidUsuario);
		usuarios.add(usuario);
	}
	
	/**
	 * Este metodo le asigna un id de usuario al usuario recibido por parametro.
	 * @param un Usuario y un int que va a ser el id del usuario.
	 * @return Usuario
	 */
	private Usuario asignaridUsuario(Usuario usuario, int id) {
		
		usuario.setidUsuario(id);
		return usuario;
	}
	
	/**
	 * Este metodo agrega una revision a la muestra si y solo si,
	 * el usuario que esta haciendo la revision es usuario del sistema y 
	 * la muestra esta en la lista de muestras.
	 * No podra hacer una revision sobre su propia muestra ni tampoco hacer una revision
	 * si ya lo hizo anteriormente sobre esa muestra.
	 * @throws Exception si no se cumplen las condiciones anteriormente mencionadas
	 * @param 	una Muestra que se le va a agregar una revision
	 * 			un Usuario que es el usuario que esta opinando
	 * 			una Opinion que es la opinion del usuario
	 */
	public void agregarRevision(Muestra muestra, Usuario usuario, Opinion opinion) throws Exception{
		
		int idUsuario = usuario.getidUsuario();
		//Tiene que existir la muestra en el sistema
		//tiene que existir el usuario en el sistema
		//No tiene que haber subido el mismo la muestra o ya haber opinado
		if(this.muestraExisteEnElSistema(muestra)
				&&   this.esUsuario(idUsuario)
				&& ! this.yaOpino(muestra, idUsuario)) {
					//Si se cumplen todas esas condiciones, envio a la muestra la revision
					//Aca se puede generar una excepcion ya que la muestra puede no aceptar mas revisiones
					// o no puede aceptar revisiones de usuarios basicos
					usuario.agregarRevision(muestra, opinion);
		   	}
			else {
				//Este es el caso en el que no cumpla las condiciones pedidas
				throw new Exception("No se puede opinar porque no es usuario del sistema, la muestra no esta en sistema o ya opino");
			}
		}
	
	/**
	 * Este metodo retorna un booleano si la muestra dada por parametro esta en la lista de muestras
	 * @param una Muestra
	 * @return un booleano indicando si la muestra esta en el sistema
	 */	
	private boolean muestraExisteEnElSistema(Muestra muestra) {
		
		return muestras.contains(muestra);
	}
	
	/**
	 * Este metodo retorna true si el usuario ya hizo una revision de esa muestra
	 * o bien subio el la muestra. false en otro caso
	 * Precondicion: la lista de revisiones de la muestra no puede ser vacia.
	 * @param 	una Muestra en la cual voy a buscar si el usuario hizo una revision
	 * 			un int que representa el id a buscar en las revisiones 
	 * @return un booleano
	 */	
	private boolean yaOpino(Muestra muestra, int idUsuario) {
		
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
						.anyMatch(r -> r.getIdUsuario() == idUsuario);
	}

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
	public void registrarMuestra(Usuario usuario, LocalDate fecha, Imagen imagen, Ubicacion ubicacion, Opinion opinion) {
		
		int id = usuario.getidUsuario();
		//Chequeo que el idUsuario que paso como parametro sea un id que este dentro de mis usuarios
		if(this.esUsuario(id)) {
			Muestra muestra = new Muestra(id, LocalDate.now(), imagen, ubicacion, opinion, this);
			muestras.add(muestra);
			usuario.agregarMuestra(muestra);
			this.agregarAZona(muestra);
			}
	}
	
	/**
	 * Este metodo agrega una nueva muestra a una zona de cobertura(o mas de una si se solapan)
	 * @param una Muestra
	 */
	private void agregarAZona(Muestra muestra) {
		for(ZonaDeCobertura zona : zonas) {
				zona.agregarMuestra(muestra);
		}
	}
	
	
	/**
	 * Este metodo registra una organizacion a una zona de cobertura de su interes.
	 * Verifica que la organizacion sea parte del sistema
	 * Si la zona no existe, no hace nada
	 * @param una Ong
	 * @param una ZonaDeCobertura
	 */
	public void registrarOngAZona(Ong organizacion, ZonaDeCobertura zona) throws Exception{
		if(organizaciones.contains(organizacion) 
				&& zonas.contains(zona)) {
			zona.registrar(organizacion);
		}
		else if (organizaciones.contains(organizacion)) {
			throw new Exception("Error: la zona no existe");
		}
		else {
			throw new Exception("Error: la organizacion no es existe en el sistema");	
		}
	}

	/**
	 * Este metodo retorna si el id esta dentro de la lista de usuarios.
	 * @param un int que representa el id del usuario
	 * @return un boolean
	 */
	public boolean esUsuario(int id) {
		
		return usuarios.stream().anyMatch(u -> u.getidUsuario() == id);
	}
	
	/**
	 * Este metodo filtra las muestras segun el criterio dado por el filtro que se utilice.
	 * @param un Filtro que puede ser cualquiera de las implementaciones de la clase Filtro
	 * @return una lista de Muestra.
	 */
	public List<Muestra> filtrarMuestras(Filtro filtro) {
		
		return filtro.filtrar(muestras);
	}
	
	/**
	 * Este metodo agrega una ZonaDeCobertura a la lista de zonas
	 * @param una ZonaDeCobertura
	 */
	public void agregarZona(ZonaDeCobertura zona) {
		zonas.add(zona);
	}
	
	/**
	 * Este metodo agrega una ong a la lista de organizaciones
	 * @param una Ong
	 */
	public void agregarOrganizacion(Ong organizacion) {
		organizaciones.add(organizacion);
	}
	
	public void revisarNivelesDeUsuario() {
		/**
		 * Este metodo es el algoritmo para subir o bajar de categoria de experto.
		 * Si los usuarios tienen conocimiento validado, no se les puede modificar el nivel.
		 */
		usuarios.stream().forEach(u -> this.recategorizar(u));
	}
	
	/**
	 * Este metodo, dado un usuario lo recategoriza segun su rendimiento.
	 * Si el usuario tiene conocimiento validado, no se modifica su nivel.
	 * Si es basico y tiene el rendimiento esperado, sube de categoria.
	 * Si es experto y no cumple con el rendimiento esperado, baja de categoria.
	 * @param un Usuario a recategorizar.
	 */
	private void recategorizar(Usuario usuario) {
		
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
	/**
	 * Este metodo chequea que el usuario haya realizado mas de 10 envios y
	 * mas de 20 revisiones que se hayan subido 30 dias anteriores a la fecha actual. 
	 * @param un Usuario el cual se va a ver si tiene el rendimiento esperado
	 * @return un booleano 
	 */
	private boolean tieneRendimientoEsperado(Usuario usuario) {
		
		return this.enviosUltimos30dias(usuario) > 10 && this.revisionesUltimos30dias(usuario) > 20;
	}

	/**
	 * Retorna la cantidad de envios de los ultimos 30 dias del usuario dado por parametro
	 * @param un Usuario del cual quiero saber la cantidad de envios
	 * @return un int.
	 */
	private int enviosUltimos30dias(Usuario usuario) {
		
		return usuario.cantidadEnviosUltimos30Dias();
	}

	/**
	 * Retorna la cantidad de revisiones de los ultimos 30 dias del usuario dado por parametro
	 * @param un Usuario del cual quiero saber la cantidad de revisiones
	 * @return un int.
	 */
	private int revisionesUltimos30dias(Usuario usuario) {
		
		return usuario.cantidadRevisionesUltimos30Dias();
	}

	/**
	 * Este metodo se llama cuando una muestra ha sido verificada por expertos.
	 * Notifica a las ZonasDeCobertura que contengan a esa muestra, su validacion.
	 * @param una Muestra
	 */
	public void seValidoMuestra(Muestra muestra) {
		for(ZonaDeCobertura zona : zonas) {
			if(zona.esMuestraDeZona(muestra)) {
				zona.notificarValidacion();
			}
		}
	}
}