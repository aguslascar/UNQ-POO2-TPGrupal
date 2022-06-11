package ar.edu.unq.po2.tp.grupal.filtro;

import java.time.LocalDate;
/**
 * Una interfaz que sera la base de clases que comparen fechas.
 * @author aguslascar
 */
public interface ComparadorDeFechas {

	
	boolean comparar(LocalDate fecha1, LocalDate fecha2);
}
