package uaiGym.model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import uaiGym.model.Endereco;
import uaiGym.model.enuns.EstadoEnum;

public class EnderecoDAO extends Dao<Endereco> {

    public EnderecoDAO(Connection connection) {
	super(connection);
    }

    @Override
    public Endereco recuperarPorId(int id) {

	Endereco endereco = null;

	String sql = "SELECT e.* FROM endereco e " + "WHERE e.idEndereco = ? ";

	try (PreparedStatement pstm = getConnection().prepareStatement(sql)) {
	    pstm.setInt(1, id);
	    pstm.execute();

	    try (ResultSet rst = pstm.getResultSet()) {

		if (rst.next()) {

		    Integer idEnd = rst.getInt(1);
		    String logradouro = rst.getString(3);
		    String numero = rst.getString(4);
		    String complemento = rst.getString(5);
		    String bairro = rst.getString(6);
		    String cidade = rst.getString(7);
		    EstadoEnum estado = EstadoEnum.valueOf(rst.getString(8));
		    String cep = rst.getString(9);

		    endereco = new Endereco(idEnd, logradouro, numero, complemento, bairro, cidade, cep, estado);

		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}

	return endereco;
    }

    @Override
    public void salvar(Endereco entidade) {

	String sql = "{CALL sp_inserir_endereco(?,?,?,?,?,?,?,?)}";

	try (CallableStatement stms = getConnection().prepareCall(sql)) {

	    stms.setInt(1, entidade.getId());
	    stms.setString(2, entidade.getRua());
	    stms.setString(3, entidade.getNumero());
	    stms.setString(4, entidade.getComplemento());
	    stms.setString(5, entidade.getBairro());
	    stms.setString(6, entidade.getCidade());
	    stms.setString(7, entidade.getEstado().toString());
	    stms.setString(8, entidade.getCep());

	    stms.executeQuery();

	} catch (SQLException e) {
	    e.printStackTrace();
	}

    }

    @Override
    public void atualizar(Endereco entidade) {
	String sql = "{CALL sp_atualiza_endereco(?,?,?,?,?,?,?,?)}";

	try (CallableStatement stms = getConnection().prepareCall(sql)) {

	    stms.setInt(1, entidade.getId());
	    stms.setString(2, entidade.getRua());
	    stms.setString(3, entidade.getNumero());
	    stms.setString(4, entidade.getComplemento());
	    stms.setString(5, entidade.getBairro());
	    stms.setString(6, entidade.getCidade());
	    stms.setString(7, entidade.getEstado().toString());
	    stms.setString(8, entidade.getCep());

	    stms.executeQuery();

	} catch (SQLException e) {
	    e.printStackTrace();
	}

    }

    @Override
    public void excluir(int id) {
    }

    @Override
    public List<Endereco> listarTodos() {
	return null;
    }
}
