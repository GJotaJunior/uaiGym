package uaiGym.model.pessoa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uaiGym.model.AvaliacaoFisica;
import uaiGym.model.Endereco;
import uaiGym.model.enuns.PerfilEnum;
import uaiGym.model.enuns.SexoEnum;

public class Aluno extends Usuario {

	private final int numeroMatricula;
	private Instrutor instrutor;
	private List<AvaliacaoFisica> avaliacoes;
	private List<Treino> treinos;
	private boolean estaAtivo;

	public Aluno(String nome, String cpf, Date nascimento, String telefone, SexoEnum sexo, Endereco endereco,
			int numeroMatricula, Instrutor instrutor, PerfilEnum perfil) {
		super(nome, cpf, nascimento, telefone, sexo, endereco, perfil);
		this.numeroMatricula = numeroMatricula;
		this.instrutor = instrutor;
		avaliacoes = new ArrayList<AvaliacaoFisica>();
		estaAtivo = true;
	}

	public int getNumeroMatricula() {
		return numeroMatricula;
	}

	public Instrutor getInstrutor() {
		return instrutor;
	}

	public void setInstrutor(Instrutor instrutor) {
		this.instrutor = instrutor;
	}

	public List<AvaliacaoFisica> getAvaliacao() {
		return avaliacoes;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numeroMatricula;
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
		Aluno other = (Aluno) obj;
		if (numeroMatricula != other.numeroMatricula)
			return false;
		return true;
	}
	
}
