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
	actions.put("GET/instrutor/desativar", new InstrutorDisabledAction());

	actions.put("GET/aluno/", new AlunoListAction());
	actions.put("GET/aluno/listagem", new AlunoListAction());
	actions.put("GET/aluno/cadastrar", new AlunoRegisterAction());
	actions.put("POST/aluno/cadastrar", new AlunoRegisterAction());
	actions.put("GET/aluno/desativar", new AlunoDisabledAction());

	actions.put("GET/avaliacao", new AvaliacoesListAction());
	actions.put("GET/avaliacao/", new AvaliacoesListAction());

	actions.put("GET/recepcionista/", new RecepcaoListAction());
	actions.put("GET/recepcionista/listagem", new RecepcaoListAction());
	actions.put("GET/recepcionista/cadastrar", new RecepcaoRegisterAction());
	actions.put("POST/recepcionista/cadastrar", new RecepcaoRegisterAction());
	actions.put("GET/recepcionista/desativar", new RecepcaoDisabledAction());

	actions.put("GET/gerente/", new GerenteListAction());
	actions.put("GET/gerente/listagem", new GerenteListAction());
	actions.put("GET/gerente/cadastrar", new GerenteRegisterAction());
	actions.put("POST/gerente/cadastrar", new GerenteRegisterAction());
	actions.put("GET/gerente/desativar", new GerenteDisabledAction());
	
	actions.put("GET/equipamento/", new EquipamentoListAction());
	actions.put("GET/equipamento/listagem", new EquipamentoListAction());
	actions.put("GET/equipamento/cadastrar", new EquipamentoRegisterAction());
	actions.put("POST/equipamento/cadastrar", new EquipamentoRegisterAction());

    }

    public static Action getAction(HttpServletRequest request) {
	String method = request.getMethod();
	String url = request.getPathInfo().toLowerCase();

	Action action = actions.getOrDefault(method + url, new NULLAction());
	return action;
    }
}
