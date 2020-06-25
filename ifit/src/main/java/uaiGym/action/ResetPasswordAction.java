package uaiGym.action;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uaiGym.service.AuthService;
import uaiGym.service.EncryptionService;

public class ResetPasswordAction implements Action {

	private String doGet(HttpServletRequest request) {

		String id = request.getParameter("id");
		try {
			if (id != null && !id.isEmpty()) {
				id = EncryptionService.decrypt(id);
				return "nova-senha";
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "redefinir-senha";
	}

	private String doPost(HttpServletRequest request) {

		String usuario = request.getParameter("usuario");
		System.out.println(usuario);
		AuthService.redefinePassword(usuario);

		return "email-enviado";
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
