package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;

import entities.Personas.Empleado;
import entities.Servicios.Servicio;
import entities.Servicios.ServicioVehiculo;
import entities.Vehiculos.Vehiculo;
import logs.Log;

public class ServicioVehiculoRepository extends Repository<ServicioVehiculo> {

	private static ServicioVehiculoRepository Instancia;
	
	public static ServicioVehiculoRepository getInstancia() {
		if (Instancia == null) {
			Instancia = new ServicioVehiculoRepository();
		}
		
		return Instancia;
	}
	
	private String BASE_QUERY = "SELECT * FROM 1 INNER JOIN 2 ON 2.3 = 1.3 INNER JOIN 4 ON 4.5 = 1.5 INNER JOIN 6 ON 6.7 = 1.7";
	private String SELECT_BY_ID_QUERY = String.join(" ", this.BASE_QUERY, "WHERE 8=?");
	
	@Override
	protected String getBaseQuery() {
		return this.BASE_QUERY;
	}

	@Override
	protected String getSelectByIDQuery() {
		return this.SELECT_BY_ID_QUERY;
	}

	@Override
	protected LinkedList<String> PrepareBaseQuery(ServicioVehiculo servicioVehiculo) {
		LinkedList<String> values = new LinkedList<>();
		
		values.add(this.getTableName(servicioVehiculo));
		values.add(this.getTableName(servicioVehiculo.getEmpleado()));
		values.add(this.getIDName(servicioVehiculo.getEmpleado()));
		values.add(this.getTableName(servicioVehiculo.getServicio()));
		values.add(this.getIDName(servicioVehiculo.getServicio()));
		values.add(this.getTableName(servicioVehiculo.getVehiculo()));
		values.add(this.getIDName(servicioVehiculo.getVehiculo()));
		values.add(this.getIDName(servicioVehiculo));
		
		return values;
	}

	@Override
	protected ServicioVehiculo getNewEntity() {
		return new ServicioVehiculo();
	}

	@Override
	public void add(ServicioVehiculo servicioVehiculo) {
		String query = "INSERT INTO 1 (FechaRealizacion, ID_Empleado, ID_Servicio, ID_Vehiculo) VALUES (?, ?, ?, ?)";
		LinkedList<String> values = new LinkedList<String>();
		values.add(this.getTableName(servicioVehiculo));
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement(this.getQuery(query, values), PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, servicioVehiculo.getFechaRealizacion().toString());
			stmt.setInt(2, servicioVehiculo.getEmpleado().getID());
			stmt.setInt(3, servicioVehiculo.getServicio().getID());
			stmt.setInt(4, servicioVehiculo.getVehiculo().getID());
			
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				servicioVehiculo.setID(rs.getInt(1));
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
	public void update(ServicioVehiculo servicioVehiculo) {
		String query = "UPDATE 1 SET FechaRealizacion=?, ID_Empleado=?, ID_Servicio=?, ID_Vehiculo=? WHERE 2=?";
		LinkedList<String> values = new LinkedList<String>();
		
		values.add(this.getTableName(servicioVehiculo));
		values.add(this.getIDName(servicioVehiculo));
		
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement(this.getQuery(query, values));
			stmt.setString(1, servicioVehiculo.getFechaRealizacion().toString());
			stmt.setInt(2, servicioVehiculo.getEmpleado().getID());
			stmt.setInt(3, servicioVehiculo.getServicio().getID());
			stmt.setInt(4, servicioVehiculo.getVehiculo().getID());
			stmt.setInt(5, servicioVehiculo.getID());
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
	protected void mapResult(ResultSet rs, ServicioVehiculo servicioVehiculo) throws SQLException {
		try {
			Empleado empleado = new Empleado();
			empleado.setID(rs.getInt("ID_Empleado"));
			
			Vehiculo vehiculo = new Vehiculo();
			vehiculo.setID(rs.getInt("ID_Vehiculo"));
			
			Servicio servicio = new Servicio();
			servicio.setID(rs.getInt("ID_Servicio"));
			
			servicioVehiculo.setID(rs.getInt("ID_ServicioVehiculo"));
			servicioVehiculo.setFechaRealizacion(rs.getDate("FechaRealizacion").toLocalDate());
			servicioVehiculo.setEmpleado(EmpleadoRepository.getInstancia().getByID(empleado));
			servicioVehiculo.setServicio(ServicioRepository.getInstancia().getByID(servicio));
			servicioVehiculo.setVehiculo(VehiculoRepository.getInstancia().getByID(vehiculo));
			servicioVehiculo.setPagado(rs.getBoolean("Pagado"));
		}
		catch (SQLException ex) {
			Log.registrarSevereLog(ex);
			throw ex;
		}
	}

	public ServicioVehiculo getExistingService(ServicioVehiculo servicioVehiculo) {
		String query = "SELECT * FROM t_ServicioVehiculo WHERE ID_ServicioVehiculo!=? AND ID_Servicio=? AND ID_Vehiculo=? AND FechaRealizacion LIKE ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement(
							query,
							PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, servicioVehiculo.getID());
			stmt.setInt(2, servicioVehiculo.getServicio().getID());
			stmt.setInt(3, servicioVehiculo.getVehiculo().getID());
			stmt.setString(4, servicioVehiculo.getFechaRealizacion().toString());
			rs = stmt.executeQuery();
			
			if (rs == null) return null;
			
			while (rs.next()) {
				this.mapResult(rs, servicioVehiculo);
			}
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			Log.registrarSevereLog(ex);
		}
		finally {
			this.closeConnection(stmt, rs);
		}
		
		return servicioVehiculo;
	}
	
