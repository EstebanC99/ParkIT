package entities.Alquileres;

import entities.BaseEntity;

public class FormaPago extends BaseEntity{
	
	public FormaPago() {
		
	}
	
	private double descuento;
	
	private double incremento;
	

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public double getIncremento() {
		return incremento;
	}

	public void setIncremento(double incremento) {
		this.incremento = incremento;
	}
	

}
