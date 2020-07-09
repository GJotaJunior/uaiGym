package uaiGym.service;

public class CPFValidator {

	public static boolean validarCPF(String cpf) {
		cpf = cpf.replace(".", "").replace("-", "");
		if (cpf.equals(""))
			return false;
		if (cpf.length() != 11 || cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222")
				|| cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555")
				|| cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888")
				|| cpf.equals("99999999999"))
			return false;
		// Valida 1o digito
		int soma = 0;
		for (int i = 0; i < 9; i++) {
			soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
		}
		int cpfTeste = soma * 10 % 11;
		if (cpfTeste == 10)
			cpfTeste = 0;
		if (cpfTeste != Character.getNumericValue(cpf.charAt(9)))
			return false;
		// Valida 2o digito
		soma = 0;
		for (int i = 0; i < 10; i++) {
			soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
		}
		cpfTeste = soma * 10 % 11;
		if (cpfTeste == 10)
			cpfTeste = 0;
		if (cpfTeste != Character.getNumericValue(cpf.charAt(10)))
			return false;
		return true;
	}

	/*
	 * Validacao em JS
	 * 
	 * function validarCPF(cpf) { cpf = cpf.replace(/\D/g, ''); if (cpf == "")
	 * return false; if (cpf.length != 11 || cpf == "00000000000" || cpf ==
	 * "11111111111" || cpf == "22222222222" || cpf == "33333333333" || cpf ==
	 * "44444444444" || cpf == "55555555555" || cpf == "66666666666" || cpf ==
	 * "77777777777" || cpf == "88888888888" || cpf == "99999999999") return false;
	 * // Valida 1o digito let soma = 0; for (i = 0; i < 9; i++) { soma +=
	 * parseInt(cpf.charAt(i)) * (10 - i); } let cpfTeste = soma * 10 % 11; if
	 * (cpfTeste == 10) cpfTeste = 0; if (cpfTeste != parseInt(cpf.charAt(9)))
	 * return false; // Valida 2o digito soma = 0; for (i = 0; i < 10; i++) { soma
	 * += parseInt(cpf.charAt(i)) * (11 - i); } cpfTeste = soma * 10 % 11; if
	 * (cpfTeste == 10) cpfTeste = 0; if (cpfTeste != parseInt(cpf.charAt(10)))
	 * return false; return true; }
	 */

}
