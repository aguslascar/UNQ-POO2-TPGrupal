package ar.edu.unq.po2.tp.grupal.aplicacion;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
	
	@BeforeEach
	void setUp() throws Exception {
		app = new AplicacionWeb();
		imagen = mock(Imagen.class);
		ubicacion = mock(Ubicacion.class);
		opinion = mock(Opinion.class);

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

}
