package logic.Personas;

import data.EmpleadoRepository;
import entities.Personas.Empleado;
import exceptions.ValidationException;
import logic.Logic;

public class EmpleadoLogic extends Logic<Empleado, EmpleadoRepository>{

	public EmpleadoLogic() {
		this.Repository = EmpleadoRepository.getInstancia();
	}
	
	private static EmpleadoLogic Instancia;
	
	public static EmpleadoLogic getInstancia() {
		if (Instancia == null) {
			Instancia = new EmpleadoLogic();
		}
		
		return Instancia;
	}

	@Override
	protected void validateAdd(Empleado myEntity) throws ValidationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateDelete(Empleado myEntity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateUpdate(Empleado myEntity) {
		// TODO Auto-generated method stub
		
	}
	
	
}
