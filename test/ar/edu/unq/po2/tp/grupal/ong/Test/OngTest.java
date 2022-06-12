package ar.edu.unq.po2.tp.grupal.ong.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import ar.edu.unq.po2.tp.grupal.ong.FuncionalidadExterna;
import ar.edu.unq.po2.tp.grupal.ong.Ong;

public class OngTest {

	FuncionalidadExterna muestra;
	FuncionalidadExterna validacion;
	Ong ong = new Ong("Ong1", muestra, validacion);

	@BeforeEach
	public void setUp() {
		this.muestra = mock(FuncionalidadExterna.class);
		this.validacion = mock(FuncionalidadExterna.class);
	}

	// Se testea el get para el nombre de la Ong.
	@Test
	public void testGetNombre() {
		assertEquals(ong.getNombre(), "Ong1");
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

}
