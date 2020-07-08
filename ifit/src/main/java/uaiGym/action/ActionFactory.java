package uaiGym.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
  
  static Map<String, Action> actions = new HashMap<String, Action>();
  static {
	actions.put("GET/", new LoginAction());
	actions.put("GET/cadastrar", new RegisterAction());
	actions.put("GET/sair", new LogoutAction());
	actions.put("GET/redefinir-senha", new ResetPasswordAction());

	actions.put("POST/", new LoginAction());
	actions.put("POST/cadastrar", new RegisterAction());
	actions.put("POST/redefinir-senha", new ResetPasswordAction());
	actions.put("POST/redefinir-senha-confirma", new ResetPasswordConfirmAction());

	actions.put("GET/instrutor/", new InstrutorListAction());
	actions.put("GET/instrutor/listagem", new InstrutorListAction());
	actions.put("GET/instrutor/cadastrar", new InstrutorRegisterAction());
	actions.put("GET/instrutor/avaliacao/cadastrar", new PhysicalEvaluationRegisterAction());
	actions.put("POST/instrutor/cadastrar", new InstrutorRegisterAction());
	actions.put("POST/instrutor/avaliacao/cadastrar", new PhysicalEvaluationRegisterAction());

	actions.put("GET/aluno/", new AlunoListAction());
	actions.put("GET/aluno/listagem", new AlunoListAction());
	actions.put("GET/aluno/cadastrar", new AlunoRegisterAction());
	actions.put("POST/aluno/cadastrar", new AlunoRegisterAction());

	actions.put("GET/avaliacoes", new AvaliacoesListAction());
	
	actions.put("GET/recepcionista/", new RecepcaoListAction());
	actions.put("GET/recepcionista/listagem", new RecepcaoListAction());
	actions.put("GET/recepcionista/cadastrar", new RecepcaoRegisterAction());
	actions.put("POST/recepcionista/cadastrar", new RecepcaoRegisterAction());
      
	}

	public static Action getAction(HttpServletRequest request) {
		String method = request.getMethod();
		String url = request.getPathInfo().toLowerCase();

		Action action = actions.getOrDefault(method + url, new NULLAction());
		return action;
	}
}
