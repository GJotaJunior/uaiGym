package uaiGym.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uaiGym.model.dao.AlunoDAO;
import uaiGym.service.DataBase.ConnectionFactory;

public class ResetPasswordConfirmAction implements Action {

	private String doPost(HttpServletRequest request) {

		String idCriptografado = (String) request.getSession().getAttribute("url");
		String senha = request.getParameter("senha");
		System.out.println(idCriptografado + " | " + senha);

		boolean sucesso = false;
		if (!senha.isEmpty() && senha != null) {
			AlunoDAO aluno;
			try {
				ConnectionFactory cf = new ConnectionFactory();
				aluno = new AlunoDAO(cf.recuperarConexao());
				sucesso = aluno.updatePassword(idCriptografado, senha);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return (sucesso) ? "redefinir-senha-confirma" : "erro";
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String returns;
		switch (request.getMethod()) {
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
