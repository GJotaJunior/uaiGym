package uaiGym.model.pessoa;

import java.util.Date;

public class Funcionario extends Pessoa {
	
	private int codigoFuncional;
	private int numeroContrato;
	private Date admissao;
	private Date demissao;
	
	public int getCodigoFuncional() {
		return codigoFuncional;
	}
	public void setCodigoFuncional(int codigoFuncional) {
		this.codigoFuncional = codigoFuncional;
	}
	public int getNumeroContrato() {
		return numeroContrato;
	}
	public void setNumeroContrato(int numeroContrato) {
		this.numeroContrato = numeroContrato;
	}
	public Date getAdmissao() {
		return admissao;
	}
	public void setAdmissao(Date admissao) {
		this.admissao = admissao;
	}
	public Date getDemissao() {
		return demissao;
	}
	public void setDemissao(Date demissao) {
		this.demissao = demissao;
	}
	
}
