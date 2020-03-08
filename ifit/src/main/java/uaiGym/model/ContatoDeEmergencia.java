package uaiGym.model;

import uaiGym.model.enuns.ParentescoEnum;

public class ContatoDeEmergencia {

	private String nome;
	private String telefone;
	private ParentescoEnum parentesco;

	public ContatoDeEmergencia(String nome, String telefone, ParentescoEnum parentesco) {
		this.nome = nome;
		this.telefone = telefone;
		this.setParentesco(parentesco);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public ParentescoEnum getParentesco() {
		return parentesco;
	}

	public void setParentesco(ParentescoEnum parentesco) {
		this.parentesco = parentesco;
	}

}