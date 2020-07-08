<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
                    <h5 class="card-title">Cadastro de Avaliação Física</h5>
                </div>
			

                <div class="card-body container-fluid">
					<div class="container justify-content-between">
                    <div class="form-group">
                        <div
								class="col-md-11 col-form-label text-muted text-right">
                            <h11>*</h11> Campo Obrigatório
                        </div>
                    </div>
                    	
                    	<div class="form-group row">
                    		<label class="col-md-2 col-form-label text-center"
								for="aluno">Aluno: <h11>*</h11></label>
                    			<select class="form-control-lg col-md-10"
								name="aluno" id="aluno">
									<c:forEach var="aluno" items="${alunos}">
										<option value="${aluno.id}">${aluno.nome}</option>
									</c:forEach>
								</select>
                    	</div>

                        <div class="form-group row h-300 mt-5">
                            <label
								class="col-md-2 col-form-label text-center" for="altura">Altura: <h11>*</h11></label>
                            <div class="col-md-4">
                                <input id="altura" name="altura"
									placeholder="Valor em (m). Ex: 1.92" class="form-control"
									required="" type="text" maxlength="4">
                            </div>
                            
                            <label
								class="col-md-2 col-form-label text-center" for="peso">Peso: <h11>*</h11></label>
                            <div class="col-md-4">
                                <input id="peso" name="peso"
									placeholder="Valor em (kg) Ex: 76.5" class="form-control"
									required="" type="text" maxlength="6">
                            </div>
                        </div>

                        <div class="form-group row h-300">
                            <label
								class="col-md-2 col-form-label text-center" for="gordura">Percentual de gordura: <h11>*</h11></label>
                            <div class="col-md-4">
                                <input id="gordura" name="gordura"
									placeholder="Valor em (%). Ex: 14.2" class="form-control"
									required="" type="text" maxlength="5">
                            </div>
                            
                            <label
								class="col-md-2 col-form-labe text-center" for="residuos">Percentual de residuos: <h11>*</h11></label>
                            <div class="col-md-4">
                                <input class="form-control" required=""
									type="text" name="residuos" id="residuos"
									placeholder="Valor em (%). Ex: 55.2" maxlength="5">
                            </div>
                        </div>
						
						<div class="form-group row h-300">
                            <label
								class="col-md-2 col-form-label text-center" for="musculos">Percentual de musculos: <h11>*</h11>
                            </label>
                            <div class="col-md-4">
                                <input id="musculo" name="musculos"
									placeholder="Valor em (%). Ex: 30.6" class="form-control"
									required="" type="text" maxlength="5">
                            </div>
                        </div>         
				</div>
                        
                        
                <!-- Button (Double) -->
                <div class="card-footer mt-5 mb-5">
                    <div class="form-group">
                        <div class="col-md-12 mt-3 text-center">
                            <button id="Cadastrar" name="Cadastrar"
										class="btn btn-success" type="Submit">Cadastrar</button>
                            <button id="Cancelar" name="Cancelar"
										class="btn btn-danger" type="Reset">Cancelar</button>
                        </div>
                    </div>
                </div>
            </div>
		</form>
	    </jsp:body>
</tags:template>