package uaiGym.model;

import java.util.Date;

import uaiGym.model.pessoa.Aluno;
import uaiGym.model.pessoa.Instrutor;

public class AvaliacaoFisica {

	private Aluno aluno;
	private Instrutor instrutor;
	private Date data;
	private MedidasCorporais medidas;

	public AvaliacaoFisica(Aluno aluno, Instrutor instrutor, Date data, MedidasCorporais medidas) {
		this.aluno = aluno;
		this.instrutor = instrutor;
		this.data = data;
		this.medidas = medidas;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public Instrutor getInstrutor() {
		return instrutor;
	}

	public Date getData() {
		return data;
	}

	public MedidasCorporais getMedidas() {
		return medidas;
	}

}
