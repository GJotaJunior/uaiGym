package uaiGym.model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uaiGym.model.ContatoDeEmergencia;
import uaiGym.model.enuns.ParentescoEnum;

public class ContatoDeEmergenciaDAO extends Dao<ContatoDeEmergencia>{

    public ContatoDeEmergenciaDAO(Connection connection) {
	super(connection);
    }

    @Override
    public ContatoDeEmergencia recuperarPorId(int id) {
	ContatoDeEmergencia contato = null;
	String sql = "SELECT c.* FROM Contato c WHERE c.idContato = ?";
	
	try(PreparedStatement pstm = getConnection().prepareStatement(sql)){
	    pstm.setInt(1, id);
	    
	    pstm.execute();
	    
	    try(ResultSet rs = pstm.getResultSet()){
		if(rs.next()) {
		    contato = new ContatoDeEmergencia(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), ParentescoEnum.valueOf(rs.getString(5)));
		}
	    }
	}
	catch (SQLException e) {
	    e.printStackTrace();
	}
	
	return contato;
    }

    @Override
    public void salvar(ContatoDeEmergencia contato) {
	String sql = "{CALL sp_inserir_contato(?, ?, ?, ?)}";
	
	try(CallableStatement stms = getConnection().prepareCall(sql)){
	    stms.setInt(1, contato.getIdAluno());
	    stms.setString(2, contato.getNome());
	    stms.setString(3, contato.getTelefone());
	    stms.setString(4, String.valueOf(contato.getParentesco()));
	    
	    stms.execute();
	}
	catch(SQLException e) {
	    e.printStackTrace();
	}
	
    }

    @Override
    public void excluir(int id) {
	String sql = "DELETE FROM Contato WHERE idContato = ?";
	
	try(PreparedStatement pstm = getConnection().prepareStatement(sql)){
	    pstm.setInt(1, id);
	    
	    pstm.execute();
	}
	catch(SQLException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public List<ContatoDeEmergencia> listarTodos() {
	List<ContatoDeEmergencia> listaContatos = new ArrayList<ContatoDeEmergencia>();
	
	String sql = "SELECT c.* FROM Contato";
	
	try(PreparedStatement pstm = getConnection().prepareStatement(sql)){
	    pstm.execute();
	    
	    try(ResultSet rs = pstm.getResultSet()){
		while(rs.next()) {
		    ContatoDeEmergencia contato = new ContatoDeEmergencia(rs.getInt(1), rs.getInt(2),
			    rs.getString(3), rs.getString(4), ParentescoEnum.valueOf(rs.getString(5)));
		    
		    listaContatos.add(contato);
		}
	    }
	}
	catch(SQLException e) {
	    e.printStackTrace();
	}
	
	return null;
    }

    @Override
    public void atualizar(ContatoDeEmergencia contato) {
	String sql = "{CALL sp_atualiza_contato(?, ?, ?, ?)}";
	
	try(CallableStatement stms = getConnection().prepareCall(sql)){
	    stms.setInt(1, contato.getIdContato());
	    stms.setString(2, contato.getNome());
	    stms.setString(3, contato.getTelefone());
	    stms.setString(4, String.valueOf(contato.getParentesco()));
	    
	    stms.execute();
	}
	catch(SQLException e) {
	    e.printStackTrace();
	}
	
    }

}
