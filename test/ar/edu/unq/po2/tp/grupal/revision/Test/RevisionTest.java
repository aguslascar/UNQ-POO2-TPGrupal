package ar.edu.unq.po2.tp.grupal.revision.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.tp.grupal.revision.NivelDeUsuario;
import ar.edu.unq.po2.tp.grupal.revision.Opinion;
import ar.edu.unq.po2.tp.grupal.revision.Revision;

public class RevisionTest {

	private Revision revision;
	private Opinion opinion;
	private NivelDeUsuario nivelDeUsuario;
	private LocalDate fecha;
	private int idUsuario;
	
	@BeforeEach
	public void setUp() {
		opinion = mock(Opinion.class);
		nivelDeUsuario = mock(NivelDeUsuario.class);
		fecha = LocalDate.of(2021, 01, 20);    // Fecha 20 de Enero del 2021
		idUsuario = 2012002;
		revision = new Revision(opinion, fecha, nivelDeUsuario, idUsuario);
	}
	
	// Se testea que al momento de instanciar una Revision, la fecha quede guardada correctamente y sea accesible
	// públicamente
	@Test
	public void testObtenerLaFechaEnLaQueSeRealizoLaRevision() {
		assertEquals(revision.getFecha(), fecha);
	}
	
	// Se testea que al momento de instanciar una Revision, el nivel de usuario del usuario que realizó la revisión
	// sea guardado correctamente y sea accesible de manera pública
	@Test
	public void testObtenerElNivelDeUsuarioDelUsuarioQueRealizoLaRevision() {
		assertEquals(revision.getNivelDeUsuario(), nivelDeUsuario);
	}
	
	// Se testea que al momento de instanciar una Revisión, la opinión del usuario que realizó la revisión sea 
	// guardada correctamente y sea accesible de manera pública
	@Test
	public void testObtenerOpinionDelUsuarioQueRealizoLaRevision() {
		assertEquals(revision.getOpinion(), opinion);
	}
	
	// Se testea que al momento de instanciar una Revisión, el id del usuario que realizó la revisión sea guardado
	// correctamente y sea accesible de manera pública
	@Test
	public void testObtenerElIdDelUsuarioQueRealizoLaRevision() {
		assertEquals(revision.getIdUsuario(), idUsuario);
	}
}
