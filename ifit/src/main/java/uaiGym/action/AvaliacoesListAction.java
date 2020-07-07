package uaiGym.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uaiGym.model.AvaliacaoFisica;
import uaiGym.model.dao.AvaliacaoFisicaDAO;
import uaiGym.model.pessoa.Usuario;
import uaiGym.service.AuthService;
import uaiGym.service.DataBase.ConnectionFactory;

public class AvaliacoesListAction implements Action {

	private String doGet(HttpServletRequest request) {
		
		try {
			AuthService auth = new AuthService(request.getSession());
			Connection connection = new ConnectionFactory().recuperarConexao();
			
			if (!auth.isValid()) return "index";
			
			Usuario usuario = (Usuario) auth.getAuthenticator().getAttribute("usuario");
			List<AvaliacaoFisica> avaliacoes = new AvaliacaoFisicaDAO(connection).buscarAvaliacoesPorIdUsuario(usuario.getId());
			
			request.setAttribute("aluno", usuario);
			request.setAttribute("avaliacoes", avaliacoes);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			return "menu";
		}
		
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
