package uaiGym.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uaiGym.model.enuns.PerfilEnum;
import uaiGym.model.pessoa.Aluno;
import uaiGym.model.pessoa.Funcionario;
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
	else
	    new FuncionarioDAO(getConnection()).salvar((Funcionario) entidade);
    }

    @Override
    public void excluir(int id) {
	PerfilEnum perfil = getUsuario(id).getPerfil();
	if (perfil == PerfilEnum.ALUNO)
	    new AlunoDAO(getConnection()).excluir(id);
	else
	    new FuncionarioDAO(getConnection()).excluir(id);
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
	else
	    new FuncionarioDAO(getConnection()).atualizar((Funcionario) entidade);
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
		    else
			return new FuncionarioDAO(getConnection()).recuperarPorId(id);
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return null;
    }

}
