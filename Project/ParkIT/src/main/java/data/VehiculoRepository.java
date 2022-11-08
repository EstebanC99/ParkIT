package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.Personas.Cliente;
import entities.Vehiculos.TipoVehiculo;
import entities.Vehiculos.Vehiculo;

public class VehiculoRepository extends Repository<Vehiculo>{

	private static VehiculoRepository Instancia;
	
	public static VehiculoRepository getInstancia() {
		if (Instancia == null) {
			Instancia = new VehiculoRepository();
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
	protected LinkedList<String> PrepareBaseQuery(Vehiculo vehiculo) {
		LinkedList<String> values = new LinkedList<>();
		
		values.add(this.getTableName(vehiculo));
		values.add(this.getTableName(vehiculo.getCliente()));
		values.add(this.getIDName(vehiculo.getCliente()));
		values.add(this.getTableName(vehiculo.getTipoVehiculo()));
		values.add(this.getIDName(vehiculo.getTipoVehiculo()));
		values.add(this.getIDName(vehiculo));
		
		return values;
	}

	@Override
	protected Vehiculo getNewEntity() {
		return new Vehiculo();
	}

	@Override
	public void add(Vehiculo vehiculo) {
		String query = "INSERT INTO 1 (Patente, Modelo, Marca, ID_Cliente, ID_TipoVehiculo) VALUES (?, ?, ?, ?, ?)";
		LinkedList<String> values = new LinkedList<>();
		values.add(this.getTableName(vehiculo));
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement(this.getQuery(query, values), PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, vehiculo.getPatente());
			stmt.setString(2, vehiculo.getModelo());
			stmt.setString(3, vehiculo.getMarca());
			stmt.setInt(4, vehiculo.getCliente().getID());
			stmt.setInt(5, vehiculo.getTipoVehiculo().getID());
			
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				vehiculo.setID(rs.getInt(1));
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
	public void update(Vehiculo vehiculo) {
		String query = "UPDATE 1 SET Patente=?, Modelo=?, Marca=?, ID_Cliente=?, ID_TipoVehiculo=? WHERE 2=?";
		LinkedList<String> values = new LinkedList<>();
		
		values.add(this.getTableName(vehiculo));
		values.add(this.getIDName(vehiculo));
		
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement(this.getQuery(query, values));
			stmt.setString(1, vehiculo.getPatente());
			stmt.setString(2, vehiculo.getModelo());
			stmt.setString(3, vehiculo.getMarca());
			stmt.setInt(4, vehiculo.getCliente().getID());
			stmt.setInt(5, vehiculo.getTipoVehiculo().getID());
			stmt.setInt(6, vehiculo.getID());
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
	protected void mapResult(ResultSet rs, Vehiculo vehiculo) throws SQLException {
		try {
			Cliente cliente= new Cliente();
			cliente.setID(rs.getInt("ID_Cliente"));
			
			TipoVehiculo tipoVehiculo= new TipoVehiculo();
			tipoVehiculo.setID(rs.getInt("ID_TipoVehiculo"));
			
			vehiculo.setID(rs.getInt("ID_Vehiculo"));
			vehiculo.setPatente(rs.getString("Patente"));
			vehiculo.setMarca(rs.getString("Marca"));
			vehiculo.setModelo(rs.getString("Modelo"));
			vehiculo.setCliente(ClienteRepository.getInstancia().getByID(cliente));
			vehiculo.setTipoVehiculo(TipoVehiculoRepository.getInstancia().getByID(tipoVehiculo));
		}
		catch (SQLException ex) {
			throw ex;
		}
	}

	public Vehiculo findByPatente(Vehiculo vehiculo) {
		String query = "SELECT * FROM 1 INNER JOIN 2 ON 2.3 = 1.3 INNER JOIN 4 ON 4.5 = 1.5 WHERE 1.Patente LIKE ?";
		LinkedList<String> values = this.PrepareBaseQuery(vehiculo);
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		Vehiculo v = new Vehiculo();
		
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement(
							this.getQuery(query, values),
							PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, vehiculo.getPatente());
			rs = stmt.executeQuery();
			
			if (rs == null) return null;
			
			while (rs.next()) {
				this.mapResult(rs, v);
			}
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		finally {
			this.closeConnection(stmt, rs);
		}
		
		return v;
		
	}
	
}
