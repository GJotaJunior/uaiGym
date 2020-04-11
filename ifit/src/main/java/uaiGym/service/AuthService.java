package uaiGym.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
* Authentication Control
* 
* @author  Gledson Rodrigues de Oliveira Junior (groliveirajr@gmail.com)
* @version 0.1
* @since   2020-03-01
*/

import javax.servlet.http.HttpSession;

import uaiGym.model.dao.AlunoDAO;
import uaiGym.model.dao.GerenteDAO;
import uaiGym.model.dao.InstrutorDAO;
import uaiGym.model.dao.RecepcaoDAO;
import uaiGym.model.enuns.PerfilEnum;
import uaiGym.model.pessoa.Usuario;
import uaiGym.service.DataBase.ConnectionFactory;

// implementar os metodos com "TODO" quando aprendermos conectar com o banco
public class AuthService {

	private HttpSession authenticator;
	private boolean isValid;
	private List<String> messages;

	public AuthService(HttpSession session) {
		authenticator = session;
	}

	public HttpSession getAuthenticator() {
		return authenticator;
	}

	public boolean isValid() {
		return isValid;
	}

	public List<String> getMessages() {
		return messages;
	}

	public boolean login(String emailOrCpf, String password) throws SQLException, IOException, NoSuchAlgorithmException {
		password = securityPassword(password);

		Connection connection = new ConnectionFactory().recuperarConexao();
		String sql = "SELECT idUsuario, perfil FROM Usuario WHERE email = ? AND senha = ? OR CPF = ? AND senha = ?";
		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.setString(1, emailOrCpf);
			pstm.setString(2, password);
			pstm.setString(3, emailOrCpf);
			pstm.setString(4, password);
			pstm.execute();

			try (ResultSet rst = pstm.getResultSet()) {
				Usuario usuario = null;
				if (rst.next()) {
					String perfil = rst.getString(2);
					Integer id = rst.getInt(1);
					if (perfil.equals(PerfilEnum.ALUNO.toString())) {
						usuario = new AlunoDAO(new ConnectionFactory().recuperarConexao()).recuperarPorId(id);
					} else if (perfil.equals(PerfilEnum.INSTRUTOR.toString())) {
						usuario = new InstrutorDAO(new ConnectionFactory().recuperarConexao()).recuperarPorId(id);
					} else if (perfil.equals(PerfilEnum.RECEPCAO.toString())) {
						usuario = new RecepcaoDAO(new ConnectionFactory().recuperarConexao()).recuperarPorId(id);
					} else if (perfil.equals(PerfilEnum.GERENCIA.toString())) {
						usuario = new GerenteDAO(new ConnectionFactory().recuperarConexao()).recuperarPorId(id);
					}
				}
				if (usuario != null) {
					authenticator.setAttribute("usuario", usuario);
					return true;
				} else {
					messages.add("As informações de login não foram inseridas corretamente!");
					return false;
				}
			}
		}
	}

	public void register(String email, String user, String password, String passwordConfirm) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		if (!password.equals(passwordConfirm))
			messages.add("As senhas não são iguais!");
		password = securityPassword(password);

		// TODO verificar parametros e implementar o metodo
		isValid = true;
		messages.add("Cadastrado com sucesso!");
	}

	public void logout() {
		authenticator.invalidate();
	}

	public String securityPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
		byte messageDigest[] = algorithm.digest(password.getBytes("UTF-8"));

		StringBuffer result = new StringBuffer();
		for (byte b : messageDigest) {
			result.append(b);
		}
		return result.toString();
	}
}
