package uaiGym.model;

public class Exercicio {
    private String nomeExercico;
    private String nomeEquipamento;
    private Integer qtSerie;
    private Integer qtRepeticao;

    public String getNomeExercico() {
	return nomeExercico;
    }

    public void setNomeExercico(String nomeExercico) {
	this.nomeExercico = nomeExercico;
    }

    public String getNomeEquipamento() {
	return nomeEquipamento;
    }

    public void setNomeEquipamento(String nomeEquipamento) {
	this.nomeEquipamento = nomeEquipamento;
    }

    public Integer getQtSerie() {
	return qtSerie;
    }

    public void setQtSerie(Integer qtSerie) {
	this.qtSerie = qtSerie;
    }

    public Integer getQtRepeticao() {
	return qtRepeticao;
    }

    public void setQtRepeticao(Integer qtRepeticao) {
	this.qtRepeticao = qtRepeticao;
    }
}