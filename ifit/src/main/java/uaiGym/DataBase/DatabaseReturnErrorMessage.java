package uaiGym.DataBase;

public class DatabaseReturnErrorMessage {

    static String mensagemErro;

    public static String getMessage(int codigoBanco) {

	DatabaseReturnEnum dbrn = null;

	for (DatabaseReturnEnum retornoEnum : DatabaseReturnEnum.values()) {
	    if (retornoEnum.getCod() == codigoBanco) {
		dbrn = retornoEnum;
		break;
	    }
	}

	switch (dbrn) {
	case GENERAL_ERROR:
	    mensagemErro = "Desculpe, mas não foi possível realizar esta ação. Tente novamente mais tarde ou procure o administrador do sistema!";
	    break;
	case SUCESSO:
	    mensagemErro = "Sucesso";
	    break;
	case ERROR_EQUIPAMENTO_EXERCICIO:
	    mensagemErro = "Não é possível excluir o equipamento, pois existe(m) exercício(s) vinculado(s).";
	    break;
	case ERROR_EXERCICIO_TREINO:
	    mensagemErro = "Não é possível excluir o exercício, pois ele está vinculado a um ou mais treinos.";
	    break;
	case ERROR_TREINO_EXERCICIO:
	    mensagemErro = "Não é possível excluir o treino, pois ele possui exercício(s) vinculado(s).";
	    break;
	case ERROR_TREINO_ALUNO:
	    mensagemErro = "Não é possível excluir o treino, pois existe(m) aluno(s) realiando este treino.";
	    break;
	default:
	    mensagemErro = "Desculpe, mas não foi possível realizar esta ação. Tente novamente mais tarde ou procure o administrador do sistema!";
	    break;

	}

	return mensagemErro;
    }

}

/*
 * Exemplo de como pegar a mensagem pelo codigo
 * 
public class EnumTeste {

    public static void main(String[] args) {
	
	System.out.println(DatabaseReturnErrorMessage.getMessage(100));
    }

}
*/