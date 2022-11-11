package logic.Servicios;

import data.ServicioRepository;
import entities.Servicios.Servicio;
import logic.LogicBase;

public class AdministrarServicioLogic extends LogicBase<Servicio, ServicioRepository> {
	
	private static AdministrarServicioLogic instancia;
	
	public static AdministrarServicioLogic getIntancia() {
		if (instancia == null) {
			instancia = new AdministrarServicioLogic();
		}
		
		return instancia;
	}
	
	public AdministrarServicioLogic() {
		
		this.Repository = ServicioRepository.getInstancia();
	}

	
}
