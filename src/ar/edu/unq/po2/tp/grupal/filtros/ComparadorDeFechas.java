package ar.edu.unq.po2.tp.grupal.filtros;

import java.time.LocalDate;

public interface ComparadorDeFechas {

	/**
	 * @author aguslascar
	 * Una interfaz que sera la base de clases que comparen fechas.
	 */
	boolean comparar(LocalDate fecha1, LocalDate fecha2);
}
