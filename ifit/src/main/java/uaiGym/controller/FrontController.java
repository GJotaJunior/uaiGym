package uaiGym.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uaiGym.action.Action;
import uaiGym.action.ActionFactory;

@WebServlet("/*")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Action action = ActionFactory.getAction(request);
			String view = action.execute(request, response);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/" + view + ".jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			throw new ServletException("Error in action: " + e);
		}
	}

}
