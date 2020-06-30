package uaiGym.model;

import java.util.ArrayList;
import java.util.List;

public class Treino {

    private Integer id;
    private String nomeTreino;
    private List<Exercicio> exercicios;
//    private Date dtTreino;
    private Integer qtRepeticao;
    private Integer qtSerie;
    
    public Treino() {
	exercicios = new ArrayList<Exercicio>();
    }

    public String getNomeTreino() {
	return nomeTreino;
    }

    public void setNomeTreino(String nomeTreino) {
	this.nomeTreino = nomeTreino;
    }

    public List<Exercicio> getExercicios() {
	return exercicios;
    }

    public void setExercicios(List<Exercicio> exercicios) {
	this.exercicios = exercicios;
    }

    /*
    public Date getDtTreino() {
	return dtTreino;
    }

    public void setDtTreino(Date dtTreino) {
	this.dtTreino = dtTreino;
    }*/

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

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

}
