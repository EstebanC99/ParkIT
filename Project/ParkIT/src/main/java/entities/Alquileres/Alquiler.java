package entities.Alquileres;

import java.time.LocalDate;
import java.time.LocalTime;

import entities.BaseEntity;
import entities.Personas.Empleado;
import entities.Vehiculos.Vehiculo;
import entities.Cocheras.Cochera;

public class Alquiler extends BaseEntity{

	public Alquiler() {
		this.Empleado = new Empleado();
		this.TipoAlquiler = new TipoAlquiler();
		this.Vehiculo = new Vehiculo();
		this.Cochera = new Cochera();
	}
	
	private LocalDate FechaInicio;
	
	private LocalTime HoraInicio;
	
	private LocalDate FechaFin;
	
	private LocalTime HoraFin;
	
	private boolean Pagado;
	
	private FormaPago FormaPago;
	
	private Empleado Empleado;
	
	private TipoAlquiler TipoAlquiler;
	
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

	public void setFechaFin(LocalDate fechaFin) {
		this.FechaFin = fechaFin;
	}

	public LocalTime getHoraFin() {
		return this.HoraFin;
	}

	public void setHoraFin(LocalTime horaFin) {
		this.HoraFin = horaFin;
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
	
	public Cochera getCochera() {
		return this.Cochera;
	}

	public void setCochera(Cochera cochera) {
		this.Cochera = cochera;
	}
	
	
}
