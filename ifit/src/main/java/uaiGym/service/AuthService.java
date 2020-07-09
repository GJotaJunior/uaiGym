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

import uaiGym.model.dao.UsuarioDAOFactory;
import uaiGym.model.enuns.PerfilEnum;
import uaiGym.model.pessoa.Usuario;
import uaiGym.service.DataBase.ConnectionFactory;
import uaiGym.service.dto.EmailDto;

public class AuthService {

	private HttpSession authenticator;
	private List<String> messages;

	public AuthService(HttpSession session) {
		authenticator = session;
	}

	public HttpSession getAuthenticator() {
		return authenticator;
	}

	public List<String> getMessages() {
		return messages;
	}

	public boolean login(String emailOrCpf, String password)
			throws SQLException, IOException, NoSuchAlgorithmException, ClassNotFoundException {
		password = securityPassword(password);

		Connection connection = new ConnectionFactory().recuperarConexao();
		String sql = "SELECT idUsuario FROM Usuario WHERE email = ? AND senha = ? OR CPF = ? AND senha = ?";
		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.setString(1, emailOrCpf);
			pstm.setString(2, password);
			pstm.setString(3, emailOrCpf);
			pstm.setString(4, password);
			pstm.execute();

			try (ResultSet rst = pstm.getResultSet()) {
				Usuario usuario = null;
				if (rst.next()) {
					Integer id = rst.getInt(1);
					usuario = new UsuarioDAOFactory(connection).recuperarPorId(id);
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

	public void register(String email, String user, String password, String passwordConfirm)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		if (!password.equals(passwordConfirm))
			messages.add("As senhas não são iguais!");
		password = securityPassword(password);

		messages.add("Cadastrado com sucesso!");
	}

	public void logout() {
		authenticator.invalidate();
	}

	public static String securityPassword(String password)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
		byte messageDigest[] = algorithm.digest(password.getBytes("UTF-8"));

		StringBuffer result = new StringBuffer();
		for (byte b : messageDigest) {
			result.append(b);
		}
		return result.toString();
	}

	public static void redefinePassword(String emailOrCpf) {
		String sql = "SELECT idUsuario, email, nome FROM Usuario WHERE email = ? OR cpf = ?";

		try (PreparedStatement pstm = new ConnectionFactory().recuperarConexao().prepareStatement(sql)) {
			pstm.setString(1, emailOrCpf);
			pstm.setString(2, emailOrCpf);
			pstm.execute();
			try (ResultSet rst = pstm.getResultSet()) {
				if (rst.next()) {
					Integer id = rst.getInt(1);
					String email = rst.getString(2).trim();
					String name = rst.getString(3);
					if (email != null && !email.isEmpty()) {
						String url = "http://uaigym.herokuapp.com" + EncryptionService.linkGenerator(id);
						sendEmailForRedefinePassword(url, email, name);
					}
				} else {
					throw new Exception("O email informado não está cadastrado!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sendEmailForRedefinePassword(String url, String email, String name) {

		String messageBody = "Olá " + name + "!\n Recebemos sua solicitação de redefinição de senha.\n"
				+ "Para prosseguir, acesse o link abaixo: " + url;

		EmailDto emailDTO = new EmailDto(email, "Solicitação de redefinição de senha!", messageBody);
		new EmailSender().EnviarEmail(emailDTO, "Recuperação de senha enviada");
	}

	public static void newPassword(Integer id, String password)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		password = securityPassword(password);

		String sql = "UPDATE Usuario SET senha = ? WHERE idUsuario = ?";

		try (PreparedStatement pstm = new ConnectionFactory().recuperarConexao().prepareStatement(sql)) {
			pstm.setInt(1, id);
			pstm.setString(2, password);
			pstm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isValid() {
		return (authenticator.getAttribute("usuario") != null);
	}

	public boolean isAllowed(PerfilEnum perfilAutorizado) {
	    Usuario usuario = (Usuario) authenticator.getAttribute("usuario");
	    return (usuario.getPerfil() == perfilAutorizado);
	}
}
