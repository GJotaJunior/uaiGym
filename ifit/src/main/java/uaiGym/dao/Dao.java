package uaiGym.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class Dao<E> {
	
	private Connection connection;
	
	protected Connection getConnection() {
		return connection;
	}
	
	public Dao(Connection connection) {
		this.connection = connection;
	}
	
	public abstract E recuperarPorId(int id) throws SQLException;
	public abstract void salvar (E entidade) throws SQLException;
	public abstract void excluir (int id) throws SQLException;
	public abstract List<E> listarTodos() throws SQLException;
	
}
