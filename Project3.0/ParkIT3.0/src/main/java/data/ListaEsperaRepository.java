package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.Alquileres.ListaEspera;
import entities.Cocheras.TipoCochera;
import entities.Personas.Cliente;
import logs.Log;

public class ListaEsperaRepository extends Repository<ListaEspera> {

	private static ListaEsperaRepository Instancia;
	
	public static ListaEsperaRepository getInstancia() {
		if (Instancia == null) {
			Instancia = new ListaEsperaRepository();
		}
		
		return Instancia;
	}
	
	private String BASE_QUERY = "SELECT * FROM 1 INNER JOIN 2 ON 2.3 = 1.3 INNER JOIN 4 ON 4.5 = 1.5";
	private String SELECT_BY_ID_QUERY = String.join(" ", this.BASE_QUERY, "WHERE 6=?");
	
	@Override
	protected String getBaseQuery() {
		return this.BASE_QUERY;
	}

	@Override
	protected String getSelectByIDQuery() {
		return this.SELECT_BY_ID_QUERY;
	}

	@Override
	protected LinkedList<String> PrepareBaseQuery(ListaEspera listaEspera) {
		LinkedList<String> values = new LinkedList<String>();
		
		values.add(this.getTableName(listaEspera));
		values.add(this.getTableName(listaEspera.getTipoCochera()));
		values.add(this.getIDName(listaEspera.getTipoCochera()));
		values.add(this.getTableName(listaEspera.getCliente()));
		values.add(this.getIDName(listaEspera.getCliente()));
		values.add(this.getIDName(listaEspera));
		
		return values;
	}

	@Override
	protected ListaEspera getNewEntity() {
		return new ListaEspera();
	}

	@Override
	public void add(ListaEspera listaEspera) {
		String query = "INSERT INTO 1 (FechaIngreso, HoraIngreso, ID_TipoCochera, ID_Cliente) VALUES (?, ?, ?, ?)";
		LinkedList<String> values = new LinkedList<String>();
		values.add(this.getTableName(listaEspera));
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement(this.getQuery(query, values), PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, listaEspera.getFechaIngreso().toString());
			stmt.setString(2, listaEspera.getHoraIngreso().toString());
			stmt.setInt(3, listaEspera.getTipoCochera().getID());
			stmt.setInt(4, listaEspera.getCliente().getID());
			
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				listaEspera.setID(rs.getInt(1));
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
	public void update(ListaEspera listaEspera) {
		String query = "UPDATE 1 SET ID_TipoCochera=?, ID_Cliente=? WHERE 2=?";
		LinkedList<String> values = new LinkedList<String>();
		
		values.add(this.getTableName(listaEspera));
		values.add(this.getIDName(listaEspera));
		
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement(this.getQuery(query, values));
			stmt.setInt(3, listaEspera.getTipoCochera().getID());
			stmt.setInt(4, listaEspera.getCliente().getID());
			stmt.setInt(5, listaEspera.getID());
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
	protected void mapResult(ResultSet rs, ListaEspera listaEspera) throws SQLException {
		try {
			TipoCochera tipoCochera = new TipoCochera();
			tipoCochera.setID(rs.getInt("ID_TipoCochera"));
			
			Cliente cliente = new Cliente();
			cliente.setID(rs.getInt("ID_Cliente"));
			
			listaEspera.setID(rs.getInt("ID_ListaEspera"));
			listaEspera.setFechaIngreso(rs.getDate("FechaIngreso").toLocalDate());
			listaEspera.setHoraIngreso(rs.getTime("HoraIngreso").toLocalTime());
			listaEspera.setCliente(ClienteRepository.getInstancia().getByID(cliente));
			listaEspera.setTipoCochera(TipoCocheraRepository.getInstancia().getByID(tipoCochera));
		}
		catch (SQLException ex) {
			Log.registrarSevereLog(ex);
			throw ex;
		}
	}

}
