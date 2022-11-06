package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.BaseEntity;

public abstract class Repository<TEntity extends BaseEntity> {
	
	public abstract TEntity getByID(TEntity entity);
	
	public abstract LinkedList<TEntity> getAll();
	
	public abstract void add(TEntity entity);
	
	public abstract void update(TEntity entity);
	
	public abstract void remove(TEntity entity);
	
	protected abstract void mapResult(ResultSet rs, TEntity entity) throws SQLException;
	
	protected String getQuery(String query, LinkedList<String> valores) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(query);
		
		for (Integer i = 1; i <= valores.size(); i++) {
			int pos = sql.indexOf(i.toString());
			while (pos != -1) {
				sql.replace(pos, pos + 1, valores.get(i - 1));
				pos = sql.indexOf(i.toString());
			}
		}
		
		return sql.toString();
	}
	
	protected final void closeConnection(PreparedStatement stmt, ResultSet rs) {
		try {
			if(rs!=null) {rs.close();}
			if(stmt!=null) {stmt.close();}
			DbConnector.getInstancia().releaseConn();	
		} 
		catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	protected final void closeConnection(PreparedStatement stmt) {
		try {
			if(stmt!=null) {stmt.close();}
			DbConnector.getInstancia().releaseConn();	
		} 
		catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	protected final <AnyEntity extends BaseEntity> String getTableName (AnyEntity entity) {
		return "t_".concat(entity.getClass().getSimpleName().toLowerCase());
	}
	
	protected final <AnyEntity extends BaseEntity> String getIDName(AnyEntity entity) {
		return "ID_".concat(entity.getClass().getSimpleName().toLowerCase());
	}
}
