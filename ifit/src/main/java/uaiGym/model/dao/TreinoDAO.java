package uaiGym.model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uaiGym.model.Exercicio;
import uaiGym.model.Treino;

public class TreinoDAO extends Dao<Treino> {

    public TreinoDAO(Connection connection) {
	super(connection);
    }

    @Override
    public Treino recuperarPorId(int id) {
	Treino treino = null;

	String sql = "SELECT t.*, et.qtRepeticao, et.qtSerie, ex.idExercicio, ex.nome AS nomeExercicio "
		+ " FROM treino t " + " INNER JOIN exerciciostreino et ON et.idTreino = t.idTreino "
		+ " INNER JOIN exercicio ex ON ex.idExercicio = et.idExercicio WHERE t.idTreino = ?";

	try (PreparedStatement pstm = getConnection().prepareStatement(sql)) {
	    pstm.setInt(1, id);
	    pstm.execute();

	    try (ResultSet rst = pstm.getResultSet()) {

		if (rst.next()) {

		    String nomeTreino = rst.getString(2);
		    Integer qtRepeticao = rst.getInt(3);
		    Integer qtSerie = rst.getInt(4);

		    ExercicioDAO exeDAO = new ExercicioDAO(getConnection());
		    List<Exercicio> exercicios = exeDAO.buscarTodosPorIdTreino(rst.getInt(5));

		    treino = new Treino();

		    treino.setNomeTreino(nomeTreino);
		    treino.setQtRepeticao(qtRepeticao);
		    treino.setQtSerie(qtSerie);
		    treino.setExercicios(exercicios);
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return treino;
    }

    @Override
    public void salvar(Treino entidade) {
	String sql = "{CALL sp_inserir_treino(?)}";

	try (CallableStatement stms = getConnection().prepareCall(sql)) {

	    stms.setString(1, entidade.getNomeTreino());
	    stms.executeQuery();

	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public void excluir(int id) {
	String sql = "{CALL sp_deleta_treino(?)}";

	try (CallableStatement stms = getConnection().prepareCall(sql)) {

	    stms.setInt(1, id);

	    stms.executeQuery();

	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public List<Treino> listarTodos() {
	List<Treino> treinos = new ArrayList<Treino>();

	String sql = "SELECT t.* FROM treino t";

	try (PreparedStatement pstm = getConnection().prepareStatement(sql)) {
	    try (ResultSet rst = pstm.getResultSet()) {
		while (rst.next()) {
		    int id = rst.getInt(1);
		    Treino treino = recuperarPorId(id);
		    treinos.add(treino);

		}

	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return treinos;
    }

    @Override
    public void atualizar(Treino entidade) {
	String sql = "{CALL sp_atualiza_treino(?,?,?,?,?,?)}";

	try (CallableStatement stms = getConnection().prepareCall(sql)) {

	    stms.setInt(1, entidade.getId());
	    stms.setString(2, entidade.getNomeTreino());
	    stms.executeQuery();

	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    public List<Treino> buscarTreinosPorIdUsuario(int id) {
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
		    // treino.setDtTreino(rs.getDate(1));

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

	String sql = "SELECT et.qtRepeticao, et.qtSerie, e.nome, equip.nome " + "FROM ExerciciosTreino et "
		+ "INNER JOIN Exercicio e ON e.idExercicio = et.idExercicio "
		+ "INNER JOIN Equipamento equip ON equip.idEquipamento = e.idEquipamento " + "WHERE et.idTreino = ?";

	try (PreparedStatement pstm = getConnection().prepareStatement(sql)) {
	    pstm.setInt(1, id);
	    pstm.execute();

	    try (ResultSet rs = pstm.getResultSet()) {
		while (rs.next()) {
		    Exercicio exercicio = new Exercicio();
		    // exercicio.setNomeEquipamento(rs.getString(4));
		    exercicio.setNomeExercico(rs.getString(3));
		    // exercicio.setQtSerie(rs.getInt(2));
		    // exercicio.setQtRepeticao(rs.getInt(1));

		    exercicios.add(exercicio);
		}
	    }

	} catch (SQLException e) {
	    e.printStackTrace();
	}

	return exercicios;
    }

}
