package ar.edu.unq.po2.tp.grupal.muestra.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.tp.grupal.aplicacion.AplicacionWeb;
import ar.edu.unq.po2.tp.grupal.aplicacion.Imagen;
import ar.edu.unq.po2.tp.grupal.muestra.Muestra;
import ar.edu.unq.po2.tp.grupal.revision.Opinion;
import ar.edu.unq.po2.tp.grupal.revision.Revision;
import ar.edu.unq.po2.tp.grupal.usuario.NivelDeUsuario;
import ar.edu.unq.po2.tp.grupal.zonaDeCobertura.Ubicacion;

public class MuestraTest {
	
	private Revision revision1;
	private Revision revision2;
	private Revision revision3;
	private Revision revision4;
	private LocalDate fecha;
	private Muestra muestra;
	private Ubicacion ubicacion;
	private Imagen foto;
	private Opinion opinionAutor;
	private Opinion opinion1;
	private Opinion opinion2;
	private NivelDeUsuario nivelDeUsuario1;
	private NivelDeUsuario nivelDeUsuario2;
	private int idUsuario;
	private AplicacionWeb app;
	
	@BeforeEach
	public void setUp() throws Exception {
		nivelDeUsuario1 = mock(NivelDeUsuario.class);
		nivelDeUsuario2 = mock(NivelDeUsuario.class);
		fecha = LocalDate.of(2012, 12, 20);    // Fecha 20 de Diciembre de 2012    
		ubicacion = mock(Ubicacion.class);
	    revision1 = mock(Revision.class);
	    revision2 = mock(Revision.class);
	    revision3 = mock(Revision.class);
	    revision4 = mock(Revision.class);
	    foto = mock(Imagen.class);
	    opinionAutor = mock(Opinion.class);
	    opinion1 = mock(Opinion.class);
	    opinion2 = mock(Opinion.class);
	    idUsuario = 2578670;
	    app = mock(AplicacionWeb.class);
		muestra = new Muestra(idUsuario, fecha, foto, ubicacion, opinionAutor, app); 
	}
	
	// Se testea que al instanciar una nueva Muestra, el idUsuario este bien almacenado y sea accesible publicamente
	@Test
	public void testConocerElIdDelUsuarioQueSubioLaMuestra() throws Exception {
		assertEquals(muestra.getIdUsuario(), idUsuario);
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
		when(revision1.getNivelDeUsuario()).thenReturn(nivelDeUsuario1);
		when(nivelDeUsuario1.esExperto()).thenReturn(false);
		
		muestra.recibirRevision(revision1);
		
		assertTrue(muestra.getRevisiones().contains(revision1));
	}
	
	// Se testea que al obtener el resultado actual de una muestra de la que no opino nadie mas que el autor,
	// se retorne la opinión del autor y el mensaje sea el esperado
	@Test
	public void testObtenerResultadoActualSinRevisionesSoloCuentaLaOpinionDelAutor() {
		when(opinionAutor.getDescripcion()).thenReturn("Vinchuca Sordida");
		
		assertEquals(muestra.getResultadoActual(), muestra.getOpinion().getDescripcion());
	}
	
	// Se testea que cuando la opinion de otros usuarios es mas votada que la opinion del autor, entonces cuente
	// la opinión de los usuarios que tenga mas votos y se retorne el mensaje esperado
	@Test
	public void testObtenerResultadoActualCuandoLaOpinionDeLosUsuariosLeGanaALaOpinionDelAutor() throws Exception {
		when(opinionAutor.getDescripcion()).thenReturn("Vinchuca Sordida");
		when(revision1.getOpinion()).thenReturn(opinion1);
		when(revision2.getOpinion()).thenReturn(opinion2);
		when(revision1.getNivelDeUsuario()).thenReturn(nivelDeUsuario1);
		when(revision2.getNivelDeUsuario()).thenReturn(nivelDeUsuario1);
		when(nivelDeUsuario1.esExperto()).thenReturn(false);
		when(opinion1.getDescripcion()).thenReturn("Vinchuca Infestans");
		when(opinion2.getDescripcion()).thenReturn("Vinchuca Infestans");
		
		muestra.recibirRevision(revision1);
		muestra.recibirRevision(revision2);
		
		assertEquals(muestra.getResultadoActual(), "Vinchuca Infestans");
	}
	
