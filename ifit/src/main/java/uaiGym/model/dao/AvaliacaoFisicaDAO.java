package uaiGym.model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uaiGym.model.AvaliacaoFisica;
import uaiGym.model.MedidasCorporais;
import uaiGym.model.pessoa.Instrutor;
import uaiGym.service.DataBase.DatabaseUtils;

public class AvaliacaoFisicaDAO extends Dao<AvaliacaoFisica> {

    public AvaliacaoFisicaDAO(Connection connection) {
	super(connection);
    }

    @Override
    public AvaliacaoFisica recuperarPorId(int id) {

	AvaliacaoFisica avaliacao = null;

	String sql = "SELECT a.*, f.idUsuario FROM avaliacao "
		+ "INNER JOIN funcionario f ON f.idFuncionario = a.idFuncionario" + "WHERE a.idAvaliacao = ? ";

	try (PreparedStatement pstm = getConnection().prepareStatement(sql)) {
	    pstm.setInt(1, id);
	    pstm.execute();

	    try (ResultSet rst = pstm.getResultSet()) {

		if (rst.next()) {

		    Integer idAva = rst.getInt(1);
		    Date dtAvaliacao = rst.getDate(4);
		    float altura = rst.getFloat(5);
		    float peso = rst.getFloat(6);
		    float gorduraPercentual = rst.getFloat(7);
		    float residuosPercentual = rst.getFloat(8);
		    float musculoPercentual = rst.getFloat(9);

		    int idUsuario = rst.getInt(10);

		    MedidasCorporais medidas = new MedidasCorporais(altura, peso, gorduraPercentual, residuosPercentual,
			    musculoPercentual);
		    Instrutor instrutor = new InstrutorDAO(getConnection()).recuperarPorId(idUsuario);

		    avaliacao = new AvaliacaoFisica(idAva, instrutor.getId(), dtAvaliacao, medidas);

		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}

	return avaliacao;
    }

    @Override
    public void salvar(AvaliacaoFisica entidade) {

	String sql = "{CALL sp_inserirAvaliacao(?,?,?,?,?,?,?,?)}";

	try (CallableStatement stms = getConnection().prepareCall(sql)) {

	    stms.setInt(1, 1);
	    stms.setInt(2, entidade.getIdInstrutor());
	    stms.setDate(3, DatabaseUtils.converteData(entidade.getData()));
	    stms.setFloat(4, entidade.getMedidas().getAltura());
	    stms.setFloat(5, entidade.getMedidas().getPeso());
	    stms.setFloat(6, entidade.getMedidas().getGorduraPercentual());
	    stms.setFloat(7, entidade.getMedidas().getResiduosPercentual());
	    stms.setFloat(8, entidade.getMedidas().getMusculoPercentual());

	    stms.executeQuery();

	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public void excluir(int id) {
    }

    @Override
    public List<AvaliacaoFisica> listarTodos() {
	List<AvaliacaoFisica> listaAvaliacao = new ArrayList<AvaliacaoFisica>();

	String sql = "SELECT DISTINCT a.*, f.idUsuario FROM avaliacao "
		+ "INNER JOIN funcionario f ON f.idFuncionario = a.idFuncionario";

	try (PreparedStatement pstm = getConnection().prepareStatement(sql)) {
	    pstm.execute();

	    try (ResultSet rst = pstm.getResultSet()) {
		while (rst.next()) {

		    Integer idAva = rst.getInt(1);
		    Date dtAvaliacao = rst.getDate(4);
		    float altura = rst.getFloat(5);
		    float peso = rst.getFloat(6);
		    float gorduraPercentual = rst.getFloat(7);
		    float residuosPercentual = rst.getFloat(8);
		    float musculoPercentual = rst.getFloat(9);

		    int idUsuario = rst.getInt(10);

		    MedidasCorporais medidas = new MedidasCorporais(altura, peso, gorduraPercentual, residuosPercentual,
			    musculoPercentual);
		    Instrutor instrutor = new InstrutorDAO(getConnection()).recuperarPorId(idUsuario);

		    AvaliacaoFisica avaliacao = new AvaliacaoFisica(idAva, instrutor.getId(), dtAvaliacao, medidas);

		    listaAvaliacao.add(avaliacao);

		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}

	return listaAvaliacao;
    }

    @Override
    public void atualizar(AvaliacaoFisica entidade) {

	String sql = "{CALL sp_atualiza_avaliacao(?,?,?,?,?,?,?,?,?)}";

	try (CallableStatement stms = getConnection().prepareCall(sql)) {

	    stms.setInt(1, entidade.getId());
	    stms.setInt(2, 1);
	    stms.setInt(3, entidade.getIdInstrutor());
	    stms.setDate(4, DatabaseUtils.converteData(entidade.getData()));
	    stms.setFloat(5, entidade.getMedidas().getAltura());
	    stms.setFloat(6, entidade.getMedidas().getPeso());
	    stms.setFloat(7, entidade.getMedidas().getGorduraPercentual());
	    stms.setFloat(8, entidade.getMedidas().getResiduosPercentual());
	    stms.setFloat(9, entidade.getMedidas().getMusculoPercentual());

	    stms.executeQuery();

	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    public List<AvaliacaoFisica> buscarAvaliacoesPorIdUsuario(int idUsuario) {

	List<AvaliacaoFisica> avaliacoes = new ArrayList<AvaliacaoFisica>();

	String sql = "SELECT ava.* " + "FROM Avaliacao ava " + "INNER JOIN Aluno a ON ava.idAluno = a.idAluno "
		+ "INNER JOIN Usuario u ON a.idUsuario = u.idUsuario WHERE u.idUsuario = ? ORDER BY ava.dtAvaliacao DESC";

	try (PreparedStatement pstm = getConnection().prepareStatement(sql)) {

	    pstm.setInt(1, idUsuario);
	    pstm.execute();

	    try (ResultSet rst = pstm.getResultSet()) {

		while (rst.next()) {
			int id = rst.getInt(1);
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

		    AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica(instrutor.getId(), data, medidas);

		    avaliacoes.add(avaliacaoFisica);
		}
	    }

	} catch (SQLException e) {
		System.out.println(e.getMessage());
	    e.printStackTrace();
	}
	return avaliacoes;
    }
}
