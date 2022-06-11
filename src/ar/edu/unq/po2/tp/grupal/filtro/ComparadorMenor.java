package ar.edu.unq.po2.tp.grupal.filtro;

import java.time.LocalDate;

/**
 * Una clase que compara fechas con criterio de ser menor.
 * @author aguslascar
 */
public class ComparadorMenor implements ComparadorDeFechas {
	
	

	/**
	 * Este metodo compara entre la fecha 1 y la fecha 2 y retorna si la fecha1 es menor a la fecha2
	 * Obligatoriamente para la funcionalidad de la clase, la fecha1 es la que quiero ver si es menor a la fecha2
	 * @param dos LocalDate que van a representar las fechas a comparar.
	 * @return un booleano que indica si fecha1 es menor a fecha2
	 */
	@Override
	public boolean comparar(LocalDate fecha1, LocalDate fecha2) {
		
		return fecha1.isBefore(fecha2);
	}

}
