<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>

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

            <div class="album py-5 bg-light">
                <div class="container">
                    <div class="row">
                        <div class="col-md-4">
                            <a href="#">
                                <div class="card mb-4 shadow-sm">
                                    <img src="../img/img03.png">
                                    <div class="card-body">
                                        <p class="card-text" style="text-align: center;">Gerenciar Instrutores</p>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="col-md-4">
                            <a href="#">
                                <div class="card mb-4 shadow-sm">
                                    <img class="cartao" src="../img/img04.png">
                                    <div class="card-body">
                                        <p class="card-text" style="text-align: center;">Gerenciar Alunos</p>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="col-md-4">
                            <a href="#">
                                <div class="card mb-4 shadow-sm">
                                    <img class="cartao" src="../img/img06.png">
                                    <div class="card-body">
                                        <p class="card-text" style="text-align: center;">Gerenciar Equipamentos</p>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="col-md-4">
                            <a href="#">
                                <div class="card mb-4 shadow-sm">
                                    <img class="cartao" src="../img/img01.png">
                                    <div class="card-body">
                                        <p class="card-text" style="text-align: center;">Gerenciar Recepcionistas</p>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="col-md-4">
                            <a href="#">
                                <div class="card mb-4 shadow-sm">
                                    <img class="cartao" src="../img/img05.png">
                                    <div class="card-body">
                                        <p class="card-text" style="text-align: center;">Gerenciar Alunos</p>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="col-md-4">
                            <a href="#">
                                <div class="card mb-4 shadow-sm">
                                    <img class="cartao" src="../img/img09.png">
                                    <div class="card-body">
                                        <p class="card-text" style="text-align: center;">Gerenciar Perfil</p>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>
            </div>

        </main>
    </jsp:body>
</tags:template>