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
	protected void validateAdd(Cochera cochera) throws ValidationException {
		this.validateRequiredFields(cochera);
		this.validateExistingNumber(cochera);
	}

	@Override
	protected void validateDelete(Cochera cochera) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateUpdate(Cochera cochera) throws ValidationException {
		this.validateRequiredFields(cochera);
		this.validateExistingNumber(cochera);
	}

	private void validateRequiredFields(Cochera cochera) throws ValidationException {
		if (cochera.getNroCochera() <= 0)
			throw new ValidationException("El Nro de Cochera es requerido");
		
		if (cochera.getUbicacion() == null || cochera.getUbicacion() == "")
			throw new ValidationException("La Ubicación es requerida");
		
		if (cochera.getTipoCochera() == null)
			throw new ValidationException("El Tipo de Cochera es requerido");
	}
	
	private void validateExistingNumber(Cochera cochera) throws ValidationException{
		Cochera cocheraExistente = this.Repository.findByNumber(cochera);
		
		if (cocheraExistente.getID() == 0)
			return;
		
		if (cocheraExistente != null && cocheraExistente.getID() != cochera.getID())
			throw new ValidationException("El Nro de Cochera ya se encuentra registrado");
	}
}
