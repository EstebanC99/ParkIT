package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.Personas.Empleado;
import logs.Log;

public class EmpleadoRepository extends Repository<Empleado>{

	private static EmpleadoRepository Instancia;
	
	public static EmpleadoRepository  getInstancia() {
		if (Instancia == null) {
			Instancia = new EmpleadoRepository();
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
	protected LinkedList<String> PrepareBaseQuery(Empleado empleado) {
		LinkedList<String> values = new LinkedList<>();
		
		values.add(this.getTableName(empleado));
		values.add(this.getIDName(empleado));
		
		return values;
	}

	@Override
	protected Empleado getNewEntity() {
		return new Empleado();
	}

	@Override
	public void add(Empleado empleado) {
		String query = "INSERT INTO 1 (Nombre, Apellido, DNI, Email, Telefono, Direccion, FechaNacimiento, Cuit) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		LinkedList<String> values = new LinkedList<>();
		values.add(this.getTableName(empleado));
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement(this.getQuery(query, values), PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, empleado.getNombre());
			stmt.setString(2, empleado.getApellido());
			stmt.setString(3, empleado.getDNI());
			stmt.setString(4, empleado.getEmail());
			stmt.setString(5, empleado.getTelefono());
			stmt.setString(6, empleado.getDireccion());
			stmt.setString(7, empleado.getFechaNacimiento().toString());
			stmt.setString(8, empleado.getCuit());
			
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				empleado.setID(rs.getInt(1));
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
	public void update(Empleado empleado) {
		String query = "UPDATE 1 SET Nombre=?, Apellido=?, DNI=?, Email=?, Telefono=?, Direccion=?, FechaNacimiento=?, Cuit=? WHERE 2=?";
		LinkedList<String> values = new LinkedList<>();
		
		values.add(this.getTableName(empleado));
		values.add(this.getIDName(empleado));
		
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement(this.getQuery(query, values));
			stmt.setString(1, empleado.getNombre());
			stmt.setString(2, empleado.getApellido());
			stmt.setString(3, empleado.getDNI());
			stmt.setString(4, empleado.getEmail());
			stmt.setString(5, empleado.getTelefono());
			stmt.setString(6, empleado.getDireccion());
			stmt.setString(7, empleado.getFechaNacimiento().toString());
			stmt.setString(8, empleado.getCuit());
			stmt.setInt(9, empleado.getID());
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
	protected void mapResult(ResultSet rs, Empleado empleado) throws SQLException {
		try {
			empleado.setID(rs.getInt("ID_Empleado"));
			empleado.setNombre(rs.getString("Nombre"));
			empleado.setApellido(rs.getString("Apellido"));
			empleado.setDNI(rs.getString("DNI"));
			empleado.setEmail(rs.getString("Email"));
			empleado.setTelefono(rs.getString("Telefono"));
			empleado.setDireccion(rs.getString("Direccion"));
			empleado.setFechaNacimiento(rs.getDate("FechaNacimiento").toLocalDate());
			empleado.setCuit(rs.getString("Cuit"));
		}
		catch (SQLException ex) {
			Log.registrarSevereLog(ex);
			throw ex;
		}
	}

}
