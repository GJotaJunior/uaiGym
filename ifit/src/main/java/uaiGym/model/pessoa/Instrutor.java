package uaiGym.model.pessoa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import uaiGym.model.AvaliacaoFisica;
import uaiGym.model.Endereco;
import uaiGym.model.enuns.PerfilEnum;
import uaiGym.model.enuns.SexoEnum;

public class Instrutor extends Funcionario {

	private Set<Aluno> alunos;

	public Instrutor(Integer id, String email, String senha, String nome, String cpf, Date nascimento,
			Set<String> telefones, SexoEnum sexo, Endereco endereco, PerfilEnum perfil, String contrato,
			Date admissao, Date demissao, Set<Aluno> alunos) {
		super(id, email, senha, nome, cpf, nascimento, telefones, sexo, endereco, perfil, contrato, admissao, demissao);
		this.alunos = alunos;
	}

	public Set<Aluno> getAlunos() {
		return alunos;
	}

	public List<Aluno> getAlunosPorNome(String nome) {
		List<Aluno> alunosComONome = new ArrayList<Aluno>();
		for (Aluno aluno : alunos) {
			if (aluno.getNome().contains(nome))
				alunosComONome.add(aluno);
		}
		return alunosComONome;

	}

	public void cadastrarAvaliacaoAluno(AvaliacaoFisica avaliacao) {
		Aluno aluno = avaliacao.getAluno();
		aluno.addAvaliacaoFisica(avaliacao);
		alunos.add(aluno);
	}
}
