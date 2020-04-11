package uaiGym.model.pessoa;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import uaiGym.model.Endereco;
import uaiGym.model.Equipamento;
import uaiGym.model.enuns.PerfilEnum;
import uaiGym.model.enuns.SexoEnum;

public class Recepcao extends Funcionario {

	private Set<Aluno> alunos;
	private Set<Equipamento> equipamentos;
	private Map<Aluno, Instrutor> ligacaoAlunoInstrutor;

	public Recepcao(Integer id, String email, String senha, String nome, String cpf, Date nascimento,
			Set<String> telefones, SexoEnum sexo, Endereco endereco, PerfilEnum perfil, int codigoFuncional,
			String contrato, Date admissao, Date demissao, Set<Aluno> alunos, Set<Equipamento> equipamentos,
			Map<Aluno, Instrutor> ligacaoAlunoInstrutor) {
		super(id, email, senha, nome, cpf, nascimento, telefones, sexo, endereco, perfil, contrato, admissao, demissao);
		this.alunos = alunos;
		this.equipamentos = equipamentos;
		this.ligacaoAlunoInstrutor = ligacaoAlunoInstrutor;
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
