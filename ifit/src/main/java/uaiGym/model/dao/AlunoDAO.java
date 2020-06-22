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

import uaiGym.model.AvaliacaoFisica;
import uaiGym.model.ContatoDeEmergencia;
import uaiGym.model.Exercicio;
import uaiGym.model.MedidasCorporais;
import uaiGym.model.Treino;
import uaiGym.model.enuns.ParentescoEnum;
import uaiGym.model.enuns.SexoEnum;
import uaiGym.model.pessoa.Aluno;
import uaiGym.model.pessoa.Instrutor;
import uaiGym.service.DataBase.DatabaseUtils;

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
		    String email = rst.getString(7);
		    String senha = rst.getString(8);
		    String nome = rst.getString(3);
		    String cpf = rst.getString(4);
		    Date nascimento = rst.getDate(5);
		    SexoEnum sexo = SexoEnum.valueOf(rst.getString(6));
		    String matricula = rst.getString(9);
		    Instrutor instrutor = new InstrutorDAO(getConnection()).getInstrutorPorIdDoAluno(id);
		    boolean estaAtivo = rst.getString(10).equals("ATIVO");

		    aluno = new Aluno(email, senha, nome, cpf, nascimento, getTelefonesPorId(id), sexo,
			    getEnderecoPorId(id), matricula, instrutor, getAvaliacoesPorId(id), getTreinosPorId(id),
			    estaAtivo, getContatosDeEmergenciaPorId(id));
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
		    String nome = rst.getString(3);
		    String telefone = rst.getString(4);
		    ParentescoEnum parentesco = ParentescoEnum.valueOf(rst.getString(5));

		    ContatoDeEmergencia contatoDeEmergencia = new ContatoDeEmergencia(nome, telefone, parentesco);
		    contatos.add(contatoDeEmergencia);
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}

	return contatos;
    }

    private List<Treino> getTreinosPorId(Integer id) {
	List<Treino> treinos = new ArrayList<>();

	String sql = "SELECT at.dtTreino, t.nome, t.idTreino" + " FROM AlunoTreino at"
		+ " INNER JOIN Aluno a ON a.idAluno = at.idAluno" + " INNER JOIN Treino t ON t.idTreino = at.idTreino"
		+ " WHERE a.idUsuario = ?";

	try (PreparedStatement pstm = getConnection().prepareStatement(sql)) {
	    pstm.setInt(1, id);
	    pstm.execute();

	    try (ResultSet rs = pstm.getResultSet()) {
		while (rs.next()) {
		    Treino treino = new Treino();
		    treino.setNomeTreino(rs.getString(2));
		    treino.setExercicios(getExerciciosTreino(rs.getInt(3)));
		    treino.setDtTreino(rs.getDate(1));

		    treinos.add(treino);
		}
	    }

	} catch (SQLException e) {
	    e.printStackTrace();
	}

	return treinos;
    }

    private List<Exercicio> getExerciciosTreino(Integer id) {
	List<Exercicio> exercicios = new ArrayList<>();
	
	String sql = "SELECT et.qtRepeticao, et.qtSerie, e.nome, equip.nome "
		+ "FROM ExerciciosTreino et "
		+ "INNER JOIN Exercicio e ON e.idExercicio = et.idExercicio "
		+ "INNER JOIN Equipamento equip ON equip.idEquipamento = e.idEquipamento "
		+ "WHERE et.idTreino = ?";
	
	try (PreparedStatement pstm = getConnection().prepareStatement(sql)) {
	    pstm.setInt(1, id);
	    pstm.execute();
	    
	    try (ResultSet rs = pstm.getResultSet()) {
		while(rs.next()) {
		    Exercicio exercicio = new Exercicio();
		    exercicio.setNomeEquipamento(rs.getString(4));
		    exercicio.setNomeExercico(rs.getString(3));
		    exercicio.setQtSerie(rs.getInt(2));
		    exercicio.setQtRepeticao(rs.getInt(1));
		    
		    exercicios.add(exercicio);
		}
	    }
	    
	} catch (SQLException e) {
	    e.printStackTrace();
	}

	return exercicios;
    }

    private Set<AvaliacaoFisica> getAvaliacoesPorId(int id) {

	Set<AvaliacaoFisica> avaliacoes = new HashSet<AvaliacaoFisica>();

	String sql = "SELECT ava.* " + "FROM Avaliacao ava " + "INNER JOIN Aluno a ON ava.idAluno = a.idAluno "
		+ "INNER JOIN Usuario u ON a.idUsuario = u.idUsuario WHERE u.idUsuario = ?";

	try (PreparedStatement pstm = getConnection().prepareStatement(sql)) {

	    pstm.setInt(1, id);
	    pstm.execute();

	    try (ResultSet rst = pstm.getResultSet()) {

		while (rst.next()) {
		    int idInstrutor = rst.getInt(3);
		    Instrutor instrutor = new InstrutorDAO(getConnection()).recuperarPorId(idInstrutor);
		    Date data = rst.getDate(4);
		    float altura = rst.getFloat(5);
		    float peso = rst.getFloat(6);
		    float gorduraPercentual = rst.getFloat(7);
		    float residuosPercentual = rst.getFloat(8);
		    float musculoPercentual = rst.getFloat(9);
		    MedidasCorporais medidas = new MedidasCorporais(altura, peso, gorduraPercentual, residuosPercentual,
			    musculoPercentual);

		    AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica(instrutor, data, medidas);

		    avaliacoes.add(avaliacaoFisica);
		}
	    }

	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return avaliacoes;
    }

    @Override
    public void salvar(Aluno entidade) {
	// Exemplo de como seria a chamada da procedure de listagem de aluno por Id
	// Tutorial que me ajudou
	// https://www.mysqltutorial.org/calling-mysql-stored-procedures-from-jdbc/W

	String sql = "{CALL sp_inserirAluno(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

	try (CallableStatement stms = getConnection().prepareCall(sql)) {
	    stms.setString(1, entidade.getPerfil().toString());
	    stms.setString(2, entidade.getNome());
	    stms.setString(3, entidade.getCpf());
	    stms.setDate(4,  DatabaseUtils.converteData(entidade.getDtNascimento()));
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

	String sql = "SELECT u.*, a.matricula, a.status FROM Aluno a INNER JOIN Usuario u ON a.idUsuario = u.idUsuario";

	try (PreparedStatement pstm = getConnection().prepareStatement(sql)) {
	    pstm.execute();

	    try (ResultSet rst = pstm.getResultSet()) {
		while (rst.next()) {
		    int id = rst.getInt(1);
		    String email = rst.getString(7);
		    String senha = rst.getString(8);
		    String nome = rst.getString(3);
		    String cpf = rst.getString(4);
		    Date nascimento = rst.getDate(5);
		    SexoEnum sexo = SexoEnum.valueOf(rst.getString(6));
		    String matricula = rst.getString(9);
		    Instrutor instrutor = new InstrutorDAO(getConnection()).getInstrutorPorIdDoAluno(id);
		    boolean estaAtivo = rst.getString(10).equals("ATIVO");

		    Aluno aluno = new Aluno(email, senha, nome, cpf, nascimento, getTelefonesPorId(id), sexo,
			    getEnderecoPorId(id), matricula, instrutor, getAvaliacoesPorId(id), getTreinosPorId(id),
			    estaAtivo, getContatosDeEmergenciaPorId(id));

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
	String sql = "{CALL sp_atualiza_aluno(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

	try (CallableStatement stms = getConnection().prepareCall(sql)) {
	    stms.setInt(1, entidade.getId());
	    stms.setString(2, entidade.getPerfil().toString());
	    stms.setString(3, entidade.getNome());
	    stms.setString(4, entidade.getCpf());
	    stms.setDate(5, DatabaseUtils.converteData(entidade.getDtNascimento()));
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

}
