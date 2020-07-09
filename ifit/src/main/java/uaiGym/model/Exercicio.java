package uaiGym.model;

public class Exercicio {
    private Integer id;
    private String nomeExercicio;
    private Equipamento equipamento;
    private Integer qtRepeticao;
    private Integer qtSerie;

    public String getNomeExercicio() {
	return nomeExercicio;
    }

    public void setNomeExercico(String nomeExercicio) {
	this.nomeExercicio = nomeExercicio;
    }

    public Equipamento getEquipamento() {
	return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
	this.equipamento = equipamento;
    }

    public int getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Integer getQtRepeticao() {
	return qtRepeticao;
    }

    public void setQtRepeticao(Integer qtRepeticao) {
	this.qtRepeticao = qtRepeticao;
    }

    public Integer getQtSerie() {
	return qtSerie;
    }

    public void setQtSerie(Integer qtSerie) {
	this.qtSerie = qtSerie;
    }

}