package logic.Vehiculos;

import java.util.LinkedList;

import data.VehiculoRepository;
import entities.Vehiculos.Vehiculo;
import exceptions.ValidationException;
import logic.Logic;

public class VehiculoLogic extends Logic<Vehiculo, VehiculoRepository> {

	public VehiculoLogic() {
		this.Repository = VehiculoRepository.getInstancia();
	}
	
	private static VehiculoLogic Instancia;
	
	public static VehiculoLogic getInstancia() {
		if (Instancia == null) {
			Instancia = new VehiculoLogic();
		}
		
		return Instancia;
	}
	
	@Override
	protected void validateAdd(Vehiculo vehiculo) throws ValidationException {
		this.validateRequiredFields(vehiculo);
		this.validateExistingPatente(vehiculo);
	}

	@Override
	protected void validateDelete(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateUpdate(Vehiculo vehiculo) throws ValidationException {
		this.validateRequiredFields(vehiculo);
		this.validateExistingPatente(vehiculo);
	}

	private void validateRequiredFields(Vehiculo vehiculo) throws ValidationException {
		if (vehiculo.getPatente() == null || vehiculo.getPatente() == "")
			throw new ValidationException("La Patente es requerida");
		
		if (vehiculo.getModelo() == null || vehiculo.getMarca() == "")
			throw new ValidationException("El Modelo es requerido");
		
		if (vehiculo.getMarca() == null || vehiculo.getMarca() == "")
			throw new ValidationException("La Marca es requerida");
		
		if (vehiculo.getCliente() == null)
			throw new ValidationException("Debe elegir un Cliente");
		
		if (vehiculo.getTipoVehiculo() == null)
			throw new ValidationException("Debe elegir un Tipo de Vehiculo");
	}
	
	private void validateExistingPatente(Vehiculo vehiculo) throws ValidationException {
		Vehiculo vehiculoExistente = this.Repository.findByPatente(vehiculo);
		
		if (vehiculoExistente != null && vehiculoExistente.getID() != vehiculo.getID() && vehiculoExistente.getID() != 0)
			throw new ValidationException("La Patente ya se encuentra registrada en otro Vehiculo");		
	}
	
	public LinkedList<Vehiculo> getVehiculosSinAlquiler(){
		return this.Repository.getVehiculosLibres();
	}
	
}
