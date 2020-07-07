package uaiGym.action;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uaiGym.model.AvaliacaoFisica;
import uaiGym.model.MedidasCorporais;
import uaiGym.model.dao.AvaliacaoFisicaDAO;
import uaiGym.model.dao.InstrutorDAO;
import uaiGym.model.pessoa.Instrutor;
import uaiGym.model.pessoa.Usuario;
import uaiGym.service.DataBase.ConnectionFactory;

public class PhysicalEvaluationRegisterAction implements Action {

    private String doGet(HttpServletRequest request) {
	return "instrutor/cadastrar-avaliacao-fisica";
    }

    private String doPost(HttpServletRequest request) {

	// AuthService authenticator = new AuthService(request.getSession());
	Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
	
	float altura = Float.valueOf(request.getParameter("altura"));
	float peso = Float.valueOf(request.getParameter("altura"));
	float gorduraPercentual = Float.valueOf(request.getParameter("altura"));
	float residuosPercentual = Float.valueOf(request.getParameter("altura"));
	float musculoPercentual = Float.valueOf(request.getParameter("altura"));
	
	MedidasCorporais medidas = new MedidasCorporais(altura, peso, gorduraPercentual, residuosPercentual, musculoPercentual);
	Instrutor instrutor = null;
	
	try {
	    // if (authenticator.isValid()) {
		ConnectionFactory cf = new ConnectionFactory();
		InstrutorDAO instrutorDAO = new InstrutorDAO(cf.recuperarConexao());
		
		instrutor = instrutorDAO.recuperarPorId(usuario.getId());
		
		System.out.println("buscou instrutor");
	    // } else {
	    //	request.setAttribute("mensagem", "Ação não permitida.");
	    // }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	
	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	Date dtAvaliacao = null;
	
	try {
	    dtAvaliacao = formato.parse(request.getParameter("dtAdmissao"));
	} catch (ParseException e1) {
	    e1.printStackTrace();
	}
	
	AvaliacaoFisica avaliacao = new AvaliacaoFisica(instrutor.getId(), dtAvaliacao , medidas);
	
	System.out.println("mapeamento realizado");
	
	try {
	   // if (authenticator.isValid()) {
	    ConnectionFactory cf = new ConnectionFactory();
	    AvaliacaoFisicaDAO avaliacaoFisicaDAO = new AvaliacaoFisicaDAO(cf.recuperarConexao());

	    avaliacaoFisicaDAO.salvar(avaliacao);
	    System.out.println("avaliacao registrada");
	    // } else {
	    //	request.setAttribute("mensagem", "Ação não permitida.");
	    // }
	} catch (ClassNotFoundException | IOException | SQLException e) {
	    e.printStackTrace();
	}

	System.out.println("terminou de salvar");

	return "instrutor/cadastrar-avaliacao-fisica";
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
