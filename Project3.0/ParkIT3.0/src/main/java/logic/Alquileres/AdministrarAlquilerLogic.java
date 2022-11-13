package logic.Alquileres;

import java.util.LinkedList;

import data.AlquilerRepository;
import dto.Filtros.FiltroAlquileres;
import entities.Alquileres.Alquiler;
import exceptions.ValidationException;
import global.DateFormatter;
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
	public void add(Alquiler alquiler) throws ValidationException {
		this.validateAdd(alquiler);
		
		alquiler.setPrecio(PrecioAlquilerLogic.getInstancia().obtenerPrecioVigente(alquiler.getTipoAlquiler(), alquiler.getCochera().getTipoCochera()));
		
		this.Repository.add(alquiler);
	}
	
	@Override
	protected void validateAdd(Alquiler alquiler) throws ValidationException {
		this.validarCamposRequeridos(alquiler);
		this.validarAlquilerPorVehiculo(alquiler);
	}

	@Override
	protected void validateDelete(Alquiler alquiler) throws ValidationException {
		
		
	}

	@Override
	protected void validateUpdate(Alquiler alquiler) throws ValidationException {
		this.validarCamposRequeridos(alquiler);
		this.validarAlquilerPorVehiculo(alquiler);
	}

	public LinkedList<Alquiler> searchByFilter(FiltroAlquileres filtro){
		return this.Repository.searchByFilters(filtro);
	}
	
	public void pagar(Alquiler alquiler) throws ValidationException {
		if (alquiler.isPagado())
			throw new ValidationException("El alquiler ya fue pagado");
		
		if (alquiler.getPrecio() == 0)
			throw new ValidationException("El precio es inválido");
		
		this.Repository.guardarPago(alquiler);
	}
	
	public LinkedList<Alquiler> getAlquileresVigentes(){
		return this.Repository.getAlquileresVigentes();
	}
	
	public LinkedList<Alquiler> getAlquileresUsuario(int id){
		return this.Repository.getAlquileresUsuario(id);
	}
	
	public int getCantidadAlquileresVigentes() {
		return this.getAlquileresVigentes().size();
	}
	
	public int getCantidadAlquileresImpagos() {
		int cantidadImpagos = 0;
		
		for (Alquiler alquiler : this.getAlquileresVigentes()) {
			if (alquiler.estaVencido())
				cantidadImpagos++;
		}
		
		return cantidadImpagos;
	}
	
	private void validarAlquilerPorVehiculo(Alquiler alquiler) throws ValidationException {
		Alquiler alquilerExistente = this.Repository.getAlquilerPorVehiculo(alquiler.getVehiculo());
		
		if (alquilerExistente == null) return;
		
		if (alquilerExistente.getID() != alquiler.getID())
			throw new ValidationException("Ya existe un alquiler registrado para el vehiculo " + alquiler.getVehiculo().getPatente() +
					". En la fecha " + DateFormatter.getFormattedDate(alquiler.getFechaInicio()));
	}
	
	private void validarCamposRequeridos(Alquiler alquiler) throws ValidationException {
		if (alquiler.getEmpleado() == null || alquiler.getEmpleado().getID() == 0)
			throw new ValidationException("Debe seleccionar un empleado");
		
		if (alquiler.getTipoAlquiler() == null || alquiler.getTipoAlquiler().getID() == 0)
			throw new ValidationException("Debe seleccionar un tipo de alquiler");
		
		if (alquiler.getTiempoEstadia() == 0)
			throw new ValidationException("El tiempo de estadia no puede ser 0");
		
		if (alquiler.getVehiculo() == null || alquiler.getVehiculo().getID() == 0)
			throw new ValidationException("Debe seleccionar un vehiculo");
		
		if (alquiler.getCochera() == null || alquiler.getCochera().getID() == 0)
			throw new ValidationException("Debe seleccionar una cochera");
		
		if (alquiler.getFormaPago() == null || alquiler.getFormaPago().getID() == 0)
			throw new ValidationException("Debe seleccionar una forma de pago");
	}
}
