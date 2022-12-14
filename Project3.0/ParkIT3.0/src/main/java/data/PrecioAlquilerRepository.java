package data;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;

import entities.Alquileres.PrecioAlquiler;
import entities.Alquileres.TipoAlquiler;
import entities.Cocheras.TipoCochera;
import logs.Log;

public class PrecioAlquilerRepository extends Repository<PrecioAlquiler> {

	private String BASE_QUERY = "SELECT * FROM 1 INNER JOIN 2 ON 2.3 = 1.3 INNER JOIN 4 ON 4.5 = 1.5";
	
	@Override
	protected String getBaseQuery() {
		return this.BASE_QUERY;
	}
	
	private String SELECT_BY_ID_QUERY = String.join(" ", this.BASE_QUERY, "WHERE 6=?");
	
	@Override
	protected String getSelectByIDQuery() {
		return this.SELECT_BY_ID_QUERY;
	}
	
	private static PrecioAlquilerRepository instancia;
	
	public static PrecioAlquilerRepository getInstancia() {
		if (instancia == null) {
			instancia = new PrecioAlquilerRepository();
		}
		
		return instancia;
	}
	
	@Override
	protected PrecioAlquiler getNewEntity() {
		return new PrecioAlquiler();
	}
	
	@Override
	protected LinkedList<String> PrepareBaseQuery(PrecioAlquiler precioAlquiler) {
		LinkedList<String> values = new LinkedList<String>();
		
		values.add(this.getTableName(precioAlquiler));
		values.add(this.getTableName(precioAlquiler.getTipoCochera()));
		values.add(this.getIDName(precioAlquiler.getTipoCochera()));
		values.add(this.getTableName(precioAlquiler.getTipoAlquiler()));
		values.add(this.getIDName(precioAlquiler.getTipoAlquiler()));
		values.add(this.getIDName(precioAlquiler));
		
		return values;
	}

	@Override
	public void add(PrecioAlquiler precioAlquiler) {
		String query = "INSERT INTO 1 (Precio, FechaVigencia, ID_TipoCochera, ID_TipoAlquiler) VALUES (?, ?, ?, ?)";
		LinkedList<String> values = new LinkedList<String>();
		values.add(this.getTableName(precioAlquiler));
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement(this.getQuery(query, values), PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setDouble(1, precioAlquiler.getPrecio());
			stmt.setString(2, precioAlquiler.getFechaVigencia().toString());
			stmt.setInt(3, precioAlquiler.getTipoCochera().getID());
			stmt.setInt(4, precioAlquiler.getTipoAlquiler().getID());
			
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				precioAlquiler.setID(rs.getInt(1));
			}
			
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			Log.registrarSevereLog(ex);
		}
		finally {
			this.closeConnection(stmt, rs);
		}
	}

	@Override
	public void update(PrecioAlquiler precioAlquiler) {
		String query = "UPDATE 1 SET Precio=?, FechaVigencia=? WHERE 2=?";
		LinkedList<String> values = new LinkedList<String>();
		
		values.add(this.getTableName(precioAlquiler));
		values.add(this.getIDName(precioAlquiler));
		
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement(this.getQuery(query, values));
			stmt.setDouble(1, precioAlquiler.getPrecio());
			stmt.setString(2, precioAlquiler.getFechaVigencia().toString());
			stmt.setInt(3, precioAlquiler.getID());
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
	
	@Override
	protected void mapResult(ResultSet rs, PrecioAlquiler precioAlquiler) throws SQLException{
		try {
			TipoAlquiler tipoAlquiler = new TipoAlquiler();
			tipoAlquiler.setID(rs.getInt("ID_TipoAlquiler"));
			
			TipoCochera tipoCochera = new TipoCochera();
			tipoCochera.setID(rs.getInt("ID_TipoCochera"));
			
			precioAlquiler.setID(rs.getInt("ID_PrecioAlquiler"));
			precioAlquiler.setPrecio(rs.getDouble("Precio"));
			precioAlquiler.setFechaVigencia(rs.getDate("FechaVigencia").toLocalDate());
			precioAlquiler.setTipoAlquiler(TipoAlquilerRepository.getInstancia().getByID(tipoAlquiler));
			precioAlquiler.setTipoCochera(TipoCocheraRepository.getInstancia().getByID(tipoCochera));
		}
		catch (SQLException ex) {
			Log.registrarSevereLog(ex);
			throw ex;
		}
	}
	
	public PrecioAlquiler findByDate(PrecioAlquiler precioAlquiler) {
		String query = "SELECT * FROM 1 INNER JOIN 2 ON 2.3 = 1.3 INNER JOIN 4 ON 4.5 = 1.5 WHERE 1.6!=? AND 1.7=? AND 1.8=? AND 1.FechaVigencia LIKE ?";
		LinkedList<String> values = this.PrepareBaseQuery(precioAlquiler);
		values.add( "ID_".concat(precioAlquiler.getTipoCochera().getClass().getSimpleName().toLowerCase()));
		values.add("ID_".concat(precioAlquiler.getTipoAlquiler().getClass().getSimpleName().toLowerCase()));
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		PrecioAlquiler e= new PrecioAlquiler();
		
		try {
			stmt= DbConnector.getInstancia().getConn().prepareStatement(
					this.getQuery(query, values));
			stmt.setInt(1, precioAlquiler.getID());
			stmt.setInt(2, precioAlquiler.getTipoCochera().getID());
			stmt.setInt(3, precioAlquiler.getTipoAlquiler().getID());
			stmt.setString(4, precioAlquiler.getFechaVigencia().toString());
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				this.mapResult(rs, e);
				break;
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			Log.registrarSevereLog(ex);
		} finally {
			this.closeConnection(stmt, rs);
		}
		
		return e;
	}
	
	public PrecioAlquiler getPrecioVigente(PrecioAlquiler precioAlquiler) {
		String filterQuery = "CALL sp_getCurrentPrice (?, ?, ?)";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(filterQuery);
			stmt.setInt(1, precioAlquiler.getTipoAlquiler().getID());
			stmt.setInt(2, precioAlquiler.getTipoCochera().getID());
			stmt.setString(3, LocalDate.now().toString());

			rs = stmt.executeQuery();
			
			if (rs == null ) return precioAlquiler;
			
			while (rs.next()) {
				this.mapResult(rs, precioAlquiler);
			}
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		finally
		{
			this.closeConnection(stmt, rs);
		}
		
		return precioAlquiler;	
	}
	
}
