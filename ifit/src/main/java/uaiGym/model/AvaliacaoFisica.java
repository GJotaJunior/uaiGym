package uaiGym.model;

import java.util.Date;

public class AvaliacaoFisica {

    private Integer id;
    private Integer idInstrutor;
    private Date data;
    private MedidasCorporais medidas;

    public AvaliacaoFisica(Integer idInstrutor, Date data, MedidasCorporais medidas) {
	this.idInstrutor = idInstrutor;
	this.data = data;
	this.medidas = medidas;
    }

    public AvaliacaoFisica(Integer id, Integer idInstrutor, Date data, MedidasCorporais medidas) {
	this.id = id;
	this.idInstrutor = idInstrutor;
	this.data = data;
	this.medidas = medidas;
    }

    public Integer getIdInstrutor() {
	return idInstrutor;
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
