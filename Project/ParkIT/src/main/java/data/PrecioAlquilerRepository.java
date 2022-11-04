package data;

import java.sql.*;
import java.util.LinkedList;

import entities.Alquileres.PrecioAlquiler;
import entities.Alquileres.TipoAlquiler;
import entities.Cocheras.TipoCochera;

public class PrecioAlquilerRepository extends Repository<PrecioAlquiler> {

	private String BASE_QUERY = "SELECT * FROM 1 INNER JOIN 2 ON 2.3 = 1.3 INNER JOIN 4 ON 4.5 = 1.5";
	
	private LinkedList<String> PrepareBaseQuery(PrecioAlquiler precioAlquiler) {
		LinkedList<String> values = new LinkedList<>();
		
		values.add("t_".concat(precioAlquiler.getClass().getSimpleName().toLowerCase()));
		values.add("t_".concat(precioAlquiler.getTipoCochera().getClass().getSimpleName().toLowerCase()));
		values.add("ID_".concat(precioAlquiler.getTipoCochera().getClass().getSimpleName().toLowerCase()));
		values.add("t_".concat(precioAlquiler.getTipoAlquiler().getClass().getSimpleName().toLowerCase()));
		values.add("ID_".concat(precioAlquiler.getTipoAlquiler().getClass().getSimpleName().toLowerCase()));
		values.add("ID_".concat(precioAlquiler.getClass().getSimpleName().toLowerCase()));
		
		return values;
	}
	
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
			this.closeConnection(rs, stmt);
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
			this.closeConnection(rs, stmt);
		}
		
		return lista;
	}

	@Override
	public void add(PrecioAlquiler entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(PrecioAlquiler entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(PrecioAlquiler entity) {
		// TODO Auto-generated method stub
		
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
