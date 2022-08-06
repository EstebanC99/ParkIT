package logic;

import data.BasicRepository;
import entities.BaseEntity;

public abstract class LogicBase<TEntity extends BaseEntity, TRepository extends BasicRepository<TEntity>> {

	protected TRepository Repository;
	
}
