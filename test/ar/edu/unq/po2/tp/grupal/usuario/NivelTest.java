package ar.edu.unq.po2.tp.grupal.usuario;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

class NivelTest {

	NivelDeUsuario nivel;

	@Test
	void testEsExpertoUnBasico() {
		//Testeo que el nivel basico no sea experto.
		nivel = new Basico();
		assertFalse(nivel.esExperto());
	}
	@Test
	void testEsExpertoUnExperto() {
		//Testeo que el nivel experto sea experto.
		nivel = new Experto();
		assertTrue(nivel.esExperto());
	}
}
