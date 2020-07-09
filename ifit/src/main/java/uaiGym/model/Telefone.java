package uaiGym.model;

public class Telefone {
    private Integer idTelefone;
    private Integer idUsuario;
    private String telefone;
    
    public Telefone(Integer idUsuario, String telefone) {
	this.idUsuario = idUsuario;
	this.telefone = telefone;
    }
    
    public Telefone(Integer idTelefone, Integer idUsuario, String telefone) {
	this.idTelefone = idTelefone;
	this.idUsuario = idUsuario;
	this.telefone = telefone;
    }

    public Integer getIdTelefone() {
        return idTelefone;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public String getTelefone() {
        return telefone;
    }
    
    public void setTelefone() {
	this.telefone = telefone;
    }
}
