package data;

import java.sql.*;
import java.util.LinkedList;

import entities.BaseEntity;

public abstract class BasicRepository<TEntity extends BaseEntity> {
	
	protected TEntity myEntity;
	
	protected abstract TEntity getEntity();
	
	protected BasicRepository() {
		
	}
	
	public final LinkedList<TEntity> getAll(){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		LinkedList<TEntity> e= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT * FROM ?");
			stmt.setString(1, ("t_" + myEntity.getClass().toString().toLowerCase()));
			rs = stmt.executeQuery();
					
			e = this.mapResults(rs);
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		return e;
	}
	
	public final TEntity getByID(TEntity myEntity) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		TEntity e= null;
		
		try {
			stmt= DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT * FROM ? WHERE ?=?");
			stmt.setString(1, ("t_" + myEntity.getClass().toString().toLowerCase()));
			stmt.setString(2, ("ID_" + myEntity.getClass().toString().toLowerCase()));
			stmt.setInt(3, (myEntity.getID()));
			rs = stmt.executeQuery();
					
			e = this.mapResults(rs).getFirst();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		return e;
	}
	
	protected LinkedList<TEntity> mapResults(ResultSet rs) throws SQLException{
		LinkedList<TEntity> entities = new LinkedList<>();
		
		try {
			if (rs != null) {
				while(rs.next()) {
					TEntity entity = myEntity;
					
					entity.setID(rs.getInt("ID_TipoVehiculo"));
					entity.setDescripcion(rs.getString("Descripcion"));
					
					entities.add(entity);
				}
			}
		}
		catch (SQLException ex) {
			throw ex;
		}
		
		return entities;
	};
}
