package entities.Vehiculos;

import entities.BaseEntity;
import entities.Personas.Cliente;

public class Vehiculo extends BaseEntity{

	public Vehiculo() {
		this.Cliente = new Cliente();
		this.TipoVehiculo = new TipoVehiculo();
	}
	
	private String Patente;
	
	private String Modelo;
	
	private String Marca;
	
	private Cliente Cliente;
	
	private TipoVehiculo TipoVehiculo;

	public String getPatente() {
		return this.Patente;
	}

	public void setPatente(String patente) {
		this.Patente = patente.toUpperCase();
	}

	public String getModelo() {
		return this.Modelo;
	}

	public void setModelo(String modelo) {
		this.Modelo = modelo.toUpperCase();
	}

	public String getMarca() {
		return this.Marca;
	}

	public void setMarca(String marca) {
		this.Marca = marca.toUpperCase();
	}

	public Cliente getCliente() {
		return this.Cliente;
	}

	public void setCliente(Cliente cliente) {
		this.Cliente = cliente;
	}

	public TipoVehiculo getTipoVehiculo() {
		return this.TipoVehiculo;
	}

	public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.TipoVehiculo = tipoVehiculo;
	}
	
	@Override
	public String toString() {
		return String.join(" - ", "Patente: " + this.Patente, "Marca:" + this.Marca, "Modelo: " + this.Modelo, "Titular: " + this.Cliente.toString());
	}
	
}
