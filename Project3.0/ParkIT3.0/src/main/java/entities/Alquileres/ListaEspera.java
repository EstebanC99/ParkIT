package entities.Alquileres;

import java.time.LocalDate;
import java.time.LocalTime;

import entities.BaseEntity;
import entities.Cocheras.TipoCochera;
import entities.Personas.Cliente;

public class ListaEspera extends BaseEntity {

	public ListaEspera() {
		this.TipoCochera = new TipoCochera();
		this.Cliente = new Cliente();
		this.FechaIngreso = LocalDate.now();
		this.HoraIngreso = LocalTime.now();
	}
	
	private LocalDate FechaIngreso;
	
	private LocalTime HoraIngreso;
	
	private TipoCochera TipoCochera;
	
	private Cliente Cliente;

	public LocalDate getFechaIngreso() {
		return this.FechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.FechaIngreso = fechaIngreso;
	}

	public LocalTime getHoraIngreso() {
		return this.HoraIngreso;
	}

	public void setHoraIngreso(LocalTime horaIngreso) {
		this.HoraIngreso = horaIngreso;
	}

	public TipoCochera getTipoCochera() {
		return this.TipoCochera;
	}

	public void setTipoCochera(TipoCochera tipoCochera) {
		this.TipoCochera = tipoCochera;
	}

	public Cliente getCliente() {
		return this.Cliente;
	}

	public void setCliente(Cliente cliente) {
		this.Cliente = cliente;
	}
}
