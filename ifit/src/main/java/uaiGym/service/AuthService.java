package uaiGym.service;

import java.io.IOException;
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

import uaiGym.DataBase.ConnectionFactory;

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

	public Integer login(String user, String password) throws SQLException, IOException {
		Connection connection = new ConnectionFactory().recuperarConexao();
		String sql = "SELECT idUsuario FROM Usuario WHERE email = ? and senha = ? or cpf = ? and senha = ?";
		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.setString(1, user);
			pstm.setString(2, password);
			pstm.setString(3, user);
			pstm.setString(4, password);
			pstm.execute();

			try (ResultSet rst = pstm.getResultSet()) {
				if (rst.next()) {
					return rst.getInt(1);
				}
			}
		}
		messages.add("As informações de login não foram inseridas corretamente!");
		return null;
	}

	public void register(String email, String user, String password, String passwordConfirm) {
		if (!password.equals(passwordConfirm))
			messages.add("As senhas não são iguais!");

		// TODO
		isValid = true;
		messages.add("Cadastrado com sucesso!");
	}

	public void logout() {
		authenticator.invalidate();
	}

}
