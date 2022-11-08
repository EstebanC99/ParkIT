package logic.Alquileres;

import data.FormaPagoRepository;
import data.ServicioVehiculoRepository;
import entities.Alquileres.FormaPago;
import exceptions.ValidationException;
import logic.Logic;
import logic.Servicios.ServicioVehiculoLogic;

public class FormaPagoLogic extends Logic<FormaPago, FormaPagoRepository> {
	
	public FormaPagoLogic() {
		this.Repository = FormaPagoRepository.getInstancia();
	}
	
	private static FormaPagoLogic Instancia;
	
	public static FormaPagoLogic getInstancia() {
		if (Instancia == null) {
			Instancia = new FormaPagoLogic();
		}
		
		return Instancia;
	}

	@Override
	protected void validateAdd(FormaPago formaPago) throws ValidationException {
		if(formaPago.getDescuento()<0) {
			throw new ValidationException("El descuento debe ser un valor mayor a 0");
		}
		if(formaPago.getIncremento()<0) {
			throw new ValidationException("El descuento debe ser un valor mayor a 0");
		}
		for (int i = 0; i < formaPago.getDescripcion().length(); i++)
		{
			char caracter = formaPago.getDescripcion().toUpperCase().charAt(i);
			int valorASCII = (int)caracter;
			if (valorASCII < 65 || valorASCII > 90)
				throw new ValidationException("Solo se aceptan letras en la descripción");
		}
		
	}

	@Override
	protected void validateDelete(FormaPago myEntity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateUpdate(FormaPago myEntity) {
		// TODO Auto-generated method stub
		
	}
	
	

}
