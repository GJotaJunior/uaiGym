<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <!-- Bootstrap core CSS -->
    <link href="https://getbootstrap.com/docs/4.5/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <meta charset="UTF-8">
    <title>UaiGym - ${usuario.nome}</title>
</head>

<body>
    <header>
        <div class="collapse bg-dark" id="navbarHeader">
            <div class="container">
                <div class="row">
                    <div class="col-sm-8 col-md-7 py-4">
                        <h4 class="text-white">Sobre nós</h4>
                        <p class="text-muted">Lorem ipsum dolor sit amet consectetur adipisicing elit. Aperiam aliquid
                            porro nobis beatae, quibusdam repellendus expedita eius. Molestias asperiores impedit sint,
                            provident non ad minima, beatae amet eaque totam reprehenderit.</p>
                    </div>
                    <div class="col-sm-4 offset-md-1 py-4">
                        <h4 class="text-white">Mais</h4>
                        <ul class="list-unstyled">
                            <li><a href="https://github.com/GJotaJunior/uaiGym" class="text-white">Veja no Github</a></li>
                            <li><a href="mailto:uaigymacad@gmail.com" class="text-white">Nos envie um email</a></li>
                            <li><a href="/" class="text-white">Página inicial</a></li>
                            <li><a href="/web/sair" class="text-white">Sair</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="navbar navbar-dark bg-dark shadow-sm">
            <div class="container d-flex justify-content-between">
                <a href="#" class="navbar-brand d-flex align-items-center">
                    <strong>Bem-vindo ${usuario.nome}!</strong>
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarHeader"
                    aria-controls="navbarHeader" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
            </div>
        </div>
    </header>

    <main role="main">

        <section class="text-center">
            <div class="container">
                <h1 style="font-family: fantasy;">UAIGYM</h1>
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

    <footer class="text-muted">
        <div class="container">
            <p class="float-right">
                <a href="#">Voltar ao início</a>
            </p>
            <p>&copy; UaiGym 2020 - Todos os direitos reservados!</p>
        </div>
    </footer>
</body>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
    integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
    crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="https://getbootstrap.com/docs/4.5/assets/js/vendor/jquery.slim.min.js"><\/script>')</script>
<script src="https://getbootstrap.com/docs/4.5/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-1CmrxMRARb6aLqgBO7yyAxTOQE2AKb9GfXnEo760AUcUmFx3ibVJJAzGytlQcNXd"
    crossorigin="anonymous"></script>

</html>