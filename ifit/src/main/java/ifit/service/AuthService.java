package ifit.service;

import javax.servlet.http.HttpSession;

// implementar os metodos com "TODO" quando aprendermos conectar com o banco
public class AuthService {

	private HttpSession authenticator;
	
	public AuthService(HttpSession session) {
		authenticator = session;
	}
	
	public HttpSession getAuthenticator() {
		return authenticator;
	}
	
	public boolean login(String user, String password) {
		return (loginWithEmail(user, password) || loginWithUser(user, password));
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
		return "Cadastrado com sucesso!";
	}
	
	public void logout() {
		authenticator.invalidate();
	}
	
}
