package ar.edu.unq.po2.tp.grupal.aplicacion;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.tp.grupal.muestra.Muestra;
import ar.edu.unq.po2.tp.grupal.revision.*;
import ar.edu.unq.po2.tp.grupal.usuario.Usuario;
import ar.edu.unq.po2.tp.grupal.zonaDeCobertura.Ubicacion;

import static org.mockito.Mockito.*;

class AplicacionTest {

	AplicacionWeb app;
	Revision revision;
	Imagen imagen;
	Ubicacion ubicacion;
	Opinion opinion;
	Muestra muestraFalsa;
	Usuario usuarioValidado;
	Usuario usuarioBasico;
	Usuario usuarioExperto;
	
	@BeforeEach
	void setUp() throws Exception {
		app = new AplicacionWeb();
		imagen = mock(Imagen.class);
		ubicacion = mock(Ubicacion.class);
		opinion = mock(Opinion.class);
		muestraFalsa = mock(Muestra.class);
		usuarioValidado = mock(Usuario.class);
		usuarioBasico = mock(Usuario.class);
		usuarioExperto = mock(Usuario.class);
		revision = mock(Revision.class);
	}

	@Test
	void testCreacionDeUsuarios() {
		//Testeo que se agreguen los usuarios y se les asigne un id.
		app.registrarNuevoUsuario(usuarioValidado);
		app.registrarNuevoUsuario(usuarioBasico);
		assertEquals(2, app.getUsuarios().size());
		assertEquals(2, app.getUltimoidUsuario());
	}
	
	@Test
	void testAgregarNuevaMuestra() {
		//Testeo que se cree y se agregue una nueva muestra
		app.registrarNuevoUsuario(usuarioBasico);
		when(usuarioBasico.getidUsuario()).thenReturn(1);
		app.registrarMuestra(usuarioBasico, LocalDate.now(), imagen, ubicacion, opinion);
		assertEquals(1, app.getMuestras().size());
		//Me fijo que el id de usuario de la muestra cargada sea el mismo id del usuario que la cargo.
		assertEquals(usuarioBasico.getidUsuario(), app.getMuestras().get(0).getidUsuario());
	}
	
	@Test
	void testAgregarNuevaMuestraUsuarioNoRegistrado() {
		//Testeo que un usuario no registrado quiera subir una muestra. Usuario experto nunca fue registrado en sistema.
		app.registrarMuestra(usuarioExperto, LocalDate.now(), imagen, ubicacion, opinion);
		//La muestra no se registro en el sistema.
		assertEquals(0, app.getMuestras().size());
	}
	
	@Test 
	void testAgregarRevision() throws Exception {
		//Testeo agregarRevision, primero agregando una revision de una muestra falsa que no esta en el sistema
		//y luego de una muestra que si esta pero el usuario no.
		//Por ultimo testeo una muestra que esta y un usuario que tambien esta en sistema.
		app.registrarNuevoUsuario(usuarioBasico);
		app.registrarNuevoUsuario(usuarioExperto);
		when(usuarioBasico.getidUsuario()).thenReturn(1);
		when(revision.getid()).thenReturn(1);
		//Chequeo que se haya enviado una excepcion, ya que muestraFalsa no es una muestra del sistema.
		assertThrows(Exception.class, () -> app.agregarRevision(muestraFalsa, revision));
		
		//Pruebo agregar una muestra de un usuario con id 2, el cual no existe en sistema
		when(revision.getid()).thenReturn(2);		
		//Chequeo que se haya enviado una excepcion, ya que el usuario con id 2 no es un usuario del sistema.
		assertThrows(Exception.class, () -> app.agregarRevision(muestraFalsa, revision));

	}
	
	@Test
	void testAgregarRevisionDeOtraMuestra() {
		//Agrego una revision de una muestra de un usuario existente en el sistema.
		app.registrarNuevoUsuario(usuarioBasico);
		app.registrarNuevoUsuario(usuarioExperto);
		when(usuarioBasico.getidUsuario()).thenReturn(1);
		//Primero agrego la muestra al sistema con id 1(usuario basico)
		app.registrarMuestra(usuarioBasico, LocalDate.now(), imagen, ubicacion, opinion);
		//Luego guardo esa muestra para luego agregarle la revision
		Muestra muestraDelSistema = app.getMuestras().get(0);
		//El usuario experto(el cual nunca opino ni subio esa muestra) quiere hacer una revision
		when(usuarioExperto.getidUsuario()).thenReturn(2);
		when(revision.getid()).thenReturn(2);
		//Chequeo que no se haya lanzado una excepcion.
		assertDoesNotThrow(() -> app.agregarRevision(muestraDelSistema, revision));		
	}
	@Test
	void testAgregarRevisionDeSuPropiaMuestra() throws Exception {
		//Testeo que cuando un usuario quiera opinar sobre la muestra que el subio,
		//se lance una excepcion
		app.registrarNuevoUsuario(usuarioBasico);
		when(usuarioBasico.getidUsuario()).thenReturn(1);
		when(revision.getid()).thenReturn(1);
		//Ahora agrego una revision de una muestra de un usuario existente en el sistema.
		//Primero agrego la muestra al sistema
		app.registrarMuestra(usuarioBasico, LocalDate.now(), imagen, ubicacion, opinion);
		//Luego guardo esa muestra para luego agregarle la revision
		Muestra muestraDelSistema = app.getMuestras().get(0);
		assertThrows(Exception.class, () -> app.agregarRevision(muestraDelSistema, revision));
	}
	
