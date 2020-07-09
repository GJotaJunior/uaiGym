package uaiGym.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uaiGym.model.Equipamento;
import uaiGym.model.dao.EquipamentoDAO;
import uaiGym.model.enuns.PerfilEnum;
import uaiGym.service.AuthService;
import uaiGym.service.DataBase.ConnectionFactory;

public class EquipamentoRegisterAction implements Action {

    private String doGet(HttpServletRequest request) {

	AuthService authenticator = new AuthService(request.getSession());

	if (authenticator.isValid()) {
	    if (authenticator.isAllowed(PerfilEnum.GERENTE) || authenticator.isAllowed(PerfilEnum.RECEPCAO)) {
		return "equipamento/cadastrar";
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

		String nome = request.getParameter("nome");

		try {
		    ConnectionFactory cf = new ConnectionFactory();
		    EquipamentoDAO equipDAO = new EquipamentoDAO(cf.recuperarConexao());

		    Equipamento equipamento = new Equipamento(nome);
		    equipDAO.salvar(equipamento);
		} catch (ClassNotFoundException | IOException | SQLException e) {
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