	// Se testea que al obtener un empate entre opiniones, por mas que se encuentre la opinión del autor, entonces
	// se retorne como resultado No definido, de la manera esperada
	@Test
	public void testObtenerResultadoActualCuandoUnUsuarioOpinaDistintoAlAutor() throws Exception {
		when(opinionAutor.getDescripcion()).thenReturn("Vinchuca Infestans");
		when(revision1.getOpinion()).thenReturn(opinion1);
		when(opinion1.getDescripcion()).thenReturn("Chinche Foliada");
		when(revision1.getNivelDeUsuario()).thenReturn(nivelDeUsuario1);
		when(nivelDeUsuario1.esExperto()).thenReturn(false);
		
		muestra.recibirRevision(revision1);
		
		assertEquals(muestra.getResultadoActual(), "No definido");
	}
	
	// Se testea que cuando el estado de muestra es opinada por expertos, entonces solo cuenten las opiniones de 
	// expertos para retornar el resultado actual de muestra
	@Test
	public void testCuandoElEstadoDeMuestraEsOpinadaPorExpertosEntoncesLasOpinionesQueCuentanParaElResultadoActualSonLaDeExpertos() throws Exception {
		when(revision1.getNivelDeUsuario()).thenReturn(nivelDeUsuario1);
		when(revision2.getNivelDeUsuario()).thenReturn(nivelDeUsuario1);
		when(revision3.getNivelDeUsuario()).thenReturn(nivelDeUsuario2);
		when(revision4.getNivelDeUsuario()).thenReturn(nivelDeUsuario2);
		when(nivelDeUsuario1.esExperto()).thenReturn(false);
		when(nivelDeUsuario2.esExperto()).thenReturn(true);
		when(revision1.getOpinion()).thenReturn(opinion1);
		when(revision2.getOpinion()).thenReturn(opinion1);
		when(revision3.getOpinion()).thenReturn(opinion1);
		when(revision4.getOpinion()).thenReturn(opinion2);
		when(opinion1.getDescripcion()).thenReturn("Vinchuca Sordida");
		when(opinion2.getDescripcion()).thenReturn("Chinche Foliada");
		
		muestra.recibirRevision(revision1);
		muestra.recibirRevision(revision2);
		
		assertEquals(muestra.getResultadoActual(), "Vinchuca Sordida");
		// El estado de la muestra cambia a opinadaPorExpertos
		muestra.recibirRevision(revision3);
		muestra.recibirRevision(revision4);
		
		assertEquals(muestra.getResultadoActual(), "No definido");
	}
	
	// Se testea que cuando una nueva muestra sea creada entonces su estado sea sin verificar
	@Test
	public void testCuandoUnaNuevaMuestraEsCreadaEntoncesElEstadoEsMuestraSinVerificar() {
		assertEquals(muestra.getEstado().getDescripcion(), "Muestra sin verificar");
	}
	
	// Se testea que cuando por primera vez opine un usuario nivel experto, entonces el estado de muestra cambie a 
	// estado opinada por expertos
	@Test
	public void testCuandoUnUsuarioExpertoOpinaEntoncesElEstadoDeLaMuestraCambiaAOpinadoPorExpertos() throws Exception {
		when(nivelDeUsuario1.esExperto()).thenReturn(true);
		when(revision1.getNivelDeUsuario()).thenReturn(nivelDeUsuario1);
		
		muestra.recibirRevision(revision1);
		
		assertEquals(muestra.getEstado().getDescripcion(), "Muestra opinada por expertos"); 
	}
	
	// Se testea que cuando dos usuarios expertos coincidan en opinion, entonces el estado de la muestra cambie a
	// muestra verificada
	@Test
	public void testCuandoDosUsuariosExpertosCoincidenEnSuOpinionEntoncesElEstadoDeLaMuestraCambiaAVerificada() throws Exception {
		when(revision1.getOpinion()).thenReturn(opinion1);
		when(revision2.getOpinion()).thenReturn(opinion2);
		when(opinion1.getDescripcion()).thenReturn("Vinchuca Infestans");
		when(opinion2.getDescripcion()).thenReturn("Vinchuca Infestans");
		when(revision1.getNivelDeUsuario()).thenReturn(nivelDeUsuario1);
		when(revision2.getNivelDeUsuario()).thenReturn(nivelDeUsuario1);
		when(nivelDeUsuario1.esExperto()).thenReturn(true);
		
		muestra.recibirRevision(revision1);
		muestra.recibirRevision(revision2);
		
		assertEquals(muestra.getEstado().getDescripcion(), "Muestra verificada");
	}
	
