package ar.edu.unq.po2.tp.grupal.ong.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import ar.edu.unq.po2.tp.grupal.ong.Funcionalidad;
import ar.edu.unq.po2.tp.grupal.ong.FuncionalidadExterna;
import ar.edu.unq.po2.tp.grupal.ong.Ong;
import ar.edu.unq.po2.tp.grupal.ong.TipoDeOrg;
import ar.edu.unq.po2.tp.grupal.zonaDeCobertura.Ubicacion;
import ar.edu.unq.po2.tp.grupal.zonaDeCobertura.ZonaDeCobertura;

public class OngTest {

	Ubicacion ubicacion = new Ubicacion(0, 0);
	TipoDeOrg tipo;
	Funcionalidad muestra;
	Funcionalidad validacion;
	Ong ong = new Ong(ubicacion, tipo, 28, muestra, validacion);
	ZonaDeCobertura zona;

	@BeforeEach
	public void setUp() {
		this.muestra = mock(Funcionalidad.class);
		this.validacion = mock(Funcionalidad.class);
		this.zona = mock(ZonaDeCobertura.class);
		ong.registrarZona(zona);
	}

	@Test
	public void testHetUbicacion() {
		assertEquals(ong.getUbicacion(), ubicacion);
	}

	@Test
	public void testGetTipoDeOrg() {
		assertEquals(ong.getTipoDeOrg(), tipo);
	}

	@Test
	public void testGetCantidadDeTrabajadores() {
		assertEquals(ong.getCantTrabajadores(), 28);
	}

	// Se testea que regrese la funcionalidad externa de muestra.
	@Test
	public void testGetFuncionalidadExternaMuestra() {
		assertEquals(ong.getFuncionalidadExternaMuestra(), muestra);
	}

	// Se testea que regrese la funcionalidad externa de validacion.
	@Test
	public void testGetFuncionalidadExternaValidacion() {
		assertEquals(ong.getFuncionalidadExternaValidacion(), validacion);
	}

	// Se testea que al usar registrar zona quede registrada.
	@Test
	public void testRegistrarZona() {
		ong.registrarZona(zona);
		assertEquals(ong.getZonasSubscriptas().size(), 1);
	}

	// Se testea que al usar desuscribir zona quede, esa zona eliminada de la lista.
	@Test
	public void testDesuscribirZona() {
		ong.desuscribirZona(zona);
		assertEquals(ong.getZonasSubscriptas().size(), 0);
	}

}
