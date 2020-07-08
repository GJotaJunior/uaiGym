package uaiGym.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uaiGym.model.Endereco;
import uaiGym.model.dao.FuncionarioDAO;
import uaiGym.model.enuns.EstadoEnum;
import uaiGym.model.enuns.PerfilEnum;
import uaiGym.model.enuns.SexoEnum;
import uaiGym.model.pessoa.Funcionario;
import uaiGym.service.AuthService;
import uaiGym.service.DataBase.ConnectionFactory;

public class RecepcaoRegisterAction implements Action {

    private String doGet(HttpServletRequest request) {

	AuthService authenticator = new AuthService(request.getSession());

	if (authenticator.isValid() && authenticator.isAllowed(PerfilEnum.GERENTE)) {
	    return "recepcionista/cadastrar";
	} else {
	    return "menu";
	}

    }

    private String doPost(HttpServletRequest request) {

	AuthService authenticator = new AuthService(request.getSession());

	if (authenticator.isValid() && authenticator.isAllowed(PerfilEnum.GERENTE)) {

	    String nome = request.getParameter("nome");
	    String cpf = request.getParameter("cpf");
	    String telefone1 = request.getParameter("telefone1");
	    String telefone2 = request.getParameter("telefone2");
	    String email = request.getParameter("email");
	    String senha = request.getParameter("senha");
	    String sexo = request.getParameter("sexo");
	    Date dtNascimento = null;

	    try {
		senha = AuthService.securityPassword(senha);
	    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e2) {
		e2.printStackTrace();
	    }

	    Set<String> telefones = new HashSet<String>();
	    if (telefone1 != "")
		telefones.add(telefone1);
	    if (telefone2 != "")
		telefones.add(telefone2);

	    SexoEnum sexoEnum;
	    if (sexo.equals("masculino"))
		sexoEnum = SexoEnum.M;
	    else
		sexoEnum = SexoEnum.F;

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

	    try {
		ConnectionFactory cf = new ConnectionFactory();
		FuncionarioDAO recepcionistaDAO = new FuncionarioDAO(cf.recuperarConexao());

		Funcionario recepcionista = null;
		recepcionista = new Funcionario(email, senha, nome, cpf, dtNascimento, telefones, sexoEnum, endereco,
			perfilEnum, contrato, dtAdmissao, dtDemissao);
		recepcionistaDAO.salvar(recepcionista);
	    } catch (ClassNotFoundException | IOException | SQLException e) {
		e.printStackTrace();
	    }

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