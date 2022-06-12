package ar.edu.unq.po2.tp.grupal.ong;

import ar.edu.unq.po2.tp.grupal.zonaDeCobertura.Muestra;
import ar.edu.unq.po2.tp.grupal.zonaDeCobertura.ZonaDeCobertura;

public interface FuncionalidadExterna {
	public void nuevoEvento(Ong ong, ZonaDeCobertura zona, Muestra muestra);
}
