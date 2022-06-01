package ar.edu.unq.po2.tp.grupal.filtros;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import ar.edu.unq.po2.tp.grupal.muestra.*;

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
		comparador = new ComparadorMenor();
		when(muestra1.getFecha()).thenReturn(LocalDate.now());
		when(muestra2.getFecha()).thenReturn(LocalDate.of(2018,05,01));
		when(muestra3.getFecha()).thenReturn(LocalDate.of(2019,04,19));
		filtro1 = new FiltroFechaCreacionMuestra(LocalDate.of(2019, 04, 20), comparador);
		assertEquals(2, filtro1.filtrar(muestras).size());
	}
	
	@Test
	void testFechaDeUltimaVotacionMayorA20Abril19() {
		comparador = new ComparadorMayor();
		when(muestra1.fechaUltimaVotacion()).thenReturn(LocalDate.now());
		when(muestra2.fechaUltimaVotacion()).thenReturn(LocalDate.of(2018,05,01));
		when(muestra3.fechaUltimaVotacion()).thenReturn(LocalDate.of(2019,04,19));
		filtro1 = new FiltroFechaUltimaVotacion(LocalDate.of(2019, 04, 20), comparador);
		assertEquals(1, filtro1.filtrar(muestras).size());
	}
	
	@Test
	void testMuestrasVerificadas() {
		when(muestra1.getResultadoActual()).thenReturn("Verificada");
		when(muestra2.getResultadoActual()).thenReturn("Votada");
		when(muestra3.getResultadoActual()).thenReturn("Votada");
		filtro1 = new FiltroNivelDeVerificacion("Verificada");
		assertEquals(1, filtro1.filtrar(muestras).size());
	}
	
	@Test
	void testTipoDeInsectoDetectado() {
		when(muestra1.tipo()).thenReturn("Vinchuca");
		when(muestra2.tipo()).thenReturn("Phtia-Chinche");
		when(muestra3.tipo()).thenReturn("Chinche Foliada");
		filtro1 = new FiltroTipoDeInsecto("Chinche Foliada");
		assertEquals(1, filtro1.filtrar(muestras).size());		
	}
	
	@Test
	void TestAND() {
		when(muestra1.tipo()).thenReturn("Vinchuca");
		when(muestra2.tipo()).thenReturn("Phtia-Chinche");
		when(muestra3.tipo()).thenReturn("Chinche Foliada");
		filtro1 = new FiltroTipoDeInsecto("Chinche Foliada");
		when(muestra1.getResultadoActual()).thenReturn("Verificada");
		when(muestra2.getResultadoActual()).thenReturn("Votada");
		when(muestra3.getResultadoActual()).thenReturn("Verificada");
		filtro2 = new FiltroNivelDeVerificacion("Verificada");
		filtroAnd = new FiltroAND(filtro1,filtro2);
		assertTrue(filtroAnd.filtrar(muestras).contains(muestra3));
		assertFalse(filtroAnd.filtrar(muestras).contains(muestra1));
	}

	@Test
	void TestOR() {
		when(muestra1.tipo()).thenReturn("Vinchuca");
		when(muestra2.tipo()).thenReturn("Phtia-Chinche");
		when(muestra3.tipo()).thenReturn("Chinche Foliada");
		filtro1 = new FiltroTipoDeInsecto("Chinche Foliada");
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
