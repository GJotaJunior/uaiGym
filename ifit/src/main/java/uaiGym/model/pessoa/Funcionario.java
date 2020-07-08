package uaiGym.model.pessoa;

import java.util.Date;
import java.util.Set;

import uaiGym.model.Endereco;
import uaiGym.model.enuns.PerfilEnum;
import uaiGym.model.enuns.SexoEnum;

public class Funcionario extends Usuario {
    
    private Integer idFuncionario;
    private String contrato;
    private Date admissao;
    private Date demissao;
    
    public Funcionario(String email, String senha, String nome, String cpf, Date nascimento, Set<String> telefones,
	    SexoEnum sexo, Endereco endereco, PerfilEnum perfil, String contrato, Date admissao, Date demissao) {
	super(email, senha, nome, cpf, nascimento, telefones, sexo, endereco, perfil);
	this.contrato = contrato;
	this.admissao = admissao;
	this.demissao = demissao;
    }
    
    public Funcionario(Integer idUsuario, String email, String senha, String nome, String cpf, Date nascimento,
    	    SexoEnum sexo, PerfilEnum perfil, Integer idFuncionario, String contrato, Date admissao, Date demissao) {
    	super(idUsuario, email, senha, nome, cpf, nascimento, sexo, perfil);
    	this.idFuncionario = idFuncionario;
    	this.contrato = contrato;
    	this.admissao = admissao;
    	this.demissao = demissao;
    }
    
    public Funcionario(Integer idUsuario, String email, String senha, String nome, String cpf, Date nascimento, Set<String> telefones,
	    SexoEnum sexo, Endereco endereco, PerfilEnum perfil, Integer idFuncionario, String contrato, Date admissao, Date demissao) {
	super(idUsuario, email, senha, nome, cpf, nascimento, telefones, sexo, endereco, perfil);
	this.idFuncionario = idFuncionario;
	this.contrato = contrato;
	this.admissao = admissao;
	this.demissao = demissao;
    }
    public Integer getIdFuncionario() {
	return idFuncionario;
    }
    
    public Date getDemissao() {
	return demissao;
    }

    public void setDemissao(Date demissao) {
	this.demissao = demissao;
    }

    public String getContrato() {
	return contrato;
    }
    
    public void setContrato(String contrato) {
	this.contrato = contrato;
    }

    public Date getAdmissao() {
	return admissao;
    }
    
    public void setAdmissao(Date dt) {
	this.admissao = dt;
    }

    @Override
    public String toString() {
	super.toString();
	return "Funcionario [contrato=" + contrato + ", admissao=" + admissao + ", demissao=" + demissao + "]";
    }

    
}
