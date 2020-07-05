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

	actions.put("GET/instrutor/cadastrar", new InstrutorRegisterAction());
	actions.put("POST/instrutor/cadastrar", new InstrutorRegisterAction());
    }

    public static Action getAction(HttpServletRequest request) {
	String method = request.getMethod();
	String url = request.getPathInfo().toLowerCase();

	Action action = actions.getOrDefault(method + url, new NULLAction());
	return action;
    }
}
