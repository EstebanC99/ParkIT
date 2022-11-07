package logic.Servicios;

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
	protected void validateAdd(ServicioVehiculo myEntity) throws ValidationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateDelete(ServicioVehiculo myEntity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateUpdate(ServicioVehiculo myEntity) {
		// TODO Auto-generated method stub
		
	}

	
}
