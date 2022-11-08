package logic.Servicios;

import java.time.LocalDate;

import data.ServicioVehiculoRepository;
import entities.Servicios.ServicioVehiculo;
import exceptions.ValidationException;
import logic.Logic;

public class ServicioVehiculoLogic extends Logic<ServicioVehiculo, ServicioVehiculoRepository> {

	public ServicioVehiculoLogic() {
		this.Repository = ServicioVehiculoRepository.getInstancia();
	}
	
	private static ServicioVehiculoLogic Instancia;
	
	public static ServicioVehiculoLogic getInstancia() {
		if (Instancia == null) {
			Instancia = new ServicioVehiculoLogic();
		}
		
		return Instancia;
	}
	
	@Override
	protected void validateAdd(ServicioVehiculo servicioVehiculo) throws ValidationException {
		this.validateRequiredFields(servicioVehiculo);
		this.validateDate(servicioVehiculo);
		this.validateExistingService(servicioVehiculo);
		
	}

	@Override
	protected void validateDelete(ServicioVehiculo servicioVehiculo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateUpdate(ServicioVehiculo servicioVehiculo) throws ValidationException {
		this.validateRequiredFields(servicioVehiculo);
		this.validateDate(servicioVehiculo);
		this.validateExistingService(servicioVehiculo);
	}

	private void validateRequiredFields(ServicioVehiculo servicioVehiculo) throws ValidationException{
		if (servicioVehiculo.getFechaRealizacion() == null || servicioVehiculo.getFechaRealizacion() == LocalDate.MIN)
			throw new ValidationException("La Fecha del Servicio es requerida");
		
		if (servicioVehiculo.getEmpleado() == null)
			throw new ValidationException("El Empleado es requerido");
		
		if (servicioVehiculo.getVehiculo() == null)
			throw new ValidationException("El Vehiculo es requerido");
		
		if (servicioVehiculo.getServicio() == null)
			throw new ValidationException("El Servicio es requerido");
	}
	
	private void validateDate(ServicioVehiculo servicioVehiculo) throws ValidationException {
		if (servicioVehiculo.getFechaRealizacion().isBefore(LocalDate.now()))
			throw new ValidationException("No puede regitrar un servicio para una fecha anterior a la actual");
	}
	
	private void validateExistingService(ServicioVehiculo servicioVehiculo) throws ValidationException {
		if (this.Repository.getExistingService(servicioVehiculo) != null)
			throw new ValidationException("Ya existe un mismo servicio registrado para ese vehiculo en la fecha elegida");
	}
}
