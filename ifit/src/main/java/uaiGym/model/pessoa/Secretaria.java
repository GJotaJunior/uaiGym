package uaiGym.model.pessoa;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import uaiGym.model.Endereco;
import uaiGym.model.Equipamento;
import uaiGym.model.enuns.PerfilEnum;
import uaiGym.model.enuns.SexoEnum;

public class Secretaria extends Funcionario {
	
	private Set<Aluno> alunos;
	private Set<Equipamento> equipamentos;
	private Map<Aluno, Instrutor> ligacaoAlunoInstrutor;

	public Secretaria(String nome, String cpf, Date nascimento, String telefone, SexoEnum sexo, Endereco endereco,
			int codigoFuncional, int numeroContrato, Calendar admissao, PerfilEnum perfil) {
		super(nome, cpf, nascimento, telefone, sexo, endereco, codigoFuncional, numeroContrato, admissao, perfil);
		alunos = new HashSet<>();
		equipamentos = new HashSet<>();
		ligacaoAlunoInstrutor = new HashMap<>();
	}

	public void cadastroAlunos(Aluno aluno) {
		if (aluno != null)
			alunos.add(aluno);
	}

	public void cadastroEquipamento(Equipamento equipamento) {
		if (equipamento != null)
			equipamentos.add(equipamento);
	}

	public void criarLigacaoAlunoProfessor(Aluno aluno, Instrutor instrutor) {
		if (aluno != null && instrutor != null)
			this.ligacaoAlunoInstrutor.put(aluno, instrutor);
	}

	public Set<Aluno> getAlunos() {
		return alunos;
	}

	public Set<Equipamento> getEquipamentos() {
		return equipamentos;
	}

	public Instrutor getInstrutorPorAluno(Aluno aluno) {
		return this.ligacaoAlunoInstrutor.get(aluno);
	}

	public Set<Aluno> getAlunorPorInstrutor(Instrutor instrutor) {
		return instrutor.getAlunos();
	}

	public void remocaoAluno(Aluno aluno) {
		if (this.alunos.contains(aluno))
			this.alunos.remove(aluno);
	}
}
