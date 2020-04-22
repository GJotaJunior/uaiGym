package uaiGym.model.pessoa;

import java.util.List;

public class Gerente extends Funcionario {
    List<Instrutor> instrutores;
    List<Recepcao> secretariado;
    public List<Instrutor> getInstrutores() {
        return instrutores;
    }
    public void setInstrutores(List<Instrutor> instrutores) {
        this.instrutores = instrutores;
    }
    public List<Recepcao> getSecretariado() {
        return secretariado;
    }
    public void setSecretariado(List<Recepcao> secretariado) {
        this.secretariado = secretariado;
    }
}
