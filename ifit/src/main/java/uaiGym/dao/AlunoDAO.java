package uaiGym.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import uaiGym.model.Endereco;
import uaiGym.model.enuns.PerfilEnum;
import uaiGym.model.enuns.SexoEnum;
import uaiGym.model.pessoa.Aluno;
import uaiGym.model.pessoa.Instrutor;

public class AlunoDAO extends Dao<Aluno> {

	public AlunoDAO(Connection connection) {
		super(connection);
	}

	@Override
	public Aluno recuperarPorId(int id) {
		// Exemplo de como seria a chamada da procedure de listagem de aluno por Id
		// Tutorial que me ajudou https://www.mysqltutorial.org/calling-mysql-stored-procedures-from-jdbc/W
		
		Aluno aluno = null;

		String sql = "{CALL find_aluno_by_Id(?)}";
		try (CallableStatement stmt = getConnection().prepareCall(sql)) {
			stmt.setInt(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					aluno = new Aluno(rs.getString("nome"), rs.getString("cpf"), rs.getDate("nascimento"),
							rs.getString("telefone"), SexoEnum.valueOf(rs.getString("sexo")),
							(Endereco) rs.getObject("endereco"), rs.getInt("numeroMatricula"),
							(Instrutor) rs.getObject("instrutor"), PerfilEnum.valueOf(rs.getString("perfil")));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aluno;
	}

	@Override
	public void salvar(Aluno entidade) {
		// TODO Auto-generated method stub

	}

	@Override
	public void excluir(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Aluno> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}
