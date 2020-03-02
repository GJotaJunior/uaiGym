package ifit.service;

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
	
	public AuthService(HttpSession session) {
		authenticator = session;
	}
	
	public HttpSession getAuthenticator() {
		return authenticator;
	}
	
	public boolean isValid() {
		return isValid;
	}
	
	public boolean login(String user, String password) {
		if (loginWithEmail(user, password) || loginWithUser(user, password)) {
			isValid = true;
		}
		return isValid;
	}
	
	public boolean loginWithUser(String user, String password) {
		// TODO
		return false;
	}

	public boolean loginWithEmail(String email, String password) {
		// TODO
		return false;
	}

	public String register(String email, String user, String password, String passwordConfirm) {
		if (!password.equals(passwordConfirm)) 
			return "As senhas não são iguais!";
		
		// TODO
		isValid = true;
		return "Cadastrado com sucesso!";
	}
	
	public void logout() {
		authenticator.invalidate();
	}
	
}
