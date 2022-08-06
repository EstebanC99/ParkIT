package logic.Vehiculos;

import java.util.LinkedList;

import data.TipoVehiculoRepository;
import entities.Vehiculos.TipoVehiculo;
import logic.LogicBase;

public class AdministrarTipoVehiculoLogic extends LogicBase<TipoVehiculo, TipoVehiculoRepository> {
	
	public AdministrarTipoVehiculoLogic() {
		
		this.Repository = TipoVehiculoRepository.getInstancia();
	}
	
	public LinkedList<TipoVehiculo> getAll(){
		
		return this.Repository.getAll();
	}
	
	public TipoVehiculo getByID(TipoVehiculo tipoVehiculo) {
		
		return this.Repository.getByID(tipoVehiculo);
	}
	
}