	// Se testea que cuando el estado de la muestra es sin verificar, entonces se le permita a cualquier usuario
	// opinar sobre esta muestra
	@Test
	public void testPermitirACualquierUsuarioOpinarCuandoElEstadoDeMuestraEsSinVerificar() throws Exception {
		when(revision1.getNivelDeUsuario()).thenReturn(nivelDeUsuario1);
		when(nivelDeUsuario1.esExperto()).thenReturn(false);
		when(revision2.getNivelDeUsuario()).thenReturn(nivelDeUsuario2);
		when(nivelDeUsuario2.esExperto()).thenReturn(true);
			
		muestra.recibirRevision(revision1);
		
		// Se realiza esta validacion en este punto, ya que si se prueba despues de revision2, el estado habrá
		// cambiado a opinada por expertos
		assertEquals(muestra.getEstado().getDescripcion(), "Muestra sin verificar");
		
		muestra.recibirRevision(revision2);
			
		assertTrue(muestra.getRevisiones().contains(revision1));
		assertTrue(muestra.getRevisiones().contains(revision2));
	}
	
	// Se testea que cuando el estado de muestra es opinada por expertos, entonces se le permita unicamente a 
	// usuarios expertos opinar sobre la muestra
	@Test
	public void testPermitirSoloOpinarAUsuariosExpertosCuandoElEstadoDeMuestraEsOpinadaPorExpertos() throws Exception {
		when(revision1.getNivelDeUsuario()).thenReturn(nivelDeUsuario1);
		when(nivelDeUsuario1.esExperto()).thenReturn(true);
		when(revision1.getOpinion()).thenReturn(opinion1);
		when(opinion1.getDescripcion()).thenReturn("Ninguna");
		when(revision2.getNivelDeUsuario()).thenReturn(nivelDeUsuario2);
		when(nivelDeUsuario2.esExperto()).thenReturn(false);
		when(revision2.getOpinion()).thenReturn(opinion2);
		when(opinion2.getDescripcion()).thenReturn("Vinchuca Sordida");
		
		// Cambia el estado de la muestra a opinada por expertos
		muestra.recibirRevision(revision1);
		try {
		    // Se intenta agregar una revision de usuario basico y se espera una excepcion
			muestra.recibirRevision(revision2);	
		} catch(Exception e) {
		
		    assertTrue(muestra.getRevisiones().contains(revision1));
		    assertFalse(muestra.getRevisiones().contains(revision2));
		}
	}
	
	// Se testea que cuando el estado de muestra es verificada, entonces no se permita a ningun tipo de usuario
	// opinar sobre esta muestra
	@Test 
	public void testNoPermitirANingunTipoDeUsuarioOpinarSobreUnaMuestraConEstadoVerificada() throws Exception {
		when(revision1.getNivelDeUsuario()).thenReturn(nivelDeUsuario1);
		when(revision2.getNivelDeUsuario()).thenReturn(nivelDeUsuario1);
		when(revision3.getNivelDeUsuario()).thenReturn(nivelDeUsuario1);
		when(revision4.getNivelDeUsuario()).thenReturn(nivelDeUsuario2);
		when(nivelDeUsuario1.esExperto()).thenReturn(true);
		when(nivelDeUsuario2.esExperto()).thenReturn(false);
		when(revision1.getOpinion()).thenReturn(opinion1);
		when(revision2.getOpinion()).thenReturn(opinion2);
		when(revision3.getOpinion()).thenReturn(opinion2);
		when(revision4.getOpinion()).thenReturn(opinion1);
		when(opinion1.getDescripcion()).thenReturn("Vinchuca Sordida");
		when(opinion2.getDescripcion()).thenReturn("Vinchuca Sordida");
		
		// Cambia el estado de la muestra a verificada 
		muestra.recibirRevision(revision1);
		muestra.recibirRevision(revision2);	
		try {
			// Se intenta agregar una revision de usuario basico y otro experto, y se espera una excepcion
			muestra.recibirRevision(revision3);	
			muestra.recibirRevision(revision4);
			fail("Se esperaba una excepcion");
		} catch(Exception e) {
			assertTrue(muestra.getRevisiones().contains(revision1));
			assertTrue(muestra.getRevisiones().contains(revision2));
			assertFalse(muestra.getRevisiones().contains(revision3));
			assertFalse(muestra.getRevisiones().contains(revision4));
			
		}
	}
	
