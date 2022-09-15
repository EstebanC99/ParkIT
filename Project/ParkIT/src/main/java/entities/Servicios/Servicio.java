package entities.Servicios;

import entities.BaseEntity;

public class Servicio extends BaseEntity{
	
	private float Precio;
	
	
	public float getPrecio() {
		return Precio;
	}
	public void setPrecio(float precio) {
		Precio = precio;
	}
}
