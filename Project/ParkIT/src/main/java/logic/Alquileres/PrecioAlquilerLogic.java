package logic.Alquileres;

import java.time.LocalDate;

import data.PrecioAlquilerRepository;
import logic.Logic;
import entities.Alquileres.PrecioAlquiler;
import exceptions.ValidationException;

public class PrecioAlquilerLogic extends Logic<PrecioAlquiler, PrecioAlquilerRepository> {
	private static PrecioAlquilerLogic Instancia;
	
	public static PrecioAlquilerLogic getInstancia() {
		if (Instancia == null) {
			Instancia = new PrecioAlquilerLogic();
		}
		
		return Instancia;
	}
	
	public PrecioAlquilerLogic() {
		this.Repository = PrecioAlquilerRepository.getInstancia();
	}

	@Override
	protected void validateAdd(PrecioAlquiler precioAlquiler) throws ValidationException {
		if (LocalDate.now().isAfter(precioAlquiler.getFechaVigencia())) {
			throw new ValidationException("Fecha de hoy mayor a la de vigencia");
		}
	}

	@Override
	protected void validateDelete(PrecioAlquiler precioAlquiler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateUpdate(PrecioAlquiler precioAlquiler) {
		// TODO Auto-generated method stub
		
	}
	
	
}
