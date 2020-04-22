package uaiGym.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uaiGym.model.Treino;
import uaiGym.model.dao.AlunoDAO;
import uaiGym.model.dao.InstrutorDAO;
import uaiGym.model.pessoa.Aluno;
import uaiGym.service.DataBase.ConnectionFactory;

@WebServlet("/teste-dao")
public class TesteDao extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public TesteDao() {
	super();
	// TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	try {

	    ConnectionFactory cf = new ConnectionFactory();
	    InstrutorDAO instDao = new InstrutorDAO(cf.recuperarConexao());
	    AlunoDAO ad = new AlunoDAO(cf.recuperarConexao());

	    // Teste 1
	    /*
	     * Instrutor inst = new Instrutor(); inst.setPerfil(PerfilEnum.INSTRUTOR);
	     * inst.setNome("Instrutor grand�o"); inst.setCpf("12312312312"); //Date dtnasc
	     * = new SimpleDateFormat("yyyy-MM-dd").parse("1992-01-01");
	     * //inst.setDtNascimento(dtnasc); inst.setSexo(SexoEnum.M);
	     * inst.setEmail("email@teste.com"); inst.setSenha("1234"); //Date dtadm = new
	     * SimpleDateFormat("yyyy-MM-dd").parse("2019-10-05");
	     * //inst.setAdmissao(dtadm); inst.setContrato("contrato4654"); //Date dtdem =
	     * new SimpleDateFormat("yyyy-MM-dd").parse("2019-10-05");
	     * //inst.setDemissao(dtdem);
	     * 
	     * Endereco end = new Endereco("Rua rua", "12C", "Frente", "Centro",
	     * "Uberl�ndia", "38400470", EstadoEnum.MG);
	     * 
	     * inst.setEndereco(end);
	     * 
	     * //instDao.salvar(inst);
	     */

	    // Teste 2
	    // Instrutor instrutor = instDao.recuperarPorId(5);
	    // System.out.println(instrutor.getNome());

	    // Teste 3
	    Aluno aluno = ad.recuperarPorId(6);
	    System.out.println(aluno.getNome());

	    // Teste 4
	    /*
	     * Set<Aluno> alunos = new HashSet<Aluno>(); //alunos = alunos =
	     * instDao.getAlunosPorIdInstrutor(5); //System.out.println(alunos.toString());
	     * 
	     * System.out.println("inicio loop"); for (Aluno aluno : alunos) {
	     * System.out.println(aluno.getNome()); } System.out.println("fim loop");
	     */

	    for (Treino treino : aluno.getTreinos()) {
		System.out.println(
			treino.getNomeTreino() + "\n" + treino.getDtTreino() + "\n" + treino.getExercicios() + "\n");
	    }

	    System.out.println("THE END!!!");

	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	// TODO Auto-generated method stub
	doGet(request, response);
    }

}
