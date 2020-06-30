package uaiGym.model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uaiGym.model.Equipamento;

public class EquipamentoDAO extends Dao<Equipamento> {

    public EquipamentoDAO(Connection connection) {
	super(connection);
    }

    @Override
    public Equipamento recuperarPorId(int id) {
	Equipamento equipamento = null;

	String sql = "SELECT e.* FROM equipamento e WHERE e.idEquipamento = ?";

	try (PreparedStatement pstm = getConnection().prepareStatement(sql)) {
	    pstm.setInt(1, id);
	    pstm.execute();

	    try (ResultSet rst = pstm.getResultSet()) {

		if (rst.next()) {

		    String nomeEquipamento = rst.getString(2);

		    equipamento = new Equipamento();

		    equipamento.setNomeEquipamento(nomeEquipamento);
		    equipamento.setId(id);
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return equipamento;
    }

    @Override
    public void salvar(Equipamento entidade) {
	String sql = "{CALL sp_inserir_equipamento(?)}";

	try (CallableStatement stms = getConnection().prepareCall(sql)) {

	    stms.setString(1, entidade.getNomeEquipamento());
	    stms.executeQuery();

	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public void excluir(int id) {
	String sql = "{CALL sp_deleta_equipamento(?)}";

	try (CallableStatement stms = getConnection().prepareCall(sql)) {

	    stms.setInt(1, id);

	    stms.executeQuery();

	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public List<Equipamento> listarTodos() {
	List<Equipamento> equipamentos = new ArrayList<Equipamento>();

	String sql = "SELECT e.* FROM equipamento e";

	try (PreparedStatement pstm = getConnection().prepareStatement(sql)) {
	    try (ResultSet rst = pstm.getResultSet()) {
		while (rst.next()) {
		    int id = rst.getInt(1);
		    Equipamento equipamento = recuperarPorId(id);
		    equipamentos.add(equipamento);

		}

	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return equipamentos;
    }

    @Override
    public void atualizar(Equipamento entidade) {
	String sql = "{CALL sp_inserir_equipamento(?,?)}";

	try (CallableStatement stms = getConnection().prepareCall(sql)) {

	    stms.setInt(1, entidade.getId());
	    stms.setString(2, entidade.getNomeEquipamento());
	    stms.executeQuery();

	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

}
