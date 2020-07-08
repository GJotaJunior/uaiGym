package uaiGym.model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import uaiGym.model.ContatoDeEmergencia;
import uaiGym.model.Telefone;
import uaiGym.model.enuns.SexoEnum;
import uaiGym.model.pessoa.Aluno;
import uaiGym.service.DataBase.DatabaseUtils;

public class AlunoDAO extends UsuarioDAO<Aluno> {

    public AlunoDAO(Connection connection) {
	super(connection);
    }

    @Override
    public Aluno recuperarPorId(int id) {
	Aluno aluno = null;

	String sql = "SELECT u.*, a.idAluno, a.matricula, a.status FROM Aluno a INNER JOIN Usuario u ON a.idUsuario = u.idUsuario WHERE a.idUsuario = ?";

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
		    Integer idAluno = rst.getInt(9);
		    String matricula = rst.getString(10);
		    boolean estaAtivo = rst.getString(11).equals("ATIVO");

		    Set<ContatoDeEmergencia> contatos = new ContatoDeEmergenciaDAO(getConnection())
			    .buscarContatosDeEmergenciaPorIdUsuario(id);

		    aluno = new Aluno(id, email, senha, nome, cpf, nascimento, getTelefonesPorId(id), sexo,
			    getEnderecoPorId(id), idAluno, matricula, null, null, estaAtivo, contatos);
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}

	return aluno;
    }

    @Override
    public void salvar(Aluno entidade) {

	String sql = "{CALL sp_inserir_aluno(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

	try (CallableStatement stms = getConnection().prepareCall(sql)) {
	    stms.setString(1, entidade.getPerfil().toString());
	    stms.setString(2, entidade.getNome());
	    stms.setString(3, entidade.getCpf());
	    stms.setDate(4, DatabaseUtils.converteData(entidade.getDtNascimento()));
	    stms.setString(5, entidade.getSexo().toString());
	    stms.setString(6, entidade.getEmail());
	    stms.setString(7, entidade.getSenha());
	    stms.setString(8, entidade.getMatricula());
	    stms.setString(9, (entidade.isEstaAtivo()) ? "ATIVO" : "INATIVO");

	    stms.setString(10, entidade.getEndereco().getRua());
	    stms.setString(11, entidade.getEndereco().getNumero().toString());
	    stms.setString(12, entidade.getEndereco().getComplemento());
	    stms.setString(13, entidade.getEndereco().getBairro());
	    stms.setString(14, entidade.getEndereco().getCidade());
	    stms.setString(15, entidade.getEndereco().getEstado().toString());
	    stms.setString(16, entidade.getEndereco().getCep());

	    stms.executeQuery();
	    
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
	    
	    String sqlLastIDAluno = "SELECT MAX(idAluno) AS lastID FROM aluno";

	    try (PreparedStatement pstmLastIDAluno = getConnection().prepareStatement(sqlLastIDAluno)) {
		pstmLastIDAluno.execute();

		try (ResultSet rstLastID = pstmLastIDAluno.getResultSet()) {

		    if (rstLastID.next()) {
			int lastIDAluno = rstLastID.getInt(1);
			entidade.setIdAluno(lastIDAluno);;
		    }
		}
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}

	Set<String> telefones = entidade.getTelefone();

	if (!telefones.isEmpty()) {

	    TelefoneDAO telDao = new TelefoneDAO(getConnection());

	    for (String numTelefone : telefones) {

		Telefone telefone = new Telefone(entidade.getId(), numTelefone);

		telDao.salvar(telefone);
	    }
	}
	
	Set<ContatoDeEmergencia> contatosEmergencia = entidade.getContatosDeEmergencia();

	if (!contatosEmergencia.isEmpty()) {

	    ContatoDeEmergenciaDAO contatoDao = new ContatoDeEmergenciaDAO(getConnection());

	    for (ContatoDeEmergencia contato : contatosEmergencia) {
		contato.setIdAluno(entidade.getIdAluno());
		contatoDao.salvar(contato);
	    }
	}

    }

    @Override
    public void excluir(int id) {
	String sql = "{CALL sp_atualiza_aluno(?)}";

	try (CallableStatement stms = getConnection().prepareCall(sql)) {
	    stms.setInt(1, id);

	    stms.executeQuery();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public List<Aluno> listarTodos() {
	List<Aluno> alunos = new ArrayList<Aluno>();

	String sql = "SELECT u.*, a.idAluno, a.matricula, a.status FROM Aluno a INNER JOIN Usuario u ON a.idUsuario = u.idUsuario ORDER BY u.nome";

	try (PreparedStatement pstm = getConnection().prepareStatement(sql)) {
	    pstm.execute();

	    try (ResultSet rst = pstm.getResultSet()) {
		while (rst.next()) {
		    int id = rst.getInt(1);

		    Aluno aluno = recuperarPorId(id);
		    alunos.add(aluno);
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}

	return alunos;
    }

    @Override
    public void atualizar(Aluno entidade) {
	String sql = "{CALL sp_atualiza_aluno(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

	try (CallableStatement stms = getConnection().prepareCall(sql)) {
	    stms.setString(1, entidade.getPerfil().toString());
	    stms.setString(2, entidade.getNome());
	    stms.setString(3, entidade.getCpf());
	    stms.setDate(4, DatabaseUtils.converteData(entidade.getDtNascimento()));
	    stms.setString(5, entidade.getSexo().toString());
	    stms.setString(6, entidade.getEmail());
	    stms.setString(7, entidade.getSenha());
	    stms.setString(8, entidade.getMatricula());
	    stms.setString(9, (entidade.isEstaAtivo()) ? "ATIVO" : "INATIVO");

	    stms.setString(10, entidade.getEndereco().getRua());
	    stms.setString(11, entidade.getEndereco().getNumero().toString());
	    stms.setString(12, entidade.getEndereco().getComplemento());
	    stms.setString(13, entidade.getEndereco().getBairro());
	    stms.setString(14, entidade.getEndereco().getCidade());
	    stms.setString(15, entidade.getEndereco().getEstado().toString());
	    stms.setString(16, entidade.getEndereco().getCep());

	    stms.executeQuery();
	} catch (SQLException e) {
	    e.printStackTrace();
	}

    }

}
