<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<tags:template>
	<jsp:attribute name="title">
		<title>Cadastrar Equipamentos - UaiGym</title>
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
	</jsp:attribute>

	<jsp:body>
		<form method="post" action="cadastrar">
            <div class="card">
                <div class="card-header text-center">
                    <h5 class="card-title">Cadastro de Equipamentos</h5>
                </div>

                <div class="card-body">
                    <div class="form-group">
                        <div
							class="col-md-11 col-form-label text-muted text-right">
                            <h11>*</h11> Campo Obrigatório
                        </div>
                    </div>

                    <div class="form-group row">
                        <label
				class="col-md-2 col-form-label text-right" for="Nome">Nome <h11>*</h11></label>
                        <div class="col-md-8">
                            <input id="nome" name="nome"
					placeholder="" class="form-control" required="" type="text">
                        </div>
                    </div>
                </div>

                <!-- Button (Double) -->
                <div class="card-footer">
                    <div class="form-group">
                        <div class="col-md-12 text-center">
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