	@Test
	void testAlgoritmoDeNivelesUsuarioValidado() {
		//Testeo la correcta funcionalidad del algoritmo que chequea los niveles de los usuarios
		//con un usuario validado
		app.registrarNuevoUsuario(usuarioValidado); //usuario validado que no puede cambiar de nivel. 
		when(usuarioValidado.esExperto()).thenReturn(true);
		when(usuarioValidado.tieneConocimientoValidado()).thenReturn(true);
		//Corro el algoritmo y me fijo que al usuarioValidado el mensaje bajar de nivel.
		app.revisarNivelesDeUsuario();
		verify(usuarioValidado, never()).bajarDeNivel();		
	}
	
	@Test
	void testAlgoritmoDeNivelesUsuarioBasico() {
		//Testeo la correcta funcionalidad del algoritmo con un usuario basico sin envios ni revisiones.
		app.registrarNuevoUsuario(usuarioBasico); // usuario basico que puede cambiar de nivel.
		when(usuarioBasico.esExperto()).thenReturn(false);
		//Corro el algoritmo y me fijo que al usuario basico no le haya llegado el mensaje subir de nivel
		app.revisarNivelesDeUsuario();
		verify(usuarioBasico, never()).subirDeNivel();
	}
	
	@Test
	void testAlgoritmoDeNivelesUsuarioBasicoEnviosEsperados() {
		//Testeo la correcta funcionalidad del algoritmo con un usuario basico con
		//envios esperados pero no cumple con las revisiones.
		app.registrarNuevoUsuario(usuarioBasico); // usuario basico que puede cambiar de nivel.
		when(usuarioBasico.esExperto()).thenReturn(false);
		when(usuarioBasico.cantidadEnviosUltimos30Dias()).thenReturn(11);
		when(usuarioBasico.cantidadRevisionesUltimos30Dias()).thenReturn(2);
		//Corro el algoritmo
		app.revisarNivelesDeUsuario();
		//Chequeo que al usuario basico no le haya llegado el mensaje subir de nivel.
		verify(usuarioBasico, never()).subirDeNivel();
	}

	@Test
	void testAlgoritmoDeNivelesUsuarioBasicoRendimientoEsperado() {
		//Testeo la correcta funcionalidad del algoritmo con un usuario basico con
		//rendimiento esperado (>20 revisiones y >10 envios)
		app.registrarNuevoUsuario(usuarioBasico); // usuario basico que puede cambiar de nivel.
		when(usuarioBasico.esExperto()).thenReturn(false);
		when(usuarioBasico.cantidadEnviosUltimos30Dias()).thenReturn(11);
		when(usuarioBasico.cantidadRevisionesUltimos30Dias()).thenReturn(22);
		//Corro el algoritmo
		app.revisarNivelesDeUsuario();
		//Chequeo que al usuario basico le haya llegado el mensaje subir de nivel.
		verify(usuarioBasico).subirDeNivel();		
	}
	
	@Test 
	void testAlgoritmoDeNivelesDeUsuarioExpertoNoValidado() {
		//Testeo la correcta funcionalidad del algoritmo con un usuario experto
		//que no es validado y no cumple con el rendimiento esperado (>20 revisiones y >10 envios)
		app.registrarNuevoUsuario(usuarioExperto);
		when(usuarioExperto.esExperto()).thenReturn(true);
		when(usuarioExperto.tieneConocimientoValidado()).thenReturn(false);
		when(usuarioExperto.cantidadEnviosUltimos30Dias()).thenReturn(8);
		when(usuarioExperto.cantidadRevisionesUltimos30Dias()).thenReturn(14);
		//Corro el algoritmo
		app.revisarNivelesDeUsuario();
		//Chequeo que al usuario experto se le haya enviado el mensaje bajar de nivel.
		verify(usuarioExperto).bajarDeNivel();
	}
	
	@Test
	void testAlgoritmoDeNivelesDeUsuarioExpertoConRendimiento() {
		//Testeo la correcta funcionalidad del algoritmo con un usuario experto
		//que no es validado y cumple con el rendimiento esperado (>20 revisiones y >10 envios)
		app.registrarNuevoUsuario(usuarioExperto);
		when(usuarioExperto.esExperto()).thenReturn(true);
		when(usuarioExperto.tieneConocimientoValidado()).thenReturn(false);
		when(usuarioExperto.cantidadEnviosUltimos30Dias()).thenReturn(11);
		when(usuarioExperto.cantidadRevisionesUltimos30Dias()).thenReturn(22);
		//Corro el algoritmo
		app.revisarNivelesDeUsuario();
		//Chequeo que al usuario experto no se le haya enviado el mensaje bajar de nivel.
		verify(usuarioExperto, never()).bajarDeNivel();		
	}
}
