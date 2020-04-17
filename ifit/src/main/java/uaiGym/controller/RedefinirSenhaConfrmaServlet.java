package uaiGym.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uaiGym.model.dao.AlunoDAO;
import uaiGym.service.DataBase.ConnectionFactory;

@WebServlet("/redefinir-senha-confirma")
public class RedefinirSenhaConfrmaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/erro.jsp");
	    
	    String idCriptografado = (String) request.getSession().getAttribute("url");
	    String senha = request.getParameter("senha");
	    System.out.println(idCriptografado +" | "+senha);
	    	
	    if(!senha.isEmpty() && senha != null) {
		AlunoDAO aluno;
		boolean sucesso = false;
		try {
		    ConnectionFactory cf = new ConnectionFactory();
		    aluno = new AlunoDAO(cf.recuperarConexao());
		    sucesso =  aluno.updatePassword(idCriptografado, senha);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		if(sucesso)
		    dispatcher = request.getRequestDispatcher("/WEB-INF/redefinir-senha-confirma.jsp");
	    }
	    
	    dispatcher.forward(request, response);
	}

}
