package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.BaseEntity;
import logs.Log;

import java.util.logging.Level;
import java.util.logging.Logger;


public abstract class Repository<TEntity extends BaseEntity> {
	    
	protected abstract String getBaseQuery();
	protected abstract String getSelectByIDQuery();
	
	protected abstract LinkedList<String> PrepareBaseQuery(TEntity entity);
	
	protected abstract TEntity getNewEntity();
	
	public TEntity getByID(TEntity entity) {
		String query = this.getSelectByIDQuery();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement(
							this.getQuery(query, this.PrepareBaseQuery(entity)),
							PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, entity.getID());
			rs = stmt.executeQuery();
			
			if (rs == null) return entity;
			
			while (rs.next()) {
				this.mapResult(rs, entity);
			}
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			Log.registrarSevereLog(ex);
		}
		finally {
			this.closeConnection(stmt, rs);
		}
		
		return entity;
	}
	
	public LinkedList<TEntity> getAll(){
		String query = this.getBaseQuery();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		LinkedList<TEntity> lista = new LinkedList<>();
		
		try {
			
			
			  stmt = DbConnector.getInstancia().getConn(). prepareStatement(
			  this.getQuery(query, this.PrepareBaseQuery(this.getNewEntity()))); rs =
			  stmt.executeQuery();
			  
			  if (rs == null ) return lista;
			  
			  while (rs.next()) { TEntity entity = this.getNewEntity(); this.mapResult(rs,
			  entity); lista.add(entity); }
			 
			 
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			Log.registrarSevereLog(ex);
		}
		finally
		{
			this.closeConnection(stmt, rs);
		}
		
		return lista;	
	}
	
	public abstract void add(TEntity entity);
	
	public abstract void update(TEntity entity);
	
	public void remove(TEntity entity) {
		String query = "DELETE FROM 1 WHERE 2=?";
		
		LinkedList<String> values = new LinkedList<>();
		values.add(this.getTableName(entity));
		values.add(this.getIDName(entity));
		
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement(this.getQuery(query, values));
			stmt.setInt(1, entity.getID());
			stmt.executeUpdate();
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			Log.registrarSevereLog(ex);
		}
		finally {
			this.closeConnection(stmt);
		}
	}
	
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
			Log.registrarSevereLog(ex);
		}
	}
	
	protected final void closeConnection(PreparedStatement stmt) {
		try {
			if(stmt!=null) {stmt.close();}
			DbConnector.getInstancia().releaseConn();	
		} 
		catch (SQLException ex) {
			ex.printStackTrace();
			Log.registrarSevereLog(ex);
		}
	}
	
	protected final <AnyEntity extends BaseEntity> String getTableName (AnyEntity entity) {
		return "t_".concat(entity.getClass().getSimpleName().toLowerCase());
	}
	
	protected final <AnyEntity extends BaseEntity> String getIDName(AnyEntity entity) {
		return "ID_".concat(entity.getClass().getSimpleName().toLowerCase());
	}
}
