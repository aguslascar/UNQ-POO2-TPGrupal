package ar.edu.unq.po2.grupal.zonaDeCobertura.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.tp.grupal.zonaDeCobertura.CalculadorDeDistancias;
import ar.edu.unq.po2.tp.grupal.zonaDeCobertura.Muestra;
import ar.edu.unq.po2.tp.grupal.zonaDeCobertura.Ubicacion;
import ar.edu.unq.po2.tp.grupal.zonaDeCobertura.ZonaDeCobertura;

class CalculadorDeDistanciasTest {
	
	Ubicacion puntoA = new Ubicacion(0, 0);
	Ubicacion puntoB = new Ubicacion(1, 2);
	Ubicacion puntoC = new Ubicacion(4, 2);
	Ubicacion puntoD = new Ubicacion(2, 8);
	
	Muestra muestraA;
	Muestra muestraB;
	Muestra muestraC;
	Muestra muestraD;
	
	ArrayList<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
	ArrayList<Muestra> muestras = new ArrayList<Muestra>();

	CalculadorDeDistancias calculador = new CalculadorDeDistancias();
	ZonaDeCobertura zona = new ZonaDeCobertura("zonaA", puntoA, 3, muestras, ubicaciones);
	CalculadorDeDistancias calculadorZ = new CalculadorDeDistancias(zona);
	
	@BeforeEach
	public void setUp() {
		ubicaciones.add(puntoA);
		ubicaciones.add(puntoB);
		ubicaciones.add(puntoC);
		ubicaciones.add(puntoD);
		
		this.muestraA = mock(Muestra.class);
		this.muestraB = mock(Muestra.class);
		this.muestraC = mock(Muestra.class);
		this.muestraD = mock(Muestra.class);
		
		muestras.add(muestraA);
		muestras.add(muestraB);
		muestras.add(muestraC);
		muestras.add(muestraD);
	}

	@Test
	void testdistancia() {
		assertEquals(calculador.distanciaEntre(puntoA, puntoB), 2);
	}

	@Test
	void testseEncuentranAMenosDe() {
		assertEquals(calculador.seEncuentranAMenosDe(puntoA, 8, ubicaciones).size(), 3);
	}
	
	@Test
	void testMuestrasObtenidasA() {
		when(muestraA.getUbicacion()).thenReturn(puntoA);
		when(muestraB.getUbicacion()).thenReturn(puntoB);
		when(muestraC.getUbicacion()).thenReturn(puntoC);
		when(muestraD.getUbicacion()).thenReturn(puntoD);
		
		assertEquals(calculadorZ.muestrasObtenidasA(muestraA, 8).size(), 3);
	}

}
