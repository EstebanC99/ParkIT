package dto.Filtros;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class FiltroAlquileres {
	
	public FiltroAlquileres() {
		this.FechaInicio = LocalDate.now();
		this.FechaFin = LocalDate.now();
	}
	
	public LocalDate FechaInicio;
	
	public LocalDate FechaFin;
	
	public int EstadoAlquiler;
	
	public int FormaPagoID;
	
	public int TipoAlquilerID;

	public void setFechaInicio(String fechaInicio) {
		try {
			this.FechaInicio = LocalDate.parse(fechaInicio);
		}
		catch (DateTimeParseException ex) {
			this.FechaInicio = LocalDate.now();
		}
	}
	
	public void setFechaFin(String fechaFin) {
		if (fechaFin == null || fechaFin == "") {
			this.FechaFin = LocalDate.now().plusMonths(1);
			return;
		}
		try {
			this.FechaFin = LocalDate.parse(fechaFin);
		}
		catch (DateTimeParseException ex) {
			this.FechaFin = LocalDate.now();
		}
	}
	
	public void setEstadoAlquiler(String estadoAlquilerID) {
		try {
			this.EstadoAlquiler = Integer.parseInt(estadoAlquilerID);
		}
		catch(NumberFormatException ex) {
			this.EstadoAlquiler = 0;
		}
	}
	
	public void setFormaPagoID(String formaPagoID) {
		try {
			this.FormaPagoID = Integer.parseInt(formaPagoID);
		}
		catch(NumberFormatException ex) {
			this.FormaPagoID = 0;
		}
	}
	
	public void setTipoAlquilerID(String tipoAlquilerID) {
		try {
			this.TipoAlquilerID = Integer.parseInt(tipoAlquilerID);
		}
		catch(NumberFormatException ex) {
			this.TipoAlquilerID = 0;
		}
	}
	
	public void limpiar() {
		this.EstadoAlquiler = 0;
		this.FormaPagoID = 0;
		this.TipoAlquilerID = 0;
		this.FechaInicio = LocalDate.now();
		this.FechaFin = LocalDate.now();
	}
}
