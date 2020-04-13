package uaiGym.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uaiGym.model.enuns.PerfilEnum;
import uaiGym.model.enuns.SexoEnum;
import uaiGym.model.pessoa.Aluno;
import uaiGym.model.pessoa.Instrutor;

public class InstrutorDAO extends UsuarioDAO<Instrutor> {

	public InstrutorDAO(Connection connection) {
		super(connection);
	}

	@Override
	public Instrutor recuperarPorId(int id) {
		Instrutor instrutor = null;

		String sql = "SELECT u.*, f.contrato, f.dtAdmissao, f.dtDemissao FROM Funcionario f INNER JOIN Usuario u ON f.idUsuario = u.idUsuario WHERE f.idUsuario = 4 AND u.perfil = 'INSTRUTOR'";

		try (PreparedStatement pstm = getConnection().prepareStatement(sql)) {
			pstm.setInt(1, id);
			pstm.execute();

			try (ResultSet rst = pstm.getResultSet()) {
				if (rst.next()) {
					String email = rst.getString(7);
					String senha = rst.getString(8);
					String nome = rst.getString(3);
					String cpf = rst.getString(4);
					Date nascimento = rst.getDate(5);
					SexoEnum sexo = SexoEnum.valueOf(rst.getString(6));
					PerfilEnum perfil = PerfilEnum.INSTRUTOR;
					String contrato = rst.getString(9);
					Date dataAdmissao = rst.getDate(10);
					Date dataDemissao = rst.getDate(11);
					
					instrutor = new Instrutor(id, email, senha, nome, cpf, nascimento, getTelefonesPorId(id), sexo,
							getEnderecoPorId(id), perfil, contrato, dataAdmissao, dataDemissao,
							getAlunosPorIdInstrutor(id));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return instrutor;
	}

	private Set<Aluno> getAlunosPorIdInstrutor(int id) {
		Set<Aluno> alunos = new HashSet<Aluno>();

		String sql = "SELECT a.idUsuario"
				+ "FROM AlunoTreino alt INNER JOIN Funcionario f ON alt.idFuncionario = f.idFuncionario"
				+ "INNER JOIN Aluno a ON alt.idAluno = a.idAluno WHERE f.idUsuario = ?";

		try (PreparedStatement pstm = getConnection().prepareStatement(sql)) {
			pstm.setInt(1, id);
			pstm.execute();
			try (ResultSet rst = pstm.getResultSet()) {
				while (rst.next()) {
					int idAluno = rst.getInt(1);
					Aluno aluno = new AlunoDAO(getConnection()).recuperarPorId(idAluno);
					alunos.add(aluno);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return alunos;
	}

	@Override
	public void salvar(Instrutor entidade) {
		// TODO Auto-generated method stub

	}

	@Override
	public void excluir(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Instrutor> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	public Instrutor getInstrutorPorIdDoAluno(Integer idAluno) {
		Integer idInstrutor = null;

		String sql = "SELECT f.idUsuario"
				+ "FROM AlunoTreino alt INNER JOIN Funcionario f ON alt.idFuncionario = f.idFuncionario"
				+ "INNER JOIN Aluno a ON alt.idAluno = a.idAluno" + "WHERE a.idUsuario = ?";

		try (PreparedStatement pstm = getConnection().prepareStatement(sql)) {
			pstm.execute();
			try (ResultSet rst = pstm.getResultSet()) {
				if (rst.next()) {
					idInstrutor = rst.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return recuperarPorId(idInstrutor);
	}

}
