package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.Alquileres.FormaPago;

public class FormaPagoRepository extends Repository<FormaPago>{
	
	private static FormaPagoRepository Instancia;
	
	public static FormaPagoRepository getInstancia() {
		if (Instancia == null) {
			Instancia = new FormaPagoRepository();
		}
		
		return Instancia;
	}
	
	private String BASE_QUERY = "SELECT * FROM 1";
	private String SELECT_BY_ID_QUERY = String.join(" ", this.BASE_QUERY, "WHERE 2=?");
	
	@Override
	protected String getBaseQuery() {
		return this.BASE_QUERY;
	}
	
	@Override
	protected String getSelectByIDQuery() {
		return this.SELECT_BY_ID_QUERY;
	}
	
	@Override
	protected LinkedList<String> PrepareBaseQuery(FormaPago formaPago) {
		LinkedList<String> values = new LinkedList<>();
		
		values.add(this.getTableName(formaPago));
		values.add(this.getIDName(formaPago));
		
		return values;
	}
	@Override
	protected FormaPago getNewEntity() {
		return new FormaPago();
	}
	@Override
	public void add(FormaPago formaPago) {
		String query = "INSERT INTO 1 (Descripcion, Descuento, Incremento) VALUES (?, ?, ?)";
		LinkedList<String> values = new LinkedList<>();
		values.add(this.getTableName(formaPago));
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement(this.getQuery(query, values), PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, formaPago.getDescripcion());
			stmt.setDouble(2, formaPago.getDescuento());
			stmt.setDouble(3, formaPago.getIncremento());
			
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				formaPago.setID(rs.getInt(1));
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
	public void update(FormaPago formaPago) {
		String query = "UPDATE 1 SET Descripcion=?, Descuento=?, Incremento=? WHERE 2=?";
		LinkedList<String> values = new LinkedList<>();
		
		values.add(this.getTableName(formaPago));
		values.add(this.getIDName(formaPago));
		
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement(this.getQuery(query, values));
			stmt.setString(1, formaPago.getDescripcion());
			stmt.setDouble(2, formaPago.getDescuento());
			stmt.setDouble(3, formaPago.getIncremento());
			stmt.setInt(4, formaPago.getID());
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
	protected void mapResult(ResultSet rs, FormaPago formaPago) throws SQLException {
		try {
			formaPago.setID(rs.getInt("ID_FormaPago"));
			formaPago.setDescripcion(rs.getString("Descripcion"));
			formaPago.setDescuento(rs.getDouble("Descuento"));
			formaPago.setIncremento(rs.getDouble("Incremento"));
		}
		catch (SQLException ex) {
			throw ex;
		}
		
	}

}