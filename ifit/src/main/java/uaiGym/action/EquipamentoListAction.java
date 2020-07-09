package uaiGym.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uaiGym.model.Equipamento;
import uaiGym.model.dao.EquipamentoDAO;
import uaiGym.model.enuns.PerfilEnum;
import uaiGym.service.AuthService;
import uaiGym.service.DataBase.ConnectionFactory;

public class EquipamentoListAction implements Action {

    private String doGet(HttpServletRequest request) {

	AuthService authenticator = new AuthService(request.getSession());

	if (authenticator.isValid()) {
	    if (authenticator.isAllowed(PerfilEnum.GERENTE) || authenticator.isAllowed(PerfilEnum.RECEPCAO)) {

		ConnectionFactory cf;
		try {
		    cf = new ConnectionFactory();
		    EquipamentoDAO equipDAO = new EquipamentoDAO(cf.recuperarConexao());
		    List<Equipamento> equipamentos = equipDAO.listarTodos();
		    request.setAttribute("equipamentos", equipamentos);
		} catch (ClassNotFoundException | SQLException | IOException e) {
		    e.printStackTrace();
		}
		return "equipamento/listagem";
	    } else {
		return "menu";
	    }
	} else {
	    return "index";
	}

    }

    private String doPost(HttpServletRequest request) {

	AuthService authenticator = new AuthService(request.getSession());

	if (authenticator.isValid()) {
	    if (authenticator.isAllowed(PerfilEnum.GERENTE) || authenticator.isAllowed(PerfilEnum.RECEPCAO)) {
		return "equipamento/listagem";
	    } else {
		return "menu";
	    }
	} else {
	    return "index";
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
