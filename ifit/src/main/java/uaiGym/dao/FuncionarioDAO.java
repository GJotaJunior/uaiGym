package uaiGym.dao;

import java.sql.Connection;
import java.util.List;

import uaiGym.model.pessoa.Funcionario;

public class FuncionarioDAO extends Dao<Funcionario> {

	public FuncionarioDAO(Connection connection) {
		super(connection);
	}

	@Override
	public Funcionario recuperarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void salvar(Funcionario entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Funcionario> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}
