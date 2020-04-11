package uaiGym.model.dao;

import java.sql.Connection;
import java.util.List;

import uaiGym.model.pessoa.Recepcao;

public class RecepcaoDAO extends Dao<Recepcao> {

	public RecepcaoDAO(Connection connection) {
		super(connection);
	}

	@Override
	public Recepcao recuperarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void salvar(Recepcao entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Recepcao> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}
