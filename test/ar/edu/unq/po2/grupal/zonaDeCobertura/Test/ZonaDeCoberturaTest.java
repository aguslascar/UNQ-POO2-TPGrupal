package ar.edu.unq.po2.grupal.zonaDeCobertura.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.tp.grupal.zonaDeCobertura.Muestra;
import ar.edu.unq.po2.tp.grupal.zonaDeCobertura.Ong;
import ar.edu.unq.po2.tp.grupal.zonaDeCobertura.Ubicacion;
import ar.edu.unq.po2.tp.grupal.zonaDeCobertura.ZonaDeCobertura;

class ZonaDeCoberturaTest {
	
	Muestra muestraA;
	Muestra muestraB;
	
	Ong ong1;
	
	Ubicacion puntoA = new Ubicacion(0, 0);
	Ubicacion puntoB = new Ubicacion(0, 1);
	Ubicacion puntoC = new Ubicacion(1, 1);
	Ubicacion puntoD = new Ubicacion(1, 0);
	
	Ubicacion puntoF = new Ubicacion(1, 2);
	Ubicacion puntoG = new Ubicacion(2, 1);
	Ubicacion puntoH = new Ubicacion(2, 2);

	ArrayList<Ubicacion> ubicaciones1 = new ArrayList<Ubicacion>();
	ArrayList<Ubicacion> ubicaciones2 = new ArrayList<Ubicacion>();
	ArrayList<Ubicacion> ubicaciones3 = new ArrayList<Ubicacion>();
	
	ArrayList<Muestra> muestras1 = new ArrayList<Muestra>();
	
	ArrayList<Ong> ongs = new ArrayList<Ong>();
	
	ZonaDeCobertura zona1 = new ZonaDeCobertura("Zona1", puntoA, 2, muestras1, ubicaciones1, ongs);
	ZonaDeCobertura zona2 = new ZonaDeCobertura("Zona2", puntoC, 2, null, ubicaciones2, null);
	ZonaDeCobertura zona3 = new ZonaDeCobertura("Zona3", puntoA, 0, null, ubicaciones3, null);
	
	ArrayList<ZonaDeCobertura> zonas = new ArrayList<ZonaDeCobertura>();
	
	
	@BeforeEach
	public void setUp() {
		ubicaciones1.add(puntoA);
		ubicaciones1.add(puntoB);
		ubicaciones1.add(puntoC);
		ubicaciones1.add(puntoD);
		
		ubicaciones2.add(puntoC);
		ubicaciones2.add(puntoF);
		ubicaciones2.add(puntoG);
		ubicaciones2.add(puntoH);
		
		zonas.add(zona2);
		zonas.add(zona3);
		
		muestras1.add(muestraA);
		muestras1.add(muestraB);
		
		this.ong1 = mock(Ong.class);
		ongs.add(ong1);
	}
	
	@Test
	void testAgregarUbicacion() {
		zona1.agregarUbicacion(puntoC);
		assertEquals(zona1.getUbicaciones().size(), 5);
	}
	
	@Test
	void testSeSolapa() {
		assertTrue(zona1.seSolapa(zona2));
	}
	
	@Test
	void testZonasSolapadas() {
		assertEquals(zona1.zonasSolapadas(zonas).size(), 1);
	}
	
	
	@Test
	void testAgregarMuestra() {
		fail("Not yet implemented");
	}

	@Test
	void testRegistrar() {
		fail("Not yet implemented");
	}
	
	@Test
	void testDesuscribir() {
		fail("Not yet implemented");
	}
	
	@Test
	void testNotificar() {
		fail("Not yet implemented");
	}

}
