package data;

import java.sql.*;
import java.util.LinkedList;

import entities.BaseEntity;
import logs.Log;

public abstract class BasicRepository<TEntity extends BaseEntity> {
	
	protected TEntity myEntity;
	
	protected abstract TEntity getNewEntity();
	
	protected BasicRepository() {
		this.myEntity = this.getNewEntity();
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
			Log.registrarSevereLog(ex);
			
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
			Log.registrarSevereLog(ex);
			
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException ex) {
				ex.printStackTrace();
				Log.registrarSevereLog(ex);
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
			Log.registrarSevereLog(e);
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
	
	public void save(TEntity myEntity) {
		String query = "UPDATE 1 SET Descripcion=? WHERE 2=?";
		LinkedList<String> values = new LinkedList<>();
		values.add( "t_".concat(myEntity.getClass().getSimpleName().toLowerCase()));
		values.add("ID_".concat(myEntity.getClass().getSimpleName().toLowerCase()));
		
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							this.getQuery(query, values)
							);
			stmt.setString(1, myEntity.getDescripcion());
			stmt.setInt(2, myEntity.getID());
			stmt.executeUpdate();

		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
    			Log.registrarSevereLog(e);
            }
		}
	}
	
	public void remove(TEntity myEntity) {
		String query = "DELETE FROM 1 WHERE 2=?";
		LinkedList<String> values = new LinkedList<>();
		values.add("t_".concat(myEntity.getClass().getSimpleName().toLowerCase()));
		values.add("ID_".concat(myEntity.getClass().getSimpleName().toLowerCase()));
		
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							this.getQuery(query, values)
							);
			stmt.setInt(1, myEntity.getID());
			stmt.executeUpdate();

		}  catch (SQLException e) {
            e.printStackTrace();
			Log.registrarSevereLog(e);
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
	
	public final TEntity getByDescription(TEntity myEntity) {
		String query = "SELECT * FROM 1 WHERE 2 LIKE ? AND 3!=?";
		LinkedList<String> values = new LinkedList<>();
		values.add( "t_".concat(myEntity.getClass().getSimpleName().toLowerCase()));
		values.add( "Descripcion");
		values.add("ID_".concat(myEntity.getClass().getSimpleName().toLowerCase()));

		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		TEntity e= null;
		
		try {
			stmt= DbConnector.getInstancia().getConn().prepareStatement(
					this.getQuery(query, values));
			stmt.setString(1, myEntity.getDescripcion());
			stmt.setInt(2, myEntity.getID());
			rs = stmt.executeQuery();
			
			LinkedList<TEntity> resultados = this.mapResults(rs);
			
			if (resultados.isEmpty()) {
				e = null;
			} else{
				e = resultados.getFirst();
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			Log.registrarSevereLog(ex);
			
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
	
	private String getQuery(String query, LinkedList<String> valores) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(query);
		
		for (Integer i = 1; i <= valores.size(); i++) {
			int pos = sql.indexOf(i.toString());
			sql.replace(pos, pos + 1, valores.get(i - 1));
		}
		
		return sql.toString();
	}
	
	protected LinkedList<TEntity> mapResults(ResultSet rs) throws SQLException{
		LinkedList<TEntity> entities = new LinkedList<>();
		
		try {
			if (rs != null) {
				while(rs.next()) {
					TEntity entity = this.getNewEntity();
					
					entity.setID(rs.getInt("ID_" + entity.getClass().getSimpleName().toLowerCase()));
					entity.setDescripcion(rs.getString("Descripcion"));
					
					entities.add(entity);
				}
			}
		}
		catch (SQLException ex) {
			Log.registrarSevereLog(ex);
			throw ex;
		}
		
		return entities;
	};
}
