package ar.edu.unq.po2.tp.grupal.revision.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.tp.grupal.revision.Opinion;

public class OpinionTest {

	private Opinion opinionVinchucaInfestans;
	private Opinion opinionVinchucaSordida;
	private Opinion opinionVinchucaGuasayana;
	private Opinion opinionChincheFoliada;
	private Opinion opinionPhtiaChinche;
	private Opinion opinionNinguna;
	private Opinion opinionImagenPocoClara;
	
	@BeforeEach
	public void setUp() {
		opinionVinchucaInfestans = Opinion.VINCHUCAINFESTANS;
		opinionVinchucaSordida = Opinion.VINCHUCASORDIDA;
		opinionVinchucaGuasayana = Opinion.VINCHUCAGUASAYANA;
		opinionChincheFoliada = Opinion.CHINCHEFOLIADA;
		opinionPhtiaChinche = Opinion.PHTIACHINCHE;
		opinionNinguna = Opinion.NINGUNA;
		opinionImagenPocoClara = Opinion.IMAGENPOCOCLARA;
	}
	
	// Se testea que al momento de instanciar la opinion 'VINCHUCAINFESTANS', entonces se pueda conocer la descripción
	// del nombre de esta instancia, y que esta sea la descripción deseada
	@Test
	public void testConocerDescripcionDeOpinionVinchucaInfestans() {
		assertEquals(opinionVinchucaInfestans.getDescripcion(), "Vinchuca Infestans");
	}
	
	// Se testea que al momento de instanciar la opinion 'VINCHUCASORDIDA', entonces se pueda conocer la descripción
	// del nombre de esta instancia, y que esta sea la descripción deseada
	@Test
	public void testConocerDescripcionDeOpinionVinchucaSordida() {
		assertEquals(opinionVinchucaSordida.getDescripcion(), "Vinchuca Sordida");
	}
	
	// Se testea que al momento de instanciar la opinion 'VINCHUCAGUASAYANA', entonces se pueda conocer la descripción
	// del nombre de esta instancia, y que esta sea la descripción deseada
	@Test
	public void testConocerDescripcionDeOpinionVinchucaGuasayana() {
		assertEquals(opinionVinchucaGuasayana.getDescripcion(), "Vinchuca Guasayana");
	}
	
	// Se testea que al momento de instanciar la opinion 'CHINCHEFOLIADA', entonces se pueda conocer la descripción
	// del nombre de esta instancia, y que esta sea la descripción deseada
	@Test
	public void testConocerDescripcionDeOpinionChincheFoliada() {
		assertEquals(opinionChincheFoliada.getDescripcion(), "Chinche Foliada");
	}
	
	// Se testea que al momento de instanciar la opinion 'PHTIACHINCHE', entonces se pueda conocer la descripción
	// del nombre de esta instancia, y que esta sea la descripción deseada
	@Test
	public void testConocerDescripcionDeOpinionPhtiaChinche() {
		assertEquals(opinionPhtiaChinche.getDescripcion(), "Phtia-Chinche");
	}
	
	// Se testea que al momento de instanciar la opinion 'NINGUNA', entonces se pueda conocer la descripción
	// del nombre de esta instancia, y que esta sea la descripción deseada
	@Test
	public void testConocerDescripcionDeOpinionNinguna() {
		assertEquals(opinionNinguna.getDescripcion(), "Ninguna");
	}
	
	// Se testea que al momento de instanciar la opinion 'IMAGENPOCOCLARA', entonces se pueda conocer la descripción
	// del nombre de esta instancia, y que esta sea la descripción deseada
	@Test
	public void testConocerDescripcionDeOpinionImagenPocoClara() {
		assertEquals(opinionImagenPocoClara.getDescripcion(), "Imagen poco clara");
	}
}
