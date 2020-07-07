package uaiGym.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uaiGym.model.AvaliacaoFisica;
import uaiGym.model.ContatoDeEmergencia;
import uaiGym.model.Endereco;
import uaiGym.model.Treino;
import uaiGym.model.dao.AlunoDAO;
import uaiGym.model.enuns.EstadoEnum;
import uaiGym.model.enuns.ParentescoEnum;
import uaiGym.model.enuns.PerfilEnum;
import uaiGym.model.enuns.SexoEnum;
import uaiGym.model.pessoa.Aluno;
import uaiGym.service.AuthService;
import uaiGym.service.DataBase.ConnectionFactory;

public class AlunoRegisterAction implements Action {

    private String doGet(HttpServletRequest request) {

	AuthService authenticator = new AuthService(request.getSession());

	if (authenticator.isValid() && authenticator.isAllowed(PerfilEnum.GERENTE))
	    return "aluno/cadastrar";
	else
	    return "menu";

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

	    String matricula = request.getParameter("matricula");

	    String contatoNome1 = request.getParameter("contatoNome1");
	    String contatoTelefone1 = request.getParameter("contatoTelefone1");
	    String contatoParentesco1 = request.getParameter("contatoParentesco1");
	    String contatoNome2 = request.getParameter("contatoNome2");
	    String contatoTelefone2 = request.getParameter("contatoTelefone2");
	    String contatoParentesco2 = request.getParameter("contatoParentesco2");

	    Set<ContatoDeEmergencia> contatosEmergencia = new HashSet<ContatoDeEmergencia>();
	    if (contatoNome1 != "" && contatoTelefone1 != "" && contatoParentesco1 != "") {
		ParentescoEnum par1 = ParentescoEnum.valueOf(contatoParentesco1);
		ContatoDeEmergencia contatoEmergencia1 = new ContatoDeEmergencia(contatoNome1, contatoTelefone1, par1);
		contatosEmergencia.add(contatoEmergencia1);
	    }
	    if (contatoNome2 != "" && contatoTelefone2 != "" && contatoParentesco2 != "") {
		ParentescoEnum par2 = ParentescoEnum.valueOf(contatoParentesco2);
		ContatoDeEmergencia contatoEmergencia2 = new ContatoDeEmergencia(contatoNome2, contatoTelefone2, par2);
		contatosEmergencia.add(contatoEmergencia2);
	    }

	    Set<AvaliacaoFisica> avaliacoes = new HashSet<AvaliacaoFisica>();

	    List<Treino> treinos = new ArrayList<Treino>();

	    try {
		ConnectionFactory cf = new ConnectionFactory();
		AlunoDAO alunoDAO = new AlunoDAO(cf.recuperarConexao());

		Aluno aluno = new Aluno(email, senha, nome, cpf, dtNascimento, telefones, sexoEnum, endereco, matricula,
			avaliacoes, treinos, true, contatosEmergencia);
		alunoDAO.salvar(aluno);
	    } catch (ClassNotFoundException | IOException | SQLException e) {
		e.printStackTrace();
	    }

	    return "aluno/listagem";
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
