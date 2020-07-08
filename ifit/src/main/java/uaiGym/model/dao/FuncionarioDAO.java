package uaiGym.model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uaiGym.model.enuns.PerfilEnum;
import uaiGym.model.enuns.SexoEnum;
import uaiGym.model.pessoa.Aluno;
import uaiGym.model.pessoa.Funcionario;
import uaiGym.service.DataBase.DatabaseUtils;

public class FuncionarioDAO extends UsuarioDAO<Funcionario> {

	public FuncionarioDAO(Connection connection) {
		super(connection);
	}

	@Override
	public Funcionario recuperarPorId(int id) {
		Funcionario funcionario = null;

		String sql = "SELECT u.*, f.idFuncionario, f.contrato, f.dtAdmissao, f.dtDemissao " + "FROM Funcionario f "
			+ "INNER JOIN Usuario u ON u.idUsuario = f.idUsuario "
			+ "WHERE u.idUsuario = ?";
		System.out.println("consulta");

		try (PreparedStatement pstm = getConnection().prepareStatement(sql)) {
		    pstm.setInt(1, id);
		    pstm.execute();

		    try (ResultSet rst = pstm.getResultSet()) {

			if (rst.next()) {
			    Integer idUsuario = rst.getInt(1);
			    PerfilEnum perfil = PerfilEnum.valueOf(rst.getString(2));
			    String nome = rst.getString(3);
			    String cpf = rst.getString(4);
			    Date nascimento = null;//rst.getDate(5);
			    SexoEnum sexo = SexoEnum.valueOf(rst.getString(6));
			    String email = rst.getString(7);
			    String senha = rst.getString(8);
			    Integer idFuncionario = rst.getInt(9);
			    String contrato = rst.getString(10);
			    Date dataAdmissao = rst.getDate(11);
			    Date dataDemissao = rst.getDate(12);

			
			    funcionario = new Funcionario(idUsuario, email, senha, nome, cpf, nascimento, null, sexo, null, perfil, idFuncionario, contrato, dataAdmissao, dataDemissao);
			    
			}
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}

		return funcionario;
	}

	@Override
	public void salvar(Funcionario entidade) {
	    
	    String sql = "{CALL sp_inserir_funcionario(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

		try (CallableStatement stms = getConnection().prepareCall(sql)) {
		    stms.setString(1, entidade.getPerfil().toString());
		    stms.setString(2, entidade.getNome());
		    stms.setString(3, entidade.getCpf());
		    stms.setDate(4, DatabaseUtils.converteData(entidade.getDtNascimento()));
		    stms.setString(5, entidade.getSexo().toString());
		    stms.setString(6, entidade.getEmail());
		    stms.setString(7, entidade.getSenha());
		    stms.setString(8, entidade.getContrato());
		    stms.setDate(9, DatabaseUtils.converteData(entidade.getAdmissao()));
		    stms.setDate(10, DatabaseUtils.converteData(entidade.getDemissao()));
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
	    String sql = "{CALL sp_desativa_funcionario(?)}";
		try (CallableStatement stms = getConnection().prepareCall(sql)) {
		    stms.setInt(1, id);
		    stms.executeQuery();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	}

	@Override
	public List<Funcionario> listarTodos() {
	    	List<Funcionario> funcionarios = new ArrayList<>();
	    
		String sql = "SELECT u.*, f.idFuncionario, f.contrato, f.dtAdmissao, f.dtDemissao FROM Funcionario f "
			+ "INNER JOIN Usuario u ON u.idUsuario = f.idUsuario ";
		
		try(PreparedStatement pstm = getConnection().prepareStatement(sql)) {
		    pstm.execute();
		    
		    try (ResultSet rs = pstm.getResultSet()) {
			while (rs.next()) {
			    Integer idUsuario = rs.getInt(1);
			    PerfilEnum perfil = PerfilEnum.valueOf(rs.getString(2));
			    String nome = rs.getString(3);
			    String cpf = rs.getString(4);
			    Date nascimento = null;//rst.getDate(5);
			    SexoEnum sexo = SexoEnum.valueOf(rs.getString(6));
			    String email = rs.getString(7);
			    String senha = rs.getString(8);
			    Integer idFuncionario = rs.getInt(9);
			    String contrato = rs.getString(10);
			    Date dataAdmissao = rs.getDate(11);
			    Date dataDemissao = rs.getDate(12);

			
			    Funcionario funcionario = new Funcionario(idUsuario, email, senha, nome, cpf, nascimento, null, sexo, null, perfil, idFuncionario, contrato, dataAdmissao, dataDemissao);
			    
			    funcionarios.add(funcionario);
			}
		    }
		}
		catch (SQLException e) {
		    e.printStackTrace();
		}
		
		return funcionarios;
	}

	@Override
	public void atualizar(Funcionario entidade) {
	    String sql = "{CALL sp_atualiza_funcionario(?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

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
		    stms.setString(13, entidade.getEndereco().getNumero());
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
	
	   private Set<Aluno> getAlunosPorIdFuncionario(int id) {
		Set<Aluno> alunos = new HashSet<Aluno>();

		String sql = "SELECT a.idAluno " + "FROM AlunoTreino alt "
			+ "INNER JOIN Funcionario f ON alt.idFuncionario = f.idFuncionario "
			+ "INNER JOIN Aluno a ON alt.idAluno = a.idAluno " + "WHERE f.idUsuario = ?";

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
	   
	   public List<Funcionario> listarTodosGerentes() {
	    	List<Funcionario> gerentes = new ArrayList<>();
	    
		String sql = "SELECT u.*, f.idFuncionario, f.contrato, f.dtAdmissao, f.dtDemissao FROM Funcionario f "
			+ "INNER JOIN Usuario u ON u.idUsuario = f.idUsuario "
			+ "WHERE u.perfil = 'GERENTE'";
		
		try(PreparedStatement pstm = getConnection().prepareStatement(sql)) {
		    pstm.execute();
		    
		    try (ResultSet rs = pstm.getResultSet()) {
			while (rs.next()) {
			    Integer idUsuario = rs.getInt(1);
			    PerfilEnum perfil = PerfilEnum.valueOf(rs.getString(2));
			    String nome = rs.getString(3);
			    String cpf = rs.getString(4);
			    Date nascimento = null;//rst.getDate(5);
			    SexoEnum sexo = SexoEnum.valueOf(rs.getString(6));
			    String email = rs.getString(7);
			    String senha = rs.getString(8);
			    Integer idFuncionario = rs.getInt(9);
			    String contrato = rs.getString(10);
			    Date dataAdmissao = rs.getDate(11);
			    Date dataDemissao = rs.getDate(12);

			    Funcionario gerente = new Funcionario(idUsuario, email, senha, nome, cpf, nascimento, null, sexo, null, perfil, idFuncionario, contrato, dataAdmissao, dataDemissao);
			    
			    gerentes.add(gerente);
			}
		    }
		}
		catch (SQLException e) {
		    e.printStackTrace();
		}
		
		return gerentes;
	}
	   public List<Funcionario> listarTodosRecepcionistas() {
	    	List<Funcionario> recepcionistas = new ArrayList<>();
	    
		String sql = "SELECT u.*, f.idFuncionario, f.contrato, f.dtAdmissao, f.dtDemissao FROM Funcionario f "
			+ "INNER JOIN Usuario u ON u.idUsuario = f.idUsuario "
			+ "WHERE u.perfil = 'RECEPCAO'";
		
		try(PreparedStatement pstm = getConnection().prepareStatement(sql)) {
		    pstm.execute();
		    
		    try (ResultSet rs = pstm.getResultSet()) {
			while (rs.next()) {
			    Integer idUsuario = rs.getInt(1);
			    PerfilEnum perfil = PerfilEnum.valueOf(rs.getString(2));
			    String nome = rs.getString(3);
			    String cpf = rs.getString(4);
			    Date nascimento = null;//rst.getDate(5);
			    SexoEnum sexo = SexoEnum.valueOf(rs.getString(6));
			    String email = rs.getString(7);
			    String senha = rs.getString(8);
			    Integer idFuncionario = rs.getInt(9);
			    String contrato = rs.getString(10);
			    Date dataAdmissao = rs.getDate(11);
			    Date dataDemissao = rs.getDate(12);

			    Funcionario recepcionista = new Funcionario(idUsuario, email, senha, nome, cpf, nascimento, null, sexo, null, perfil, idFuncionario, contrato, dataAdmissao, dataDemissao);

			    recepcionistas.add(recepcionista);
			}
		    }
		}
		catch (SQLException e) {
		    e.printStackTrace();
		}
		
		return recepcionistas;
	}
	   
	   public List<Funcionario> listarTodosInstrutores() {
	    	List<Funcionario> instrutores = new ArrayList<>();
	    
		String sql = "SELECT u.*, f.idFuncionario, f.contrato, f.dtAdmissao, f.dtDemissao FROM Funcionario f "
			+ "INNER JOIN Usuario u ON u.idUsuario = f.idUsuario "
			+ "WHERE u.perfil = 'INSTRUTOR'";
		
		try(PreparedStatement pstm = getConnection().prepareStatement(sql)) {
		    pstm.execute();
		    
		    try (ResultSet rs = pstm.getResultSet()) {
			while (rs.next()) {
			    Integer idUsuario = rs.getInt(1);
			    PerfilEnum perfil = PerfilEnum.valueOf(rs.getString(2));
			    String nome = rs.getString(3);
			    String cpf = rs.getString(4);
			    Date nascimento = null;//rst.getDate(5);
			    SexoEnum sexo = SexoEnum.valueOf(rs.getString(6));
			    String email = rs.getString(7);
			    String senha = rs.getString(8);
			    Integer idFuncionario = rs.getInt(9);
			    String contrato = rs.getString(10);
			    Date dataAdmissao = rs.getDate(11);
			    Date dataDemissao = rs.getDate(12);

			    Funcionario instrutor = new Funcionario(idUsuario, email, senha, nome, cpf, nascimento, null, sexo, null, perfil, idFuncionario, contrato, dataAdmissao, dataDemissao);

			    
			    instrutores.add(instrutor);
			}
		    }
		}
		catch (SQLException e) {
		    e.printStackTrace();
		}
		
		return instrutores;
	}
}
