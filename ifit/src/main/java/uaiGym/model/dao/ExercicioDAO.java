package uaiGym.model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uaiGym.model.Equipamento;
import uaiGym.model.Exercicio;

public class ExercicioDAO extends Dao<Exercicio> {

    public ExercicioDAO(Connection connection) {
	super(connection);
    }

    @Override
    public Exercicio recuperarPorId(int id) {
	Exercicio exercicio = null;

	String sql = "SELECT e.* FROM exercicio e WHERE e.idExercicio = ?";

	try (PreparedStatement pstm = getConnection().prepareStatement(sql)) {
	    pstm.setInt(1, id);
	    pstm.execute();

	    try (ResultSet rst = pstm.getResultSet()) {

		if (rst.next()) {
		    
		    EquipamentoDAO equiDAO = new EquipamentoDAO(getConnection());

		    String nomeExercicio = rst.getString(3);
		    int idEquipamento = rst.getInt(2);
		    Equipamento equipamento = equiDAO.recuperarPorId(idEquipamento);

		    exercicio = new Exercicio();

		    exercicio.setNomeExercico(nomeExercicio);
		    exercicio.setEquipamento(equipamento);
		    exercicio.setId(id);
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return exercicio;
    }

    @Override
    public void salvar(Exercicio entidade) {
	String sql = "{CALL sp_inserir_exercicio(?,?)}";

	try (CallableStatement stms = getConnection().prepareCall(sql)) {

	    stms.setInt(1, entidade.getEquipamento().getId());
	    stms.setString(2, entidade.getNomeExercicio());
	    stms.executeQuery();

	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public void excluir(int id) {
	String sql = "{CALL sp_deleta_exercicio(?)}";

	try (CallableStatement stms = getConnection().prepareCall(sql)) {

	    stms.setInt(1, id);

	    stms.executeQuery();

	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public List<Exercicio> listarTodos() {
	List<Exercicio> exercicios = new ArrayList<Exercicio>();

	String sql = "SELECT e.*, eq.nome AS nomeEquipamento FROM exercicio e INNER JOIN equipamento eq ON eq.idEquipamento = e.idExercicio";

	try (PreparedStatement pstm = getConnection().prepareStatement(sql)) {
	    try (ResultSet rst = pstm.getResultSet()) {
		while (rst.next()) {
		    int id = rst.getInt(1);
		    Exercicio exercicio = recuperarPorId(id);
		    exercicios.add(exercicio);

		}

	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return exercicios;
    }

    @Override
    public void atualizar(Exercicio entidade) {
	String sql = "{CALL sp_inserir_exercicio(?,?,?)}";

	try (CallableStatement stms = getConnection().prepareCall(sql)) {

	    stms.setInt(1, entidade.getId());
	    stms.setInt(2, entidade.getEquipamento().getId());
	    stms.setString(3, entidade.getNomeExercicio());
	    stms.executeQuery();

	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

}
