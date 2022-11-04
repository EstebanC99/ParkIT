package entities.Alquileres;

import java.time.LocalDate;

import entities.BaseEntity;
import entities.Cocheras.TipoCochera;

public class PrecioAlquiler extends BaseEntity {

	public PrecioAlquiler() { }
	
	private Double Precio;
	
	private LocalDate FechaVigencia;
	
	private TipoCochera TipoCochera;
	
	private TipoAlquiler TipoAlquiler;

	public Double getPrecio() {
		return Precio;
	}
	
	public void setPrecio(Double precio) {
		this.Precio  = precio;
	}

	public LocalDate getFechaVigencia() {
		return FechaVigencia;
	}
	
	public void setFechaVigencia(LocalDate fechaVigencia) {
		this.FechaVigencia = fechaVigencia;
	}

	public TipoCochera getTipoCochera() {
		if (TipoCochera == null)
			TipoCochera = new TipoCochera();
		
		return TipoCochera;
	}
	
	public void setTipoCochera(TipoCochera tipoCochera) {
		this.TipoCochera = tipoCochera;
	}

	public TipoAlquiler getTipoAlquiler() {
		if (TipoAlquiler == null)
			TipoAlquiler = new TipoAlquiler();
		
		return TipoAlquiler;
	}
	
	public void setTipoAlquiler(TipoAlquiler tipoAlquiler) {
		this.TipoAlquiler = tipoAlquiler;
	}
	
}
