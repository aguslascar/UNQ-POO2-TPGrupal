package ar.edu.unq.po2.tp.grupal.aplicacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.tp.grupal.muestra.Muestra;
import ar.edu.unq.po2.tp.grupal.revision.Revision;
import ar.edu.unq.po2.tp.grupal.zonaDeCobertura.Ubicacion;
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
	
	public void agregarRevision(Muestra muestra, Revision revision) {
		/**
		 * Este metodo agrega una revision a la muestra si y solo si,
		 * la muestra esta en la lista de muestras
		 */
		muestras.stream()
				.filter(m -> m.getidUsuario().equals(muestra.getidUsuario()))
				.findFirst().ifPresent(m -> m.agregarRevision(revision));
		//Me fijo si la muestra esta en la lista de muestras.
		//Si esta, agrego la revision.
		
	}

	public void registrarMuestra(Usuario usuario, LocalDate fecha, Imagen imagen, Ubicacion ubicacion, Opinion opinion) {
		/**
		 * Este metodo crea una nueva muestra con los parametros dados y la registra
		 * en la lista de muestras.
		 */
		Muestra muestra = new Muestra(usuario, LocalDate.now(), imagen, ubicacion, opinion);
		muestras.add(muestra);
	}

}
