#################PROCEDURES DE INSERÇÃO##########################

#CALL sp_inserir_funcionario(perfil, nome, cpf, dtNascimento, sexo, email, senha, contrato, dtAdmissao, dtDemissao, logradouro, numero, complemento, bairro, cidade, uf, cep);
CALL sp_inserir_funcionario('GERENTE', 'Laura Bella Trump', 11122233344, '1996-05-18', 'F', 'laurinha.amaisbela@teste.com', '3-841036622-13-319211830-31-91-3085-16103-1075435-56-77-120-7669-9819-7120-41-5670-12', 'contrato1', '2020-01-10', null, 'Rua da prata', 213, 'Casa da frente', 'Finotti', 'Uberlândia', 'MG', '38400123');
CALL sp_inserir_funcionario('RECEPCAO', 'Frederico Antônio', 21122233344, '1989-02-09', 'M', 'fred.tonin@teste.com', '3-841036622-13-319211830-31-91-3085-16103-1075435-56-77-120-7669-9819-7120-41-5670-12', 'contrato2', '2020-02-10', '2020-04-04', 'Rua do ouro', 312, 'Casa do fundo', 'Morumbi', 'Uberlândia', 'MG', '38400456');
CALL sp_inserir_funcionario('RECEPCAO', 'Tânia Araújo Pereira', 19122233344, '2000-09-10', 'F', 'tania.pereirinha@teste.com', '3-841036622-13-319211830-31-91-3085-16103-1075435-56-77-120-7669-9819-7120-41-5670-12', 'contrato3', '2020-01-10', null, 'Rua da madeira', 4213, 'Bloco 1 Apto 301', 'Jardim Patrícia', 'Uberlândia', 'MG', '38400789');
CALL sp_inserir_funcionario('INSTRUTOR', 'Paulo Roberto', 19162233344, '2002-12-15', 'M', 'paulaobombado@teste.com', '3-841036622-13-319211830-31-91-3085-16103-1075435-56-77-120-7669-9819-7120-41-5670-12', 'contrato4', '2020-01-10', null, 'Rua Padre Antônio Manoel', 1010, '', 'Pequis', 'Uberlândia', 'MG', '38401156');
CALL sp_inserir_funcionario('INSTRUTOR', 'Kléber Jacinto Pinto', 69162233345, '1991-01-04', 'M', 'kleberpinto@teste.com', '3-841036622-13-319211830-31-91-3085-16103-1075435-56-77-120-7669-9819-7120-41-5670-12', 'contrato5', '2020-01-10', null, 'Avenida Rondon Pacheco', 9403, 'Edifício Paula Dantas', 'Altamira', 'Uberlândia', 'MG', '38404563');

#CALL sp_inserir_aluno(perfil, nome, cpf, dtNascimento, sexo, email, senha, matricula, status, logradouro, numero, complemento, bairro, cidade, uf, cep);
CALL sp_inserir_aluno('ALUNO', 'Nawan Soares', 12362233345, '1999-09-04', 'M', 'nawan@teste.com', '3-841036622-13-319211830-31-91-3085-16103-1075435-56-77-120-7669-9819-7120-41-5670-12', 'mat1', 'ATIVO', 'Avenida da secretária', 12, null, 'Planalto', 'Uberlândia', 'MG', '38404565');
CALL sp_inserir_aluno('ALUNO', 'Wanderson Gutemberg Silva', 12362233388, '1992-02-03', 'M', 'wands@teste.com', '3-841036622-13-319211830-31-91-3085-16103-1075435-56-77-120-7669-9819-7120-41-5670-12', 'mat2', 'INATIVO', 'Rua Monte Carmelo', 15, 'a mais bonita da rua', 'Martins', 'Uberlândia', 'MG', '38404561');
CALL sp_inserir_aluno('ALUNO', 'Gledson Júnior', 57362233388, '2000-08-01', 'M', 'gledin@teste.com', '3-841036622-13-319211830-31-91-3085-16103-1075435-56-77-120-7669-9819-7120-41-5670-12', 'mat3', 'ATIVO', 'Travessa aqui', 24, '', 'Alvorada', 'Uberlândia', 'MG', '38404560');
CALL sp_inserir_aluno('ALUNO', 'Yure yure', 57362233308, '2001-04-24', 'M', 'yurelindao@teste.com', '3-841036622-13-319211830-31-91-3085-16103-1075435-56-77-120-7669-9819-7120-41-5670-12', 'mat4', 'ATIVO', 'Pç Borges da Fonseca', 17, '', 'Cruzeiro dos Peixotos', 'Uberlândia', 'MG', '38404562');

#CALL sp_inserir_telefone(idUsuario,telefone)
CALL sp_inserir_telefone(1, '34991919191');
CALL sp_inserir_telefone(2, '34991919393');
CALL sp_inserir_telefone(3, '34991919494');

#CALL sp_inserir_contato(idAluno, nome, telefone, parentesco)
CALL sp_inserir_contato(1, 'Maria abadia','34991919292','TIO_A');
CALL sp_inserir_contato(2, 'Tião Carreiro','34991919393','AVO_A');
CALL sp_inserir_contato(3, 'Donald Bolsonaro','34991919494','PAI');

#CALL sp_inserir_equipamento(nome)
CALL sp_inserir_equipamento('Halter Oxer Bola Revestido');
CALL sp_inserir_equipamento('Bicicleta Ergométrica');
CALL sp_inserir_equipamento('Bicicleta Ergométrica Yangfit Mini Bike');
CALL sp_inserir_equipamento('Estação de musculação');
CALL sp_inserir_equipamento('Elíptico');
CALL sp_inserir_equipamento('Jump Kangoo');
CALL sp_inserir_equipamento('Esteira');
CALL sp_inserir_equipamento('Trampolim');
CALL sp_inserir_equipamento('Remo Seco');
CALL sp_inserir_equipamento('Mini biscicleta');

