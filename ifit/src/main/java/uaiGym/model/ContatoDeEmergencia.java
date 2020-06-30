package uaiGym.model;

import uaiGym.model.enuns.ParentescoEnum;

public class ContatoDeEmergencia {
    
    private Integer idContato;
    private Integer idAluno;
    private String nome;
    private String telefone;
    private ParentescoEnum parentesco;

    public ContatoDeEmergencia(Integer idContato, Integer idAluno, String nome, String telefone, ParentescoEnum parentesco) {
	this.idContato = idContato;
	this.idAluno = idAluno;
	this.nome = nome;
	this.telefone = telefone;
	this.setParentesco(parentesco);
    }
    
    public ContatoDeEmergencia(Integer idAluno, String nome, String telefone, ParentescoEnum parentesco) {
	this.idAluno = idAluno;
	this.nome = nome;
	this.telefone = telefone;
	this.parentesco = parentesco;
    }

    public Integer getIdContato() {
        return idContato;
    }

    public Integer getIdAluno() {
        return idAluno;
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