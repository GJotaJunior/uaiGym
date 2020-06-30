package uaiGym.model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uaiGym.model.Telefone;

public class TelefoneDAO extends Dao<Telefone> {
    
    public TelefoneDAO(Connection connection) {
	super(connection);
    }

    @Override
    public Telefone recuperarPorId(int id) {
	Telefone telefone = null;
	
	String sql = "SELECT t.* FROM Telefone t WHERE t.idTelefone = ?";
	
	try (PreparedStatement pstm = getConnection().prepareStatement(sql)) {
	    pstm.setInt(1, id);
	    pstm.execute();

	    try (ResultSet rst = pstm.getResultSet()) {

		if (rst.next()) {

		    Integer idTelefone = rst.getInt(1);
		    Integer idUsuario = rst.getInt(2);
		    String numeroTelefone = rst.getString(3);
		    
		    telefone = new Telefone(idTelefone, idUsuario, numeroTelefone);
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	
	return telefone;
    }

    @Override
    public void salvar(Telefone telefone) {
	String sql = "{CALL sp_inserir_telefone(?,?)}";
	
	try(CallableStatement stms = getConnection().prepareCall(sql)) {
	    stms.setInt(1, telefone.getIdUsuario());
	    stms.setString(2, telefone.getTelefone());
	    
	    stms.execute();
	} catch (SQLException e) {
	    e.printStackTrace();
	} 
    }

    @Override
    public void excluir(int id) {
	String sql = "DELETE FROM Telefone WHERE idTelefone = ?";
	
	try(PreparedStatement pstm = getConnection().prepareCall(sql)){
	    pstm.setInt(1, id);
	    pstm.execute();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	
    }

    @Override
    public List<Telefone> listarTodos() {
	List<Telefone> listaTelefone = new ArrayList<Telefone>();
	
	String sql = "SELECT t.* FROM Telefone t";
	
	try(PreparedStatement pstm = getConnection().prepareStatement(sql)){
	    pstm.execute();
	    
	    try(ResultSet rs = pstm.getResultSet()){
		while(rs.next()) {
		    Telefone telefone = new Telefone(rs.getInt(1), rs.getInt(2), rs.getString(3));
		    
		    listaTelefone.add(telefone);
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return listaTelefone;
    }

    @Override
    public void atualizar(Telefone telefone) {
	String sql = "{CALL sp_atualiza_telefone(?, ?)}";
	
	try(CallableStatement stms = getConnection().prepareCall(sql)){
	    stms.setInt(1, telefone.getIdTelefone());
	    stms.setString(2, telefone.getTelefone());
	    
	    stms.execute();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	
    }
    
    public List<Telefone> listarTelefonesUsuario(int id) {
	List<Telefone> listaTelefone = new ArrayList<Telefone>();
	
	String sql = "SELECT t.* FROM telefone WHERE t.idUsuario = ?";
	
	try(PreparedStatement pstm = getConnection().prepareStatement(sql)){
	    pstm.setInt(1, id);
	    
	    pstm.execute();
	    
	    try(ResultSet rs = pstm.getResultSet()){
		while(rs.next()) {
		    Telefone telefone = new Telefone(rs.getInt(1), rs.getInt(2), rs.getString(3));
		    
		    listaTelefone.add(telefone);
		}
	    } 
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	
	return listaTelefone;
    }

}
