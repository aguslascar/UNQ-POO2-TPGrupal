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
	
	@BeforeEach
	void setUp() throws Exception {
		app = new AplicacionWeb();
		imagen = mock(Imagen.class);
		ubicacion = mock(Ubicacion.class);
		opinion = mock(Opinion.class);
		muestraFalsa = mock(Muestra.class);

	}

	@Test
	void testCreacionDeUsuarios() {
		//Testeo que se creen los usuarios y se guarden con su id.
		app.registrarNuevoUsuario(false);
		app.registrarNuevoUsuario(true);
		assertEquals(2, app.getUsuarios().size());
		assertEquals(2, app.getUltimoidUsuario());
	}
	
	@Test
	void testAgregarNuevaMuestra() {
		//Testeo que se cree y se agregue una nueva muestra
		app.registrarNuevoUsuario(false);
		Usuario usuario = app.getUsuarios().get(0);
		app.registrarMuestra(1, LocalDate.now(), imagen, ubicacion, opinion);
		assertEquals(1, app.getMuestras().size());
		//Me fijo que el id de usuario de la muestra cargada sea el mismo id del usuario que la cargo.
		assertEquals(usuario.getidUsuario(), app.getMuestras().get(0).getidUsuario());
	}
	
	@Test 
	void testAgregarRevision() throws Exception {
		//Testeo agregarRevision, primero agregando una revision de una muestra falsa que no esta en el sistema
		//y luego de una muestra que si esta pero el usuario no.
		//Por ultimo testeo una muestra que esta y un usuario que tambien esta en sistema.
		app.registrarNuevoUsuario(false);
		app.agregarRevision(muestraFalsa, revision, 1);
		//Chequeo que nunca se haya llamado al mensaje agregarRevision de esa muestra, ya que no
		//es muestra del sistema
		verify(muestraFalsa, never()).agregarRevision(revision);
		//Pruebo agregar una muestra de un usuario con id 2, el cual no existe en sistema
		//Chequeo que se arroje la excepcion.
		assertThrows(Exception.class, () -> app.agregarRevision(muestraFalsa, revision, 2));
		//Ahora agrego una revision de una muestra y un usuario existentes en el sistema.
		//Primero agrego la muestra al sistema
		app.registrarMuestra(1, LocalDate.now(), imagen, ubicacion, opinion);
		//Luego guardo esa muestra para luego agregarle la revision
		Muestra muestraDelSistema = app.getMuestras().get(0);
		//Chequeo que no se haya lanzado una excepcion.
		assertDoesNotThrow(() -> app.agregarRevision(muestraDelSistema, revision, 1));
	}
	

}
