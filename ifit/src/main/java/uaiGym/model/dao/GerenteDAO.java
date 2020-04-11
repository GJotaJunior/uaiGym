package uaiGym.model.dao;

import java.sql.Connection;
import java.util.List;

import uaiGym.model.pessoa.Gerente;

public class GerenteDAO extends Dao<Gerente> {

	public GerenteDAO(Connection connection) {
		super(connection);
	}

	@Override
	public Gerente recuperarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void salvar(Gerente entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Gerente> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}
