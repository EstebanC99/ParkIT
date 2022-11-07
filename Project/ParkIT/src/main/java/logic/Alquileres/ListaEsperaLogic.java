package logic.Alquileres;

import data.ListaEsperaRepository;
import entities.Alquileres.ListaEspera;
import exceptions.ValidationException;
import logic.Logic;

public class ListaEsperaLogic extends Logic<ListaEspera, ListaEsperaRepository> {

	public ListaEsperaLogic() {
		this.Repository = ListaEsperaRepository.getInstancia();
	}
	
	private static ListaEsperaLogic Instancia;
	
	public static ListaEsperaLogic getInstancia() {
		if (Instancia == null) {
			Instancia = new ListaEsperaLogic();
		}
		
		return Instancia;
	}
	
	@Override
	protected void validateAdd(ListaEspera myEntity) throws ValidationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateDelete(ListaEspera myEntity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateUpdate(ListaEspera myEntity) {
		// TODO Auto-generated method stub
		
	}

	
}
