package uaiGym.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NULLAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		return "erro";
	}

}
