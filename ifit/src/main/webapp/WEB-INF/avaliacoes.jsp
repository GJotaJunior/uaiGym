<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<tags:template>
	<jsp:attribute name="title">
		<title>Visualizar Avaliações - UaiGym</title>
	</jsp:attribute>

	<jsp:body>
		<div class="card">
        	<div class="card-header text-center">
            	<h5 class="card-title">Avaliações do aluno ${aluno.nome}</h5>
           	</div>

                    
                <div class="form-group">
                    <div class="col-md-12">
                    	<table class="table">
							<thead>
						    	<tr>
						      		<th scope="col">#</th>
						      		<th scope="col">Altura</th>
						      		<th scope="col">Peso</th>
						      		<th scope="col">Percentual de gordura</th>
						      		<th scope="col">Percentual de resíduos</th>
						      		<th scope="col">Percentual de musculos</th>
						      		<th scope="col">Data</th>
						      		<th scope="col">Instrutor</th>
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
								      		<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${item.medidas.altura}"/> M</td>
								      		<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${item.medidas.peso}"/> KG</td>
								      		<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${item.medidas.gorduraPercentual}"/> %</td>
								      		<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${item.medidas.residuosPercentual}"/> %</td>
								      		<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${item.medidas.musculoPercentual}"/> %</td>
								      		<td><fmt:formatDate pattern="dd/MM/yyyy" value="${item.data}"/></td>
								      		<td>${item.instrutor.nome}</td>
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