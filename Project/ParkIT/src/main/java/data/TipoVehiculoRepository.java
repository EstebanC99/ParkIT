package data;

import entities.Vehiculos.*;

public class TipoVehiculoRepository extends BasicRepository<TipoVehiculo>{
	
	private static TipoVehiculoRepository instancia;
	 
	@Override
	protected TipoVehiculo getNewEntity() {
		return new TipoVehiculo();
	}
	
	public static TipoVehiculoRepository getInstancia() {
		if (instancia == null) {
			instancia = new TipoVehiculoRepository();
		}
		
		return instancia;
	}
	
	protected TipoVehiculo getEntity() {
		if (myEntity == null) {
			myEntity = new TipoVehiculo();
		}
		
		return myEntity;
	}

}