	public LinkedList<ServicioVehiculo> getServiciosPorFecha(LocalDate fecha){
		String query = String.join(" ", this.BASE_QUERY, "WHERE FechaRealizacion=?");
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		LinkedList<ServicioVehiculo> lista = new LinkedList<>();
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(this.getQuery(query, this.PrepareBaseQuery(this.getNewEntity())));
			stmt.setString(1, fecha.toString());
			rs = stmt.executeQuery();
			
			if (rs == null ) return lista;
			
			while (rs.next()) {
				ServicioVehiculo entity = this.getNewEntity();
				this.mapResult(rs, entity);
				lista.add(entity);
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
	
	public void indicarPago(ServicioVehiculo servicioVehiculo) {
		String query = "UPDATE 1 SET Pagado=? WHERE 2=?";
		LinkedList<String> values = new LinkedList<String>();
		
		values.add(this.getTableName(servicioVehiculo));
		values.add(this.getIDName(servicioVehiculo));
		
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement(this.getQuery(query, values));
			stmt.setBoolean(1, servicioVehiculo.isPagado());
			stmt.setInt(2, servicioVehiculo.getID());
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

	public LinkedList<ServicioVehiculo> getServicioUsuario(int id) {
		String query = "SELECT t_serviciovehiculo.* FROM t_serviciovehiculo INNER JOIN t_vehiculo ON t_serviciovehiculo.ID_Vehiculo = t_vehiculo.ID_Vehiculo INNER JOIN t_cliente ON t_vehiculo.ID_Cliente = ? ORDER BY t_serviciovehiculo.FechaRealizacion DESC";
		PreparedStatement stmt = null;
		ResultSet rs = null;
			
		LinkedList<ServicioVehiculo> lista = new LinkedList<ServicioVehiculo>();
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(query);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
					
			if (rs == null ) return lista;
					
			while (rs.next()) {
				ServicioVehiculo entity = this.getNewEntity();
				this.mapResult(rs, entity);
				lista.add(entity);
			}
		}catch (SQLException ex) {
			ex.printStackTrace();
		}finally{
			this.closeConnection(stmt, rs);
		}
				
		return lista;	
	}

		
}
