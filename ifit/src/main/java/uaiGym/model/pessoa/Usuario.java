package uaiGym.model.pessoa;

import java.util.Date;
import java.util.Set;

import uaiGym.model.Endereco;
import uaiGym.model.enuns.PerfilEnum;
import uaiGym.model.enuns.SexoEnum;

public abstract class Usuario {

    private Integer id;
    private String email;
    private String senha;

    private String nome;
    private String cpf;
    private Date dtNascimento;
    private Set<String> telefones;
    private SexoEnum sexo;
    private Endereco endereco;
    private PerfilEnum perfil;

    public Usuario() {
    }

    public Usuario(String email, String senha, String nome, String cpf, Date dtNascimento, Set<String> telefones,
	    SexoEnum sexo, Endereco endereco, PerfilEnum perfil) {

	this.setEmail(email);
	this.setSenha(senha);
	this.nome = nome;
	this.cpf = cpf;
	this.dtNascimento = dtNascimento;
	this.telefones = telefones;
	this.sexo = sexo;
	this.endereco = endereco;
	this.perfil = perfil;
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

    public Date getDtNascimento() {
	return dtNascimento;
    }
    
    public void setDtNascimento(Date dtNascimento) {
	this.dtNascimento = dtNascimento;
    }

    public Set<String> getTelefone() {
	return telefones;
    }

    public void setTelefone(String telefone) {
	this.telefones.add(telefone);
    }

    public SexoEnum getSexo() {
	return sexo;
    }
    
    public void setSexo(SexoEnum se) {
	this.sexo = se;
    }

    public Endereco getEndereco() {
	return endereco;
    }

    public void setEndereco(Endereco endereco) {
	this.endereco = endereco;
    }

    public PerfilEnum getPerfil() {
	return perfil;
    }

    public void setPerfil(PerfilEnum perfil) {
	this.perfil = perfil;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
	result = prime * result + ((dtNascimento == null) ? 0 : dtNascimento.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Usuario other = (Usuario) obj;
	if (cpf == null) {
	    if (other.cpf != null)
		return false;
	} else if (!cpf.equals(other.cpf))
	    return false;
	if (dtNascimento == null) {
	    if (other.dtNascimento != null)
		return false;
	} else if (!dtNascimento.equals(other.dtNascimento))
	    return false;
	return true;
    }

    public int getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getSenha() {
	return senha;
    }

    public void setSenha(String senha) {
	this.senha = senha;
    }

    @Override
    public String toString() {
	return "Usuario [id=" + id + ", email=" + email + ", senha=" + senha + ", nome=" + nome + ", cpf=" + cpf
		+ ", dtNascimento=" + dtNascimento + ", telefones=" + telefones + ", sexo=" + sexo + ", endereco="
		+ endereco + ", perfil=" + perfil + "]";
    }
    
    

}
