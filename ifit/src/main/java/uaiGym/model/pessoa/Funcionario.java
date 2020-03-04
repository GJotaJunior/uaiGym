package uaiGym.model.pessoa;

import java.util.Calendar;
import java.util.Date;

import uaiGym.model.Endereco;
import uaiGym.model.enuns.SexoEnum;

public abstract class Funcionario extends Pessoa {

	private final int codigoFuncional;
	private final int numeroContrato;
	private final Calendar admissao;
	private Calendar demissao;

	public Funcionario(String nome, String cpf, Date nascimento, String telefone, SexoEnum sexo, Endereco endereco, int codigoFuncional, int numeroContrato, Calendar admissao) {
		super(nome, cpf, nascimento, telefone, sexo, endereco);
		this.codigoFuncional = codigoFuncional;
		this.numeroContrato = numeroContrato;
		this.admissao = admissao;
	}

	public Calendar getDemissao() {
		return demissao;
	}

	public void setDemissao(Calendar demissao) {
		this.demissao = demissao;
	}

	public int getCodigoFuncional() {
		return codigoFuncional;
	}

	public int getNumeroContrato() {
		return numeroContrato;
	}

	public Calendar getAdmissao() {
		return admissao;
	}

}
