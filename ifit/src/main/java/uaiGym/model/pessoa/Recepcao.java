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

    public Recepcao(String email, String senha, String nome, String cpf, Date nascimento, Set<String> telefones,
	    SexoEnum sexo, Endereco endereco, PerfilEnum perfil, String contrato, Date admissao,
	    Date demissao, Set<Aluno> alunos, Set<Equipamento> equipamentos,
	    Map<Aluno, Instrutor> ligacaoAlunoInstrutor) {
	super(email, senha, nome, cpf, nascimento, telefones, sexo, endereco, perfil, contrato, admissao, demissao);
	this.alunos = alunos;
	this.equipamentos = equipamentos;
	this.ligacaoAlunoInstrutor = ligacaoAlunoInstrutor;
    }

    public Recepcao(String email, String senha, String nome, String cpf, java.sql.Date nascimento,
			Set<String> telefonesPorId, SexoEnum sexo, Endereco enderecoPorId, PerfilEnum perfil, String contrato,
			java.sql.Date admissao, java.sql.Date demissao) {
    	super(email, senha, nome, cpf, nascimento, telefonesPorId, sexo, enderecoPorId, perfil, contrato, admissao, demissao);
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
