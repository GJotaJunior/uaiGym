<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<tags:template>
	<jsp:attribute name="title">
		<title>Cadastrar Avaliação de Aluno - UaiGym</title>
		<style type="text/css">
h11 {
	color: red;
}

#logo {
	width: 50%;
	height: 50%;
}

.panel-heading {
	font-size: 150%;
}
</style>
		<link rel="stylesheet"
			href="../../assets/font-awesome/css/font-awesome.min.css">
	</jsp:attribute>

	<jsp:body>
		<form method="post" action="cadastrar">
            <div class="card">
                <div class="card-header text-center">
                    <h5 class="card-title">Cadastro de de Avaliação Física</h5>
                </div>

                <div class="card-body">
                    <div class="form-group">
                        <div
							class="col-md-11 col-form-label text-muted text-right">
                            <h11>*</h11> Campo Obrigatório
                        </div>
                    </div>

                        <legend>Informações da Avaliação</legend>

                        <div class="form-group row">
                            <label
							class="col-md-2 col-form-label text-right" for="altura">Altura: <h11>*</h11></label>
                            <div class="col-md-8">
                                <input id="altura" name="altura"
								placeholder="Valor em (m)" class="form-control" required="" type="text">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label
							class="col-md-2 col-form-label text-right" for="peso">Peso: <h11>*</h11></label>
                            <div class="col-md-2">
                                <input id="peso" name="peso"
								placeholder="Valor em (kg)" class="form-control" required=""
								type="text" maxlength="11">
                            </div>

                            <label
							class="col-md-1 col-form-label text-right" for="dtNascimento">Percentual de gordura:<h11>*</h11></label>
                            <div class="col-md-2">
                                <input id="gordura"
								name="gordura" placeholder="Valor em (%)"
								class="form-control" required="" type="text" maxlength="10">
                            </div>

                            <label
							class="col-md-1 col-form-label text-right" for="sexo">Percentual de residuos: <h11>*</h11></label>
                            <div class="form-check form-check-inline">
                                <input class="form-control" required=""
								type="text" name="residuos" id="residuos" placeholder="Valor em (%)">
                            </div>
                            <label
							class="col-md-2 col-form-label text-right" for="telefone1">Percentual de musculos: <h11>*</h11>
                            </label>
                            <div class="col-md-2">
                                <input id="musculo"
								name="musculos" placeholder="Valor em (%)"
								class="form-control" required="" type="text" maxlength="10">
                            </div>
                        </div>          
		
		</form>
	    </jsp:body>
</tags:template>