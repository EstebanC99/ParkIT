package logic.Personas;

import data.ClienteRepository;
import entities.Personas.Cliente;
import exceptions.ValidationException;
import logic.Logic;

public class ClienteLogic extends Logic<Cliente, ClienteRepository> {

	private static ClienteLogic Instancia;
	
	public static ClienteLogic getInstancia() {
		if (Instancia == null) {
			Instancia = new ClienteLogic();
		}
		
		return Instancia;
	}
	
	public ClienteLogic() {
		this.Repository = ClienteRepository.getInstancia();
	}
	
	@Override
	protected void validateAdd(Cliente myEntity) throws ValidationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateDelete(Cliente myEntity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateUpdate(Cliente myEntity) {
		// TODO Auto-generated method stub
		
	}

}
