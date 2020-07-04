package uaiGym.model;

public class Equipamento {
    private Integer id;
    private String nomeEquipamento;

    public Equipamento(String nomeEquipamento) {
	this.nomeEquipamento = nomeEquipamento;
    }

    public Equipamento() {
    }

    public String getNomeEquipamento() {
	return nomeEquipamento;
    }

    public void setNomeEquipamento(String nomeEquipamento) {
	this.nomeEquipamento = nomeEquipamento;
    }

    public int getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

}
