package ar.edu.unq.po2.tp.grupal.zonaDeCobertura;

public class Ong implements Observer {

	String nombre;
	FuncionalidadExterna muestra;
	FuncionalidadExterna validacion;

	public Ong(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public void update() {
		// Llamar funcionalidad externa para muestra.
		
	}

	@Override
	public void updateValidacion() {
		// Llamar funcionalidad externa para validación.
		
	}


}
