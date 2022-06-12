package ar.edu.unq.po2.tp.grupal.usuario.Test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.tp.grupal.usuario.Basico;
import ar.edu.unq.po2.tp.grupal.usuario.Experto;
import ar.edu.unq.po2.tp.grupal.usuario.NivelDeUsuario;

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
