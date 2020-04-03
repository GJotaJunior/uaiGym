package uaiGym.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uaiGym.service.AuthService;

@WebServlet("/")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	RequestDispatcher dispatcher;
	AuthService authenticator = new AuthService(request.getSession());

	if (authenticator.isValid()) {
	    dispatcher = request.getRequestDispatcher("/WEB-INF/menu.jsp");
	} else {
	    request.setAttribute("mensagem", authenticator.getMessages());
	    dispatcher = request.getRequestDispatcher("index.jsp");
	}

	dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	RequestDispatcher dispatcher;
	AuthService authenticator = new AuthService(request.getSession());

	String usuario = request.getParameter("usuario");
	String senha = request.getParameter("senha");

	try {
		authenticator.login(usuario, senha);
	} catch (Exception e) {
		e.printStackTrace();
	}

	request.setAttribute("mensagem", authenticator.getMessages());

	dispatcher = request.getRequestDispatcher("/");
	dispatcher.forward(request, response);
    }

}
