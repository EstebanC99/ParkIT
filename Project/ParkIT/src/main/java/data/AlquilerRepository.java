package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.Alquileres.Alquiler;
import entities.Alquileres.FormaPago;
import entities.Alquileres.TipoAlquiler;
import entities.Cocheras.Cochera;
import entities.Personas.Empleado;
import entities.Vehiculos.Vehiculo;

public class AlquilerRepository extends Repository<Alquiler>{

	private static AlquilerRepository Instancia;
	
	public static AlquilerRepository getInstancia() {
		if (Instancia == null) {
			Instancia = new AlquilerRepository();
		}
		return Instancia;
	}
	
	private String BASE_QUERY = 
			"SELECT * FROM t_Alquiler a "
			+ "INNER JOIN t_Empleado e ON e.ID_Empleado = a.ID_Empleado "
			+ "INNER JOIN t_TipoAlquiler t ON t.ID_TipoAlquiler = a.ID_TipoAlquiler "
			+ "INNER JOIN t_Vehiculo v ON v.ID_Vehiculo = a.ID_Vehiculo "
			+ "INNER JOIN t_Cochera c ON c.ID_Cochera = a.ID_Cochera";
	
	private String SELECT_BY_ID_QUERY = String.join(" ", this.BASE_QUERY, "WHERE a.ID_Alquiler=?");
	
	@Override
	protected String getBaseQuery() {
		return this.BASE_QUERY;
	}

	@Override
	protected String getSelectByIDQuery() {
		return this.SELECT_BY_ID_QUERY;
	}

	@Override
	protected LinkedList<String> PrepareBaseQuery(Alquiler entity) {
		return new LinkedList<String>();
	}

	@Override
	protected Alquiler getNewEntity() {
		return new Alquiler();
	}

	@Override
	public void add(Alquiler alquiler) {
		String query = "INSERT INTO t_Alquiler (FechaInicio, HoraInicio, FechaFin, HoraFin, Pagado, ID_FormaPago, ID_Empleado, ID_TipoAlquiler, ID_Vehiculo, Precio, ID_Cochera) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, alquiler.getFechaFin().toString());
			stmt.setString(2, alquiler.getHoraInicio().toString());
			stmt.setString(3, alquiler.getFechaFin().toString());
			stmt.setString(4, alquiler.getHoraFin().toString());
			stmt.setBoolean(5, false);
			stmt.setInt(6, alquiler.getFormaPago().getID()); // ACA PONER LO DE LA FORMA DE PAGO
			stmt.setInt(7, alquiler.getEmpleado().getID());
			stmt.setInt(8, alquiler.getTipoAlquiler().getID());
			stmt.setInt(9, alquiler.getVehiculo().getID());
			stmt.setDouble(10, alquiler.getPrecio());
			stmt.setInt(11, alquiler.getCochera().getID());
			
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				alquiler.setID(rs.getInt(1));
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
	public void update(Alquiler alquiler) {
		String query = "UPDATE t_Alquiler SET FechaInicio=?, HoraInicio=?, FechaFin=?, HoraFin=?, Pagado=?, ID_FormaPago=?, ID_Empleado=?, ID_TipoAlquiler=?, ID_Vehiculo=?, Precio=?, ID_Cochera=? WHERE ID_Alquiler=?";
		
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement(query);
			stmt.setString(1, alquiler.getFechaFin().toString());
			stmt.setString(2, alquiler.getHoraInicio().toString());
			stmt.setString(3, alquiler.getFechaFin().toString());
			stmt.setString(4, alquiler.getHoraFin().toString());
			stmt.setBoolean(5, false);
			stmt.setInt(6, alquiler.getFormaPago().getID()); // ACA PONER LO DE LA FORMA DE PAGO
			stmt.setInt(7, alquiler.getEmpleado().getID());
			stmt.setInt(8, alquiler.getTipoAlquiler().getID());
			stmt.setInt(9, alquiler.getVehiculo().getID());
			stmt.setDouble(10, alquiler.getPrecio());
			stmt.setInt(11, alquiler.getCochera().getID());
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
	protected void mapResult(ResultSet rs, Alquiler alquiler) throws SQLException {
		try {
			FormaPago formaPago = new FormaPago();
			formaPago.setID(rs.getInt("ID_FormaPago"));
			
			Empleado empleado = new Empleado();
			empleado.setID(rs.getInt("ID_Empleado"));
			
			TipoAlquiler tipoAlquiler = new TipoAlquiler();
			tipoAlquiler.setID(rs.getInt("ID_TipoAlquiler"));
			
			Vehiculo vehiculo = new Vehiculo();
			vehiculo.setID(rs.getInt("ID_Vehiculo"));
			
			Cochera cochera = new Cochera();
			cochera.setID(rs.getInt("ID_Cochera"));
			
			alquiler.setID(rs.getInt("ID_Alquiler"));
			alquiler.setFechaInicio(rs.getDate("FechaInicio").toLocalDate());
			alquiler.setHoraInicio(rs.getTime("HoraInicio").toLocalTime());
			alquiler.setPagado(rs.getBoolean("Pagado"));
			alquiler.setPrecio(rs.getDouble("Precio"));
			
			alquiler.setFormaPago(FormaPagoRepository.getInstancia().getByID(formaPago));
			alquiler.setEmpleado(EmpleadoRepository.getInstancia().getByID(empleado));
			alquiler.setTipoAlquiler(TipoAlquilerRepository.getInstancia().getByID(tipoAlquiler));
			alquiler.setVehiculo(VehiculoRepository.getInstancia().getByID(vehiculo));
			alquiler.setCochera(CocheraRepository.getInstancia().getByID(cochera));
		}
		catch (SQLException ex) {
			throw ex;
		}
	}

	
	
}
