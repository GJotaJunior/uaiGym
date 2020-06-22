package uaiGym.model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uaiGym.model.enuns.PerfilEnum;
import uaiGym.model.enuns.SexoEnum;
import uaiGym.model.pessoa.Gerente;
import uaiGym.service.DataBase.DatabaseUtils;

public class GerenteDAO extends UsuarioDAO<Gerente> {

	public GerenteDAO(Connection connection) {
		super(connection);
	}

	@Override
	public Gerente recuperarPorId(int id) {
		Gerente gerente = null;

		String sql = "SELECT u.*, f.contrato, f.dtAdmissao, f.dtDemissao " + "FROM Funcionario f "
			+ "INNER JOIN Usuario u ON u.idUsuario = f.idUsuario "
			+ "WHERE u.idUsuario = ? AND u.perfil = 'GERENTE'";

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
			    PerfilEnum perfil = PerfilEnum.GERENCIA;
			    String contrato = rst.getString(9);
			    Date dataAdmissao = rst.getDate(10);
			    Date dataDemissao = rst.getDate(11);

//			    gerente = new Gerente();
			    gerente = new Gerente(email, senha, nome, cpf, nascimento, null, sexo, null, perfil, contrato, dataAdmissao, dataDemissao);

//			    gerente.setEmail(email);
//			    gerente.setSenha(senha);
//			    gerente.setNome(nome);
//			    gerente.setCpf(cpf);
//			    gerente.setDtNascimento(nascimento);
//			    gerente.setSexo(sexo);
//			    gerente.setPerfil(perfil);
//			    gerente.setContrato(contrato);
//			    gerente.setAdmissao(dataAdmissao);
//			    gerente.setDemissao(dataDemissao);
			    
			}
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}

		return gerente;
	}

	@Override
	public void salvar(Gerente entidade) {
	    
	    String sql = "{CALL sp_inserirFuncionario(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

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
	public List<Gerente> listarTodos() {
	    	List<Gerente> gerentes = new ArrayList<>();
	    
		String sql = "SELECT u.*, f.contrato, f.dtAdmissao, f.dtDemissao FROM Funcionario f "
			+ "INNER JOIN Usuario u ON u.idUsuario = f.idUsuario "
			+ "WHERE u.perfil = 'GERENTE'";
		
		try(PreparedStatement pstm = getConnection().prepareStatement(sql)) {
		    pstm.execute();
		    
		    try (ResultSet rs = pstm.getResultSet()) {
			while (rs.next()) {
			    String email = rs.getString(7);
			    String senha = rs.getString(8);
			    String nome = rs.getString(3);
			    String cpf = rs.getString(4);
			    Date nascimento = rs.getDate(5);
			    SexoEnum sexo = SexoEnum.valueOf(rs.getString(6));
			    PerfilEnum perfil = PerfilEnum.GERENCIA;
			    String contrato = rs.getString(9);
			    Date dataAdmissao = rs.getDate(10);
			    Date dataDemissao = rs.getDate(11);

//			    Gerente gerente = new Gerente();
			    Gerente gerente = new Gerente(email, senha, nome, cpf, nascimento, null, sexo, null, perfil, contrato, dataAdmissao, dataDemissao);

//			    gerente.setEmail(email);
//			    gerente.setSenha(senha);
//			    gerente.setNome(nome);
//			    gerente.setCpf(cpf);
//			    gerente.setDtNascimento(nascimento);
//			    gerente.setSexo(sexo);
//			    gerente.setPerfil(perfil);
//			    gerente.setContrato(contrato);
//			    gerente.setAdmissao(dataAdmissao);
//			    gerente.setDemissao(dataDemissao);
			    
			    gerentes.add(gerente);
			}
		    }
		}
		catch (SQLException e) {
		    e.printStackTrace();
		}
		
		return gerentes;
	}

	@Override
	public void atualizar(Gerente entidade) {
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

}
