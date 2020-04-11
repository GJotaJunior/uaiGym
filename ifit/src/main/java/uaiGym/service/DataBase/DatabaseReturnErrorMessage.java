package uaiGym.service.DataBase;

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
	    mensagemErro = "Desculpe, mas n�o foi poss�vel realizar esta a��o. Tente novamente mais tarde ou procure o administrador do sistema!";
	    break;
	case SUCESSO:
	    mensagemErro = "Sucesso";
	    break;
	case ERROR_EQUIPAMENTO_EXERCICIO:
	    mensagemErro = "N�o � poss�vel excluir o equipamento, pois existe(m) exerc�cio(s) vinculado(s).";
	    break;
	case ERROR_EXERCICIO_TREINO:
	    mensagemErro = "N�o � poss�vel excluir o exerc�cio, pois ele est� vinculado a um ou mais treinos.";
	    break;
	case ERROR_TREINO_EXERCICIO:
	    mensagemErro = "N�o � poss�vel excluir o treino, pois ele possui exerc�cio(s) vinculado(s).";
	    break;
	case ERROR_TREINO_ALUNO:
	    mensagemErro = "N�o � poss�vel excluir o treino, pois existe(m) aluno(s) realizando este treino.";
	    break;
	default:
	    mensagemErro = "Desculpe, mas n�o foi poss�vel realizar esta a��o. Tente novamente mais tarde ou procure o administrador do sistema!";
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