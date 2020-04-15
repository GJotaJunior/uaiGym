package uaiGym.model.dao;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import uaiGym.model.Endereco;
import uaiGym.model.enuns.EstadoEnum;
import uaiGym.service.EncryptionService;

public abstract class UsuarioDAO<E> extends Dao<E> {

    public UsuarioDAO(Connection connection) {
	super(connection);
    }

    protected Endereco getEnderecoPorId(Integer id) {
	Endereco endereco = null;

	String sql = "SELECT * FROM Endereco WHERE idUsuario = ?";

	try (PreparedStatement pstm = getConnection().prepareStatement(sql)) {
	    pstm.setInt(1, id);
	    pstm.execute();
	    try (ResultSet rst = pstm.getResultSet()) {
		if (rst.next()) {
		    String logradouro = rst.getString(3);
		    Integer numero = rst.getInt(4);
		    String complemento = rst.getString(5);
		    String bairro = rst.getString(6);
		    String cidade = rst.getString(7);
		    String cep = rst.getString(9);
		    EstadoEnum estado = EstadoEnum.valueOf(rst.getString(8));

		    endereco = new Endereco(logradouro, numero, complemento, bairro, cidade, cep, estado);
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return endereco;
    }

    public Integer getQuantUsuarios() {
	String sql = "SELECT Count(*) FROM Usuario";

	try (PreparedStatement pstm = getConnection().prepareStatement(sql)) {
	    pstm.execute();
	    try (ResultSet rst = pstm.getResultSet()) {
		if (rst.next()) {
		    int quantidadeUsuario = rst.getInt(1);
		    return quantidadeUsuario;
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}

	return null;
    }

    protected Set<String> getTelefonesPorId(Integer id) {
	Set<String> telefones = new HashSet<String>();

	String sql = "SELECT telefone FROM Telefone WHERE idUsuario = ?";

	try (PreparedStatement pstm = getConnection().prepareStatement(sql)) {
	    pstm.setInt(1, id);
	    pstm.execute();

	    try (ResultSet rst = pstm.getResultSet()) {
		while (rst.next()) {
		    String telefone = rst.getString(1);
		    telefones.add(telefone);
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return telefones;
    }

    public String getNomeByUrl(String url) throws NumberFormatException, UnsupportedEncodingException {
	String nome = null;

	String sql = "SELECT nome FROM Usuario WHERE idUsuario = ?";

	Integer id = Integer.parseInt(EncryptionService.decrypt(url));

	try (PreparedStatement pstm = getConnection().prepareStatement(sql)) {
	    pstm.setInt(1, id);
	    pstm.execute();

	    nome = String.valueOf(pstm.getResultSet());

	} catch (SQLException e) {
	    e.printStackTrace();
	}

	return nome;
    }
}
