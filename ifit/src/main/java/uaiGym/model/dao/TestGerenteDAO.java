package uaiGym.model.dao;

import java.util.Date;
import java.util.HashSet;

import uaiGym.model.Endereco;
import uaiGym.model.enuns.EstadoEnum;
import uaiGym.model.enuns.PerfilEnum;
import uaiGym.model.enuns.SexoEnum;
import uaiGym.model.pessoa.Gerente;
import uaiGym.service.DataBase.ConnectionFactory;

public class TestGerenteDAO {

	public static void main(String[] args) {

		ConnectionFactory factory = null;
		GerenteDAO gerenteDAO = null;

		try {
			factory = new ConnectionFactory();
			gerenteDAO = new GerenteDAO(factory.recuperarConexao());
		} catch (Exception e) {
			e.printStackTrace();
		}

		Gerente gerente = gerenteDAO.recuperarPorId(1);
		if (!gerente.getNome().split(" ")[0].equals("Laura"))
			System.out.println("Deu merda ao recuperar!");

		gerenteDAO.excluir(1);

		gerenteDAO.listarTodos().forEach(g -> System.out.println(g.getNome()));
		if (gerenteDAO.listarTodos().isEmpty())
			System.out.println("Deu merda no listar todos!");

		Gerente novoG = new Gerente("novo@gerente", "senha", "Novo Gerente", "01030040600", new Date(2000, 12, 15), new HashSet<String>(), SexoEnum.M,
				new Endereco("Rua rua", "numero", "complemento", "bairro", "cidade", "cep", EstadoEnum.MG),
				PerfilEnum.GERENCIA, "contrato", null, null);

		gerenteDAO.salvar(novoG);
	}

}
