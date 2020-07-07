<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<tags:template>
	<jsp:attribute name="title">
		<title>Cadastrar Aluno - UaiGym</title>
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
		<link rel="stylesheet" href="../../assets/font-awesome/css/font-awesome.min.css">
		<script>
			function limpa_formulario_cep() {
				//Limpa valores do formulário de cep.
				document.getElementById('logradouro').value = ("");
				document.getElementById('bairro').value = ("");
				document.getElementById('cidade').value = ("");
				document.getElementById('estado').value = ("");
	
			}
	
			function meu_callback(conteudo) {
				if (!("erro" in conteudo)) {
					//Atualiza os campos com os valores.
					document.getElementById('logradouro').value = (conteudo.logradouro);
					document.getElementById('bairro').value = (conteudo.bairro);
					document.getElementById('cidade').value = (conteudo.localidade);
					document.getElementById('estado').value = (conteudo.uf);
				} //end if.
				else {
					//CEP não Encontrado.
					limpa_formulario_cep();
					alert("CEP não encontrado.");
					document.getElementById('cep').value = ("");
				}
			}
	
			function pesquisacep(valor) {
	
				//Nova variável "cep" somente com dígitos.
				var cep = valor.replace(/\D/g, '');
	
				//Verifica se campo cep possui valor informado.
				if (cep !== "") {
	
					//Expressão regular para validar o CEP.
					var validacep = /^[0-9]{8}$/;
	
					//Valida o formato do CEP.
					if (validacep.test(cep)) {
	
						//Preenche os campos com "..." enquanto consulta webservice.
						document.getElementById('logradouro').value = "...";
						document.getElementById('bairro').value = "...";
						document.getElementById('cidade').value = "...";
						document.getElementById('estado').value = "...";
	
						//Cria um elemento javascript.
						var script = document.createElement('script');
	
						//Sincroniza com o callback.
						script.src = '//viacep.com.br/ws/' + cep
								+ '/json/?callback=meu_callback';
	
						//Insere script no documento e carrega o conteúdo.
						document.body.appendChild(script);
	
					} //end if.
					else {
						//cep é inválido.
						limpa_formulario_cep();
						alert("Formato de CEP inválido.");
					}
				} //end if.
				else {
					//cep sem valor, limpa formulário.
					limpa_formulario_cep();
				}
			}
	
			function formatar(mascara, documento) {
				var i = documento.value.length;
				var saida = mascara.substring(0, 1);
				var texto = mascara.substring(i);
	
				if (texto.substring(0, 1) != saida) {
					documento.value += texto.substring(0, 1);
				}
	
			}
	
			function idade() {
				var data = document.getElementById("dtNascimento").value;
				var dia = data.substr(0, 2);
				var mes = data.substr(3, 2);
				var ano = data.substr(6, 4);
				var d = new Date();
				var ano_atual = d.getFullYear(), mes_atual = d.getMonth() + 1, dia_atual = d
						.getDate();
	
				ano = +ano, mes = +mes, dia = +dia;
	
				var idade = ano_atual - ano;
	
				if (mes_atual < mes || mes_atual == mes_aniversario
						&& dia_atual < dia) {
					idade--;
				}
				return idade;
			}
	
		</script>
	
	</jsp:attribute>

	<jsp:body>
		<form method="post" action="cadastrar">
            <div class="card">
                <div class="card-header text-center">
                    <h5 class="card-title">Cadastro de Aluno</h5>
                </div>

                <div class="card-body">
                    <div class="form-group">
                        <div
							class="col-md-11 col-form-label text-muted text-right">
                            <h11>*</h11> Campo Obrigatório
                        </div>
                    </div>

                    <fieldset>
                        <legend>Dados pessoais</legend>

                        <div class="form-group row">
                            <label
								class="col-md-2 col-form-label text-right" for="Nome">Nome <h11>*</h11></label>
                            <div class="col-md-8">
                                <input id="nome" name="nome"
									placeholder="" class="form-control" required="" type="text">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label
								class="col-md-2 col-form-label text-right" for="cpf">CPF <h11>*</h11></label>
                            <div class="col-md-2">
                                <input id="cpf" name="cpf"
									placeholder="Apenas números" class="form-control" required=""
									type="text" maxlength="11" pattern="[0-9]+$">
                            </div>

                            <label
								class="col-md-1 col-form-label text-right" for="dtNascimento">Dt. Nasc.<h11>*</h11></label>
                            <div class="col-md-2">
                                <input id="dtNascimento" name="dtNascimento"
									placeholder="DD/MM/AAAA" class="form-control" required=""
									type="text" maxlength="10"
									OnKeyPress="formatar('##/##/####', this)">
                            </div>

                            <label
								class="col-md-1 col-form-label text-right" for="sexo">Sexo <h11>*</h11></label>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input"
									type="radio" name="sexo" value="feminino">
                                <label class="form-check-label"
									for="sexo">Feminino</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input"
									type="radio" name="sexo" value="masculino">
                                <label class="form-check-label"
									for="sexo">Masculino</label>
                            </div>
                        </div>

                        <!-- Prepended text-->
                        <div class="form-group row">
                            <label
								class="col-md-2 col-form-label text-right" for="telefone1">Telefone <h11>*</h11>
                            </label>
                            <div class="col-md-2">
                                <div class="input-group">
                                    <span class="input-group-text"><i
										class="fa fa-phone" aria-hidden="true"></i></span>
                                    <input id="telefone1"
										name="telefone1" class="form-control"
										placeholder="XX XXXXX-XXXX" required="" type="text"
										maxlength="13" pattern="\[0-9]{2}\ [0-9]{4,6}-[0-9]{3,4}$"
										OnKeyPress="formatar('## #####-####', this)">
                                </div>
                            </div>

                            <label
								class="col-md-1 col-form-label text-right" for="telefone2">Telefone</label>
                            <div class="col-md-2">
                                <div class="input-group">
                                    <span class="input-group-text"><i
										class="fa fa-phone" aria-hidden="true"></i></span>
                                    <input id="telefone2"
										name="telefone2" class="form-control"
										placeholder="XX XXXXX-XXXX" type="text" maxlength="13"
										pattern="\[0-9]{2}\ [0-9]{4,6}-[0-9]{3,4}$"
										OnKeyPress="formatar('## #####-####', this)">
                                </div>
                            </div>
                        </div>

                        <!-- Prepended text-->
                        <div class="form-group row">
                            <label
								class="col-md-2 col-form-label text-right" for="email">Email <h11>*</h11>
                            </label>
                            <div class="col-md-5">
                                <div class="input-group">
                                    <span class="input-group-text"><i
										class="fa fa-envelope" aria-hidden="true"></i></span>
                                    <input id="email"
										name="email" class="form-control"
										placeholder="email@email.com" required="" type="text"
										pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$">
                                </div>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label
								class="col-md-2 col-form-label text-right" for="senha">Senha <h11>*</h11></label>
                            <div class="col-md-2">
                                <div class="input-group">
                                    <span class="input-group-text"><i
										class="fa fa-key" aria-hidden="true"></i></span>
                                    <input id="senha" name="senha"
										class="form-control" required="" type="password">
                                </div>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label
								class="col-md-2 col-form-label text-right" for="confirmar-senha">Confirmar Senha
                                <h11>*</h11>
							</label>
                            <div class="col-md-2">
                                <div class="input-group">
                                    <span class="input-group-text"><i
										class="fa fa-key" aria-hidden="true"></i></span>
                                    <input id="confirmar-senha"
										name="confirmar-senha" class="form-control" required=""
										type="password">
                                </div>
                            </div>
                        </div>

                    </fieldset>
                    <hr>
                    <fieldset>
                        <legend>Endereço</legend>
                        <!-- Search input-->
                        <div class="form-group row">
                            <label
								class="col-md-2 col-form-label text-right" for="cep">CEP <h11>*</h11></label>
                            <div class="col-md-2">
                                <input id="cep" name="cep"
									placeholder="Apenas números" class="form-control input-md"
									required="" value="" type="search" maxlength="8"
									pattern="[0-9]+$">
                            </div>
                            <div class="col-md-2">
                                <button type="button"
									class="btn btn-primary" onclick="pesquisacep(cep.value)">Pesquisar</button>
                            </div>
                        </div>

                        <!-- Prepended text-->
                        <div class="form-group row">
                            <label
								class="col-md-2 col-form-label text-right" for="logradouro">Endereço</label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <span class="input-group-text">Rua</span>
                                    <input id="logradouro" name="logradouro"
										class="form-control" placeholder="" required="" type="text">
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="input-group">
                                    <span class="input-group-text">Nº <h11>*</h11></span>
                                    <input id="numero" name="numero"
										class="form-control" placeholder="" required="" type="text">
                                </div>
                            </div>

                            <div class="col-md-4">
                                <div class="input-group">
                                    <span class="input-group-text">Complemento</span>
                                    <input id="complemento"
										name="complemento" class="form-control" placeholder="" type="text">
                                </div>
                            </div>
                        </div>

                        <div class="form-group row">
                            <div class="col-md-2"></div>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <span class="input-group-text">Bairro</span>
                                    <input id="bairro" name="bairro"
										class="form-control" placeholder="" required="" type="text">
                                </div>
                            </div>

                            <div class="col-md-4">
                                <div class="input-group">
                                    <span class="input-group-text">Cidade</span>
                                    <input id="cidade" name="cidade"
										class="form-control" placeholder="" required="" type="text">
                                </div>
                            </div>

                            <div class="col-md-3">
                                <div class="input-group">
                                    <span class="input-group-text">Estado</span>
                                    <input id="estado" name="estado"
										class="form-control" placeholder="" required="" type="text">
                                </div>

                            </div>
                        </div>
                    </fieldset>

                    <hr>

                    <fieldset>
                        <legend>Informações adicionais</legend>
                        <div class="form-group row">
                            <label
								class="col-md-2 col-form-label text-right" for="matricula">Matrícula</label>
                            <div class="col-md-4">
                                <input id="matricula" name="matricula"
									type="text" placeholder="" class="form-control input-md">
                            </div>
                        </div>
                    </fieldset>
                    
                    <fieldset>
                    	<legend>Contato de emergência</legend>
                    	
                    	<div class="form-group row">
                            <label
								class="col-md-2 col-form-label text-right" for="contatoNome1">Nome</label>
                            <div class="col-md-2">
                                <input id="contatoNome1" name="contatoNome1"
									placeholder="" class="form-control" type="text">
                            </div>
                        
                            <label
								class="col-md-1 col-form-label text-right" for="contatoTelefone1">Telefone
                            </label>
                            <div class="col-md-2">
                                <div class="input-group">
                                    <span class="input-group-text"><i
										class="fa fa-phone" aria-hidden="true"></i></span>
                                    <input id="contatoTelefone1"
										name="contatoTelefone1" class="form-control"
										placeholder="XX XXXXX-XXXX" type="text"
										maxlength="13" pattern="\[0-9]{2}\ [0-9]{4,6}-[0-9]{3,4}$"
										OnKeyPress="formatar('## #####-####', this)">
                                </div>
                            </div>
                        
                            <label class="col-md-1 control-label" for="contatoParentesco1">Parentesco</label>
                            <div class="col-md-2">
                                <select id="contatoParentesco1" name="contatoParentesco1" class="form-control">
                                    <option value=""></option>
                                    <option value="PAI">PAI</option>
                                    <option value="MAE">MÃE</option>
                                    <option value="FILHO_A">FILHO(A)</option>
                                    <option value="AVO_A">AVÔ(Ó)</option>
                                    <option value="CONJUGE">CÔNJUGE</option>
                                    <option value="NAMORADO_A">NAMORADO(A)</option>
                                    <option value="AMIGO_A">AMIGO(A)</option>
                                    <option value="TIO_A">TIO(A)</option>
                                    <option value="OUTRO">OUTRO</option>
                                </select>
                            </div>
                        </div>
                        
                        <div class="form-group row">
                            <label
								class="col-md-2 col-form-label text-right" for="contatoNome2">Nome</label>
                            <div class="col-md-2">
                                <input id="contatoNome2" name="contatoNome2"
									placeholder="" class="form-control" type="text">
                            </div>
                        
                            <label
								class="col-md-1 col-form-label text-right" for="contatoTelefone2">Telefone
                            </label>
                            <div class="col-md-2">
                                <div class="input-group">
                                    <span class="input-group-text"><i
										class="fa fa-phone" aria-hidden="true"></i></span>
                                    <input id="contatoTelefone2"
										name="contatoTelefone2" class="form-control"
										placeholder="XX XXXXX-XXXX" type="text"
										maxlength="13" pattern="\[0-9]{2}\ [0-9]{4,6}-[0-9]{3,4}$"
										OnKeyPress="formatar('## #####-####', this)">
                                </div>
                            </div>
                        
                            <label class="col-md-1 control-label" for="contatoParentesco2">Parentesco</label>
                            <div class="col-md-2">
                                <select id="contatoParentesco2" name="contatoParentesco2" class="form-control">
                                    <option value=""></option>
                                    <option value="PAI">PAI</option>
                                    <option value="MAE">MÃE</option>
                                    <option value="FILHO_A">FILHO(A)</option>
                                    <option value="AVO_A">AVÔ(Ó)</option>
                                    <option value="CONJUGE">CÔNJUGE</option>
                                    <option value="NAMORADO_A">NAMORADO(A)</option>
                                    <option value="AMIGO_A">AMIGO(A)</option>
                                    <option value="TIO_A">TIO(A)</option>
                                    <option value="OUTRO">OUTRO</option>
                                </select>
                            </div>
                        </div>
                    	
                    </fieldset>
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
    </div>
    </form>
	    </jsp:body>
</tags:template>