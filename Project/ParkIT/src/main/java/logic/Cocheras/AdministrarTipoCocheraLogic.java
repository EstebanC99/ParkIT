package logic.Cocheras;

import data.TipoCocheraRepository;
import entities.Cocheras.TipoCochera;
import logic.LogicBase;

public class AdministrarTipoCocheraLogic extends LogicBase<TipoCochera, TipoCocheraRepository> {

	private static AdministrarTipoCocheraLogic instancia;
	
	public static AdministrarTipoCocheraLogic getIntancia() {
		if (instancia == null) {
			instancia = new AdministrarTipoCocheraLogic();
		}
		
		return instancia;
	}
	
	public AdministrarTipoCocheraLogic() {
		
		this.Repository = TipoCocheraRepository.getInstancia();
	}
	
}
