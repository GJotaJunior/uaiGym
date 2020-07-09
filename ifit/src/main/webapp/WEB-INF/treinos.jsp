<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="uaiGym.model.enuns.PerfilEnum" %>

<tags:template>
	<jsp:attribute name="title">
		<title>Visualizar Treinos - UaiGym</title>
	</jsp:attribute>

	<jsp:body>
		<div class="card">
        	<div class="card-header text-center">
        		<c:if test="${usuario.perfil == PerfilEnum.ALUNO}">
		      		<h5 class="card-title">Treinos do aluno ${usuario.nome}</h5>
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
                    	<c:forEach var="item" items="${requestScope.treinos}" varStatus="loop">
                    	<div class="card">
                    		<div class="card-header text-center bg-dark text-white">
                    			${item.getNomeTreino()}
                    		</div>
                    		<div class="card-body alert-dark">
                    			<div class="row">
	                    			<c:forEach var="exec" items="${item.getExercicios()}" varStatus="loop">
			                    		<div class="col-md-6">
			                    			<div class="card">
			                    				<div class="card-header text-center bg-dark text-white">
			                    					${exec.getNomeExercicio()}
			                    				</div>
			                    				<div class="card-body alert-warning">
			                    					<div class="row">
			                    						<div class="col-md-6">
			                    							Quantidade de séries: ${exec.getQtSerie()}
			                    						</div>
			                    						<div class="col-md-6">
			                    							Quantidade de repetições: ${exec.getQtRepeticao()}
			                    						</div>
			                    					</div>
			                    				</div>
			                    			</div>
			                    		</div>
			                    	</c:forEach>
		                    	</div>
                    		</div>
                    	</div>
                    	</c:forEach>
                    </div>
                </div>
			</div>
		</div>
	</jsp:body>
</tags:template>