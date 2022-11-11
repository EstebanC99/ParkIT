package logic.Vehiculos;

import data.TipoVehiculoRepository;
import entities.Vehiculos.TipoVehiculo;
import logic.LogicBase;

public class AdministrarTipoVehiculoLogic extends LogicBase<TipoVehiculo, TipoVehiculoRepository> {
	
	private static AdministrarTipoVehiculoLogic instancia;
	
	public static AdministrarTipoVehiculoLogic getIntancia() {
		if (instancia == null) {
			instancia = new AdministrarTipoVehiculoLogic();
		}
		
		return instancia;
	}
	
	public AdministrarTipoVehiculoLogic() {
		
		this.Repository = TipoVehiculoRepository.getInstancia();
	}

	
}
