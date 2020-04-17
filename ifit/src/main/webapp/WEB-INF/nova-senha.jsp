<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.util.List"%>
<%@page import="uaiGym.model.pessoa.Aluno"%>
<%@page import="uaiGym.model.dao.UsuarioDAO"%>
<%@page import="uaiGym.model.pessoa.Usuario"%>
<%@page import="uaiGym.service.EncryptionService"%>
<%@page import="uaiGym.service.DataBase.ConnectionFactory"%>
<%@page import="uaiGym.model.dao.AlunoDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
    String url = request.getParameter("id");
    ConnectionFactory cf = new ConnectionFactory();
    AlunoDAO alunoDao = new AlunoDAO(cf.recuperarConexao());
    String nome = alunoDao.getNomeByUrl(url);

    request.setAttribute("url", url);
    String primeiroNome = nome;
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Redefinir senha - UaiGym</title>
</head>
<body>
	<p>
		Olá
		<%=primeiroNome%>!
	</p>
	<p>Insira abaixo a sua nova senha:</p>
	<form action="redefinir-senha-confirma" method="POST">
		<label for="senha">Nova senha</label> <input type="text" name="senha" />
		<br /> <label for="nova-senha">Confirme a nova senha</label> <input
			type="text" name="nova-senha" /> <input type="submit"
			value="Redefinir" />
	</form>
</body>
</html>