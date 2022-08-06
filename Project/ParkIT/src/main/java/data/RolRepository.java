package data;

import entities.Usuarios.Rol;

public class RolRepository extends BasicRepository<Rol> {
	
	private static RolRepository instancia;
	
	public static RolRepository getInstancia() {
		if (instancia == null) {
			instancia = new RolRepository();
		}
		
		return instancia;
	}
	
	protected Rol getEntity() {
		if (myEntity == null) {
			myEntity = new Rol();
		}
		
		return myEntity;
	}
}
