package entities.Alquileres;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import entities.BaseEntity;
import entities.Personas.Empleado;
import entities.Vehiculos.Vehiculo;
import global.DateFormatter;
import global.TiposAlquileres;
import entities.Cocheras.Cochera;

public class Alquiler extends BaseEntity{

	public Alquiler() {
		this.Empleado = new Empleado();
		this.TipoAlquiler = new TipoAlquiler();
		this.Vehiculo = new Vehiculo();
		this.Cochera = new Cochera();
		this.FechaInicio = LocalDate.now();
		this.HoraInicio = LocalTime.now();
		this.setFechaFin();
		this.setHoraFin();
	}
	
	private LocalDate FechaInicio;
	
	private LocalTime HoraInicio;
	
	private LocalDate FechaFin;
	
	private LocalTime HoraFin;
	
	private boolean Pagado;
	
	private FormaPago FormaPago;
	
	private Empleado Empleado;
	
	private TipoAlquiler TipoAlquiler;
	
	private int TiempoEstadia;
	
	private Vehiculo Vehiculo;
	
	private double Precio;
	
	private Cochera Cochera;

	public LocalDate getFechaInicio() {
		return this.FechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.FechaInicio = fechaInicio;
	}

	public LocalTime getHoraInicio() {
		return this.HoraInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.HoraInicio = horaInicio;
	}

	public LocalDate getFechaFin() {
		return this.FechaFin;
	}

	public void setFechaFin() {
		this.FechaFin = this.getFinalDateTime().toLocalDate();
	}

	public LocalTime getHoraFin() {
		return this.HoraFin;
	}

	public void setHoraFin() {
		this.HoraFin = this.getFinalDateTime().toLocalTime();
	}

	public boolean isPagado() {
		return this.Pagado;
	}

	public void setPagado(boolean pagado) {
		this.Pagado = pagado;
	}

	public FormaPago getFormaPago() {
		return this.FormaPago;
	}

	public void setFormaPago(FormaPago formaPago) {
		this.FormaPago = formaPago;
	}
	
	public Empleado getEmpleado() {
		return this.Empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.Empleado = empleado;
	}

	public TipoAlquiler getTipoAlquiler() {
		return this.TipoAlquiler;
	}

	public void setTipoAlquiler(TipoAlquiler tipoAlquiler) {
		this.TipoAlquiler = tipoAlquiler;
	}

	public Vehiculo getVehiculo() {
		return this.Vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.Vehiculo = vehiculo;
	}

	public double getPrecio() {
		return this.Precio;
	}

	public void setPrecio(double precio) {
		this.Precio = precio;
	}

	public double getTotalPrecio() {
		double precio = this.Precio * this.TiempoEstadia;
		return precio + (precio * this.FormaPago.getTotalImputar());
	}
	
	public Cochera getCochera() {
		return this.Cochera;
	}

	public void setCochera(Cochera cochera) {
		this.Cochera = cochera;
	}
	
	public void setTiempoEstadia(int tiempoEstadia) {
		this.TiempoEstadia = tiempoEstadia;
		this.setFechaFin();
		this.setHoraFin();
	}
	
	public int getTiempoEstadia() {
		return this.TiempoEstadia;
	}
	
	public void setTiempoEstadia(String tiempoEstadia) {
		try {
			this.setTiempoEstadia(Integer.parseInt(tiempoEstadia));
		}
		catch (NumberFormatException ex) {
			this.setTiempoEstadia(0);
		}
	}
	
	public String getFechaHoraInicio() {
		return String.join(" ", DateFormatter.getFormattedDate(this.FechaInicio), DateFormatter.getFormattedHour(this.HoraInicio));
	}
	
	public String getFechaHoraFin() {
		return String.join(" ", DateFormatter.getFormattedDate(this.FechaFin), DateFormatter.getFormattedHour(this.HoraFin));
	}
	
	private LocalDateTime getFinalDateTime() {
		LocalDateTime finalDateTime = LocalDateTime.of(this.FechaInicio, this.HoraInicio);
		
		if (this.TipoAlquiler.getID() == TiposAlquileres.PORHORA) {
			finalDateTime = finalDateTime.plusHours(this.TiempoEstadia);
		}
		
		if (this.TipoAlquiler.getID() == TiposAlquileres.PORDIA) {
			finalDateTime = finalDateTime.plusDays(this.TiempoEstadia);
		}
		
		if (this.TipoAlquiler.getID() == TiposAlquileres.PORMES) {
			finalDateTime = finalDateTime.plusMonths(this.TiempoEstadia);
		}
		
		return finalDateTime;
	}
	
	public boolean estaVencido() {
		return !this.Pagado && this.FechaFin.isBefore(LocalDate.now());
	}
}
