package uaiGym.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uaiGym.model.dao.InstrutorDAO;
import uaiGym.model.pessoa.Instrutor;
import uaiGym.service.DataBase.ConnectionFactory;

public class InstrutorListAction implements Action {

    private String doGet(HttpServletRequest request) {

	ConnectionFactory cf;
	try {
	    cf = new ConnectionFactory();
	    InstrutorDAO instrutorDAO = new InstrutorDAO(cf.recuperarConexao());
	    List<Instrutor> instrutores = instrutorDAO.listarTodos();
	    request.setAttribute("instrutores", instrutores);
	} catch (ClassNotFoundException | SQLException | IOException e) {
	    e.printStackTrace();
	}
	return "instrutor/listagem";

    }

    private String doPost(HttpServletRequest request) {

	return "instrutor/listagem";
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
