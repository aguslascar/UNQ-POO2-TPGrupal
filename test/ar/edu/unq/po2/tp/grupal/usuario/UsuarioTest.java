package ar.edu.unq.po2.tp.grupal.usuario;

import static org.junit.jupiter.api.Assertions.*;


import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

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
	void testAgregarRevision() throws Exception {
		//Testeo que el usuario agregue una revision en su lista de revisiones y tambien en el sistema.
		//Primero chequeo que la lista de revisiones este vacia y luego de agregar la revision,
		//chequeo que se haya agregado y que se haya llamado al mensaje del sistema agregarRevision(param).
		assertEquals(0, usuario.getRevisiones().size());
		usuario.agregarRevision(muestra, opinion);
		assertEquals(1, usuario.getRevisiones().size());
		verify(sistema).agregarRevision(muestra, usuario.getRevisiones().get(0));
	}
	
	@Test
	void testAgregarRevisionExcepcion() throws Exception {
		//Testeo que cuando haya una excepcion por no estar la muestra o usuario en el sistema
		//no se pueda agregar una revision.
		doThrow(new Exception()).when(sistema).agregarRevision(any(), any());
		usuario.agregarRevision(muestra, opinion);
		//Chequeo que no se haya agregado a revision a la lista de revisiones, por ende 
		//tampoco se agrega al sistema.
		assertEquals(0, usuario.getRevisiones().size());
	}
	
	@Test
	void testRevisionesUltimos30Dias() {
		//Testeo que me devuelva solo las revisiones que tengan menos de 30 dias.
		usuario.agregarRevision(muestra, opinion);
		usuario.agregarRevision(muestra, opinion);
		//No supe como "mockear" LocalDate para probar una muestra que tenga mas de 30 dias.
		assertEquals(2, usuario.revisionesUltimos30Dias().size());
		}
		
	@Test
	void testSubirYBajarDeNivel() {
		//Testeo que un usuario Basico suba a nivel Experto y luego baje a nivel Basico.
		usuario = new Usuario(1, sistema, false);
		usuario.subirDeNivel();
		assertTrue(usuario.getNivel().esExperto());
		usuario.bajarDeNivel();
		assertFalse(usuario.getNivel().esExperto());
	}
}