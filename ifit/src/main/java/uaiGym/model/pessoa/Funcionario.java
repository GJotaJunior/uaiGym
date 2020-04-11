package uaiGym.model.pessoa;

import java.util.Date;
import java.util.Set;

import uaiGym.model.Endereco;
import uaiGym.model.enuns.PerfilEnum;
import uaiGym.model.enuns.SexoEnum;

public abstract class Funcionario extends Usuario {

	private String contrato;
	private final Date admissao;
	private Date demissao;

	public Funcionario(Integer id, String email, String senha, String nome, String cpf, Date nascimento,
			Set<String> telefones, SexoEnum sexo, Endereco endereco, PerfilEnum perfil,
			String contrato, Date admissao, Date demissao) {
		super(id, email, senha, nome, cpf, nascimento, telefones, sexo, endereco, perfil);
		this.contrato = contrato;
		this.admissao = admissao;
		this.demissao = demissao;
	}

	public Date getDemissao() {
		return demissao;
	}

	public void setDemissao(Date demissao) {
		this.demissao = demissao;
	}

	public String getContrato() {
		return contrato;
	}

	public Date getAdmissao() {
		return admissao;
	}

}
