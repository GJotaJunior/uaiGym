package uaiGym.model.pessoa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import uaiGym.model.Endereco;
import uaiGym.model.Equipamento;
import uaiGym.model.enuns.PerfilEnum;
import uaiGym.model.enuns.SexoEnum;

public class Gerente extends Recepcao {
	List<Instrutor> instrutores;
	List<Recepcao> secretariado;

	public Gerente(Integer id, String email, String senha, String nome, String cpf, Date nascimento,
			Set<String> telefones, SexoEnum sexo, Endereco endereco, PerfilEnum perfil, int codigoFuncional,
			String contrato, Date admissao, Date demissao, Set<Aluno> alunos, Set<Equipamento> equipamentos,
			Map<Aluno, Instrutor> ligacaoAlunoInstrutor, List<Instrutor> instrutores, List<Recepcao> secretariado) {
		super(id, email, senha, nome, cpf, nascimento, telefones, sexo, endereco, perfil, codigoFuncional, contrato,
				admissao, demissao, alunos, equipamentos, ligacaoAlunoInstrutor);
		this.instrutores = instrutores;
		this.secretariado = secretariado;
	}

	public List<Instrutor> getInstrutores() {
		return instrutores;
	}

	public void cadastroInstrutor(Instrutor instrutor) {
		this.instrutores.add(instrutor);
	}

	public List<Recepcao> getSecretariado() {
		return secretariado;
	}

	public void cadastroSecretaria(Recepcao secretaria) {
		this.secretariado.add(secretaria);
	}

	public List<Equipamento> listaEquipamentos() {
		List<Equipamento> equipamentos = new ArrayList<>();
//	for (Secretaria secretaria : this.secretariado) {
//	    for (int i = 0; i < secretaria.getEquipamentos().size(); i++) {
//		if (!equipamentos.contains(secretaria.getEquipamentos().get(i)))
//		    equipamentos.add(secretaria.getEquipamentos().get(i));
//	    }
//	}
		return equipamentos;
	}

	public void remocaoInstrutor(Instrutor instrutor) {
		if (this.instrutores.contains(instrutor))
			this.instrutores.remove(instrutor);
	}

	public void remocaoAluno(Aluno aluno) {
		for (Instrutor instrutor : this.instrutores) {
			if (instrutor.getAlunos().contains(aluno))
				instrutor.getAlunos().remove(aluno);
		}
	}

	public void remocaoSecretaria(Recepcao secretaria) {
		if (this.secretariado.contains(secretaria))
			this.secretariado.remove(secretaria);
	}

}
