package uaiGym.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uaiGym.model.enuns.PerfilEnum;
import uaiGym.model.pessoa.Aluno;
import uaiGym.model.pessoa.Gerente;
import uaiGym.model.pessoa.Instrutor;
import uaiGym.model.pessoa.Recepcao;
import uaiGym.model.pessoa.Usuario;

public class UsuarioDAOFactory extends UsuarioDAO<Usuario> {

	public UsuarioDAOFactory(Connection connection) {
		super(connection);
	}

	@Override
	public Usuario recuperarPorId(int id) {
		return getUsuario(id);
	}

	@Override
	public void salvar(Usuario entidade) {
		PerfilEnum perfil = entidade.getPerfil();
		if (perfil == PerfilEnum.ALUNO) 
			new AlunoDAO(getConnection()).salvar((Aluno) entidade);
		else if (perfil == PerfilEnum.GERENCIA)
			new GerenteDAO(getConnection()).salvar((Gerente) entidade);
		else if (perfil == PerfilEnum.INSTRUTOR)
			new InstrutorDAO(getConnection()).salvar((Instrutor) entidade);
		else if (perfil == PerfilEnum.RECEPCAO)
			new RecepcaoDAO(getConnection()).salvar((Recepcao) entidade);
	}

	@Override
	public void excluir(int id) {
		PerfilEnum perfil = getUsuario(id).getPerfil();
		if (perfil == PerfilEnum.ALUNO) 
			new AlunoDAO(getConnection()).excluir(id);
		else if (perfil == PerfilEnum.GERENCIA)
			new GerenteDAO(getConnection()).excluir(id);
		else if (perfil == PerfilEnum.INSTRUTOR)
			new InstrutorDAO(getConnection()).excluir(id);
		else if (perfil == PerfilEnum.RECEPCAO)
			new RecepcaoDAO(getConnection()).excluir(id);
	}

	@Override
	public List<Usuario> listarTodos() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		String sql = "SELECT idUsuario FROM Usuario";
		
		try (PreparedStatement pstm = getConnection().prepareStatement(sql)) {
			pstm.execute();
			
			try (ResultSet rst = pstm.getResultSet()) {
				while (rst.next()) {
					int id = rst.getInt(1);
					usuarios.add(getUsuario(id));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usuarios;
	}

	@Override
	public void atualizar(Usuario entidade) {
		PerfilEnum perfil = entidade.getPerfil();
		if (perfil == PerfilEnum.ALUNO) 
			new AlunoDAO(getConnection()).atualizar((Aluno) entidade);
		else if (perfil == PerfilEnum.GERENCIA)
			new GerenteDAO(getConnection()).atualizar((Gerente) entidade);
		else if (perfil == PerfilEnum.INSTRUTOR)
			new InstrutorDAO(getConnection()).atualizar((Instrutor) entidade);
		else if (perfil == PerfilEnum.RECEPCAO)
			new RecepcaoDAO(getConnection()).atualizar((Recepcao) entidade);
	}

	private Usuario getUsuario(int id) {
		String sql = "SELECT perfil FROM Usuario WHERE idUsuario = ?";
		try (PreparedStatement pstm = getConnection().prepareStatement(sql)) {
			pstm.setInt(1, id);
			pstm.execute();

			try (ResultSet rst = pstm.getResultSet()) {
				if (rst.next()) {
					PerfilEnum perfil = PerfilEnum.valueOf(rst.getString(1));
					if (perfil == PerfilEnum.ALUNO)
						return new AlunoDAO(getConnection()).recuperarPorId(id);
					else if (perfil == PerfilEnum.GERENCIA)
						return new GerenteDAO(getConnection()).recuperarPorId(id);
					else if (perfil == PerfilEnum.INSTRUTOR)
						return new InstrutorDAO(getConnection()).recuperarPorId(id);
					else if (perfil == PerfilEnum.RECEPCAO)
						return new RecepcaoDAO(getConnection()).recuperarPorId(id);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
