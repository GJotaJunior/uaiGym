#################PROCEDURES DE INSERÇÃO##########################

#CALL sp_inserirFuncionario(perfil, nome, cpf, dtNascimento, sexo, email, senha, contrato, dtAdmissao, dtDemissao, logradouro, numero, complemento, bairro, cidade, uf, cep);
CALL sp_inserirFuncionario('GERENTE', 'Laura Bella Trump', 11122233344, '1996-05-18', 'F', 'laurinha.amaisbela@teste.com', '123456', 'contrato1', '2020-01-10', null, 'Rua da prata', 213, 'Casa da frente', 'Finotti', 'Uberlândia', 'MG', '38400123');
CALL sp_inserirFuncionario('RECEPCAO', 'Frederico Antônio', 21122233344, '1989-02-09', 'M', 'fred.tonin@teste.com', '123456', 'contrato2', '2020-02-10', '2020-04-04', 'Rua do ouro', 312, 'Casa do fundo', 'Morumbi', 'Uberlândia', 'MG', '38400456');
CALL sp_inserirFuncionario('RECEPCAO', 'Tânia Araújo Pereira', 19122233344, '2000-09-10', 'F', 'tania.pereirinha@teste.com', '123456', 'contrato3', '2020-01-10', null, 'Rua da madeira', 4213, 'Bloco 1 Apto 301', 'Jardim Patrícia', 'Uberlândia', 'MG', '38400789');
CALL sp_inserirFuncionario('INSTRUTOR', 'Paulo Roberto', 19162233344, '2002-12-15', 'M', 'paulaobombado@teste.com', '123456', 'contrato4', '2020-01-10', null, 'Rua Padre Antônio Manoel', 1010, '', 'Pequis', 'Uberlândia', 'MG', '38401156');
CALL sp_inserirFuncionario('INSTRUTOR', 'Kléber Jacinto Pinto', 69162233345, '1991-01-04', 'M', 'kleberpinto@teste.com', '123456', 'contrato5', '2020-01-10', null, 'Avenida Rondon Pacheco', 9403, 'Edifício Paula Dantas', 'Altamira', 'Uberlândia', 'MG', '38404563');

#CALL sp_inserirAluno(perfil, nome, cpf, dtNascimento, sexo, email, senha, matricula, status, logradouro, numero, complemento, bairro, cidade, uf, cep);
CALL sp_inserirAluno('ALUNO', 'Nawan Soares', 12362233345, '1999-09-04', 'M', 'nawan@teste.com', '123456', 'mat1', 'ATIVO', 'Avenida da secretária', 12, null, 'Planalto', 'Uberlândia', 'MG', '38404565');
CALL sp_inserirAluno('ALUNO', 'Wanderson Gutemberg Silva', 12362233388, '1992-02-03', 'M', 'wands@teste.com', '123456', 'mat2', 'INATIVO', 'Rua Monte Carmelo', 15, 'a mais bonita da rua', 'Martins', 'Uberlândia', 'MG', '38404561');
CALL sp_inserirAluno('ALUNO', 'Gledson Júnior', 57362233388, '2000-08-01', 'M', 'gledin@teste.com', '123456', 'mat3', 'ATIVO', 'Travessa aqui', 24, '', 'Alvorada', 'Uberlândia', 'MG', '38404560');
CALL sp_inserirAluno('ALUNO', 'Yure yure', 57362233308, '2001-04-24', 'M', 'yurelindao@teste.com', '123456', 'mat4', 'ATIVO', 'Pç Borges da Fonseca', 17, '', 'Cruzeiro dos Peixotos', 'Uberlândia', 'MG', '38404562');

#CALL sp_inserirTelefone(idUsuario,telefone)
CALL sp_inserirTelefone(1, '34991919191');
CALL sp_inserirTelefone(2, '34991919393');
CALL sp_inserirTelefone(3, '34991919494');

