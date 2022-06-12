package ar.edu.unq.po2.tp.grupal.filtro.Test;

import static org.junit.jupiter.api.Assertions.*;


import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.tp.grupal.filtro.ComparadorDeFechas;
import ar.edu.unq.po2.tp.grupal.filtro.ComparadorMayor;
import ar.edu.unq.po2.tp.grupal.filtro.ComparadorMenor;
import ar.edu.unq.po2.tp.grupal.filtro.Filtro;
import ar.edu.unq.po2.tp.grupal.filtro.FiltroAND;
import ar.edu.unq.po2.tp.grupal.filtro.FiltroFechaCreacionMuestra;
import ar.edu.unq.po2.tp.grupal.filtro.FiltroFechaUltimaVotacion;
import ar.edu.unq.po2.tp.grupal.filtro.FiltroNivelDeVerificacion;
import ar.edu.unq.po2.tp.grupal.filtro.FiltroOR;
import ar.edu.unq.po2.tp.grupal.filtro.FiltroTipoDeInsecto;
import ar.edu.unq.po2.tp.grupal.muestra.*;
import ar.edu.unq.po2.tp.grupal.revision.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FiltrosTest {
	
	Filtro filtro1;
	Filtro filtro2;
	FiltroOR filtroOr;
	FiltroAND filtroAnd;
	Muestra muestra1;
	Muestra muestra2;
	Muestra muestra3;
	List<Muestra> muestras;
	ComparadorDeFechas comparador;

	@BeforeEach
	void setUp() throws Exception {
		muestras = new ArrayList<Muestra>();
		muestra1 = mock(Muestra.class);
		muestra2 = mock(Muestra.class);
		muestra3 = mock(Muestra.class);
		muestras.add(muestra1);
		muestras.add(muestra2);
		muestras.add(muestra3);
		
	}

	@Test
	void testFechaDeCreacionMenorA20Abril19() {
		//Configuro el comparador como comparador por menor
		//Testeo un filtro por fecha de creacion con 3 muestras.
		//Dos de ellas con fecha anterior al 20/04/2019 y una con fecha actual.
		//Se testea que el filtro filtre muestra2 y muestra3 y descarte muestra1.
		comparador = new ComparadorMenor();
		when(muestra1.getFecha()).thenReturn(LocalDate.now());
		when(muestra2.getFecha()).thenReturn(LocalDate.of(2018,05,01));
		when(muestra3.getFecha()).thenReturn(LocalDate.of(2019,04,19));
		filtro1 = new FiltroFechaCreacionMuestra(LocalDate.of(2019, 04, 20), comparador);
		assertEquals(2, filtro1.filtrar(muestras).size());
		assertTrue(filtro1.filtrar(muestras).contains(muestra3));
		assertTrue(filtro1.filtrar(muestras).contains(muestra2));
	}
	
	@Test
	void testFechaDeUltimaVotacionMayorA20Abril19() {
		//Configuro el comparador como comparador por mayor
		//Testeo un filtro por fecha de la ultima votacion con 3 muestras.
		//Dos de ellas con fecha anterior al 20/04/2019 y una con fecha actual.
		//Se testea que el filtro filtre la muestra 1 y no las otras dos
		comparador = new ComparadorMayor();
		when(muestra1.fechaUltimaVotacion()).thenReturn(LocalDate.now());
		when(muestra2.fechaUltimaVotacion()).thenReturn(LocalDate.of(2018,05,01));
		when(muestra3.fechaUltimaVotacion()).thenReturn(LocalDate.of(2019,04,19));
		filtro1 = new FiltroFechaUltimaVotacion(LocalDate.of(2019, 04, 20), comparador);
		assertEquals(1, filtro1.filtrar(muestras).size());
		assertTrue(filtro1.filtrar(muestras).contains(muestra1));
	}
	
	@Test
	void testMuestrasVerificadas() {
		//Testeo el filtro de nivel de verificacion.
		//Tengo 1 muestra verificada y las otras dos sin.
		//Testeo que el filtro filtre la muestra1 y no las otras dos.
		when(muestra1.getResultadoActual()).thenReturn("Verificada");
		when(muestra2.getResultadoActual()).thenReturn("Votada");
		when(muestra3.getResultadoActual()).thenReturn("Votada");
		filtro1 = new FiltroNivelDeVerificacion("Verificada");
		assertEquals(1, filtro1.filtrar(muestras).size());
		assertTrue(filtro1.filtrar(muestras).contains(muestra1));
	}
	
	@Test
	void testTipoDeInsectoDetectado() {
		//Testeo el filtro de tipo de insecto.
		//Testeo que el filtro filtre la muestra3 y no el resto
		when(muestra1.getOpinion()).thenReturn(Opinion.VINCHUCAINFESTANS);
		when(muestra2.getOpinion()).thenReturn(Opinion.VINCHUCASORDIDA);
		when(muestra3.getOpinion()).thenReturn(Opinion.CHINCHEFOLIADA);
		filtro1 = new FiltroTipoDeInsecto(Opinion.CHINCHEFOLIADA);
		assertEquals(1, filtro1.filtrar(muestras).size());
		assertTrue(filtro1.filtrar(muestras).contains(muestra3));		
	}
	
	@Test
	void TestAND() {
		//Testeo el filtro AND con 2 filtros por tipo de insecto y nivel de verificacion.
		//Chequeo que contenga muestra3 y no contenga muestra1 ni muestra2.
		when(muestra1.getOpinion()).thenReturn(Opinion.VINCHUCAINFESTANS);
		when(muestra2.getOpinion()).thenReturn(Opinion.VINCHUCASORDIDA);
		when(muestra3.getOpinion()).thenReturn(Opinion.CHINCHEFOLIADA);
		filtro1 = new FiltroTipoDeInsecto(Opinion.CHINCHEFOLIADA);
		when(muestra1.getResultadoActual()).thenReturn("Verificada");
		when(muestra2.getResultadoActual()).thenReturn("Votada");
		when(muestra3.getResultadoActual()).thenReturn("Verificada");
		filtro2 = new FiltroNivelDeVerificacion("Verificada");
		filtroAnd = new FiltroAND(filtro1,filtro2);
		assertTrue(filtroAnd.filtrar(muestras).contains(muestra3));
		assertFalse(filtroAnd.filtrar(muestras).contains(muestra2));
		assertFalse(filtroAnd.filtrar(muestras).contains(muestra1));
	}

	@Test
	void TestOR() {
		//Testeo el filtro OR con 2 filtros por tipo de insecto y nivel de verificacion.
		//Chequeo que contenga muestra3 y muestra1 y no contenga muestra2.
		when(muestra1.getOpinion()).thenReturn(Opinion.VINCHUCAINFESTANS);
		when(muestra2.getOpinion()).thenReturn(Opinion.VINCHUCASORDIDA);
		when(muestra3.getOpinion()).thenReturn(Opinion.CHINCHEFOLIADA);
		filtro1 = new FiltroTipoDeInsecto(Opinion.CHINCHEFOLIADA);
		when(muestra1.getResultadoActual()).thenReturn("Verificada");
		when(muestra2.getResultadoActual()).thenReturn("Votada");
		when(muestra3.getResultadoActual()).thenReturn("Verificada");
		filtro2 = new FiltroNivelDeVerificacion("Verificada");
		filtroOr = new FiltroOR(filtro1,filtro2);
		assertTrue(filtroOr.filtrar(muestras).contains(muestra3));
		assertTrue(filtroOr.filtrar(muestras).contains(muestra1));
		assertFalse(filtroOr.filtrar(muestras).contains(muestra2));
	}
}
