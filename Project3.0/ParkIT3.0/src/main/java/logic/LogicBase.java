package logic;

import java.util.LinkedList;

import data.BasicRepository;
import entities.BaseEntity;
import exceptions.ValidationException;

public abstract class LogicBase<TEntity extends BaseEntity, TRepository extends BasicRepository<TEntity>> {

	protected TRepository Repository;
	
	public LinkedList<TEntity> getAll(){
		return this.Repository.getAll();
	}
	
	public TEntity getByID(TEntity myEntity) {
		return this.Repository.getByID(myEntity);
	}
	
	public void add(TEntity myEntity) throws ValidationException{
		this.validateDescription(myEntity);
		
		this.Repository.add(myEntity);
	}
	
	public void remove(TEntity myEntity) {
		if (myEntity == null)
			return;
		
		this.Repository.remove(myEntity);
	}
	
	public void save(TEntity myEntity) throws ValidationException {
		if (myEntity == null)
			return;
		
		this.validateDescription(myEntity);
		
		this.Repository.save(myEntity);
	}
	
	public void validateDescription(TEntity myEntity) throws ValidationException{
		if (myEntity.getDescripcion() == "" || myEntity.getDescripcion() == null)
			throw new ValidationException("El campo Descripcion es requerido");
		
		if (this.Repository.getByDescription(myEntity) != null)
			throw new ValidationException("Ya existe un registro con la misma Descripcion");
	}
}
