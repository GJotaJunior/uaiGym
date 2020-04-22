package uaiGym.model;

import java.util.Date;
import java.util.List;

public class Treino {

    private String nomeTreino;
    private List<Exercicio> exercicios;
    private Date dtTreino;

    public String getNomeTreino() {
	return nomeTreino;
    }

    public void setNomeTreino(String nomeTreino) {
	this.nomeTreino = nomeTreino;
    }

    public List<Exercicio> getExercicios() {
	return exercicios;
    }

    public void addExercicio(Exercicio exercicio) {
	this.exercicios.add(exercicio);
    }

    public Date getDtTreino() {
	return dtTreino;
    }

    public void setDtTreino(Date dtTreino) {
	this.dtTreino = dtTreino;
    }

}
