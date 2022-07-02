package ar.edu.unq.po2.tp.grupal.aplicacion.Test;

import static org.junit.jupiter.api.Assertions.*;


import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.tp.grupal.aplicacion.AplicacionWeb;
import ar.edu.unq.po2.tp.grupal.aplicacion.Imagen;
import ar.edu.unq.po2.tp.grupal.filtro.Filtro;
import ar.edu.unq.po2.tp.grupal.muestra.Muestra;
import ar.edu.unq.po2.tp.grupal.ong.Ong;
import ar.edu.unq.po2.tp.grupal.revision.*;
import ar.edu.unq.po2.tp.grupal.usuario.NivelDeUsuario;
import ar.edu.unq.po2.tp.grupal.usuario.Usuario;
import ar.edu.unq.po2.tp.grupal.zonaDeCobertura.Ubicacion;
import ar.edu.unq.po2.tp.grupal.zonaDeCobertura.ZonaDeCobertura;

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
	Filtro filtro;
	NivelDeUsuario nivelDeUsuario;
	Ong organizacion;
	ZonaDeCobertura zona;
	NivelDeUsuario nivel;
	
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
		filtro = mock(Filtro.class);
		nivelDeUsuario = mock(NivelDeUsuario.class);
		zona = mock(ZonaDeCobertura.class);
		organizacion = mock(Ong.class);
		nivel = mock(NivelDeUsuario.class);
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
	void testAgregarNuevaMuestra() throws Exception {
		//Testeo que se cree y se agregue una nueva muestra
		app.registrarNuevoUsuario(usuarioBasico);
		when(usuarioBasico.getidUsuario()).thenReturn(1);
		when(usuarioBasico.getNivel()).thenReturn(nivel);
		app.registrarMuestra(usuarioBasico, LocalDate.now(), imagen, ubicacion, opinion);
		assertEquals(1, app.getMuestras().size());
		//Me fijo que el id de usuario de la muestra cargada sea el mismo id del usuario que la cargo.
		assertEquals(usuarioBasico.getidUsuario(), app.getMuestras().get(0).getIdUsuario());
	}
	
	@Test
	void testAgregarNuevaMuestraUsuarioNoRegistrado() throws Exception {
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
		when(revision.getIdUsuario()).thenReturn(1);
		//Chequeo que se haya enviado una excepcion, ya que muestraFalsa no es una muestra del sistema.
		assertThrows(Exception.class, () -> app.agregarRevision(muestraFalsa, revision));


	}
	
	@Test
	void testAgregarRevisionSinSerUsuarioSistema() {
		app.registrarNuevoUsuario(usuarioBasico);
		//Pruebo agregar una muestra de un usuario con id 2, el cual no existe en sistema
		when(revision.getIdUsuario()).thenReturn(2);		
		//Chequeo que se haya enviado una excepcion, ya que el usuario con id 2 no es un usuario del sistema.
		assertThrows(Exception.class, () -> app.agregarRevision(muestraFalsa, revision));		
	}
	
	@Test
	void testAgregarRevisionDeOtraMuestra() throws Exception {
		//Agrego una revision de una muestra de un usuario existente en el sistema.
		app.registrarNuevoUsuario(usuarioBasico);
		app.registrarNuevoUsuario(usuarioExperto);
		when(usuarioBasico.getidUsuario()).thenReturn(1);
		when(revision.getNivelDeUsuario()).thenReturn(nivelDeUsuario);
		when(nivelDeUsuario.esExperto()).thenReturn(true);
		when(usuarioBasico.getNivel()).thenReturn(nivel);
		//Primero agrego la muestra al sistema con id 1(usuario basico)
		app.registrarMuestra(usuarioBasico, LocalDate.now(), imagen, ubicacion, opinion);
		//Luego guardo esa muestra para luego agregarle la revision
		Muestra muestraDelSistema = app.getMuestras().get(0);
		//El usuario experto(el cual nunca opino ni subio esa muestra) quiere hacer una revision
		when(usuarioExperto.getidUsuario()).thenReturn(2);
		when(revision.getIdUsuario()).thenReturn(2);
		//Chequeo que no se haya lanzado una excepcion.
		assertDoesNotThrow(() -> app.agregarRevision(muestraDelSistema, revision));		
	}
	
	@Test
	void testAgregarRevisionDeSuPropiaMuestra() throws Exception {
		//Testeo que cuando un usuario quiera opinar sobre la muestra que el subio,
		//se lance una excepcion
		app.registrarNuevoUsuario(usuarioBasico);
		when(usuarioBasico.getidUsuario()).thenReturn(1);
		when(revision.getIdUsuario()).thenReturn(1);
		when(usuarioBasico.getNivel()).thenReturn(nivel);
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
	
	@Test
	void testFiltrar() {
		//Testeo la correcta funcionalidad del sistema al filtrar muestras
		//Creo una lista de Muestra a la cual agrego la muestra falsa
		List<Muestra> muestras = new ArrayList<Muestra>();
		muestras.add(muestraFalsa);
		//Mockeo el filtro para que cuando se filtre retorne esa lista de Muestra
		when(filtro.filtrar(app.getMuestras())).thenReturn(muestras);
		//Chequeo que el sistema hizo el llamado a filtrar(Muestra) y app.filtrarMuestras(filtro) contiene la muestra falsa.
		assertTrue(app.filtrarMuestras(filtro).contains(muestraFalsa));
	}
	
	@Test
	void testAgregarZonaDeCobertura() {
		//Testeo que se agregue una zona de cobertura a la lista de zonas
		app.agregarZona(zona);
		assertTrue(app.getZonas().contains(zona));
	}
	
	@Test
	void testAgregarOrganizacion() {
		//Testeo que se agregue una ong a la lista de organizaciones
		app.agregarOrganizacion(organizacion);
		assertTrue(app.getOrganizaciones().contains(organizacion));
	}
	
	@Test
	void testAgregarNuevaMuestraYRegistrarEnZona() throws Exception {
		//Testeo que al agregar una muestra, se agregue a la zona de cobertura
		//Mockeo la zona para que indique que la ubicacion de la muestra si pertenece a esa zona
		app.agregarZona(zona);
		app.registrarNuevoUsuario(usuarioBasico);
		when(zona.perteneceAZona(ubicacion)).thenReturn(true);
		when(usuarioBasico.getidUsuario()).thenReturn(1);
		when(usuarioBasico.getNivel()).thenReturn(nivel);
		app.registrarMuestra(usuarioBasico, LocalDate.now(), imagen, ubicacion, opinion);
		verify(zona).agregarMuestra(any());
	}
	
	@Test
	void testAgregarNuevaMuestraYNoRegistrarEnZona() throws Exception {
		//Testeo que al agregar una muestra, no se agregue a una zona de cobertura
		//Mockeo la zona para que indique que la ubicacion de la muestra no pertenece a esa zona
		app.agregarZona(zona);
		app.registrarNuevoUsuario(usuarioBasico);
		when(zona.perteneceAZona(ubicacion)).thenReturn(false);
		when(usuarioBasico.getidUsuario()).thenReturn(1);
		when(usuarioBasico.getNivel()).thenReturn(nivel);
		app.registrarMuestra(usuarioBasico, LocalDate.now(), imagen, ubicacion, opinion);
		verify(zona, never()).agregarMuestra(any());
	}
	
	@Test 
	void testMuestraHaSidoVerificada() {
		//Testeo que una muestra que pertenece a una zona, se valide y notifique a la zona
		app.agregarZona(zona);
		when(zona.esMuestraDeZona(muestraFalsa)).thenReturn(true);
		app.seValidoMuestra(muestraFalsa);
		verify(zona).notificarValidacion();
	}
	
	@Test 
	void testMuestraHaSidoVerificadaPeroNoPerteneceANingunaZona() {
		//Testeo que una muestra que no pertenece a ninguna una zona, se valide y no haga nada
		app.agregarZona(zona);
		when(zona.esMuestraDeZona(muestraFalsa)).thenReturn(false);
		app.seValidoMuestra(muestraFalsa);
		//Verifico que a la unica zona del sistema, no se le envie ningun mensaje
		verify(zona, never()).notificarValidacion();
	}
	
	@Test
	void testRegistrarUnaOrganizacionAUnaZona() throws Exception {
		//Testeo que una ong perteneciente al sistema sea agregada a su zona de cobertura de interes,
		//la cual tambien esta en el sistema.
		app.agregarOrganizacion(organizacion);
		app.agregarZona(zona);
		app.registrarOngAZona(organizacion, zona);
		verify(zona).registrar(organizacion);
	}
	
	@Test
	void testRegistrarOngAZonaInexistentes() throws Exception {
		//Testeo que una ong que no pertenece al sistema quiera registrarse a una zona que tampoco pertenece
		assertThrows(Exception.class, () -> app.registrarOngAZona(organizacion, zona));
		verify(zona, never()).registrar(organizacion);
		
	}
	
	@Test
	void testRegistrarOngPertenecienteAOngInexistente() throws Exception {
		//Testeo que una ong del sistema quiera registrarse a una zona que no pertenece al sistema.
		app.agregarOrganizacion(organizacion);
		assertThrows(Exception.class, () -> app.registrarOngAZona(organizacion, zona));
		verify(zona, never()).registrar(organizacion);
	}
}
