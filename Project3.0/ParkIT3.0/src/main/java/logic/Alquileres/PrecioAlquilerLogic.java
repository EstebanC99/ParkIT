package logic.Alquileres;

import java.time.LocalDate;

import data.PrecioAlquilerRepository;
import logic.Logic;
import entities.Alquileres.PrecioAlquiler;
import entities.Alquileres.TipoAlquiler;
import entities.Cocheras.TipoCochera;
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
		this.validateDate(precioAlquiler);
		this.validateRequireds(precioAlquiler);
	}

	@Override
	protected void validateDelete(PrecioAlquiler precioAlquiler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateUpdate(PrecioAlquiler precioAlquiler) throws ValidationException {
		this.validateDate(precioAlquiler);
		this.validateRequireds(precioAlquiler);
	}
	
	private void validateDate(PrecioAlquiler precioAlquiler) throws ValidationException{
		if (LocalDate.now().isAfter(precioAlquiler.getFechaVigencia())) 
			throw new ValidationException("Fecha de hoy mayor a la de vigencia");
		
		PrecioAlquiler precioAlquilerVigente = this.Repository.findByDate(precioAlquiler);
		
		if (precioAlquilerVigente != null && precioAlquilerVigente.getID() != 0)
			throw new ValidationException("Ya existe un precio vigente para la fecha, cochera y alquiler seleccionados"); 
	}
	
	public double obtenerPrecioVigente(TipoAlquiler tipoAlquiler, TipoCochera tipoCochera) {
		PrecioAlquiler precioAlquiler = new PrecioAlquiler();
		precioAlquiler.setTipoAlquiler(tipoAlquiler);
		precioAlquiler.setTipoCochera(tipoCochera);
		
		this.Repository.getPrecioVigente(precioAlquiler);
		
		return precioAlquiler.getPrecio();
	}
	
	private void validateRequireds(PrecioAlquiler precioAlquiler) throws ValidationException {
		if (precioAlquiler.getTipoAlquiler().getID() == 0)
			throw new ValidationException("Debe seleccionar un tipo de alquiler");
		
		if (precioAlquiler.getTipoCochera().getID() == 0)
			throw new ValidationException("Debe seleccionar un tipo de cochera");
		
	}
}
