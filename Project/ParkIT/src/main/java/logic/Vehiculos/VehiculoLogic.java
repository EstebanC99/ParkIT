package logic.Vehiculos;

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
	protected void validateAdd(Vehiculo myEntity) throws ValidationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateDelete(Vehiculo myEntity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateUpdate(Vehiculo myEntity) {
		// TODO Auto-generated method stub
		
	}

	
}
