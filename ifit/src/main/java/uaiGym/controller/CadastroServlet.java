package uaiGym.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uaiGym.service.AuthService;

@WebServlet("/cadastrar")
public class CadastroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher;
		AuthService authenticator = new AuthService(request.getSession());
		
		if (authenticator.isValid()) {
			dispatcher = request.getRequestDispatcher("/WEB-INF/menu.jsp");
		} else {
			request.setAttribute("mensagem", authenticator.getMessages());
			dispatcher = request.getRequestDispatcher("/WEB-INF/cadastro.jsp");
		}
		
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher;
		AuthService authenticator = new AuthService(request.getSession());
		
		String email = request.getParameter("email");
		String usuario = request.getParameter("usuario");
		String senha = request.getParameter("senha");
		String confirmarSenha = request.getParameter("confirmarSenha");
		
		authenticator.register(email, usuario, senha, confirmarSenha);
		request.setAttribute("mensagem", authenticator.getMessages());

		if (authenticator.isValid()) {
			dispatcher = request.getRequestDispatcher("/login");
		} else {
			dispatcher = request.getRequestDispatcher("/cadastrar");
		}
		dispatcher.forward(request, response);
	}

}
