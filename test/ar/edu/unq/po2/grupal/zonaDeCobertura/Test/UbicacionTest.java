package ar.edu.unq.po2.grupal.zonaDeCobertura.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.tp.grupal.zonaDeCobertura.Ubicacion;

class UbicacionTest {

	Ubicacion puntoA = new Ubicacion(0, 0);
	Ubicacion puntoB = new Ubicacion(0, 2);
	Ubicacion puntoC = new Ubicacion(4, 2);
	Ubicacion puntoD = new Ubicacion(2, 8);

	ArrayList<Ubicacion> lista = new ArrayList<Ubicacion>();

	@BeforeEach
	public void setUp() {
		lista.add(puntoA);
		lista.add(puntoB);
		lista.add(puntoC);
	}

	@Test
	void testdistancia() {
		assertEquals(Ubicacion.distanciaEntre(puntoA, puntoB), 2);
	}

	@Test
	void testseEncuentranAMenosDe() {
		assertEquals(puntoA.seEncuentranAMenosDe(8, lista).size(), 3);
	}

}