	// Se testea que se reciba el String esperado cuando se pida el nivel de revision actual de una muestra
	// sin verificar
	@Test
	public void testRecibirMensajeEsperadoCuandoSePideElNivelDeRevisionDeUnaMuestraSinVerificar() {
		assertEquals(muestra.getNivelDeRevision(), "Muestra sin verificar");
	}
	
	// Se testea que se reciba el String esperado cuando se pida el nivel de revision actual de una muestra
	// opinada por expertos
	@Test
	public void testRecibirMensajeEsperadoCuandoSePideElNivelDeRevisionActualDeUnaMuestraOpinadaPorExpertos() throws Exception {
		when(revision1.getNivelDeUsuario()).thenReturn(nivelDeUsuario1);
		when(nivelDeUsuario1.esExperto()).thenReturn(true);
		when(revision1.getOpinion()).thenReturn(opinion1);
		when(opinion1.getDescripcion()).thenReturn("Vinchuca Infestans");
		when(opinionAutor.getDescripcion()).thenReturn("Vinchuca Sordida");
		
		// Cambia el estado de la muestra a opinada por expertos
		muestra.recibirRevision(revision1);
		
		assertEquals(muestra.getNivelDeRevision(), "Muestra opinada por expertos");
	}
	
	// Se testea que se reciba el String esperado cuando se pida el estado actual de una muestra verificada
	@Test
	public void testRecibirMensajeEsperadoCuandoSePideElResultadoActualDeUnaMuestraVerificada() throws Exception {
		when(revision1.getNivelDeUsuario()).thenReturn(nivelDeUsuario1);
		when(nivelDeUsuario1.esExperto()).thenReturn(true);
		when(revision1.getOpinion()).thenReturn(opinion1);
		when(opinion1.getDescripcion()).thenReturn("Vinchuca Sordida");
		when(revision2.getNivelDeUsuario()).thenReturn(nivelDeUsuario1);
		when(revision2.getOpinion()).thenReturn(opinion2);
		when(opinion2.getDescripcion()).thenReturn("Vinchuca Sordida");
		
		muestra.recibirRevision(revision1);
		muestra.recibirRevision(revision2);
		
		assertEquals(muestra.getResultadoActual(), "Vinchuca Sordida");
	}
	
	// Se testea que si dos opiniones de expertos no coinciden, entonces no se cambie el estado de muestra a
	// verificada
	@Test
	public void testSiDosOpinionesDeUsuariosExpertosNoCoincidenEntoncesNoSeCambiaAEstadoDeMuestraVerificada() throws Exception {
		when(opinionAutor.getDescripcion()).thenReturn("Vinchuca Sordida");
		when(revision1.getNivelDeUsuario()).thenReturn(nivelDeUsuario1);
		when(nivelDeUsuario1.esExperto()).thenReturn(true);
		when(revision1.getOpinion()).thenReturn(opinion1);
		when(opinion1.getDescripcion()).thenReturn("Vinchuca Sordida");
		when(revision2.getNivelDeUsuario()).thenReturn(nivelDeUsuario1);
		when(revision2.getOpinion()).thenReturn(opinion2);
		when(opinion2.getDescripcion()).thenReturn("Chinche Foliada");
		
		muestra.recibirRevision(revision1);
		muestra.recibirRevision(revision2);
		
		assertEquals(muestra.getNivelDeRevision(), "Muestra opinada por expertos");
		assertTrue(muestra.getRevisiones().contains(revision1));
		assertTrue(muestra.getRevisiones().contains(revision2));
	}
	
	// Se testea que al crearse una nueva muestra, la opinión del autor de la muestra sea tomada como básica siempre,
	// asi el estado de la muestra siempre se va a inicializar como sin verificar, y va a permitir a usuarios basicos
	// opinar sobre esta.
	@Test
	public void testCuandoUnUsuarioSubeUnaMuestraEntoncesSiempreSeConsideraSuOpinionComoBasica() throws Exception {
		assertFalse(muestra.getRevisiones().get(0).getNivelDeUsuario().esExperto());
		assertEquals(muestra.getRevisiones().size(), 1);
		assertEquals(muestra.getNivelDeRevision(),"Muestra sin verificar");
	}
}
