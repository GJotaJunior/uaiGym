package uaiGym.model.pessoa;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uaiGym.model.AvaliacaoFisica;
import uaiGym.model.Endereco;
import uaiGym.model.enuns.PerfilEnum;
import uaiGym.model.enuns.SexoEnum;

public class Instrutor extends Funcionario {

	private Set<Aluno> alunos;

	public Instrutor(String nome, String cpf, Date nascimento, String telefone, SexoEnum sexo, Endereco endereco,
			int codigoFuncional, int numeroContrato, Calendar admissao, PerfilEnum perfil) {
		super(nome, cpf, nascimento, telefone, sexo, endereco, codigoFuncional, numeroContrato, admissao, perfil);
		alunos = new HashSet<Aluno>();
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
