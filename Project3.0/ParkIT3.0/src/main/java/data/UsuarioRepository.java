package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.Usuarios.Usuario;

public class UsuarioRepository extends Repository<Usuario> {

	private static UsuarioRepository Instancia;

	public static UsuarioRepository getInstancia() {
		if (Instancia == null)
			Instancia = new UsuarioRepository();

		return Instancia;
	}

	private String BASE_QUERY = "SELECT * FROM t_Usuario";
	private String SELECT_BY_ID_QUERY = String.join(" ", this.BASE_QUERY, "WHERE ID_Usuario=?");

	@Override
	protected String getBaseQuery() {
		return this.BASE_QUERY;
	}

	@Override
	protected String getSelectByIDQuery() {
		return this.SELECT_BY_ID_QUERY;
	}

	@Override
	protected LinkedList<String> PrepareBaseQuery(Usuario entity) {
		return new LinkedList<>();
	}

	@Override
	protected Usuario getNewEntity() {
		return new Usuario();
	}

	@Override
	public void add(Usuario usuario) {
		String query = "INSERT INTO 1 (NombreUsuario, Password, TipoUsuario) VALUES (?, ?, ?)";
		LinkedList<String> values = new LinkedList<>();
		values.add(this.getTableName(usuario));

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(this.getQuery(query, values),
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, usuario.getNombreUsuario());
			stmt.setString(2, usuario.getPassword());
			stmt.setInt(3, usuario.getTipo().value);

			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				usuario.setID(rs.getInt(1));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			this.closeConnection(stmt, rs);
		}
	}

	@Override
	public void update(Usuario usuario) {
		String query = "UPDATE 1 SET NombreUsuario=?, Password=?, Activo=? WHERE 2=?";
		LinkedList<String> values = new LinkedList<>();

		values.add(this.getTableName(usuario));
		values.add(this.getIDName(usuario));

		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(this.getQuery(query, values));
			stmt.setString(1, usuario.getNombreUsuario());
			stmt.setString(2, usuario.getPassword());
			stmt.setBoolean(3, usuario.isActivo());
			stmt.setInt(4, usuario.getID());
			stmt.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();

		} finally {
			this.closeConnection(stmt);
		}
	}

	@Override
	protected void mapResult(ResultSet rs, Usuario usuario) throws SQLException {
		try {
			usuario.setID(rs.getInt("ID_Usuario"));
			usuario.setNombreUsuario(rs.getString("NombreUsuario"));
			usuario.setPassword(rs.getString("Password"));
			usuario.setActivo(rs.getBoolean("Activo"));
			usuario.setTipoUsuario(rs.getInt("TipoUsuario"));
		} catch (SQLException ex) {
			throw ex;
		}
	}

	public Usuario getByUser(Usuario usuario) {
		String query = "SELECT * FROM t_Usuario WHERE NombreUsuario LIKE ?";

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(query);
			stmt.setString(1, usuario.getNombreUsuario());

			rs = stmt.executeQuery();

			if (rs != null && rs.next()) {
				Usuario usuarioEncontrado = this.getNewEntity();
				this.mapResult(rs, usuarioEncontrado);
				return usuarioEncontrado;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			this.closeConnection(stmt, rs);
		}
		return null;
	}

	public Usuario getByUserAndPassword(Usuario usu) {
		Usuario us = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("select * from t_usuario where NombreUsuario=? and Password=?");

			stmt.setString(1, usu.getNombreUsuario());
			stmt.setString(2, usu.getPassword());
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				us = new Usuario();
				us.setID(rs.getInt("ID_Usuario"));
				us.setNombreUsuario(rs.getString("NombreUsuario"));
				us.setActivo(rs.getBoolean("Activo"));
				us.setTipoUsuario(rs.getInt("TipoUsuario"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {

					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return us;
	}
}
