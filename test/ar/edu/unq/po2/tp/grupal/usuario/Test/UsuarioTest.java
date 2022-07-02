package ar.edu.unq.po2.tp.grupal.usuario.Test;

import static org.junit.jupiter.api.Assertions.*;



import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.tp.grupal.aplicacion.AplicacionWeb;
import ar.edu.unq.po2.tp.grupal.aplicacion.Imagen;
import ar.edu.unq.po2.tp.grupal.muestra.*;
import ar.edu.unq.po2.tp.grupal.revision.*;
import ar.edu.unq.po2.tp.grupal.usuario.NivelDeUsuario;
import ar.edu.unq.po2.tp.grupal.usuario.Usuario;
import ar.edu.unq.po2.tp.grupal.zonaDeCobertura.*;

class UsuarioTest {
	
	AplicacionWeb sistema;
	Imagen imagen;
	Ubicacion ubicacion;
	Usuario usuario;
	NivelDeUsuario nivel;
	Opinion opinion;
	Muestra muestra; 
	Muestra muestra2;
	
	@BeforeEach
	void setUp() throws Exception {
		sistema = mock(AplicacionWeb.class);
		ubicacion = mock(Ubicacion.class);
		opinion = mock(Opinion.class);
		muestra = mock(Muestra.class);
		muestra2 = mock(Muestra.class);
		nivel = mock(NivelDeUsuario.class);
		usuario = new Usuario(true);
		usuario.setidUsuario(1);
	}

	@Test
	void testCreacionUsuarioValidado() {
		//Testeo que el usuario haya sido creado con id 1 y sea experto.
		assertEquals(1, usuario.getidUsuario());
		assertTrue(usuario.tieneConocimientoValidado());
		assertTrue(usuario.esExperto());
	}
	
	@Test 
	void testCreacionUsuarioBasico() {
		usuario = new Usuario(false);
		assertFalse(usuario.esExperto());
	}

	
	@Test
	void testAgregarRevision() throws Exception{
		//Testeo que el usuario agregue una revision en su lista de revisiones y tambien en la muestra.
		//Primero chequeo que la lista de revisiones este vacia y luego de agregar la revision,
		//chequeo que se haya agregado y que se haya llamado al mensaje del sistema agregarRevision(param).
		assertEquals(0, usuario.getRevisiones().size());
		usuario.agregarRevision(muestra, opinion);
		assertEquals(1, usuario.getRevisiones().size());
		verify(muestra).recibirRevision(usuario.getRevisiones().get(0));
	}
	
	@Test
	void testAgregarRevisionExcepcion() throws Exception {
		//Testeo que cuando haya una excepcion por que la muestra no acepte revisiones
		doThrow(Exception.class).when(muestra).recibirRevision(any());
		usuario.agregarRevision(muestra, opinion);
		//Chequeo que no se haya agregado a revision a la lista de revisiones, por ende 
		//tampoco se agrega a la muestra.
		assertEquals(0, usuario.getRevisiones().size());
	}
	
	@Test
	void testEnviosUltimos30Dias() {
		//Testeo que me devuelva solo las muestras que tengan menos de 30 dias.
		when(muestra.getFecha()).thenReturn(LocalDate.now());
		//muestra 2 tiene fecha de subida hace 31 dias
		when(muestra2.getFecha()).thenReturn(LocalDate.of(2022,05,01));
		usuario.agregarMuestra(muestra);
		usuario.agregarMuestra(muestra2);
		//Chequeo que solamente haya una muestra dentro de los 30 dias.
		assertEquals(1, usuario.cantidadEnviosUltimos30Dias());
		}
	
	
	@Test
	void testRevisionesUltimos30Dias() {
		//Testeo que me devuelva solo las revisiones que tengan menos de 30 dias.
		usuario.agregarRevision(muestra, opinion);
		usuario.agregarRevision(muestra, opinion);
		//No supe como "mockear" LocalDate para probar una revision que tenga mas de 30 dias.
		assertEquals(2, usuario.cantidadRevisionesUltimos30Dias());
		}
		
	@Test
	void testSubirYBajarDeNivel() {
		//Testeo que un usuario Basico suba a nivel Experto y luego baje a nivel Basico.
		usuario = new Usuario(false);
		usuario.subirDeNivel();
		assertTrue(usuario.esExperto());
		usuario.bajarDeNivel();
		assertFalse(usuario.esExperto());
	}
}