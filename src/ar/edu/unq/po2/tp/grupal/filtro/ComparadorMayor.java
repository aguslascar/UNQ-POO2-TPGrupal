package ar.edu.unq.po2.tp.grupal.filtros;

import java.time.LocalDate;

public class ComparadorMayor implements ComparadorDeFechas {
	
	/**
	 * Una clase que compara fechas con criterio de ser mayor.
	 * @author aguslascar
	 */

	@Override
	public boolean comparar(LocalDate fecha1, LocalDate fecha2) {
		/**
		 * Este metodo compara entre la fecha 1 y la fecha 2 y retorna si la fecha1 es mayor a la fecha2
		 * Obligatoriamente para la funcionalidad de la clase, la fecha1 es la que quiero ver si es mayor a la fecha2
		 */
		return fecha1.isAfter(fecha2);
	}

}
