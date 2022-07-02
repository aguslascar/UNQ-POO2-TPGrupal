package ar.edu.unq.po2.tp.grupal.revision.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.tp.grupal.revision.Opinion;
import ar.edu.unq.po2.tp.grupal.revision.Revision;
import ar.edu.unq.po2.tp.grupal.usuario.NivelDeUsuario;
import ar.edu.unq.po2.tp.grupal.usuario.Usuario;

public class RevisionTest {

	private Revision revision;
	private Opinion opinion;
	private NivelDeUsuario nivelDeUsuario;
	private LocalDate fecha;
	private Usuario usuario;
	
	@BeforeEach
	public void setUp() {
		opinion = mock(Opinion.class);
		nivelDeUsuario = mock(NivelDeUsuario.class);
		fecha = LocalDate.of(2021, 01, 20);    // Fecha 20 de Enero del 2021
		usuario = mock(Usuario.class);
	}
	
	// Se testea que al momento de instanciar una Revision, la fecha quede guardada correctamente y sea accesible
	// públicamente
	@Test
	public void testObtenerLaFechaEnLaQueSeRealizoLaRevision() {
		revision = new Revision(opinion, fecha, usuario);
		
		assertEquals(revision.getFecha(), fecha);
	}
	
	// Se testea que al momento de instanciar una Revision, el nivel de usuario del usuario que realizó la revisión
	// sea guardado correctamente y sea accesible de manera pública
	@Test
	public void testObtenerElNivelDeUsuarioDelUsuarioQueRealizoLaRevision() {
		when(usuario.getNivel()).thenReturn(nivelDeUsuario);
		
		revision = new Revision(opinion, fecha, usuario);
		
		assertEquals(revision.getNivelDeUsuario(), nivelDeUsuario);
	}
	
	// Se testea que al momento de instanciar una Revisión, la opinión del usuario que realizó la revisión sea 
	// guardada correctamente y sea accesible de manera pública
	@Test
	public void testObtenerOpinionDelUsuarioQueRealizoLaRevision() {
		revision = new Revision(opinion, fecha, usuario);
		
		assertEquals(revision.getOpinion(), opinion);
	}
	
	// Se testea que al momento de instanciar una Revisión, el id del usuario que realizó la revisión sea guardado
	// correctamente y sea accesible de manera pública
	@Test
	public void testObtenerElIdDelUsuarioQueRealizoLaRevision() {
		when(usuario.getidUsuario()).thenReturn(212);
		
		revision = new Revision(opinion, fecha, usuario);
		
		assertEquals(revision.getIdUsuario(), usuario.getidUsuario());
	}
}
