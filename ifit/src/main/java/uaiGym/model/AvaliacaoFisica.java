package uaiGym.model;

import java.util.Date;

import uaiGym.model.dao.FuncionarioDAO;
import uaiGym.model.pessoa.Funcionario;
import uaiGym.service.DataBase.ConnectionFactory;

public class AvaliacaoFisica {

    private Integer id;
    private Integer idAluno;
    private Integer idInstrutor;
    private Date data;
    private MedidasCorporais medidas;

    public AvaliacaoFisica(Integer idAluno, Integer idInstrutor, Date data, MedidasCorporais medidas) {
	this.idAluno= idAluno;
	this.idInstrutor = idInstrutor;
	this.data = data;
	this.medidas = medidas;
    }

    public AvaliacaoFisica(Integer id, Integer idAluno, Integer idInstrutor, Date data, MedidasCorporais medidas) {
	this.id = id;
	this.idAluno = idAluno;
	this.idInstrutor = idInstrutor;
	this.data = data;
	this.medidas = medidas;
    }
    
    public Integer getIdAluno() {
	return idAluno;
    }
    
    public Integer getIdInstrutor() {
	return idInstrutor;
    }
    
    public Funcionario getInstrutor() throws Exception {
    	return new FuncionarioDAO(new ConnectionFactory().recuperarConexao()).recuperarPorId(idInstrutor);
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
