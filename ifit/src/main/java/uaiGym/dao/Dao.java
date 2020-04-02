package uaiGym.dao;

import java.util.List;

public interface Dao<E> {
	
	public E recuperarPorId(int id);
	public void salvar (E entidade);
	public void excluir (int id);
	public List<E> listarTodos();
	
}
