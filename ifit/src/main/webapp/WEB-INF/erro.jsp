<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<tags:template>
	<jsp:attribute name="title">
	<title>Erro!</title>
	</jsp:attribute>
    <jsp:body>
    	<div class="container">
    		<div class="row">
    			<div class="col-sm-12 text-center">
    				<h1>Ocorreu um erro inesperado! :(</h1>
    			</div>
    		</div>
    		<div class="row">
    			<div class="col-sm-12 text-center">
    				Volte para a nossa página inicial <a href="/web/">clicando aqui</a>.
    			</div>
    		</div>
    	</div>
    </jsp:body>
</tags:template>