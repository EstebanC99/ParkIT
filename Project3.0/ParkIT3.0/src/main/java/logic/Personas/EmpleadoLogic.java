package logic.Personas;

import data.EmpleadoRepository;
import entities.Personas.Empleado;
import entities.Usuarios.TipoUsuario;
import exceptions.ValidationException;
import logic.Logic;
import logic.Usuarios.UsuarioLogic;

public class EmpleadoLogic extends Logic<Empleado, EmpleadoRepository>{

	private static EmpleadoLogic Instancia;
	private UsuarioLogic UsuarioLogicService;
	
	public static EmpleadoLogic getInstancia() {
		if (Instancia == null) {
			Instancia = new EmpleadoLogic();
		}
		
		return Instancia;
	}
	
	public EmpleadoLogic() {
		this.Repository = EmpleadoRepository.getInstancia();
		this.UsuarioLogicService = UsuarioLogic.getInstancia();
	}
	
	@Override
	public void add(Empleado empleado) throws ValidationException {
		empleado.getUser().setTipoUsuario(TipoUsuario.Empleado);
		this.UsuarioLogicService.canAddOrUpdate(empleado.getUser());
		this.validateAdd(empleado);
		
		this.UsuarioLogicService.add(empleado.getUser());
		this.Repository.add(empleado);
	}
	
	@Override
	public void update(Empleado empleado) throws ValidationException {
		empleado.getUser().setTipoUsuario(TipoUsuario.Cliente);
		this.UsuarioLogicService.canAddOrUpdate(empleado.getUser());
		this.validateUpdate(empleado);
		
		this.UsuarioLogicService.update(empleado.getUser());
		this.Repository.update(empleado);
	}
	
	
	@Override
	protected void validateAdd(Empleado empleado) throws ValidationException {
		
	}

	@Override
	protected void validateDelete(Empleado empleado) throws ValidationException {
		this.UsuarioLogicService.remove(empleado.getUser());
	}

	@Override
	protected void validateUpdate(Empleado empleado) {
		
	}
	
	
}
