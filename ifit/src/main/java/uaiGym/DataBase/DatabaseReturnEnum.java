package uaiGym.DataBase;

public enum DatabaseReturnEnum {

	SUCESSO(200), ERROR(300), EXAMPLE(400);
	
	private int cod;

	DatabaseReturnEnum(Integer cod) {
		this.cod = cod;
	}
	
	public int getCod() {
		return cod;
	}
	
}


/*
	Exemplo de como pegar a mensagem pelo codigo


public class TesteEnum {

	public static void main(String[] args) {
	
		Integer codigoRetornado = 400;
		String retorno = null;
	
		for (DatabaseReturnEnum retornoEnum : DatabaseReturnEnum.values()) {
			if (retornoEnum.getCod() == codigoRetornado)
				retorno = retornoEnum.toString();
		}
		
		System.out.println(retorno);
	}
}

*/