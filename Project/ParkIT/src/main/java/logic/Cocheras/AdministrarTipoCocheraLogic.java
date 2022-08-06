package logic.Cocheras;

import java.util.LinkedList;

import data.TipoCocheraRepository;
import entities.Cocheras.TipoCochera;
import logic.LogicBase;

public class AdministrarTipoCocheraLogic extends LogicBase<TipoCochera, TipoCocheraRepository> {

	public AdministrarTipoCocheraLogic() {
		
		this.Repository = TipoCocheraRepository.getInstancia();
	}
	
	public LinkedList<TipoCochera> getAll(){
		
		return this.Repository.getAll();
	}
	
	public TipoCochera getByID(TipoCochera tipoCochera) {
		
		return this.Repository.getByID(tipoCochera);
	}
}
