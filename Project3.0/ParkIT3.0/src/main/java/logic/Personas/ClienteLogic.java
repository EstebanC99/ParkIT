package logic.Personas;

import data.ClienteRepository;
import entities.Personas.Cliente;
import entities.Usuarios.TipoUsuario;
import exceptions.ValidationException;
import logic.Logic;
import logic.Usuarios.UsuarioLogic;

public class ClienteLogic extends Logic<Cliente, ClienteRepository> {

	private static ClienteLogic Instancia;
	private UsuarioLogic UsuarioLogicService;
	
	public static ClienteLogic getInstancia() {
		if (Instancia == null) {
			Instancia = new ClienteLogic();
		}
		
		return Instancia;
	}
	
	public ClienteLogic() {
		this.Repository = ClienteRepository.getInstancia();
		this.UsuarioLogicService = UsuarioLogic.getInstancia();
	}
	
	@Override
	public void add(Cliente cliente) throws ValidationException {
		cliente.getUser().setTipoUsuario(TipoUsuario.Cliente);
		this.UsuarioLogicService.canAddOrUpdate(cliente.getUser());
		this.validateAdd(cliente);
		
		this.UsuarioLogicService.add(cliente.getUser());
		this.Repository.add(cliente);
	}
	
	@Override
	public void update(Cliente cliente) throws ValidationException {
		cliente.getUser().setTipoUsuario(TipoUsuario.Cliente);
		this.UsuarioLogicService.canAddOrUpdate(cliente.getUser());
		this.validateUpdate(cliente);
		
		this.UsuarioLogicService.update(cliente.getUser());
		this.Repository.update(cliente);
	}
	
	@Override
	protected void validateAdd(Cliente cliente) throws ValidationException {
		
	}

	@Override
	protected void validateDelete(Cliente cliente) throws ValidationException {
		this.UsuarioLogicService.remove(cliente.getUser());
	}

	@Override
	protected void validateUpdate(Cliente cliente) throws ValidationException {
		
	}

}
