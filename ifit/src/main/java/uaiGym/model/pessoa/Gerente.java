package uaiGym.model.pessoa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import uaiGym.model.Endereco;
import uaiGym.model.enuns.PerfilEnum;
import uaiGym.model.enuns.SexoEnum;

public class Gerente extends Funcionario {

	List<Instrutor> instrutores;
	List<Recepcao> secretariado;
	
	public Gerente(String email, String senha, String nome, String cpf, Date nascimento, Set<String> telefones,
			SexoEnum sexo, Endereco endereco, PerfilEnum perfil, String contrato, Date admissao, Date demissao) {
		super(email, senha, nome, cpf, nascimento, telefones, sexo, endereco, perfil, contrato, demissao, demissao);
		instrutores = new ArrayList<Instrutor>();
	}

	public List<Instrutor> getInstrutores() {
		return instrutores;
	}

	public void setInstrutores(List<Instrutor> instrutores) {
		this.instrutores = instrutores;
	}

	public List<Recepcao> getSecretariado() {
		return secretariado;
	}

	public void setSecretariado(List<Recepcao> secretariado) {
		this.secretariado = secretariado;
	}
}
