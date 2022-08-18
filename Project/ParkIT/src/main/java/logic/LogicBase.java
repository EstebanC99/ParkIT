package logic;

import java.util.LinkedList;

import data.BasicRepository;
import entities.BaseEntity;

public abstract class LogicBase<TEntity extends BaseEntity, TRepository extends BasicRepository<TEntity>> {

	protected TRepository Repository;
	
	public LinkedList<TEntity> getAll(){
		return this.Repository.getAll();
	}
	
	public TEntity getByID(TEntity myEntity) {
		return this.Repository.getByID(myEntity);
	}
	
	public void add(TEntity myEntity) {
		this.Repository.add(myEntity);
	}
	
	public void remove(TEntity myEntity) {
		if (myEntity == null)
			return;
		
		this.Repository.remove(myEntity);
	}
	
	public void save(TEntity myEntity) {
		if (myEntity == null)
			return;
		
		this.Repository.save(myEntity);
	}
}
