package ar.edu.unq.po2.tp.grupal.revision1;

public enum Opinion {

	VINCHUCAINFESTANS("Vinchuca Infestans"),
	VINCHUCASORDIDA("Vinchuca Sordida"),
	VINCHUCAGUASAYANA("Vinchuca Guasayana"),
	CHINCHEFOLIADA("Chinche Foliada"),
	PHTIACHINCHE("Phtia-Chinche"),
	NINGUNA("Ninguna"),
	IMAGENPOCOCLARA("Imagen poco clara");
	
	private String descripcion;
	
	private Opinion(String descripcion) {
		this.descripcion = descripcion;    // No se utiliza un setter debido a que es un enumerativo.
	}

}
