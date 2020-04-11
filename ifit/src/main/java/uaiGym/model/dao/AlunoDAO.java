package uaiGym.model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uaiGym.model.AvaliacaoFisica;
import uaiGym.model.ContatoDeEmergencia;
import uaiGym.model.enuns.ParentescoEnum;
import uaiGym.model.enuns.SexoEnum;
import uaiGym.model.pessoa.Aluno;
import uaiGym.model.pessoa.Treino;

public class AlunoDAO extends UsuarioDAO<Aluno> {

	public AlunoDAO(Connection connection) {
		super(connection);
	}

	@Override
	public Aluno recuperarPorId(int id) {
		Aluno aluno = null;

		String sql = "SELECT u.*, a.matricula, a.status FROM Aluno a INNER JOIN Usuario u ON a.idUsuario = u.idUsuario WHERE a.idUsuario = ?";

		try (PreparedStatement pstm = getConnection().prepareStatement(sql)) {
			pstm.setInt(1, id);
			pstm.execute();

			try (ResultSet rst = pstm.getResultSet()) {
				if (rst.next()) {
					aluno = new Aluno(id, rst.getString(7), rst.getString(8), rst.getString(3), rst.getString(4),
							rst.getDate(5), getTelefonesPorId(id), SexoEnum.valueOf(rst.getString(6)),
							getEnderecoPorId(id), rst.getString(9),
							new InstrutorDAO(getConnection()).getInstrutorPorIdDoAluno(id), getAvaliacoesPorId(id),
							getTreinosPorId(id), rst.getString(10).equals("ATIVO"), getContatosDeEmergenciaPorId(id));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return aluno;
	}

	private Set<ContatoDeEmergencia> getContatosDeEmergenciaPorId(int id) {
		Set<ContatoDeEmergencia> contatos = new HashSet<ContatoDeEmergencia>();

		String sql = "SELECT c.* FROM Contato c INNER JOIN Aluno a ON c.idAluno = a.idAluno WHERE a.idUsuario = ?";

		try (PreparedStatement pstm = getConnection().prepareStatement(sql)) {
			pstm.setInt(1, id);
			pstm.execute();

			try (ResultSet rst = pstm.getResultSet()) {
				while (rst.next()) {
					contatos.add(new ContatoDeEmergencia(rst.getString(3), rst.getString(4),
							ParentescoEnum.valueOf(rst.getString(5))));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return contatos;
	}

	private List<Treino> getTreinosPorId(Integer id) {
		// TODO
		return null;
	}

	private List<AvaliacaoFisica> getAvaliacoesPorId(int id) {
		// TODO
		return null;
	}

	@Override
	public void salvar(Aluno entidade) {
		// Exemplo de como seria a chamada da procedure de listagem de aluno por Id
		// Tutorial que me ajudou
		// https://www.mysqltutorial.org/calling-mysql-stored-procedures-from-jdbc/W

		String sql = "{CALL sp_atualiza_aluno(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

		try (CallableStatement stms = getConnection().prepareCall(sql)) {
			stms.setInt(1, entidade.getId());
			stms.setString(2, entidade.getPerfil().toString());
			stms.setString(3, entidade.getNome());
			stms.setString(4, entidade.getCpf());
			stms.setDate(5, (Date) entidade.getNascimento());
			stms.setString(6, entidade.getSexo().toString());
			stms.setString(7, entidade.getEmail());
			stms.setString(8, entidade.getSenha());
			stms.setString(9, entidade.getMatricula());
			stms.setString(10, (entidade.isEstaAtivo()) ? "ATIVO" : "INATIVO");
			stms.setString(11, entidade.getEndereco().getRua());
			stms.setString(12, entidade.getEndereco().getNumero().toString());
			stms.setString(13, entidade.getEndereco().getComplemento());
			stms.setString(14, entidade.getEndereco().getBairro());
			stms.setString(15, entidade.getEndereco().getCidade());
			stms.setString(16, entidade.getEndereco().getEstado().toString());
			stms.setString(17, entidade.getEndereco().getCep());

			stms.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

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
