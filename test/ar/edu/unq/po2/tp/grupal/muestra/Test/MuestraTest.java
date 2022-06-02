package ar.edu.unq.po2.tp.grupal.muestra.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.tp.grupal.muestra.Muestra;
import ar.edu.unq.po2.tp.grupal.muestra.Ubicacion;
import ar.edu.unq.po2.tp.grupal.revision.Opinion;
import ar.edu.unq.po2.tp.grupal.revision.Revision;

public class MuestraTest {
	
	private Revision revision1;
	private int idUsuario;
	private LocalDate fecha;
	private Muestra muestra;
	private Ubicacion ubicacion;
	private String foto;
	private Opinion opinion;
	
	@BeforeEach
	public void setUp() {
		idUsuario = 207029442;
		fecha = LocalDate.of(2012, 12, 20);    // Fecha 20 de Diciembre de 2012    
		ubicacion = mock(Ubicacion.class);
	    revision1 = mock(Revision.class);
	    foto = "Vinchuca.jpg";
	    opinion = mock(Opinion.class);
		muestra = new Muestra(idUsuario, fecha, foto, ubicacion, opinion); // Mal instanciado, diseño a resolver
	}
	
	// Se testea que al instanciar una nueva Muestra, el idUsuario este bien almacenado y sea accesible publicamente
	@Test
	public void testConocerElIdDelUsuarioQueSubioLaMuestra() {
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
	public void testAgregarUnaRevisionALaListaDeRevisiones() {
		muestra.agregarRevision(revision1);
		assertTrue(muestra.getRevisiones().contains(revision1));
	}
	
	// Se testea que al obtener el resultado actual de una muestra de la que no opino nadie mas que el autor,
	// se retorne la opinión del autor
	@Test
	public void testObtenerResultadoActualSinRevisionesSoloCuentaLaOpinionDelAutor() {
		when(opinion.getDescripcion()).thenReturn("Vinchuca Sordida");
		assertEquals(muestra.getResultadoActual(), muestra.getOpinion().getDescripcion());
	}
}
