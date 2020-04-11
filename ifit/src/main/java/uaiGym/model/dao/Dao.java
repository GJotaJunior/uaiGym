package uaiGym.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uaiGym.model.Endereco;
import uaiGym.model.enuns.EstadoEnum;

public abstract class Dao<E> {

	private Connection connection;

	protected Connection getConnection() {
		return connection;
	}

	public Dao(Connection connection) {
		this.connection = connection;
	}

	public abstract E recuperarPorId(int id);

	public abstract void salvar(E entidade);

	public abstract void excluir(int id);

	public abstract List<E> listarTodos();

	public Integer getQuantUsuarios() {
		String sql = "SELECT Count(*) FROM Usuario";

		try (PreparedStatement pstm = getConnection().prepareStatement(sql)) {
			pstm.execute();
			try (ResultSet rst = pstm.getResultSet()) {
				if (rst.next())
					return rst.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	protected Set<String> getTelefonesPorId(Integer id) {
		Set<String> telefones = new HashSet<String>();

		String sql = "SELECT telefone FROM Telefone WHERE idUsuario = ?";

		try (PreparedStatement pstm = getConnection().prepareStatement(sql)) {
			pstm.setInt(1, id);
			pstm.execute();

			try (ResultSet rst = pstm.getResultSet()) {
				while (rst.next()) {
					telefones.add(rst.getString(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return telefones;
	}

	protected Endereco getEnderecoPorId(Integer id) {
		Endereco endereco = null;

		String sql = "SELECT * FROM Endereco WHERE idUsuario = ?";

		try (PreparedStatement pstm = getConnection().prepareStatement(sql)) {
			pstm.setInt(1, id);
			pstm.execute();
			try (ResultSet rst = pstm.getResultSet()) {
				if (rst.next()) {
					endereco = new Endereco(rst.getString(3), rst.getInt(4), rst.getString(5), rst.getString(6),
							rst.getString(7), rst.getString(9), EstadoEnum.valueOf(rst.getString(8)));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return endereco;
	}
}