#CALL sp_inserir_exercicio(idEquipamento, nome)
CALL sp_inserir_exercicio(1, 'Exec1');
CALL sp_inserir_exercicio(1, 'Exec2');
CALL sp_inserir_exercicio(2, 'Exec3');
CALL sp_inserir_exercicio(3, 'Exec4');
CALL sp_inserir_exercicio(8, 'Exec5');

#CALL sp_inserir_treino(nome)
CALL sp_inserir_treino('Treino 1');
CALL sp_inserir_treino('Treino 2');
CALL sp_inserir_treino('Treino 3');
CALL sp_inserir_treino('Treino perna');
CALL sp_inserir_treino('Treino maromba');

#CALL sp_inserir_exercicios_treino(idExercicio, idTreino, qtSerie, qtRepeticao)
CALL sp_inserir_exercicios_treino(1, 1, 5, 2);
CALL sp_inserir_exercicios_treino(2, 1, 3, 6);
CALL sp_inserir_exercicios_treino(1, 2, 2, 1);

#CALL sp_inserir_aluno_treino(idAluno, idTreino, idFuncionario, dtTreino)
CALL sp_inserir_aluno_treino(1, 1, 1, '2020-04-10');
CALL sp_inserir_aluno_treino(2, 1, 1, '2020-04-02');
CALL sp_inserir_aluno_treino(3, 2, 2, '2020-01-10');

#CALL sp_inserir_avaliacao(idAluno, idFuncionario, dtAvaliacao, altura, peso, percGordura, percResiduos, percMusculo)
CALL sp_inserir_avaliacao(1, 1, '2020-02-01', '1.78', '90.32', '30', '15.5', '100');


#################PROCEDURES DE ATUALIZAÇÃO##########################

#CALL sp_atualiza_funcionario(idUsuario, perfil, nome, cpf, dtNascimento, sexo, email, senha, contrato, dtAdmissao, dtDemissao, logradouro, numero, complemento, bairro, cidade, uf, cep);
CALL sp_atualiza_funcionario('1', 'GERENTE', 'Laura Bella Trump jÚNIOR', 11122233344, '1996-05-18', 'F', 'laurinha.amaisbeladobrasil@teste.com', '3-841036622-13-319211830-31-91-3085-16103-1075435-56-77-120-7669-9819-7120-41-5670-12', 'contrato1', '2020-01-10', '2020-04-15', 'Rua da prata', 213, 'Casa da frente', 'Finotti', 'Uberlândia', 'MG', '38400123');

#CALL sp_atualiza_aluno(idUsuario, perfil, nome, cpf, dtNascimento, sexo, email, senha, matricula, status, logradouro, numero, complemento, bairro, cidade, uf, cep);
CALL sp_atualiza_aluno(6, 'ALUNO', 'Nawan Soares Benedito', 12362233345, '1999-09-04', 'M', 'nawan.com@teste.com', '3-841036622-13-319211830-31-91-3085-16103-1075435-56-77-120-7669-9819-7120-41-5670-12', 'mat6', 'ATIVO', 'Avenida da secretária', 12, 'fundo', 'Planalto', 'Uberlândia', 'MG', '38404565');

#CALL sp_atualiza_telefone(idTelefone, telefone)
CALL sp_atualiza_telefone(1, '34991919192');

#CALL sp_atualiza_contato(idContato, nome, telefone, parentesco)
CALL sp_atualiza_contato(1, 'Maria abadia Felícia','34991919290','TIO_A');

#CALL sp_atualizar_equipamento(idEquipamento, nome)
CALL sp_atualizar_equipamento(5, 'Elíptico invertiDo');

#CALL sp_atualiza_exercicio(idExercicio, idEquipamento, nome)
CALL sp_atualiza_exercicio(3, 2, 'Exec3 ATUALIZAÇÃO');

#CALL sp_atualiza_treino(idExercicioTreino, idTreino, nome, idExercicio, qtRepeticao, qtSerie)
CALL sp_atualiza_treino(1, 1, 'Treino 1 ATUAL', 1, 10, 2);

#CALL sp_atualiza_treino_aluno(idAlunoTreino, idAluno, idTreino, idFuncionario, dtTreino)
CALL sp_atualiza_treino_aluno(2, 2, 1, 1, '2020-05-10');

#CALL sp_atualiza_avaliacao(idAvaliacao, idAluno, altura, peso, percGordura, percResiduos, percMusculo)
CALL sp_atualiza_avaliacao(1, 1, '1.80', '92.32', '30', '15.5', '100');

#################PROCEDURES DE REMOÇÃO##########################

#CALL sp_deleta_contato(idContato, @status)
CALL sp_deleta_contato(3, @status);
SELECT @status AS status;

#CALL sp_deleta_exercicio(idExercicio, @status)
CALL sp_deleta_exercicio(5, @status);
SELECT @status AS status;

#CALL sp_deleta_telefone()
CALL sp_deleta_telefone(1, @status);
SELECT @status AS status;

#CALL sp_deleta_treino(idTreino, @status)
CALL sp_deleta_treino(3, @status);
SELECT @status AS status;

#CALL sp_desativa_aluno(idAluno, @status)
CALL sp_desativa_aluno(1, @status);
SELECT @status AS status;

#CALL sp_desativa_funcionario(idFuncionario, @status)
CALL sp_desativa_funcionario(1, @status);
SELECT @status AS status;

#CALL sp_deleta_equipamento(idEquipamento, @status)
CALL sp_deleta_equipamento(10, @status);
SELECT @status AS status;