package uaiGym.model.pessoa;

import java.util.Calendar;
import java.util.Date;

import uaiGym.model.Endereco;
import uaiGym.model.enuns.PerfilEnum;
import uaiGym.model.enuns.SexoEnum;

public abstract class Funcionario extends Pessoa {

	private final int codigoFuncional;
	private final int numeroContrato;
	private final Calendar admissao;
	private Calendar demissao;

	public Funcionario(String nome, String cpf, Date nascimento, String telefone, SexoEnum sexo, Endereco endereco,
			int codigoFuncional, int numeroContrato, Calendar admissao, PerfilEnum perfil) {
		super(nome, cpf, nascimento, telefone, sexo, endereco, perfil);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigoFuncional;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (codigoFuncional != other.codigoFuncional)
			return false;
		return true;
	}

}
