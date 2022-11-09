package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.Personas.Cliente;

public class ClienteRepository extends Repository<Cliente>{

	private static ClienteRepository Instancia;
	
	public static ClienteRepository getInstancia() {
		if (Instancia == null) {
			Instancia = new ClienteRepository();
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
	protected LinkedList<String> PrepareBaseQuery(Cliente cliente) {
		LinkedList<String> values = new LinkedList<>();
		
		values.add(this.getTableName(cliente));
		values.add(this.getIDName(cliente));
		
		return values;
	}

	@Override
	protected Cliente getNewEntity() {
		return new Cliente();
	}

	@Override
	public void add(Cliente cliente) {
		String query = "INSERT INTO 1 (Nombre, Apellido, DNI, Email, Telefono, Direccion) VALUES (?, ?, ?, ?, ?, ?)";
		LinkedList<String> values = new LinkedList<>();
		values.add(this.getTableName(cliente));
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement(this.getQuery(query, values), PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, cliente.getNombre());
			stmt.setString(2, cliente.getApellido());
			stmt.setString(3, cliente.getDNI());
			stmt.setString(4, cliente.getEmail());
			stmt.setString(5, cliente.getTelefono());
			stmt.setString(6, cliente.getDireccion());

			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				cliente.setID(rs.getInt(1));
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
	public void update(Cliente cliente) {
		String query = "UPDATE 1 SET Nombre=?, Apellido=?, DNI=?, Email=?, Telefono=?, Direccion=? WHERE 2=?";
		LinkedList<String> values = new LinkedList<>();
		
		values.add(this.getTableName(cliente));
		values.add(this.getIDName(cliente));
		
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement(this.getQuery(query, values));
			stmt.setString(1, cliente.getNombre());
			stmt.setString(2, cliente.getApellido());
			stmt.setString(3, cliente.getDNI());
			stmt.setString(4, cliente.getEmail());
			stmt.setString(5, cliente.getTelefono());
			stmt.setString(6, cliente.getDireccion());
			stmt.setInt(7, cliente.getID());
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
	protected void mapResult(ResultSet rs, Cliente cliente) throws SQLException {
		try {
			cliente.setID(rs.getInt("ID_Cliente"));
			cliente.setNombre(rs.getString("Nombre"));
			cliente.setApellido(rs.getString("Apellido"));
			cliente.setDNI(rs.getString("DNI"));
			cliente.setEmail(rs.getString("Email"));
			cliente.setTelefono(rs.getString("Telefono"));
			cliente.setDireccion(rs.getString("Direccion"));
		}
		catch (SQLException ex) {
			throw ex;
		}
	}

}
