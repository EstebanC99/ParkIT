package logic.Alquileres;

import data.AlquilerRepository;
import entities.Alquileres.Alquiler;
import exceptions.ValidationException;
import logic.Logic;

public class AdministrarAlquilerLogic extends Logic<Alquiler, AlquilerRepository> {

	public AdministrarAlquilerLogic() {
		this.Repository = AlquilerRepository.getInstancia();
	}
	
	private static AdministrarAlquilerLogic Instancia;
	
	public static AdministrarAlquilerLogic getInstancia() {
		if (Instancia == null)
			Instancia = new AdministrarAlquilerLogic();
		
		return Instancia;
	}
	
	@Override
	protected void validateAdd(Alquiler alquiler) throws ValidationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateDelete(Alquiler alquiler) throws ValidationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateUpdate(Alquiler alquiler) throws ValidationException {
		// TODO Auto-generated method stub
		
	}

	
}
