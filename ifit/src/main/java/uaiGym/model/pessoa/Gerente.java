package uaiGym.model.pessoa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uaiGym.model.Endereco;
import uaiGym.model.enuns.PerfilEnum;
import uaiGym.model.enuns.SexoEnum;

public class Gerente extends Pessoa {
    List<Instrutor> instrutores;
    List<Secretaria> secretariado;

    public Gerente(String nome, String cpf, Date nascimento, String telefone, SexoEnum sexo, Endereco endereco,
	    PerfilEnum perfil) {
	super(nome, cpf, nascimento, telefone, sexo, endereco, perfil);
	instrutores = new ArrayList<>();
	secretariado = new ArrayList<>();
    }

    public List<Instrutor> getInstrutores() {
	return instrutores;
    }

    public void cadastroInstrutor(Instrutor instrutor) {
	this.instrutores.add(instrutor);
    }

    public List<Secretaria> getSecretariado() {
	return secretariado;
    }

    public void cadastroSecretaria(Secretaria secretaria) {
	this.secretariado.add(secretaria);
    }

    public List<Equipamento> listaEquipamentos() {
	List<Equipamento> equipamentos = new ArrayList<>();
	for (Secretaria secretaria : this.secretariado) {
	    for (int i = 0; i < secretaria.getEquipamentos().size(); i++) {
		if (!equipamentos.contains(secretaria.getEquipamentos().get(i)))
		    equipamentos.add(secretaria.getEquipamentos().get(i));
	    }
	}
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

    public void remocaoSecretaria(Secretaria secretaria) {
	if (this.secretariado.contains(secretaria))
	    this.secretariado.remove(secretaria);
    }

}
