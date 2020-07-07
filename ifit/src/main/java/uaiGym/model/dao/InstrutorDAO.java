package uaiGym.model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uaiGym.model.Telefone;
import uaiGym.model.enuns.PerfilEnum;
import uaiGym.model.enuns.SexoEnum;
import uaiGym.model.pessoa.Aluno;
import uaiGym.model.pessoa.Instrutor;
import uaiGym.service.DataBase.DatabaseUtils;

public class InstrutorDAO extends UsuarioDAO<Instrutor> {

    public InstrutorDAO(Connection connection) {
	super(connection);
    }

    @Override
    public void salvar(Instrutor entidade) {

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
	    stms.setString(12, entidade.getEndereco().getNumero());
	    stms.setString(13, entidade.getEndereco().getComplemento());
	    stms.setString(14, entidade.getEndereco().getBairro());
	    stms.setString(15, entidade.getEndereco().getCidade());
	    stms.setString(16, entidade.getEndereco().getEstado().toString());
	    stms.setString(17, entidade.getEndereco().getCep());

	    stms.executeQuery();

	} catch (SQLException e) {
	    e.printStackTrace();
	}

	Set<String> telefones = entidade.getTelefone();

	if (!telefones.isEmpty()) {

	    String sqlLastID = "SELECT MAX(idUsuario) AS lastID FROM usuario";

	    try (PreparedStatement pstmLastID = getConnection().prepareStatement(sqlLastID)) {
		pstmLastID.execute();

		try (ResultSet rstLastID = pstmLastID.getResultSet()) {

		    if (rstLastID.next()) {
			int lastID = rstLastID.getInt(1);
			entidade.setId(lastID);
		    }
		}
	    } catch (SQLException e) {
		e.printStackTrace();
	    }

	    TelefoneDAO telDao = new TelefoneDAO(getConnection());

	    for (String numTelefone : telefones) {

		Telefone telefone = new Telefone(entidade.getId(), numTelefone);

		telDao.salvar(telefone);
	    }
	}

    }

    @Override
    public void atualizar(Instrutor entidade) {

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
    public List<Instrutor> listarTodos() {
	List<Instrutor> instrutores = new ArrayList<Instrutor>();
	String sql = "SELECT u.*, f.contrato, f.dtAdmissao, f.dtDemissao " + "FROM Funcionario f "
		+ "INNER JOIN Usuario u ON f.idUsuario = u.idUsuario " + "WHERE u.perfil = 'INSTRUTOR'";

	try (PreparedStatement pstm = getConnection().prepareStatement(sql)) {
	    pstm.execute();

	    try (ResultSet rst = pstm.getResultSet()) {

		while (rst.next()) {
		    int id = rst.getInt(1);

		    Instrutor instrutor = recuperarPorId(id);

		    instrutores.add(instrutor);
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return instrutores;
    }

    private Set<Aluno> getAlunosPorIdInstrutor(int id) {
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

}
