package ar.edu.unq.po2.tp.grupal.muestra.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.tp.grupal.muestra.Muestra;
import ar.edu.unq.po2.tp.grupal.muestra.Ubicacion;
import ar.edu.unq.po2.tp.grupal.muestra.Usuario;
import ar.edu.unq.po2.tp.grupal.revision.NivelDeUsuario;
import ar.edu.unq.po2.tp.grupal.revision.Opinion;
import ar.edu.unq.po2.tp.grupal.revision.Revision;

public class MuestraTest {
	
	private Revision revision1;
	private Revision revision2;
	private Usuario usuario;
	private Usuario usuario2;
	private LocalDate fecha;
	private Muestra muestra;
	private Ubicacion ubicacion;
	private String foto;
	private Opinion opinionAutor;
	private Opinion opinion1;
	private Opinion opinion2;
	private NivelDeUsuario nivelDeUsuario;
	
	@BeforeEach
	public void setUp() throws Exception {
		usuario = mock(Usuario.class);
		usuario2 = mock(Usuario.class);
		nivelDeUsuario = mock(NivelDeUsuario.class);
		fecha = LocalDate.of(2012, 12, 20);    // Fecha 20 de Diciembre de 2012    
		ubicacion = mock(Ubicacion.class);
	    revision1 = mock(Revision.class);
	    revision2 = mock(Revision.class);
	    foto = "Vinchuca.jpg";
	    opinionAutor = mock(Opinion.class);
	    opinion1 = mock(Opinion.class);
	    opinion2 = mock(Opinion.class);
		muestra = new Muestra(usuario, fecha, foto, ubicacion, opinionAutor); 
	}
	
	// Se testea que al instanciar una nueva Muestra, el idUsuario este bien almacenado y sea accesible publicamente
	@Test
	public void testConocerElIdDelUsuarioQueSubioLaMuestra() throws Exception {
		when(usuario.getIdUsuario()).thenReturn(20129319);
		
		Muestra nuevaMuestra = new Muestra(usuario, fecha, foto, ubicacion, opinionAutor);
		
		assertEquals(nuevaMuestra.getIdUsuario(), usuario.getIdUsuario());
	}
	
	// Se testea que al instanciar una nueva Muestra, la fecha este bien almacenada y sea accesible publicamente
	@Test
	public void testConocerLaFechaEnLaQueSeSubioLaMuestra() {
		assertEquals(muestra.getFecha(), fecha);
	}
	
	// Se testea que al instanciar una nueva Muestra, la foto este bien almacenada y sea accesible publicamente
	@Test
	public void testObtenerLaFotoQueSeAdjuntoALaMuestra() {
		assertEquals(muestra.getFoto(), foto);
	}
	
	// Se testea que al instanciar una nueva Muestra, la ubicacion este bien almacenada y sea accesible publicamente
	@Test
	public void testConocerLaUbicacionEnLaQueSeRealizoLaMuestra() {
		assertEquals(muestra.getUbicacion(), ubicacion);
	}
	
	// Se testea que al instanciar una nueva Muestra, la opinion del usuario que subio la muestra este bien almacenada y sea accesible publicamente
	@Test
	public void testConocerLaOpinionDelUsuarioQueSubioLaMuestra() {
		// sin implementar, se requiere conocer la implementacion de la opinion
	}
	
	// Se testea que al instanciar una nueva Muestra, la lista de revisiones se inicialice con la opinion del autor
	@Test
	public void testObtenerUnaListaDeRevisionesUnicamenteConLaRevisionDelAutorEnUnaMuestraRecienCreada() {
		assertTrue(muestra.getRevisiones().size() == 1);
		assertEquals(muestra.getRevisiones().get(0).getOpinion(), muestra.getOpinion());
	}
	
	// Se testea que al momento de agregar una nueva revision a la lista de revisiones en Muestra,
	// esta se agregue correctamente
	@Test
	public void testAgregarUnaRevisionALaListaDeRevisiones() throws Exception {
		when(revision1.getNivelDeUsuario()).thenReturn(nivelDeUsuario);
		when(nivelDeUsuario.esExperto()).thenReturn(false);
		
		muestra.recibirRevision(revision1);
		
		assertTrue(muestra.getRevisiones().contains(revision1));
	}
	
