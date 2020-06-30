package uaiGym.model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uaiGym.model.enuns.PerfilEnum;
import uaiGym.model.enuns.SexoEnum;
import uaiGym.model.pessoa.Recepcao;
import uaiGym.service.DataBase.DatabaseUtils;

public class RecepcaoDAO extends UsuarioDAO<Recepcao> {

	public RecepcaoDAO(Connection connection) {
		super(connection);
	}

	@Override
	public Recepcao recuperarPorId(int id) {

		Recepcao recepcao = null;

		String sql = "SELECT u.*, f.contrato, f.dtAdmissao, f.dtDemissao " + "FROM Funcionario f "
			+ "INNER JOIN Usuario u ON f.idUsuario = u.idUsuario "
			+ "WHERE f.idUsuario = ? AND u.perfil = 'RECEPCAO'";

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
			    Date admissao = rst.getDate(10);
			    Date demissao = rst.getDate(11);

				recepcao = new Recepcao(email, senha, nome, cpf, nascimento, getTelefonesPorId(id), sexo,
						getEnderecoPorId(id), perfil, contrato, admissao, demissao);
			    
			}
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}

		return recepcao;
	}

	@Override
	public void salvar(Recepcao entidade) {
		String sql = "{CALL sp_atualiza_funcionario(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

		try (CallableStatement stms = getConnection().prepareCall(sql)) {
		    stms.setInt(1, entidade.getId());
		    stms.setString(2, entidade.getPerfil().toString());
		    stms.setString(3, entidade.getNome());
		    stms.setString(4, entidade.getCpf());
		    stms.setDate(5, DatabaseUtils.converteData(entidade.getDtNascimento()));
		    stms.setString(6, entidade.getSexo().toString());
		    stms.setString(7, entidade.getEmail());
		    stms.setString(8, entidade.getSenha());
		    stms.setString(9, entidade.getContrato());
		    stms.setDate(10, DatabaseUtils.converteData(entidade.getAdmissao()));
		    stms.setDate(11, DatabaseUtils.converteData(entidade.getDemissao()));
		    stms.setString(12, entidade.getEndereco().getRua());
		    stms.setString(13, entidade.getEndereco().getNumero().toString());
		    stms.setString(14, entidade.getEndereco().getComplemento());
		    stms.setString(15, entidade.getEndereco().getBairro());
		    stms.setString(16, entidade.getEndereco().getCidade());
		    stms.setString(17, entidade.getEndereco().getEstado().toString());
		    stms.setString(18, entidade.getEndereco().getCep());

		    stms.executeQuery();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		
	}

	@Override
	public void excluir(int id) {
		String sql = "{CALL sp_desativa_funcionario(?)}";
		try (CallableStatement stms = getConnection().prepareCall(sql)) {
		    stms.setInt(1, id);
		    stms.executeQuery();
		} catch (SQLException e) {
		    e.printStackTrace();
		}	
	}

	@Override
	public List<Recepcao> listarTodos() {
		List<Recepcao> recepcionistas = new ArrayList<Recepcao>();
		String sql = "SELECT u.*, f.contrato, f.dtAdmissao, f.dtDemissao " + "FROM Funcionario f "
				+ "INNER JOIN Usuario u ON f.idUsuario = u.idUsuario " + "WHERE u.perfil = 'RECEPCAO'";

		try (PreparedStatement pstm = getConnection().prepareStatement(sql)) {
			pstm.execute();

			try (ResultSet rst = pstm.getResultSet()) {

				if (rst.next()) {
					int id = rst.getInt(1);
					String email = rst.getString(7);
					String senha = rst.getString(8);
					String nome = rst.getString(3);
					String cpf = rst.getString(4);
					Date nascimento = rst.getDate(5);
					SexoEnum sexo = SexoEnum.valueOf(rst.getString(6));
					PerfilEnum perfil = PerfilEnum.INSTRUTOR;
					String contrato = rst.getString(9);
					Date admissao = rst.getDate(10);
					Date demissao = rst.getDate(11);

					Recepcao recepcao = new Recepcao(email, senha, nome, cpf, nascimento, getTelefonesPorId(id), sexo,
							getEnderecoPorId(id), perfil, contrato, admissao, demissao);

					recepcionistas.add(recepcao);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recepcionistas;
	}

	@Override
	public void atualizar(Recepcao e) {
		String sql = "{CALL sp_atualiza_funcionario ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?}";
		
		try (CallableStatement stms = getConnection().prepareCall(sql)) {
			stms.setInt(1, e.getId());
			stms.setString(2, e.getPerfil().toString());
			stms.setString(3, e.getNome());
			stms.setString(4, e.getCpf());
			stms.setDate(5, DatabaseUtils.converteData(e.getDtNascimento()));
			stms.setString(6, e.getSexo().toString());
			stms.setString(7, e.getEmail());
			stms.setString(8, e.getSenha());
			stms.setString(9, e.getContrato());
			stms.setDate(10, DatabaseUtils.converteData(e.getAdmissao()));
			stms.setDate(11, DatabaseUtils.converteData(e.getDemissao()));
			stms.setString(12, e.getEndereco().getRua());
			stms.setString(13, e.getEndereco().getNumero());
			stms.setString(14, e.getEndereco().getComplemento());
			stms.setString(15, e.getEndereco().getBairro());
			stms.setString(16, e.getEndereco().getCidade());
			stms.setString(17, e.getEndereco().getEstado().toString());
			stms.setString(18, e.getEndereco().getCep());
			
			stms.executeQuery();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
	}

}
