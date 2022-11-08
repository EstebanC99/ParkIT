package entities.Alquileres;

import entities.BaseEntity;

public class FormaPago extends BaseEntity{
	
	public FormaPago() {
		
	}
	
	private double Descuento;
	
	private double Incremento;

	public double getDescuento() {
		return this.Descuento;
	}

	public void setDescuento(double descuento) {
		this.Descuento = descuento;
	}

	public double getIncremento() {
		return this.Incremento;
	}

	public void setIncremento(double incremento) {
		this.Incremento = incremento;
	}
	

}
