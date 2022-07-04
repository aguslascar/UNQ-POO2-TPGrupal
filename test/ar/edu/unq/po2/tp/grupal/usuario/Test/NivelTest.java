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
		//Primero veo que se haya creado correctamente el usuario preguntadonle su descripcion.
		assertEquals(nivel.getDescripcion(), "Nivel de usuario básico");
		
		assertFalse(nivel.esExperto());
	}
	@Test
	void testEsExpertoUnExperto() {
		//Testeo que el nivel experto sea experto.
		nivel = new Experto();
		//Primero veo que se haya creado correctamente el usuario preguntadonle su descripcion.
		assertEquals(nivel.getDescripcion(), "Nivel de usuario experto");
		
		assertTrue(nivel.esExperto());
		
	}
}
