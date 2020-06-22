package uaiGym.model;

import java.util.Date;

import uaiGym.model.pessoa.Instrutor;

public class AvaliacaoFisica {

    	private Integer id;
	private Instrutor instrutor;
	private Date data;
	private MedidasCorporais medidas;

	public AvaliacaoFisica(Instrutor instrutor, Date data, MedidasCorporais medidas) {
		this.instrutor = instrutor;
		this.data = data;
		this.medidas = medidas;
	}
	
	public AvaliacaoFisica(Integer id, Instrutor instrutor, Date data, MedidasCorporais medidas) {
	    	this.id = id;
		this.instrutor = instrutor;
		this.data = data;
		this.medidas = medidas;
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


	public Integer getId() {
	    return id;
	}


	public void setId(Integer id) {
	    this.id = id;
	}

}
