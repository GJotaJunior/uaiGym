#CALL FUNCIONARIO(perfil, nome, cpf, dtNascimento, sexo, email, senha, contrato, dtAdmissao, dtDemissao, logradouro, numero, complemento, bairro, cidade, uf, cep);
CALL FUNCIONARIO('GERENTE', 'Laura Bella Trump', 11122233344, '1996-05-18', 'F', 'laurinha.amaisbela@teste.com', '123456', 'contrato1', '2020-01-10', null, 'Rua da prata', 213, 'Casa da frente', 'Finotti', 'Uberlândia', 'MG', '38400123');
CALL FUNCIONARIO('RECEPCAO', 'Frederico Antônio', 21122233344, '1989-02-09', 'M', 'fred.tonin@teste.com', '123456', 'contrato2', '2020-02-10', '2020-04-04', 'Rua do ouro', 312, 'Casa do fundo', 'Morumbi', 'Uberlândia', 'MG', '38400456');
CALL FUNCIONARIO('RECEPCAO', 'Tânia Araújo Pereira', 19122233344, '2000-09-10', 'F', 'tania.pereirinha@teste.com', '123456', 'contrato3', '2020-01-10', null, 'Rua da madeira', 4213, 'Bloco 1 Apto 301', 'Jardim Patrícia', 'Uberlândia', 'MG', '38400789');
CALL FUNCIONARIO('INSTRUTOR', 'Paulo Roberto', 19162233344, '2002-12-15', 'M', 'paulaobombado@teste.com', '123456', 'contrato4', '2020-01-10', null, 'Rua Padre Antônio Manoel', 1010, '', 'Pequis', 'Uberlândia', 'MG', '38401156');
CALL FUNCIONARIO('INSTRUTOR', 'Kléber Jacinto Pinto', 69162233345, '1991-01-04', 'M', 'kleberpinto@teste.com', '123456', 'contrato5', '2020-01-10', null, 'Avenida Rondon Pacheco', 9403, 'Edifício Paula Dantas', 'Altamira', 'Uberlândia', 'MG', '38404563');

#CALL ALUNO(perfil, nome, cpf, dtNascimento, sexo, email, senha, matricula, status, logradouro, numero, complemento, bairro, cidade, uf, cep);
CALL ALUNO('ALUNO', 'Nawan Soares', 12362233345, '1999-09-04', 'M', 'nawan@teste.com', '123456', 'mat1', 'ATIVO', 'Avenida da secretária', 12, null, 'Planalto', 'Uberlândia', 'MG', '38404565');
CALL ALUNO('ALUNO', 'Wanderson Gutemberg Silva', 12362233388, '1992-02-03', 'M', 'wands@teste.com', '123456', 'mat2', 'INATIVO', 'Rua Monte Carmelo', 15, 'a mais bonita da rua', 'Martins', 'Uberlândia', 'MG', '38404561');
CALL ALUNO('ALUNO', 'Gledson Júnior', 57362233388, '2000-08-01', 'M', 'gledin@teste.com', '123456', 'mat3', 'ATIVO', 'Travessa aqui', 24, '', 'Alvorada', 'Uberlândia', 'MG', '38404560');
CALL ALUNO('ALUNO', 'Yure yure', 57362233308, '2001-04-24', 'M', 'yurelindao@teste.com', '123456', 'mat4', 'ATIVO', 'Pç Borges da Fonseca', 17, '', 'Cruzeiro dos Peixotos', 'Uberlândia', 'MG', '38404562');

#CALL EQUIPAMENTO(nome)
CALL EQUIPAMENTO('Halter Oxer Bola Revestido');
CALL EQUIPAMENTO('Bicicleta Ergométrica');
CALL EQUIPAMENTO('Bicicleta Ergométrica Yangfit Mini Bike');
CALL EQUIPAMENTO('Estação de musculação');
CALL EQUIPAMENTO('Elíptico');
CALL EQUIPAMENTO('Jump Kangoo');
CALL EQUIPAMENTO('Esteira');
CALL EQUIPAMENTO('Trampolim');
CALL EQUIPAMENTO('Remo Seco');
CALL EQUIPAMENTO('Mini biscicleta');

#CALL EXERCICIO(id_equipamento, nome)
CALL EXERCICIO(1, 'Exec1');
CALL EXERCICIO(1, 'Exec2');
CALL EXERCICIO(2, 'Exec3');
CALL EXERCICIO(3, 'Exec4');
CALL EXERCICIO(8, 'Exec5');

#CALL CONTATO(idAluno, nome, telefone, parentesco)
CALL CONTATO(1, 'Maria abadia','34991919292','TIO_A');
CALL CONTATO(2, 'Tião Carreiro','34991919393','AVO_A');
CALL CONTATO(3, 'Donald Bolsonaro','34991919494','PAI');