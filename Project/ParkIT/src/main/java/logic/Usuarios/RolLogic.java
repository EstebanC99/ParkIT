package logic.Usuarios;

import data.RolRepository;
import entities.Usuarios.Rol;
import logic.LogicBase;

public class RolLogic extends LogicBase<Rol, RolRepository>{

	private static RolLogic instancia;
	
	public static RolLogic getIntancia() {
		if (instancia == null) {
			instancia = new RolLogic();
		}
		
		return instancia;
	}
	
	public RolLogic() {
		
		this.Repository = RolRepository.getInstancia();
	}
}
