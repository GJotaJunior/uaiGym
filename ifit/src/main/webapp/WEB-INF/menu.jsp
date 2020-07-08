<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="uaiGym.model.enuns.PerfilEnum" %>

<tags:template>
	<jsp:attribute name="title">
		<title>UaiGym - ${usuario.nome}</title>
	</jsp:attribute>
    <jsp:body>
        <main role="main">

            <section class="text-center">
                <div class="container">
                    <h1 style="font-family: fantasy;">Bem vindo ${usuario.nome}!</h1>
                    <!-- <p class="lead text-muted">Something short and leading about the collection below—its contents, the
                    creator, etc. Make it short and sweet, but not too short so folks don’t simply skip over it
                    entirely.</p> -->
                    <p>
                        <img src="https://www.nicepng.com/png/full/28-281004_gym-icon-png.png" alt="logo" width="20%">
                    </p>
                    <!-- <p>
                    <a href="#" class="btn btn-primary my-2">Main call to action</a>
                    <a href="#" class="btn btn-secondary my-2">Secondary action</a>
                </p> -->
                </div>
            </section>

			<c:choose>
          		<c:when test = '${usuario.perfil == PerfilEnum.GERENTE }'>
	          		<c:import url="menus/menu-gerente.jsp"></c:import>
          		</c:when>
          		<c:when test = '${usuario.perfil == PerfilEnum.RECEPCAO }'>
          			<c:import url="menus/menu-recepcionista.jsp"></c:import>
          		</c:when>
          		<c:when test = '${usuario.perfil == PerfilEnum.INSTRUTOR }'>
          			<c:import url="menus/menu-instrutor.jsp"></c:import>
          		</c:when>
          		<c:when test = '${usuario.perfil == PerfilEnum.ALUNO }'>
          			<c:import url="menus/menu-aluno.jsp"></c:import>
          		</c:when>
          	</c:choose>
          	
        </main>
    </jsp:body>
</tags:template>