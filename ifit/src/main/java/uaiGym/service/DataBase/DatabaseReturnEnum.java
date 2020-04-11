package uaiGym.service.DataBase;

public enum DatabaseReturnEnum {

    // Erro geral
    GENERAL_ERROR(100),

    // Sucesso em caso de INSERT, UPDATE, DELETE para todas as tabelas
    SUCESSO(200),

    // RANGE 300 - Equipamento
    // Possui exerc�cio vinculado
    ERROR_EQUIPAMENTO_EXERCICIO(301),

    // RANGE 400 - Exerc�cio
    // Possui treino vinculado
    ERROR_EXERCICIO_TREINO(401),

    // RANGE 500 - Treino
    // Possui exerc�cio vinculado
    ERROR_TREINO_EXERCICIO(501),
    // Possui aluno vinculado
    ERROR_TREINO_ALUNO(502);

    private int cod;

    DatabaseReturnEnum(int cod) {
	this.cod = cod;
    }

    public int getCod() {
	return cod;
    }

}

/*
 * Exemplo de como pegar a mensagem pelo codigo
 * 
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