package uaiGym.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uaiGym.model.Endereco;
import uaiGym.model.dao.InstrutorDAO;
import uaiGym.model.enuns.EstadoEnum;
import uaiGym.model.enuns.PerfilEnum;
import uaiGym.model.enuns.SexoEnum;
import uaiGym.model.pessoa.Instrutor;
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

	    Instrutor inst = new Instrutor();

	    inst.setPerfil(PerfilEnum.INSTRUTOR);
	    inst.setNome("Instrutor grandão");
	    inst.setCpf("12312312312");
	    //Date dtnasc = new SimpleDateFormat("yyyy-MM-dd").parse("1992-01-01");
	    //inst.setDtNascimento(dtnasc);
	    inst.setSexo(SexoEnum.M);
	    inst.setEmail("email@teste.com");
	    inst.setSenha("1234");
	    //Date dtadm = new SimpleDateFormat("yyyy-MM-dd").parse("2019-10-05");
	    //inst.setAdmissao(dtadm);
	    inst.setContrato("contrato4654");
	    
	    //Date dtdem = new SimpleDateFormat("yyyy-MM-dd").parse("2019-10-05");
	    //inst.setDemissao(dtdem);
	    
	    Endereco end = new Endereco("Rua rua", "12C", "Frente", "Centro", "Uberlândia", "38400470", EstadoEnum.MG);

	    inst.setEndereco(end);

	    //instDao.salvar(inst);

	    Instrutor instrutor = instDao.recuperarPorId(5);
	    System.out.println(instrutor.getNome() + " | alunos: " + instrutor.getAlunos().toString());
	    
	    System.out.println("THE END!!!");

	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	// TODO Auto-generated method stub
	doGet(request, response);
    }

}
