<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<tags:template>
	<jsp:attribute name="title">
		<title>Listagem de Recepcionistas - UaiGym</title>
		<script>
	        if (document.URL.includes("cadastrar")) window.location.href = document.URL.replace("cadastrar", "");
	    </script>
	</jsp:attribute>

	<jsp:body>
		<div class="card">
        	<div class="card-header text-center">
            	<h5 class="card-title">Listagem de Recepcionistas</h5>
           	</div>

            <div class="card-body">
                <div class="form-group">
            	    <div class="col-md-12 text-left">
                            <a href="cadastrar" class="btn btn-primary">Cadastrar Recepcionista</a>
                    </div>
                </div>
                    
                <div class="form-group">
                    <div class="col-md-12">
                    	<table class="table">
							<thead>
						    	<tr>
						      		<th scope="col">#</th>
						      		<th scope="col">Nome</th>
						      		<th scope="col">E-mail</th>
						      		<th scope="col">Data Admissão</th>
						      		<th scope="col" class="text-center">Opções</th>
						    	</tr>
						  	</thead>
						  	<tbody>
						  		<c:if test="${requestScope.recepcionistas.size() == 0}">
						  			<tr><td colspan="4" class="text-center">Nenhum registro encontrado.</td></tr>
						  		</c:if>
						  		<c:if test="${requestScope.recepcionistas.size() > 0}">
							  		<c:forEach var="item" items="${requestScope.recepcionistas}" varStatus="loop">
							  			<tr>
								      		<th scope="row">${loop.index + 1}</th>
								      		<td>${item.getNome()}</td>
								      		<td>${item.getEmail()}</td>
								      		<td><fmt:formatDate pattern="dd/MM/yyyy" value="${item.getAdmissao()}" /></td>
								      		<c:if test="${item.getDemissao() == null}">
									      		<td class="text-center">
									      			<a href="desativar?id=${item.getIdFuncionario()}" class="btn btn-warning">Desativar</a>
									      		</td>
								      		</c:if>
								      		<c:if test="${item.getDemissao() != null}">
									      		<td class="text-center"></td>
								      		</c:if>
								    	</tr>
							  		</c:forEach>
							  	</c:if>
						  	</tbody>
						</table>
                    </div>
                </div>
			</div>
		</div>
	</jsp:body>
</tags:template>