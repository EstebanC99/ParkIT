package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.Servicios.*;
import logs.Log;

public class ServicioRepository extends BasicRepository<Servicio>{
	
	private static ServicioRepository instancia;
	 
	@Override
	protected Servicio getNewEntity() {
		return new Servicio();
	}
	
	public static ServicioRepository getInstancia() {
		if (instancia == null) {
			instancia = new ServicioRepository();
		}
		
		return instancia;
	}
	
	protected Servicio getEntity() {
		if (myEntity == null) {
			myEntity = new Servicio();
		}
		
		return myEntity;
	}
	
	public void add(Servicio myEntity) {
		String tableName= "t_".concat(myEntity.getClass().getSimpleName().toLowerCase());
		String query = "INSERT INTO "+ tableName +" (Descripcion, Precio) values(?, ?)";		
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							query,
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, myEntity.getDescripcion());
			stmt.setFloat(2, myEntity.getPrecio());

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
	
	public void save(Servicio myEntity) {
		String tableName= "t_".concat(myEntity.getClass().getSimpleName().toLowerCase());
		String id= "ID_".concat(myEntity.getClass().getSimpleName().toLowerCase());

		
		String query = "UPDATE "+tableName+" SET Descripcion=?, Precio=? WHERE "+id+"=?";
		LinkedList<String> values = new LinkedList<>();
		values.add( "t_".concat(myEntity.getClass().getSimpleName().toLowerCase()));
		values.add("ID_".concat(myEntity.getClass().getSimpleName().toLowerCase()));
		
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(query);
			stmt.setString(1, myEntity.getDescripcion());
			stmt.setFloat(2, myEntity.getPrecio());
			stmt.setInt(3, myEntity.getID());
			
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
	
	protected LinkedList<Servicio> mapResults(ResultSet rs) throws SQLException{
		LinkedList<Servicio> entities = new LinkedList<Servicio>();
		
		try {
			if (rs != null) {
				while(rs.next()) {
					Servicio entity = this.getNewEntity();
					
					entity.setID(rs.getInt("ID_" + entity.getClass().getSimpleName().toLowerCase()));
					entity.setDescripcion(rs.getString("Descripcion"));
					entity.setPrecio(rs.getFloat("Precio"));
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
