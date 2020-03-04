package uaiGym.model.pessoa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uaiGym.model.Endereco;
import uaiGym.model.enuns.SexoEnum;

public abstract class Pessoa {
	
	private String nome;
	private String cpf;
	private Date nascimento;
	private String telefone;
	private SexoEnum sexo;
	private Endereco endereco;
	private List<ContatoDeEmergencia> contatosDeEmergencia;
	
	public Pessoa(String nome, String cpf, Date nascimento, String telefone, SexoEnum sexo, Endereco endereco) {
		this.nome = nome;
		this.cpf = cpf;
		this.nascimento = nascimento;
		this.telefone = telefone;
		this.sexo = sexo;
		this.endereco = endereco;
		this.contatosDeEmergencia = new ArrayList<ContatoDeEmergencia>();
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Date getNascimento() {
		return nascimento;
	}
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public SexoEnum getSexo() {
		return sexo;
	}
	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<ContatoDeEmergencia> getContatosDeEmergencia() {
		return contatosDeEmergencia;
	}

	public void addContatoDeEmergencia(ContatoDeEmergencia contatoDeEmergencia) {
		contatosDeEmergencia.add(contatoDeEmergencia);
	}
	
}
