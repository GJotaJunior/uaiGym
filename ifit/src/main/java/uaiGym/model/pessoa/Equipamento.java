package uaiGym.model.pessoa;

import java.util.ArrayList;
import java.util.List;

public class Equipamento {
    private String nomeEquipamento;
    private List<Exercicio> exerciciosEquipamento;
    
    public Equipamento(String nomeEquipamento) {
	this.nomeEquipamento = nomeEquipamento;
	exerciciosEquipamento = new ArrayList<>();
    }

    public String getNomeEquipamento() {
        return nomeEquipamento;
    }

    public void setNomeEquipamento(String nomeEquipamento) {
        this.nomeEquipamento = nomeEquipamento;
    }

    public List<Exercicio> getExerciciosEquipamento() {
        return exerciciosEquipamento;
    }

    public void setExerciciosEquipamento(Exercicio exercicioEquipamento) {
        if(!this.exerciciosEquipamento.contains(exercicioEquipamento))
            this.exerciciosEquipamento.add(exercicioEquipamento);
    }
    
    
}
