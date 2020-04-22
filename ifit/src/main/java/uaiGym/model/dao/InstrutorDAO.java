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

import uaiGym.model.enuns.PerfilEnum;
import uaiGym.model.enuns.SexoEnum;
import uaiGym.model.pessoa.Aluno;
import uaiGym.model.pessoa.Instrutor;

public class InstrutorDAO extends UsuarioDAO<Instrutor> {

    public InstrutorDAO(Connection connection) {
	super(connection);
    }

    @Override
    public void salvar(Instrutor entidade) {

	System.out.println("Salvar inicio");

	String sql = "{CALL sp_inserirFuncionario(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

	try (CallableStatement stms = getConnection().prepareCall(sql)) {

	    stms.setString(1, entidade.getPerfil().toString());
	    stms.setString(2, entidade.getNome());
	    stms.setString(3, entidade.getCpf());
	    stms.setDate(4,
		    (entidade.getDtNascimento() != null) ? new java.sql.Date(entidade.getDtNascimento().getTime())
			    : null);
	    stms.setString(5, entidade.getSexo().toString());
	    stms.setString(6, entidade.getEmail());
	    stms.setString(7, entidade.getSenha());
	    stms.setString(8, entidade.getContrato());
	    stms.setDate(9,
		    (entidade.getAdmissao() != null) ? new java.sql.Date(entidade.getAdmissao().getTime()) : null);
	    stms.setDate(10,
		    (entidade.getDemissao() != null) ? new java.sql.Date(entidade.getDemissao().getTime()) : null);
	    stms.setString(11, entidade.getEndereco().getRua());
	    stms.setString(12, entidade.getEndereco().getNumero());
	    stms.setString(13, entidade.getEndereco().getComplemento());
	    stms.setString(14, entidade.getEndereco().getBairro());
	    stms.setString(15, entidade.getEndereco().getCidade());
	    stms.setString(16, entidade.getEndereco().getEstado().toString());
	    stms.setString(17, entidade.getEndereco().getCep());

	    stms.executeQuery();

	    System.out.println("Salvar fim");

	} catch (SQLException e) {
	    e.printStackTrace();
	}

    }

    @Override
    public Instrutor recuperarPorId(int id) {

	Instrutor instrutor = null;

	String sql = "SELECT u.*, f.contrato, f.dtAdmissao, f.dtDemissao " + "FROM Funcionario f "
		+ "INNER JOIN Usuario u ON f.idUsuario = u.idUsuario "
		+ "WHERE f.idUsuario = ? AND u.perfil = 'INSTRUTOR'";

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

		    instrutor = new Instrutor();

		    instrutor.setEmail(email);
		    instrutor.setSenha(senha);
		    instrutor.setNome(nome);
		    instrutor.setCpf(cpf);
		    instrutor.setDtNascimento(nascimento);
		    instrutor.setSexo(sexo);
		    instrutor.setPerfil(perfil);
		    instrutor.setContrato(contrato);
		    instrutor.setAdmissao(dataAdmissao);
		    instrutor.setDemissao(dataDemissao);
		    
		    Set<Aluno> alunos = getAlunosPorIdInstrutor(id);
		    
		    instrutor.setAlunos(alunos);
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}

	return instrutor;
    }

    public Set<Aluno> getAlunosPorIdInstrutor(int id) {
	Set<Aluno> alunos = new HashSet<Aluno>();

	String sql = "SELECT a.idAluno " 
		+ "FROM AlunoTreino alt "
		+ "INNER JOIN Funcionario f ON alt.idFuncionario = f.idFuncionario "
		+ "INNER JOIN Aluno a ON alt.idAluno = a.idAluno " 
		+ "WHERE f.idUsuario = ?";

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

	String sql = "SELECT f.idUsuario "
		+ "FROM AlunoTreino alt "
		+ "INNER JOIN Funcionario f ON alt.idFuncionario = f.idFuncionario "
		+ "INNER JOIN Aluno a ON alt.idAluno = a.idAluno " 
		+ "WHERE a.idUsuario = ?";

	try (PreparedStatement pstm = getConnection().prepareStatement(sql)) {
	    pstm.setInt(1, idAluno);
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
