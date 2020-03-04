package uaiGym.model.pessoa;

import java.util.Date;

import uaiGym.model.Endereco;
import uaiGym.model.enuns.SexoEnum;

public class Aluno extends Pessoa {
	
	public final int numeroMatricula;

	public Aluno(String nome, String cpf, Date nascimento, String telefone, SexoEnum sexo, Endereco endereco, int numeroMatricula) {
		super(nome, cpf, nascimento, telefone, sexo, endereco);
		this.numeroMatricula = numeroMatricula;
	}

	public int getNumeroMatricula() {
		return numeroMatricula;
	}

}
