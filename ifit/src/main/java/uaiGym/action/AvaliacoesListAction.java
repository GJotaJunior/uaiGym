package uaiGym.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uaiGym.model.AvaliacaoFisica;
import uaiGym.model.dao.AvaliacaoFisicaDAO;
import uaiGym.model.enuns.PerfilEnum;
import uaiGym.model.pessoa.Usuario;
import uaiGym.service.AuthService;
import uaiGym.service.DataBase.ConnectionFactory;

public class AvaliacoesListAction implements Action {

	AuthService auth;
	Connection connection;
	Usuario usuario;

	private String doGet(HttpServletRequest request) {

		auth = new AuthService(request.getSession());
		try {
			connection = new ConnectionFactory().recuperarConexao();
			if (!auth.isValid())
				return "index";
			if (auth.isAllowed(PerfilEnum.ALUNO) || auth.isAllowed(PerfilEnum.INSTRUTOR) || auth.isAllowed(PerfilEnum.GERENTE)) {				
				usuario = (Usuario) auth.getAuthenticator().getAttribute("usuario");
				
				if (usuario.getPerfil() == PerfilEnum.ALUNO) return avaliacoesDoAluno(request);
				else if (usuario.getPerfil() == PerfilEnum.INSTRUTOR) return avaliacoesDoInstrutor(request);
				else return todasAvaliacoes(request);
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}

		return "menu";
	}

	private String avaliacoesDoAluno(HttpServletRequest request) {
		List<AvaliacaoFisica> avaliacoes = new AvaliacaoFisicaDAO(connection)
				.buscarAvaliacoesPorIdUsuarioDoAluno(usuario.getId());

		request.setAttribute("usuario", usuario);
		request.setAttribute("avaliacoes", avaliacoes);
		return "avaliacoes";
	}

	private String avaliacoesDoInstrutor(HttpServletRequest request) {
		List<AvaliacaoFisica> avaliacoes = new AvaliacaoFisicaDAO(connection)
				.buscarAvaliacoesPorIdUsuarioDoFuncionario(usuario.getId());
		
		
		request.setAttribute("usuario", usuario);
		request.setAttribute("avaliacoes", avaliacoes);
		return "avaliacoes";
	}
	
	private String todasAvaliacoes(HttpServletRequest request) {
		List<AvaliacaoFisica> avaliacoes = new AvaliacaoFisicaDAO(connection)
				.listarTodos();
		
		request.setAttribute("usuario", usuario);
		request.setAttribute("avaliacoes", avaliacoes);
		return "avaliacoes";
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String returns;
		switch (request.getMethod()) {
		case "GET":
			returns = doGet(request);
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