#CALL sp_inserirContato(idAluno, nome, telefone, parentesco)
CALL sp_inserirContato(1, 'Maria abadia','34991919292','TIO_A');
CALL sp_inserirContato(2, 'Tião Carreiro','34991919393','AVO_A');
CALL sp_inserirContato(3, 'Donald Bolsonaro','34991919494','PAI');

#CALL sp_inserirEquipamento(nome)
CALL sp_inserirEquipamento('Halter Oxer Bola Revestido');
CALL sp_inserirEquipamento('Bicicleta Ergométrica');
CALL sp_inserirEquipamento('Bicicleta Ergométrica Yangfit Mini Bike');
CALL sp_inserirEquipamento('Estação de musculação');
CALL sp_inserirEquipamento('Elíptico');
CALL sp_inserirEquipamento('Jump Kangoo');
CALL sp_inserirEquipamento('Esteira');
CALL sp_inserirEquipamento('Trampolim');
CALL sp_inserirEquipamento('Remo Seco');
CALL sp_inserirEquipamento('Mini biscicleta');

#CALL sp_inserirExercicio(idEquipamento, nome)
CALL sp_inserirExercicio(1, 'Exec1');
CALL sp_inserirExercicio(1, 'Exec2');
CALL sp_inserirExercicio(2, 'Exec3');
CALL sp_inserirExercicio(3, 'Exec4');
CALL sp_inserirExercicio(8, 'Exec5');

#CALL sp_inserirTreino(nome)
CALL sp_inserirTreino('Treino 1');
CALL sp_inserirTreino('Treino 2');
CALL sp_inserirTreino('Treino 3');
CALL sp_inserirTreino('Treino perna');
CALL sp_inserirTreino('Treino maromba');

#CALL sp_inserirExerciciosTreino(idExercicio, idTreino, qtSerie, qtRepeticao)
CALL sp_inserirExerciciosTreino(1, 1, 5, 2);
CALL sp_inserirExerciciosTreino(2, 1, 3, 6);
CALL sp_inserirExerciciosTreino(1, 2, 2, 1);

#CALL sp_inserirAlunoTreino(idAluno, idTreino, idFuncionario, dtTreino)
CALL sp_inserirAlunoTreino(1, 1, 1, '2020-04-10');
CALL sp_inserirAlunoTreino(2, 1, 1, '2020-04-02');
CALL sp_inserirAlunoTreino(3, 2, 2, '2020-01-10');

#CALL sp_inserirAvaliacao(idAluno, idFuncionario, dtAvaliacao, altura, peso, percGordura, percResiduos, percMusculo)
CALL sp_inserirAvaliacao(1, 1, '2020-02-01', '1.78', '90.32', '30', '15.5', '100');


#################PROCEDURES DE ATUALIZAÇÃO##########################

#CALL sp_atualiza_funcionario(idUsuario, perfil, nome, cpf, dtNascimento, sexo, email, senha, contrato, dtAdmissao, dtDemissao, logradouro, numero, complemento, bairro, cidade, uf, cep);
CALL sp_atualiza_funcionario('1', 'GERENTE', 'Laura Bella Trump jÚNIOR', 11122233344, '1996-05-18', 'F', 'laurinha.amaisbeladobrasil@teste.com', '123456', 'contrato1', '2020-01-10', '2020-04-15', 'Rua da prata', 213, 'Casa da frente', 'Finotti', 'Uberlândia', 'MG', '38400123');

#CALL sp_atualiza_aluno(idUsuario, perfil, nome, cpf, dtNascimento, sexo, email, senha, matricula, status, logradouro, numero, complemento, bairro, cidade, uf, cep);
CALL sp_atualiza_aluno(6, 'ALUNO', 'Nawan Soares Benedito', 12362233345, '1999-09-04', 'M', 'nawan.com@teste.com', '123456', 'mat6', 'ATIVO', 'Avenida da secretária', 12, 'fundo', 'Planalto', 'Uberlândia', 'MG', '38404565');

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