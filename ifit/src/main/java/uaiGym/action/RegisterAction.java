package uaiGym.action;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uaiGym.service.AuthService;

public class RegisterAction implements Action {

	private String doGet(HttpServletRequest request) {

		AuthService authenticator = new AuthService(request.getSession());

		if (authenticator.isValid()) {
			return "menu";
		} else {
			request.setAttribute("mensagem", authenticator.getMessages());
			return "cadastro";
		}
	}

	private String doPost(HttpServletRequest request) {

		AuthService authenticator = new AuthService(request.getSession());

		String email = request.getParameter("email");
		String usuario = request.getParameter("usuario");
		String senha = request.getParameter("senha");
		String confirmarSenha = request.getParameter("confirmarSenha");

		try {
			authenticator.register(email, usuario, senha, confirmarSenha);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		request.setAttribute("mensagem", authenticator.getMessages());

		return (authenticator.isValid()) ? "login" : "cadastro";
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String returns;
		switch (request.getMethod()) {
		case "GET":
			returns = doGet(request);
			break;
		case "POST":
			returns = doPost(request);
			break;
		default:
			returns = "index";
		}
		return returns;
	}
	
	@Override
	public String toString() {
		return this.getClass().getName();
	}
}
