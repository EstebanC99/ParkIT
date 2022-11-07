package logic.Cocheras;

import data.CocheraRepository;
import entities.Cocheras.Cochera;
import exceptions.ValidationException;
import logic.Logic;

public class CocheraLogic extends Logic<Cochera, CocheraRepository> {
	
	private static CocheraLogic Instancia;
	
	public static CocheraLogic getInstancia() {
		if (Instancia == null) {
			Instancia = new CocheraLogic();
		}
		
		return Instancia;
	}
	
	public CocheraLogic() {
		this.Repository = CocheraRepository.getInstancia();
	}
	
	@Override
	protected void validateAdd(Cochera myEntity) throws ValidationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateDelete(Cochera myEntity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateUpdate(Cochera myEntity) {
		// TODO Auto-generated method stub
		
	}

}
