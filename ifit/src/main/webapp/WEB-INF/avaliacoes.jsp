<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="uaiGym.model.enuns.PerfilEnum" %>

<tags:template>
	<jsp:attribute name="title">
		<title>Visualizar Avaliações - UaiGym</title>
		<script>
	        if (document.URL.includes("cadastrar")) window.location.href = document.URL.replace("instrutor/avaliacao/cadastrar", "avaliacao");
	    </script>
	</jsp:attribute>

	<jsp:body>
		<div class="card">
        	<div class="card-header text-center">
        		<c:if test="${usuario.perfil == PerfilEnum.ALUNO}">
		      		<h5 class="card-title">Avaliações do aluno ${usuario.nome}</h5>
	      		</c:if>
	      		<c:if test="${usuario.perfil == PerfilEnum.INSTRUTOR}">
	      			<h5 class="card-title">Avaliações feitas pelo instrutor ${usuario.nome}</h5>
	      		</c:if>
	      		<c:if test="${usuario.perfil == PerfilEnum.GERENTE}">
	      			<h5 class="card-title">Avaliações físicas</h5>
	      		</c:if>
           	</div>

			<div class="card-body">
				<c:if test="${usuario.perfil == PerfilEnum.INSTRUTOR}">
	      			<div class="form-group">
	            	    <div class="col-md-12 text-left">
	                            <a href="/web/instrutor/avaliacao/cadastrar" class="btn btn-primary">Cadastrar avaliação</a>
	                    </div>
	                </div>
	      		</c:if>
                
                    
                <div class="form-group">
                    <div class="col-md-12">
                    	<table class="table">
							<thead>
						    	<tr>
						      		<th scope="col">#</th>
						      		<th scope="col" class="text-center">Altura</th>
						      		<th scope="col"class="text-center">Peso</th>
						      		<th scope="col" class="text-center">Percentual de gordura</th>
						      		<th scope="col" class="text-center">Percentual de resíduos</th>
						      		<th scope="col" class="text-center">Percentual de musculos</th>
						      		<th scope="col" class="text-center">Data</th>
						      		<c:if test="${usuario.perfil != PerfilEnum.INSTRUTOR}">
							      		<th scope="col">Instrutor</th>
						      		</c:if>
						      		<c:if test="${usuario.perfil != PerfilEnum.ALUNO}">
						      			<th scope="col">Aluno</th>
						      		</c:if>
						    	</tr>
						  	</thead>
						  	<tbody>
						  		<c:if test="${requestScope.avaliacoes.size() == 0}">
						  			<tr><td colspan="4" class="text-center">Nenhum registro encontrado.</td></tr>
						  		</c:if>
						  		<c:if test="${requestScope.avaliacoes.size() > 0}">
							  		<c:forEach var="item" items="${requestScope.avaliacoes}" varStatus="loop">
							  			<tr>
								      		<th scope="row">${loop.index + 1}</th>
								      		<td class="text-center"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${item.medidas.altura}"/> M</td>
								      		<td class="text-center"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${item.medidas.peso}"/> KG</td>
								      		<td class="text-center"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${item.medidas.gorduraPercentual}"/> %</td>
								      		<td class="text-center"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${item.medidas.residuosPercentual}"/> %</td>
								      		<td class="text-center"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${item.medidas.musculoPercentual}"/> %</td>
								      		<td class="text-center"><fmt:formatDate pattern="dd/MM/yyyy" value="${item.data}"/></td>	
								      		<c:if test="${usuario.perfil != PerfilEnum.INSTRUTOR}">
									      		<td>${item.instrutor.nome}</td>
								      		</c:if>
								      		<c:if test="${usuario.perfil != PerfilEnum.ALUNO}">
								      			<td>${item.aluno.nome}</td>
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