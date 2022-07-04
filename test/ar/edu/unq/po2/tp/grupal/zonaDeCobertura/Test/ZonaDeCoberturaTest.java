package ar.edu.unq.po2.tp.grupal.zonaDeCobertura.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import ar.edu.unq.po2.tp.grupal.ong.Funcionalidad;
import ar.edu.unq.po2.tp.grupal.ong.FuncionalidadExterna;
import ar.edu.unq.po2.tp.grupal.ong.Ong;
import ar.edu.unq.po2.tp.grupal.muestra.*;
import ar.edu.unq.po2.tp.grupal.zonaDeCobertura.Ubicacion;
import ar.edu.unq.po2.tp.grupal.zonaDeCobertura.ZonaDeCobertura;

class ZonaDeCoberturaTest {

	Muestra muestraA;
	Muestra muestraB;
	Muestra muestraC;

	Funcionalidad funcionalidadMuestra;
	Funcionalidad funcinoalidadValidacion;

	Ubicacion puntoA = new Ubicacion(0, 0);
	Ubicacion puntoB = new Ubicacion(0, 1);
	Ubicacion puntoC = new Ubicacion(1, 1);
	Ubicacion puntoD = new Ubicacion(1, 0);

	Ubicacion puntoF = new Ubicacion(1, 2);
	Ubicacion puntoG = new Ubicacion(2, 1);
	Ubicacion puntoH = new Ubicacion(2, 2);

	Ong ong1;
	Ong spyOng;

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

		this.funcionalidadMuestra = mock(Funcionalidad.class);
		this.funcinoalidadValidacion = mock(Funcionalidad.class);
		this.muestraC = mock(Muestra.class);

		this.ong1 = mock(Ong.class);
		
		spyOng = Mockito.spy(new Ong(puntoA, null, 0, funcionalidadMuestra, funcinoalidadValidacion));
		ongs.add(ong1);
	}

// Se teste la distancia total de la zona de cobertura en kilometros con un radio de ejemplo de 2.
	@Test
	void testDistanciaDeLaZonaEnKM() {
		assertEquals(zona1.distanciaDeLaZonaEnKM(), 12);
	}

// Se testea que el nombre sea el esperado.
	@Test
	void testGetNombre() {
		assertEquals(zona1.getNombre(), "Zona1");
	}

// Se testea que el epicentro sea el esperado.
	@Test
	void testGetEpicentro() {
		assertEquals(zona1.getEpicentro(), puntoA);
	}

// Se testea que el radio sea el esperado.
	@Test
	void testGetRadio() {
		assertEquals(zona1.getRadio(), 2);
	}

// Se testea que la cantidad de ubicaciones sea el esperado luego de agregar ubicaciones.
	@Test
	void testAgregarUbicacion() {
		zona1.agregarUbicacion(puntoC);
		assertEquals(zona1.getUbicaciones().size(), 5);
	}

// Se testea que seSolapa regrese true si la zona comparada se solapa con la zona a comparar y false si no se solapa.
	@Test
	void testSeSolapa() {
		assertTrue(zona1.seSolapa(zona2));
	}

// Se testea que zonasSolapadas regrese la cantidad de zonas esperada.
	@Test
	void testZonasSolapadas() {
		assertEquals(zona1.zonasSolapadas(zonas).size(), 1);
	}

// Se testea que al llamar agregarMuestra regrese la cantidad esperada de muestras.
	@Test
	void testAgregarMuestra() {
		when(muestraC.getUbicacion()).thenReturn(puntoB);
		zona1.agregarMuestra(muestraC);
		assertEquals(zona1.getMuestras().size(), 3);
	}

//Se testea que perteneceAZona regrese true si la Ubicacion dada pertenece a las que ya tiene la zona y false si no pertenece.
	@Test
	void testPerteneceAZona() {
		assertTrue(zona1.perteneceAZona(puntoA));
	}

	// Se testea que esMuestraDeZona regrese true si la muestra dada pertenece a las
	// que ya tiene la zona y false si no pertenece.
	@Test
	void testEsMuestraDeZona() {
		assertTrue(zona1.esMuestraDeZona(muestraA));
	}

// Se testea que la cantidad de organizaciones registradas sea el esperado luego de llamar registrar.
	@Test
	void testRegistrar() {
		zona1.registrar(ong1);
		assertEquals(zona1.getOngsSubscriptas().size(), 2);
	}

// Se testea que la cantidad de organizaciones registradas sea el esperado luego de llamar a desuscribir.
	@Test
	void testDesuscribir() {
		zona1.desuscribir(ong1);
		assertEquals(zona1.getOngsSubscriptas().size(), 0);
	}

// Se testea que la organicaciones reciba el mensaje update.
	@Test
	void testNotificarNuevaMuestra() {
		zona1.registrar(spyOng);
		zona1.notificarNuevaMuestra(muestraA);
		Mockito.verify(spyOng).nuevaMuestra(zona1, muestraA);
	}

// Se testea que la organizción reciba el mensaje updateValidación.
	@Test
	void testNotificarValidacion() {
		zona1.registrar(spyOng);
		zona1.notificarValidacion(muestraA);
		Mockito.verify(spyOng).nuevaValidacion(zona1, muestraA);
	}

// Se testea que si la ubicacion de una muestra no esta en la zona, no se agregue la muestra.
	@Test
	void testNoAgregarMuestra() {
		// La muestra va a tener como ubicacion el puntoG, el cual no es un punto de la
		// zona1
		when(muestraC.getUbicacion()).thenReturn(puntoG);
		zona1.agregarMuestra(muestraC);
		assertEquals(2, zona1.getMuestras().size());
	}

}
