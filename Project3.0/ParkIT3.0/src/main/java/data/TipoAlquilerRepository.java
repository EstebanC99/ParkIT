package data;

import entities.Alquileres.TipoAlquiler;

public class TipoAlquilerRepository extends BasicRepository<TipoAlquiler> {

	private static TipoAlquilerRepository instancia;
	
	@Override
	public TipoAlquiler getNewEntity() {
		return new TipoAlquiler();
	}
	
	public static TipoAlquilerRepository getInstancia() {
		if (instancia == null) {
			instancia = new TipoAlquilerRepository();
		}
		
		return instancia;
	}
	
	protected TipoAlquiler getEntity() {
		if (myEntity == null) {
			myEntity = new TipoAlquiler();
		}
		
		return myEntity;
	}

}
