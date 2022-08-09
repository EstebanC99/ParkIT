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
		String query = "SELECT * FROM 1";
		LinkedList<String> values = new LinkedList<>();
		values.add( "t_".concat(myEntity.getClass().getSimpleName().toLowerCase()));
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		LinkedList<TEntity> e= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().prepareStatement(
					this.getQuery(query, values));
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
		String query = "SELECT * FROM 1 WHERE 2 =?";
		LinkedList<String> values = new LinkedList<>();
		values.add( "t_".concat(myEntity.getClass().getSimpleName().toLowerCase()));
		values.add("ID_".concat(myEntity.getClass().getSimpleName().toLowerCase()));

		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		TEntity e= null;
		
		try {
			stmt= DbConnector.getInstancia().getConn().prepareStatement(
					this.getQuery(query, values));
			stmt.setInt(1, myEntity.getID());
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
	
	public void add(TEntity myEntity) {
		String query = "INSERT INTO 1 (Descripcion) values(?)";
		LinkedList<String> values = new LinkedList<>();
		values.add( "t_".concat(myEntity.getClass().getSimpleName().toLowerCase()));
		
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							this.getQuery(query, values),
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, myEntity.getDescripcion());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                myEntity.setID(keyResultSet.getInt(1));
            }

		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
	}
	
	private String getQuery(String query, LinkedList<String> valores) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(query);
		
		for (Integer i = 1; i <= valores.size(); i++) {
			int pos = query.indexOf(i.toString());
			sql.replace(pos, pos + 1, valores.get(i - 1));
		}
		
		return sql.toString();
	}
	
	protected LinkedList<TEntity> mapResults(ResultSet rs) throws SQLException{
		LinkedList<TEntity> entities = new LinkedList<>();
		
		try {
			if (rs != null) {
				while(rs.next()) {
					TEntity entity = myEntity;
					
					entity.setID(rs.getInt("ID_" + myEntity.getClass().getSimpleName().toLowerCase()));
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
