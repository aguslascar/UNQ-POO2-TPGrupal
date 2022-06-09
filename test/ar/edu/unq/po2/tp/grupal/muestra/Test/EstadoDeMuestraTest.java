package ar.edu.unq.po2.tp.grupal.muestra.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.tp.grupal.muestra.EstadoOpinadaPorExpertos;
import ar.edu.unq.po2.tp.grupal.muestra.EstadoSinVerificar;
import ar.edu.unq.po2.tp.grupal.muestra.EstadoVerificada;
import ar.edu.unq.po2.tp.grupal.muestra.Muestra;
import ar.edu.unq.po2.tp.grupal.revision.NivelDeUsuario;
import ar.edu.unq.po2.tp.grupal.revision.Opinion;
import ar.edu.unq.po2.tp.grupal.revision.Revision;

public class EstadoDeMuestraTest {

	private EstadoSinVerificar estadoSinVerificar;
	private EstadoOpinadaPorExpertos estadoOpinadaPorExpertos;
	private EstadoVerificada estadoVerificada;
	private Revision revision1;
	private Opinion opinion;
	private Muestra muestra;
	private NivelDeUsuario nivelDeUsuario;
	
	@BeforeEach
	public void setUp() {
		revision1 = mock(Revision.class);
		opinion = mock(Opinion.class);
		muestra = mock(Muestra.class);
		nivelDeUsuario = mock(NivelDeUsuario.class);
		estadoSinVerificar = new EstadoSinVerificar();
		estadoOpinadaPorExpertos = new EstadoOpinadaPorExpertos(revision1);
		estadoVerificada = new EstadoVerificada("Vinchuca Infestans");
	}
	
	// Se testea que la descripción del nombre del estado EstadoSinVerificar sea el esperado
	@Test
	public void testObtenerDescripcionDelNombreDeEstadoDeMuestraSinVerificar() {
		assertEquals(estadoSinVerificar.getDescripcion(), "Muestra sin verificar");
	}
	
	// Se testea que la descripción del nombre del estado EstadoOpinadaPorExpertos sea el esperado
	@Test
	public void testObtenerDescripcionDelNombreDeEstadoDeMuestraOpinadaPorExpertos() {
		assertEquals(estadoOpinadaPorExpertos.getDescripcion(), "Muestra opinada por expertos");
	}
	
	// Se testea que la descripción del nombre del estado EstadoVerificada sea el esperado
	@Test
	public void testObtenerDescripcionDelNombreDeEstadoDeMuestraVerificada() {
		assertEquals(estadoVerificada.getDescripcion(), "Muestra verificada");
	}
	
	// Se testea que cuando el estado de una muestra es sin verificar, entonces se realice un calculo de la opinion
	// con la mayor cantidad de votos en la lista de revisiones de la muestra, y que se haga correctamente
	@Test
	public void testObtenerResultadoActualDeUnaMuestraSinVerificar() throws Exception {
		ArrayList<Revision> revisiones = new ArrayList<Revision>();
		revisiones.add(revision1);
		when(revision1.getOpinion()).thenReturn(opinion);
		when(opinion.getDescripcion()).thenReturn("Vinchuca Sordida");
		when(revision1.getNivelDeUsuario()).thenReturn(nivelDeUsuario);
		when(nivelDeUsuario.esExperto()).thenReturn(false);
		when(muestra.getRevisiones()).thenReturn(revisiones);

		assertEquals(estadoSinVerificar.obtenerResultadoActual(muestra), "Vinchuca Sordida");
	}
	
	// Se testea que cuando el estado de una muestra es opinada por expertos, entonces se realice un calculo de la
	// opinion con la mayor cantidad de votos en la lista de revisiones de la muestra, y que se haga correctamente
	@Test
	public void testObtenerResultadoActualDeUnaMuestraOpinadaPorExpertos() {
		ArrayList<Revision> revisiones = new ArrayList<Revision>();
		revisiones.add(revision1);
		when(revision1.getOpinion()).thenReturn(opinion);
		when(opinion.getDescripcion()).thenReturn("Vinchuca Sordida");
		when(revision1.getNivelDeUsuario()).thenReturn(nivelDeUsuario);
		when(nivelDeUsuario.esExperto()).thenReturn(false);
		when(muestra.getRevisiones()).thenReturn(revisiones);

		assertEquals(estadoOpinadaPorExpertos.obtenerResultadoActual(muestra), "Vinchuca Sordida");
	}
	
	// Se testea que cuando el estado de una muestra es verificada, entonces no importen las opiniones con mayor
	// cantidad de votos en la lista de revisiones de la muestra, si no que importe la opinión con la que fue 
	// verificada la muestra, en este caso 'Vinchuca Infestans', ya que asi fue setteado en el setUp
	@Test
	public void testObtenerResultadoActualDeUnaMuestraVerificada() {
		ArrayList<Revision> revisiones = new ArrayList<Revision>();
		revisiones.add(revision1);
		when(revision1.getOpinion()).thenReturn(opinion);
		when(opinion.getDescripcion()).thenReturn("Vinchuca Sordida");
		when(revision1.getNivelDeUsuario()).thenReturn(nivelDeUsuario);
		when(nivelDeUsuario.esExperto()).thenReturn(false);
		when(muestra.getRevisiones()).thenReturn(revisiones);
		
		assertEquals(estadoVerificada.obtenerResultadoActual(muestra), "Vinchuca Infestans");
	}
	
	// Se considera que no son necesarios los tests de cambio de estado y opinion de usuarios segun estado, ya que
	// están contemplados en Muestra y no deberían ser parte de los tests de EstadoDeMuestra
}
