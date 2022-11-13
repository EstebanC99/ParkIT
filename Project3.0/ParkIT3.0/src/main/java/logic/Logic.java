package logic;

import java.util.LinkedList;

import data.Repository;
import entities.BaseEntity;
import exceptions.ValidationException;

public abstract class Logic <TEntity extends BaseEntity, TRepository extends Repository<TEntity>>{

	protected TRepository Repository;
	
	public LinkedList<TEntity> getAll(){
		return this.Repository.getAll();
	}
	
	public TEntity getByID(TEntity myEntity) {
		return this.Repository.getByID(myEntity);
	}
	
	public void add(TEntity myEntity) throws ValidationException {
		this.validateAdd(myEntity);
		
		this.Repository.add(myEntity);
	}
	
	public void remove(TEntity myEntity) throws ValidationException {
		if (myEntity == null)
			return;
		
		this.validateDelete(myEntity);
		
		this.Repository.remove(myEntity);
	}
	
	public void update(TEntity myEntity) throws ValidationException {
		if (myEntity == null)
			return;
		
		this.validateUpdate(myEntity);
		
		this.Repository.update(myEntity);
	}
	
	protected abstract void validateAdd(TEntity myEntity) throws ValidationException;
	
	protected abstract void validateDelete(TEntity myEntity) throws ValidationException;
	
	protected abstract void validateUpdate(TEntity myEntity) throws ValidationException;
}
