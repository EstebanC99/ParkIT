package logic.Usuarios;

import java.util.LinkedList;

import data.RolRepository;
import entities.Usuarios.Rol;
import logic.LogicBase;

public class RolLogic extends LogicBase<Rol, RolRepository>{

	public RolLogic() {
		
		this.Repository = RolRepository.getInstancia();
	}
	
	public LinkedList<Rol> getAll(){
		
		return this.Repository.getAll();
	}
	
	public Rol getByID(Rol rol) {
		
		return this.Repository.getByID(rol);
	}
}
