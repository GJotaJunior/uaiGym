package uaiGym.model.dao;

import java.sql.Connection;
import java.util.List;

import uaiGym.model.pessoa.Recepcao;

public class RecepcaoDAO extends UsuarioDAO<Recepcao> {

	public RecepcaoDAO(Connection connection) {
		super(connection);
	}

	@Override
	public Recepcao recuperarPorId(int id) {

		Recepcao recepcao = null;

		String sql = "SELECT u.*, f.contrato, f.dtAdmissao, f.dtDemissao " + "FROM Funcionario f "
			+ "INNER JOIN Usuario u ON f.idUsuario = u.idUsuario "
			+ "WHERE f.idUsuario = ? AND u.perfil = 'RECEPCAO'";

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
			    PerfilEnum perfil = PerfilEnum.INSTRUTOR;
			    String contrato = rst.getString(9);
			    Date dataAdmissao = rst.getDate(10);
			    Date dataDemissao = rst.getDate(11);

			    recepcao = new Recepcao();

			    recepcao.setEmail(email);
			    recepcao.setSenha(senha);
			    recepcao.setNome(nome);
			    recepcao.setCpf(cpf);
			    recepcao.setDtNascimento(nascimento);
			    recepcao.setSexo(sexo);
			    recepcao.setPerfil(perfil);
			    recepcao.setContrato(contrato);
			    recepcao.setAdmissao(dataAdmissao);
			    recepcao.setDemissao(dataDemissao);
			    
			}
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}

		return recepcao;
	}

	@Override
	public void salvar(Recepcao entidade) {
		String sql = "{CALL sp_atualiza_funcionario(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

		try (CallableStatement stms = getConnection().prepareCall(sql)) {
		    stms.setInt(1, entidade.getId());
		    stms.setString(2, entidade.getPerfil().toString());
		    stms.setString(3, entidade.getNome());
		    stms.setString(4, entidade.getCpf());
		    stms.setDate(5, (Date) entidade.getDtNascimento());
		    stms.setString(6, entidade.getSexo().toString());
		    stms.setString(7, entidade.getEmail());
		    stms.setString(8, entidade.getSenha());
		    stms.setString(9, entidade.getContrato());
		    stms.setString(10, entidade.getAdmissao());
		    stms.setString(11, entidade.getDemissao());
		    stms.setString(12, entidade.getEndereco().getRua());
		    stms.setString(13, entidade.getEndereco().getNumero().toString());
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
	public List<Recepcao> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}
