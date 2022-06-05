package ar.edu.unq.po2.tp.grupal.usuario;

import static org.junit.jupiter.api.Assertions.*;


import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ar.edu.unq.po2.tp.grupal.muestra.*;
import ar.edu.unq.po2.tp.grupal.revision.*;
import ar.edu.unq.po2.tp.grupal.aplicacion.*;
import ar.edu.unq.po2.tp.grupal.zonaDeCobertura.*;

class UsuarioTest {
	
	AplicacionWeb sistema;
	Imagen imagen;
	Ubicacion ubicacion;
	Usuario usuario;
	NivelDeUsuario nivel;
	Opinion opinion;
	Muestra muestra; 
	
	@BeforeEach
	void setUp() throws Exception {
		sistema = mock(AplicacionWeb.class);
		ubicacion = mock(Ubicacion.class);
		opinion = mock(Opinion.class);
		muestra = mock(Muestra.class);
		nivel = mock(NivelDeUsuario.class);
		usuario = new Usuario(1, sistema, true);
	}

	@Test
	void testCreacionUsuarioValidado() {
		//Testeo que el usuario haya sido creado con id 1 y sea experto.
		assertEquals(1, usuario.getIdUsuario());
		assertTrue(usuario.getNivel().esExperto());
	}
	
	@Test 
	void testCreacionUsuarioBasico() {
		usuario = new Usuario(1, sistema, false);
		assertFalse(usuario.getNivel().esExperto());
	}
	
	@Test
	void testAgregarMuestra() {
		// Testeo que el usuario pueda registrar una nueva muestra con los parametros dados.
		//Chequeo que se haya llamado al mensaje del sistema registrarMuestra(param).
		usuario.agregarMuestra(imagen, "Vinchuca", ubicacion);
		verify(sistema).registrarMuestra(eq(usuario), eq(LocalDate.now()), eq(imagen), eq(ubicacion), any(Opinion.class));
	}
	
	@Test
	void testAgregarRevision() {
		//Testeo que el usuario agregue una revision en su lista de revisiones y tambien en el sistema.
		//Primero chequeo que la lista de revisiones este vacia y luego de agregar la revision,
		//chequeo que se haya agregado y que se haya llamado al mensaje del sistema agregarRevision(param).
		assertEquals(0, usuario.getRevisiones().size());
		usuario.agregarRevision(muestra, opinion);
		assertEquals(1, usuario.getRevisiones().size());
		verify(sistema).agregarRevision(muestra, usuario.getRevisiones().get(0));
	}
}