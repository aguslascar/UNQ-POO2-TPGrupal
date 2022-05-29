package ar.edu.unq.po2.grupal.zonaDeCobertura.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.tp.grupal.zonaDeCobertura.Ubicacion;

class UbicacionTest {
	
	Ubicacion puntoA = new Ubicacion(0, 0);
	Ubicacion puntoB = new Ubicacion(0, 2);


	@Test
	void testdistancia() {
		assertEquals(Ubicacion.distanciaEntre(puntoA, puntoB), 2);
	}


}
