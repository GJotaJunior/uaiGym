package uaiGym.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uaiGym.model.dao.AlunoDAO;
import uaiGym.model.pessoa.Aluno;
import uaiGym.service.DataBase.ConnectionFactory;

public class AlunoListAction implements Action {

    private String doGet(HttpServletRequest request) {

	ConnectionFactory cf;
	try {
	    cf = new ConnectionFactory();
	    AlunoDAO alunoDAO = new AlunoDAO(cf.recuperarConexao());
	    List<Aluno> alunos = alunoDAO.listarTodos();
	    request.setAttribute("alunos", alunos);
	} catch (ClassNotFoundException | SQLException | IOException e) {
	    e.printStackTrace();
	}
	return "aluno/listagem";

    }

    private String doPost(HttpServletRequest request) {

	return "aluno/listagem";
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
