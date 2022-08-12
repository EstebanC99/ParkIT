package data;

import entities.Cocheras.TipoCochera;

public class TipoCocheraRepository extends BasicRepository<TipoCochera>{

	private static TipoCocheraRepository instancia;
	
	@Override
	protected TipoCochera getNewEntity() {
		return new TipoCochera();
	}
	
	public static TipoCocheraRepository getInstancia() {
		if (instancia == null) {
			instancia = new TipoCocheraRepository();
		}
		
		return instancia;
	}
	
	protected TipoCochera getEntity() {
		if (myEntity == null) {
			myEntity = new TipoCochera();
		}
		
		return myEntity;
	}
}
