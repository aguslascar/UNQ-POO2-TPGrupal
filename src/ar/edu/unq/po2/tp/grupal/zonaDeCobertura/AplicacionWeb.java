package ar.edu.unq.po2.tp.grupal.zonaDeCobertura;

import java.util.ArrayList;
import java.util.List;

public class AplicacionWeb {

	private List<Muestra> muestras;
	
public AplicacionWeb() {
		muestras = new ArrayList<Muestra>();
	}
	
	public List<Muestra> getMuestras() {
		return muestras;
	}
}
