package uaiGym.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uaiGym.service.AuthService;

@WebServlet("/redefinir-senha")
public class RedefinirSenhaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher;

		String id = (String) request.getAttribute("id");
		
		dispatcher = (id == null || id.isEmpty() || id.isBlank())
				? request.getRequestDispatcher("/WEB-INF/redefinir-senha.jsp")
				: request.getRequestDispatcher("/WEB-INF/nova-senha.jsp");

		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher;

		String usuario = request.getParameter("usuario");
		String contexto = request.getServletContext().getContextPath();
		System.out.println(usuario + "\n" + contexto);
		AuthService.redefinePassword(usuario, contexto);

		dispatcher = request.getRequestDispatcher("/");
		dispatcher.forward(request, response);
	}

}
