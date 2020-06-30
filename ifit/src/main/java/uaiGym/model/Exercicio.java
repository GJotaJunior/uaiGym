package uaiGym.model;

public class Exercicio {
    private Integer id;
    private String nomeExercicio;
    private Equipamento equipamento;

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

}