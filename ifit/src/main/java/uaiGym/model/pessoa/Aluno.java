package uaiGym.model.pessoa;

import java.util.Date;
import java.util.List;
import java.util.Set;

import uaiGym.model.AvaliacaoFisica;
import uaiGym.model.ContatoDeEmergencia;
import uaiGym.model.Endereco;
import uaiGym.model.Treino;
import uaiGym.model.enuns.PerfilEnum;
import uaiGym.model.enuns.SexoEnum;

public class Aluno extends Usuario {

    private String matricula;
    private Instrutor instrutor;
    private Set<AvaliacaoFisica> avaliacoes;
    private List<Treino> treinos;
    private boolean estaAtivo;
    private Set<ContatoDeEmergencia> contatosDeEmergencia;

    public Aluno(String email, String senha, String nome, String cpf, Date nascimento, Set<String> telefones,
	    SexoEnum sexo, Endereco endereco, String matricula, Instrutor instrutor, Set<AvaliacaoFisica> avaliacoes,
	    List<Treino> treinos, boolean estaAtivo, Set<ContatoDeEmergencia> contatosDeEmergencia) {
	super(email, senha, nome, cpf, nascimento, telefones, sexo, endereco, PerfilEnum.ALUNO);
	this.matricula = matricula;
	this.instrutor = instrutor;
	this.avaliacoes = avaliacoes;
	this.treinos = treinos;
	this.estaAtivo = estaAtivo;
	this.contatosDeEmergencia = contatosDeEmergencia;
    }

    public String getMatricula() {
	return matricula;
    }

    public Instrutor getInstrutor() {
	return instrutor;
    }

    public void setInstrutor(Instrutor instrutor) {
	this.instrutor = instrutor;
    }

    public Set<AvaliacaoFisica> getAvaliacao() {
	return avaliacoes;
    }

    public void setAvaliacoes(Set<AvaliacaoFisica> avaliacoes) {
	this.avaliacoes = avaliacoes;
    }

    public void addAvaliacaoFisica(AvaliacaoFisica avaliacao) {
	this.avaliacoes.add(avaliacao);
    }

    public List<Treino> getTreinos() {
	return treinos;
    }

    public void addTreino(Treino treino) {
	this.treinos.add(treino);
    }

    public boolean isEstaAtivo() {
	return estaAtivo;
    }

    public void setEstaAtivo(boolean estaAtivo) {
	this.estaAtivo = estaAtivo;
    }

    public Set<ContatoDeEmergencia> getContatosDeEmergencia() {
	return contatosDeEmergencia;
    }

    public void addContatoDeEmergencia(ContatoDeEmergencia contatoDeEmergencia) {
	contatosDeEmergencia.add(contatoDeEmergencia);
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Aluno other = (Aluno) obj;
	if (matricula == null) {
	    if (other.matricula != null)
		return false;
	} else if (!matricula.equals(other.matricula))
	    return false;
	return true;
    }

}
