package uaiGym.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import uaiGym.model.pessoa.Aluno;

public class AlunoDAO extends Dao<Aluno> {

	public AlunoDAO(Connection connection) {
		super(connection);
	}

	@Override
	public Aluno recuperarPorId(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void salvar(Aluno entidade) {
		// TODO Auto-generated method stub

	}

	@Override
	public void excluir(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Aluno> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}
