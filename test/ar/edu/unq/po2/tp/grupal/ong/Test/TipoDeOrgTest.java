package ar.edu.unq.po2.tp.grupal.ong.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.tp.grupal.ong.TipoDeOrg;

class TipoDeOrgTest {
	
	private TipoDeOrg tipoSalud;
	private TipoDeOrg tipoEducativa;
	private TipoDeOrg tipoCultural;
	private TipoDeOrg tipoAsistencia;
	
	@BeforeEach
	public void setUp() {
		tipoSalud = TipoDeOrg.SALUD;
		tipoEducativa = TipoDeOrg.EDUCATIVA;
		tipoCultural = TipoDeOrg.CULTURAL;
		tipoAsistencia = TipoDeOrg.ASISTENCIA;
		
	}
	
	// Se testea que al momento de instanciar el tipo 'SALUD', entonces se pueda conocer la descripción
	// del nombre de esta instancia, y que esta sea la descripción deseada.
	@Test
	void testTipoSalud() {
		assertEquals(tipoSalud.getDescripcion(), "Salud");
	}
	
	// Se testea que al momento de instanciar el tipo 'EDUCATIVA', entonces se pueda conocer la descripción
	// del nombre de esta instancia, y que esta sea la descripción deseada.
	@Test
	void testTipoEducativa() {
		assertEquals(tipoEducativa.getDescripcion(), "Educativa");
	}
	
	// Se testea que al momento de instanciar el tipo 'CULTURAL', entonces se pueda conocer la descripción
	// del nombre de esta instancia, y que esta sea la descripción deseada.
	@Test
	void testTipoCultural() {
		assertEquals(tipoCultural.getDescripcion(), "Cultural");
	}
	
	// Se testea que al momento de instanciar el tipo 'ASISTENCIA', entonces se pueda conocer la descripción
	// del nombre de esta instancia, y que esta sea la descripción deseada.
	@Test
	void testTipoAsistencia() {
		assertEquals(tipoAsistencia.getDescripcion(), "Asistencia");
	}

}