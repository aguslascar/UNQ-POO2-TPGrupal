package ar.edu.unq.po2.tp.grupal.revision;

public enum Opinion {

	VINCHUCAINFESTANS("Vinchuca Infestans"),
	VINCHUCASORDIDA("Vinchica Sordida"),
	VINCHUCAGUASAYANA("Vinchuca Guasayana"),
	CHINCHEFOLIADA("Chinche Foliada"),
	PHTIACHINCHE("Phtia-Chinche"),
	NINGUNA("Ninguna"),
	IMAGENPOCOCLARA("Imagen poco clara");
	
	private String descripcion;
	
	private Opinion(String descripcion) {
		this.descripcion = descripcion;	
	}
	
	public String getDescripcion() {
		return descripcion;
	}
}
