package ifit.service;

import java.util.List;

/**
* Authentication Control
* 
* @author  Gledson Rodrigues de Oliveira Junior (groliveirajr@gmail.com)
* @version 0.1
* @since   2020-03-01
*/

import javax.servlet.http.HttpSession;

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
	
	public void login(String user, String password) {
		if (loginWithEmail(user, password) || loginWithUser(user, password)) {
			isValid = true;
			messages.add("Login efetuado com sucesso!");
		}
		
		// TODO
		messages.add("As informações de login não foram inseridas corretamente!");
	}
	
	private boolean loginWithUser(String user, String password) {
		// TODO
		return false;
	}

	private boolean loginWithEmail(String email, String password) {
		// TODO
		return false;
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
