package entities.Cocheras;

import entities.BaseEntity;

public class Cochera extends BaseEntity {

	public Cochera() {
		this.TipoCochera = new TipoCochera();
	}
	
	private int NroCochera;
	
	private boolean Disponible;
	
	private String Ubicacion;
	
	private TipoCochera TipoCochera;

	public int getNroCochera() {
		return this.NroCochera;
	}

	public void setNroCochera(int nroCochera) {
		this.NroCochera = nroCochera;
	}

	public boolean isDisponible() {
		return this.Disponible;
	}

	public void setDisponible(boolean disponible) {
		this.Disponible = disponible;
	}

	public String getUbicacion() {
		return this.Ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.Ubicacion = ubicacion;
	}

	public TipoCochera getTipoCochera() {
		return this.TipoCochera;
	}

	public void setTipoCochera(TipoCochera tipoCochera) {
		this.TipoCochera = tipoCochera;
	}
	
	@Override
	public String toString() {
		return String.join(" - ", "Nro: " + String.valueOf(this.NroCochera), "Ubicacion: " + this.Ubicacion);
	}
	
}
