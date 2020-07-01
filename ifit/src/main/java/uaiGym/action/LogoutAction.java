package uaiGym.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uaiGym.service.AuthService;

public class LogoutAction implements Action {

	private String doGet(HttpServletRequest request) {

		AuthService authenticator = new AuthService(request.getSession());

		authenticator.logout();

		return "index";
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String returns;
		switch (request.getMethod()) {
		case "GET":
			returns = doGet(request);
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