	// Se testea que al obtener el resultado actual de una muestra de la que no opino nadie mas que el autor,
	// se retorne la opinión del autor y el mensaje sea el esperado
	@Test
	public void testObtenerResultadoActualSinRevisionesSoloCuentaLaOpinionDelAutor() {
		when(opinionAutor.getDescripcion()).thenReturn("Vinchuca Sordida");
		
		assertEquals(muestra.getResultadoActual(), muestra.getOpinion().getDescripcion() +
				     " - " + muestra.getEstado().getDescripcion());
	}
	
	// Se testea que cuando la opinion de otros usuarios es mas votada que la opinion del autor, entonces cuente
	// la opinión de los usuarios que tenga mas votos y se retorne el mensaje esperado
	@Test
	public void testObtenerResultadoActualCuandoLaOpinionDeLosUsuariosLeGanaALaOpinionDelAutor() throws Exception {
		when(opinionAutor.getDescripcion()).thenReturn("Vinchuca Sordida");
		when(revision1.getOpinion()).thenReturn(opinion1);
		when(revision2.getOpinion()).thenReturn(opinion2);
		when(revision1.getNivelDeUsuario()).thenReturn(nivelDeUsuario);
		when(revision2.getNivelDeUsuario()).thenReturn(nivelDeUsuario);
		when(nivelDeUsuario.esExperto()).thenReturn(false);
		when(opinion1.getDescripcion()).thenReturn("Vinchuca Infestans");
		when(opinion2.getDescripcion()).thenReturn("Vinchuca Infestans");
		
		muestra.recibirRevision(revision1);
		muestra.recibirRevision(revision2);
		
		assertEquals(muestra.getResultadoActual(), "Vinchuca Infestans" + " - " + muestra.getEstado().getDescripcion());
	}
	
	// Se testea que al obtener un empate entre opiniones, por mas que se encuentre la opinión del autor, entonces
	// se retorne como resultado No definido, de la manera esperada
	@Test
	public void testObtenerResultadoActualCuandoUnUsuarioOpinaDistintoAlAutor() throws Exception {
		when(opinionAutor.getDescripcion()).thenReturn("Vinchuca Infestans");
		when(revision1.getOpinion()).thenReturn(opinion1);
		when(opinion1.getDescripcion()).thenReturn("Chinche Foliada");
		when(revision1.getNivelDeUsuario()).thenReturn(nivelDeUsuario);
		when(nivelDeUsuario.esExperto()).thenReturn(false);
		
		muestra.recibirRevision(revision1);
		
		assertEquals(muestra.getResultadoActual(), "No definido" + " - " + muestra.getEstado().getDescripcion());
	}
	
	@Test
	public void testPermitirACualquierUsuarioOpinarCuandoElEstadoDeMuestraEsSinVerificar() throws Exception {
		when(revision1.getNivelDeUsuario()).thenReturn(nivelDeUsuario);
		when(nivelDeUsuario.esExperto()).thenReturn(false);
		
		muestra.recibirRevision(revision1);
		
		assertTrue(muestra.getRevisiones().contains(revision1));
	}
	
	@Test
	public void testCuandoUnaNuevaMuestraEsCreadaEntoncesElEstadoEsMuestraSinVerificar() {
		assertEquals(muestra.getEstado().getDescripcion(), "Muestra sin verificar");
	}
	
	@Test
	public void testCuandoUnUsuarioExpertoOpinaEntoncesElEstadoDeLaMuestraCambiaAOpinadoPorExpertos() throws Exception {
		when(nivelDeUsuario.esExperto()).thenReturn(true);
		when(revision1.getNivelDeUsuario()).thenReturn(nivelDeUsuario);
		
		muestra.recibirRevision(revision1);
		
		assertEquals(muestra.getEstado().getDescripcion(), "Muestra opinada por expertos"); 
	}
}
