package uaiGym.action;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uaiGym.model.AvaliacaoFisica;
import uaiGym.model.MedidasCorporais;
import uaiGym.model.dao.AlunoDAO;
import uaiGym.model.dao.AvaliacaoFisicaDAO;
import uaiGym.model.pessoa.Aluno;
import uaiGym.model.pessoa.Usuario;
import uaiGym.service.DataBase.ConnectionFactory;

public class PhysicalEvaluationRegisterAction implements Action {

    private String doGet(HttpServletRequest request) {
	
	List<Aluno> alunos = new ArrayList<>();
	
	try {
	    // if (authenticator.isValid()) {
		ConnectionFactory cf = new ConnectionFactory();
		AlunoDAO alunoDAO = new AlunoDAO(cf.recuperarConexao());
		
		alunos = alunoDAO.listarTodos();
		request.setAttribute("alunos", alunos);
		
		System.out.println("buscou alunos");
		
	    // } else {
	    //	request.setAttribute("mensagem", "Ação não permitida.");
	    // }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	
	return "instrutor/avaliacao/cadastrar";
    }

    private String doPost(HttpServletRequest request) {

	// AuthService authenticator = new AuthService(request.getSession());
	Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
	Integer idAluno = Integer.parseInt(request.getParameter("aluno"));
	
	float altura = Float.valueOf(request.getParameter("altura"));
	float peso = Float.valueOf(request.getParameter("peso"));
	float gorduraPercentual = Float.valueOf(request.getParameter("gordura"));
	float residuosPercentual = Float.valueOf(request.getParameter("residuos"));
	float musculoPercentual = Float.valueOf(request.getParameter("musculo"));
	
	MedidasCorporais medidas = new MedidasCorporais(altura, peso, gorduraPercentual, residuosPercentual, musculoPercentual);
	
	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	Date dtAvaliacao = null;
	
	try {
	    dtAvaliacao = formato.parse(Calendar.getInstance().getTime().toString());
	} catch (ParseException e1) {
	    e1.printStackTrace();
	}
	
	AvaliacaoFisica avaliacao = new AvaliacaoFisica(idAluno, usuario.getId(), dtAvaliacao , medidas);
	
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

	return "instrutor/avaliacao/cadastrar";
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
