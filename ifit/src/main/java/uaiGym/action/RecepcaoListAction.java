package uaiGym.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uaiGym.model.dao.FuncionarioDAO;
import uaiGym.model.enuns.PerfilEnum;
import uaiGym.model.pessoa.Funcionario;
import uaiGym.service.AuthService;
import uaiGym.service.DataBase.ConnectionFactory;

public class RecepcaoListAction implements Action {

    private String doGet(HttpServletRequest request) {

	AuthService authenticator = new AuthService(request.getSession());

	if (authenticator.isValid()
		&& (authenticator.isAllowed(PerfilEnum.GERENTE) || authenticator.isAllowed(PerfilEnum.RECEPCAO))) {
	    ConnectionFactory cf;
	    try {
		cf = new ConnectionFactory();
		FuncionarioDAO recepcaoDAO = new FuncionarioDAO(cf.recuperarConexao());
		List<Funcionario> recepcionistas = recepcaoDAO.listarTodosRecepcionistas();
		request.setAttribute("recepcionistas", recepcionistas);
	    } catch (ClassNotFoundException | SQLException | IOException e) {
		e.printStackTrace();
	    }
	    return "recepcionista/listagem";
	} else {
	    return "menu";
	}

    }

    private String doPost(HttpServletRequest request) {

	AuthService authenticator = new AuthService(request.getSession());

	if (authenticator.isValid()
		&& (authenticator.isAllowed(PerfilEnum.GERENTE) || authenticator.isAllowed(PerfilEnum.RECEPCAO))) {
	    return "recepcionista/listagem";
	} else {
	    return "menu";
	}
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
