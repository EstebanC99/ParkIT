package data;

import java.sql.*;
import java.util.LinkedList;

import entities.Alquileres.PrecioAlquiler;
import entities.Alquileres.TipoAlquiler;
import entities.Cocheras.TipoCochera;

public class PrecioAlquilerRepository extends Repository<PrecioAlquiler> {

	private String BASE_QUERY = "SELECT * FROM 1 INNER JOIN 2 ON 2.3 = 1.3 INNER JOIN 4 ON 4.5 = 1.5";
	
	private static PrecioAlquilerRepository instancia;
	
	private LinkedList<String> PrepareBaseQuery(PrecioAlquiler precioAlquiler) {
		LinkedList<String> values = new LinkedList<>();
		
		values.add(this.getTableName(precioAlquiler));
		values.add(this.getTableName(precioAlquiler.getTipoCochera()));
		values.add(this.getIDName(precioAlquiler.getTipoCochera()));
		values.add(this.getTableName(precioAlquiler.getTipoAlquiler()));
		values.add(this.getIDName(precioAlquiler.getTipoAlquiler()));
		values.add(this.getIDName(precioAlquiler));
		
		return values;
	}
	
	public static PrecioAlquilerRepository getInstancia() {
		if (instancia == null) {
			instancia = new PrecioAlquilerRepository();
		}
		
		return instancia;
	}
	
	@Override
	public PrecioAlquiler getByID(PrecioAlquiler precioAlquiler) {
		String query = String.join(" ", BASE_QUERY, "WHERE 6=?");
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement(
							this.getQuery(query, this.PrepareBaseQuery(precioAlquiler)),
							PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, precioAlquiler.getID());
			rs = stmt.executeQuery();
			
			if (rs == null) return precioAlquiler;
			
			while (rs.next()) {
				this.mapResult(rs, precioAlquiler);
			}
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		finally {
			this.closeConnection(stmt, rs);
		}
		
		return precioAlquiler;
	}
	
	@Override
	public LinkedList<PrecioAlquiler> getAll() {
		String query = BASE_QUERY;
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		LinkedList<PrecioAlquiler> lista = new LinkedList<>();
		
		try {
			stmt = DbConnector.getInstancia().getConn().
					prepareStatement(
							this.getQuery(query, this.PrepareBaseQuery(new PrecioAlquiler())));
			rs = stmt.executeQuery();
			
			if (rs == null ) return lista;
			
			while (rs.next()) {
				PrecioAlquiler precioAlquiler = new PrecioAlquiler();
				this.mapResult(rs, precioAlquiler);
				lista.add(precioAlquiler);
			}
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		finally
		{
			this.closeConnection(stmt, rs);
		}
		
		return lista;
	}

	@Override
	public void add(PrecioAlquiler precioAlquiler) {
		String query = "INSERT INTO 1 (Precio, FechaVigencia, ID_TipoCochera, ID_TipoAlquiler) VALUES (?, ?, ?, ?)";
		LinkedList<String> values = new LinkedList<>();
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
		}
		finally {
			this.closeConnection(stmt, rs);
		}
	}

	@Override
	public void update(PrecioAlquiler precioAlquiler) {
		String query = "UPDATE 1 SET Precio=?, FechaVigencia=? WHERE 2=?";
		LinkedList<String> values = new LinkedList<>();
		
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
		}
		finally {
			this.closeConnection(stmt);
		}
	}

	@Override
	public void remove(PrecioAlquiler precioAlquiler) {
		String query = "DELETE FROM 1 WHERE 2=?";
		LinkedList<String> values = new LinkedList<>();
		values.add(this.getTableName(precioAlquiler));
		values.add(this.getIDName(precioAlquiler));
		
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement(this.getQuery(query, values));
			stmt.setInt(1, precioAlquiler.getID());
			stmt.executeUpdate();
		}
		catch (SQLException ex) {
			ex.printStackTrace();
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
			throw ex;
		}
	}
	
}
