package uaiGym.action;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uaiGym.model.Endereco;
import uaiGym.model.dao.InstrutorDAO;
import uaiGym.model.enuns.EstadoEnum;
import uaiGym.model.enuns.PerfilEnum;
import uaiGym.model.enuns.SexoEnum;
import uaiGym.model.pessoa.Instrutor;
import uaiGym.service.AuthService;
import uaiGym.service.DataBase.ConnectionFactory;

public class InstrutorRegisterAction implements Action {

    private String doGet(HttpServletRequest request) {

	// AuthService authenticator = new AuthService(request.getSession());

	ConnectionFactory cf;
	try {
	    cf = new ConnectionFactory();
	    InstrutorDAO instrutorDAO = new InstrutorDAO(cf.recuperarConexao());
	    Instrutor instrutor = instrutorDAO.recuperarPorId(6);
	    System.out.println(instrutor.getNome());
	} catch (ClassNotFoundException | IOException | SQLException e) {
	    e.printStackTrace();
	}

	return "instrutor/cadastrar";
	/*
	 * if (authenticator.isValid()) { return "instrutor/cadastrar";
	 * 
	 * } else { // request.setAttribute("mensagem", authenticator.getMessages()); //
	 * return "menu"; return "instrutor/cadastrar"; }
	 */
    }

    private String doPost(HttpServletRequest request) {

	AuthService authenticator = new AuthService(request.getSession());

	String nome = request.getParameter("nome");
	String cpf = request.getParameter("cpf");
	Date dtNascimento = null;
	String sexo = request.getParameter("sexo");
	String telefone1 = request.getParameter("telefone1");
	String telefone2 = request.getParameter("telefone2");
	String email = request.getParameter("email");
	String senha = request.getParameter("senha");
	Set<String> telefones = new HashSet<String>();
	telefones.add(telefone1);
	telefones.add(telefone2);
	SexoEnum sexoEnum = SexoEnum.valueOf((sexo == "masculino" ? "M" : "F"));
	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	try {
	    dtNascimento = formato.parse(request.getParameter("dtNascimento"));
	} catch (ParseException e1) {
	    e1.printStackTrace();
	}

	String cep = request.getParameter("cep");
	String logradouro = request.getParameter("logradouro");
	String numero = request.getParameter("numero");
	String complemento = request.getParameter("complemento");
	String bairro = request.getParameter("bairro");
	String cidade = request.getParameter("cidade");
	String estado = request.getParameter("estado");
	EstadoEnum estadoEnum = EstadoEnum.valueOf(estado);
	Endereco endereco = new Endereco(logradouro, numero, complemento, bairro, cidade, cep, estadoEnum);

	String contrato = request.getParameter("contrato");
	Date dtAdmissao = null;
	Date dtDemissao = null;

	try {
	    dtAdmissao = formato.parse(request.getParameter("dtAdmissao"));
	} catch (ParseException e1) {
	    e1.printStackTrace();
	}
	PerfilEnum perfilEnum = PerfilEnum.INSTRUTOR;

	System.out.println("mapeamento realizado");

	try {
	    // if (authenticator.isValid()) {
	    ConnectionFactory cf = new ConnectionFactory();
	    InstrutorDAO instrutorDAO = new InstrutorDAO(cf.recuperarConexao());

	    Instrutor instrutor = null;
	    instrutor = new Instrutor(email, senha, nome, cpf, dtNascimento, telefones, sexoEnum, endereco, perfilEnum,
		    contrato, dtAdmissao, dtDemissao, null);
	    instrutorDAO.salvar(instrutor);
	    System.out.println("passou da instancia instrutor");
	    // } else {
	    // request.setAttribute("mensagem", "Ação não permitida.");
	    // }
	} catch (ClassNotFoundException | IOException | SQLException e) {
	    e.printStackTrace();
	}

	System.out.println("terminou de salvar");

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
