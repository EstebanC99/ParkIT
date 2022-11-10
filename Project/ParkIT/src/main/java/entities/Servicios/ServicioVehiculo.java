package entities.Servicios;

import java.time.LocalDate;

import entities.BaseEntity;
import entities.Personas.Empleado;
import entities.Vehiculos.Vehiculo;

public class ServicioVehiculo extends BaseEntity{

	public ServicioVehiculo() {
		this.Servicio = new Servicio();
		this.Empleado = new Empleado();
		this.Vehiculo = new Vehiculo();
	}
	
	private LocalDate FechaRealizacion;

	private Servicio Servicio;
	
	private Empleado Empleado;
	
	private Vehiculo Vehiculo;
	
	private boolean Pagado;
	
	public void setFechaRealizacion(LocalDate fechaRealizacion) {
		this.FechaRealizacion = fechaRealizacion;
	}
	
	public LocalDate getFechaRealizacion() {
		return this.FechaRealizacion;
	}

	public Servicio getServicio() {
		return this.Servicio;
	}

	public void setServicio(Servicio servicio) {
		this.Servicio = servicio;
	}

	public Empleado getEmpleado() {
		return this.Empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.Empleado = empleado;
	}

	public Vehiculo getVehiculo() {
		return this.Vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.Vehiculo = vehiculo;
	}
	
	public void setPagado(boolean pagado) {
		this.Pagado = pagado;
	}
	
	public boolean isPagado() {
		return this.Pagado;
	}
	
	public String getFormattedPrecio() {
		return "$" + String.valueOf(this.Servicio.getPrecio());
	}
	
}
