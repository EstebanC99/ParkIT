package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.Cocheras.Cochera;
import entities.Cocheras.TipoCochera;
import logs.Log;

public class CocheraRepository extends Repository<Cochera>{

	private String BASE_QUERY = "SELECT * FROM 1 INNER JOIN 2 ON 2.3 = 1.3";
	
	@Override
	protected String getBaseQuery() {
		return String.join(" ",this.BASE_QUERY, "ORDER BY NroCochera");
	}
	
	private String SELECT_BY_ID_QUERY = String.join(" ", this.BASE_QUERY, "WHERE 4=?");
	
	@Override
	protected String getSelectByIDQuery() {
		return this.SELECT_BY_ID_QUERY;
	}
	
	private static CocheraRepository Instancia;
	
	public static CocheraRepository getInstancia() {
		if (Instancia == null) {
			Instancia = new CocheraRepository();
		}
		
		return Instancia;
	}
	
	@Override
	protected Cochera getNewEntity() {
		return new Cochera();
	}
	
	@Override
	protected LinkedList<String> PrepareBaseQuery(Cochera cochera) {
		LinkedList<String> values = new LinkedList<>();
		
		values.add(this.getTableName(cochera));
		values.add(this.getTableName(cochera.getTipoCochera()));
		values.add(this.getIDName(cochera.getTipoCochera()));
		values.add(this.getIDName(cochera));
		
		return values;
	}
	

	@Override
	public void add(Cochera cochera) {
		String query = "INSERT INTO 1 (NroCochera, Disponible, Ubicacion, ID_TipoCochera) VALUES (?, ?, ?, ?)";
		LinkedList<String> values = new LinkedList<>();
		values.add(this.getTableName(cochera));
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement(this.getQuery(query, values), PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setDouble(1, cochera.getNroCochera());
			stmt.setBoolean(2, cochera.isDisponible());
			stmt.setString(3, cochera.getUbicacion());
			stmt.setInt(4, cochera.getTipoCochera().getID());
			
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				cochera.setID(rs.getInt(1));
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
	public void update(Cochera cochera) {
		String query = "UPDATE 1 SET NroCochera=?, Disponible=?, Ubicacion=?, ID_TipoCochera=? WHERE 2=?";
		LinkedList<String> values = new LinkedList<>();
		
		values.add(this.getTableName(cochera));
		values.add(this.getIDName(cochera));
		
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement(this.getQuery(query, values));
			stmt.setDouble(1, cochera.getNroCochera());
			stmt.setBoolean(2, cochera.isDisponible());
			stmt.setString(3, cochera.getUbicacion());
			stmt.setInt(4, cochera.getTipoCochera().getID());
			stmt.setInt(5, cochera.getID());
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
	protected void mapResult(ResultSet rs, Cochera cochera) throws SQLException {
		try {
			TipoCochera tipoCochera = new TipoCochera();
			tipoCochera.setID(rs.getInt("ID_TipoCochera"));
			
			cochera.setID(rs.getInt("ID_Cochera"));
			cochera.setNroCochera(rs.getInt("NroCochera"));
			cochera.setDisponible(rs.getBoolean("Disponible"));
			cochera.setUbicacion(rs.getString("Ubicacion"));
			cochera.setTipoCochera(TipoCocheraRepository.getInstancia().getByID(tipoCochera));
		}
		catch (SQLException ex) {
			Log.registrarSevereLog(ex);
			throw ex;
		}
	}
	
	public Cochera findByNumber(Cochera cochera) {
		String query = "SELECT * FROM t_Cochera WHERE NroCochera=? AND ID_Cochera!=?";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		Cochera c = new Cochera();
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement(
							query,
							PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, cochera.getNroCochera());
			stmt.setInt(2, cochera.getID());
			rs = stmt.executeQuery();
			
			if (rs == null) return null;
			
			while (rs.next()) {
				this.mapResult(rs, c);
				break;
			}
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			Log.registrarSevereLog(ex);
		}
		finally {
			this.closeConnection(stmt, rs);
		}
		
		return c;
	}
	
	public LinkedList<Cochera> getCocherasLibres() {
		String query = "CALL sp_getCocherasLibres";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		LinkedList<Cochera> lista = new LinkedList<>();
		
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(query);
			rs = stmt.executeQuery();
			
			if (rs == null ) return lista;
			
			while (rs.next()) {
				Cochera cochera = this.getNewEntity();
				this.mapResult(rs, cochera);
				lista.add(cochera);
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
}
