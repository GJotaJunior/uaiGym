package uaiGym.dao;

import java.sql.Connection;
import java.util.List;

public abstract class Dao<E> {
	
	private Connection connection;
	
	protected Connection getConnection() {
		return connection;
	}
	
	public Dao(Connection connection) {
		this.connection = connection;
	}
	
	public abstract E recuperarPorId(int id);
	public abstract void salvar (E entidade);
	public abstract void excluir (int id);
	public abstract List<E> listarTodos();
	
}
