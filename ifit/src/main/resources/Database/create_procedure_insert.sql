#sp_inserir_telefone
DELIMITER $$

CREATE PROCEDURE `sp_inserir_telefone`(_idUsuario INT, _telefone VARCHAR(15))

BEGIN

	INSERT INTO Telefone(idUsuario, telefone) VALUES (_idUsuario, _telefone);

END $$
DELIMITER

#sp_inserir_contato
DELIMITER $$

CREATE PROCEDURE `sp_inserir_contato`(_idAluno INT, _nome VARCHAR(255), _telefone VARCHAR(15), _parentesco VARCHAR(15))

BEGIN

	INSERT INTO Contato(idAluno, nome, telefone, parentesco) VALUES (_idAluno, _nome, _telefone, _parentesco);

END $$
DELIMITER

#sp_inserir_endereco
DELIMITER $$

CREATE PROCEDURE `sp_inserir_endereco`(_idUsuario INT, _logradouro VARCHAR(255), _numero CHAR(6), _complemento VARCHAR(100), 
                                   _bairro VARCHAR(50), _cidade VARCHAR(80), _uf CHAR(2), _cep CHAR(8))

BEGIN

	INSERT INTO Endereco(idUsuario, logradouro, numero, complemento, bairro, cidade, uf, cep) 
    VALUES (_idUsuario, _logradouro, _numero, _complemento, _bairro, _cidade, _uf, _cep);

END $$
DELIMITER

#sp_inserir_usuario
DELIMITER $$

CREATE PROCEDURE `sp_inserir_usuario`(IN _perfil ENUM('ALUNO', 'INSTRUTOR', 'RECEPCAO', 'GERENTE'), _nome VARCHAR(255), _cpf CHAR(11), _dtNascimento DATE, _sexo ENUM('M', 'F'), _email VARCHAR(255),
_senha VARCHAR(255), _logradouro VARCHAR(255), _numero CHAR(6), _complemento VARCHAR(100), _bairro VARCHAR(50), _cidade VARCHAR(80), _uf CHAR(2), _cep CHAR(8), OUT _idUsuario INT)

BEGIN
	
	INSERT INTO Usuario(perfil, nome, cpf, dtNascimento, sexo, email, senha) VALUES (_perfil, _nome, _cpf, _dtNascimento, _sexo, _email, _senha);
	
	SET _idUsuario = LAST_INSERT_ID();
	
	CALL sp_inserirEndereco(_idUsuario, _logradouro, _numero, _complemento, _bairro, _cidade, _uf, _cep);

END $$
DELIMITER

#sp_inserir_aluno
DELIMITER $$

CREATE PROCEDURE `sp_inserir_aluno`(IN _perfil ENUM('ALUNO', 'INSTRUTOR', 'RECEPCAO', 'GERENTE'), _nome VARCHAR(255), _cpf CHAR(11), _dtNascimento DATE, _sexo ENUM('M', 'F'), _email VARCHAR(255),
_senha VARCHAR(255), _matricula VARCHAR(255), _status ENUM('ATIVO', 'INATIVO'), _logradouro VARCHAR(255), _numero CHAR(6), _complemento VARCHAR(100), _bairro VARCHAR(50), _cidade VARCHAR(80), _uf CHAR(2), _cep CHAR(8))


BEGIN
	
	CALL sp_inserirUsuario(_perfil, _nome, _cpf, _dtNascimento, _sexo, _email, _senha, _logradouro, _numero, _complemento, _bairro, _cidade, _uf, _cep, @_idUsuario);
	
	INSERT INTO Aluno(idUsuario, matricula, status) VALUES (@_idUsuario, _matricula, _status);

END $$
DELIMITER

#sp_inserir_funcionario
DELIMITER $$

CREATE PROCEDURE `sp_inserir_funcionario`(IN _perfil ENUM('ALUNO', 'INSTRUTOR', 'RECEPCAO', 'GERENTE'), _nome VARCHAR(255), _cpf CHAR(11), _dtNascimento DATE, _sexo ENUM('M', 'F'), _email VARCHAR(255),
_senha VARCHAR(255), _contrato VARCHAR(255), _dtAdmissao DATE, _dtDemissao DATE, _logradouro VARCHAR(255), _numero CHAR(6), _complemento VARCHAR(100), _bairro VARCHAR(50), _cidade VARCHAR(80), _uf CHAR(2), _cep CHAR(8))

BEGIN
	
	CALL sp_inserirUsuario(_perfil, _nome, _cpf, _dtNascimento, _sexo, _email, _senha, _logradouro, _numero, _complemento, _bairro, _cidade, _uf, _cep, @_idUsuario);
	
	
	INSERT INTO Funcionario(idUsuario, contrato, dtAdmissao, dtDemissao) VALUES (@_idUsuario, _contrato, _dtAdmissao, _dtDemissao);

END $$
DELIMITER

#sp_inserir_equipamento
DELIMITER $$

CREATE PROCEDURE `sp_inserir_equipamento`(IN _nome VARCHAR(255))

BEGIN

	INSERT INTO Equipamento(nome) VALUES (_nome);

END $$
DELIMITER

#sp_inserir_treino
DELIMITER $$

CREATE PROCEDURE `sp_inserir_treino`(IN _nome VARCHAR(255))

BEGIN

	INSERT INTO Treino(nome) VALUES (_nome);

END $$
DELIMITER

#sp_inserir_exercicio
DELIMITER $$

CREATE PROCEDURE `sp_inserir_exercicio`(IN _idEquipamento INT ,_nome VARCHAR(255))

BEGIN

	INSERT INTO Exercicio(idEquipamento, nome) VALUES (_idEquipamento, _nome);

END $$
DELIMITER

#sp_inserir_exercicios_treino
DELIMITER $$

CREATE PROCEDURE `sp_inserir_exercicios_treino`(IN _idExercicio INT ,_idTreino INT, _qtSerie INT, _qtRepeticao INT)

BEGIN

	INSERT INTO ExerciciosTreino(idExercicio, idTreino, qtSerie, qtRepeticao) VALUES (_idExercicio, _idTreino, _qtSerie, _qtRepeticao);

END $$
DELIMITER

#sp_inserir_aluno_treino
DELIMITER $$

CREATE PROCEDURE `sp_inserir_aluno_treino`(_idAluno INT, _idTreino INT, _idFuncionario INT, _dtTreino DATE)

BEGIN

	INSERT INTO AlunoTreino(idAluno, idTreino, idFuncionario, dtTreino) 
    VALUES (_idAluno, _idTreino, _idFuncionario, _dtTreino);

END $$
DELIMITER

#sp_inserir_avaliacao
DELIMITER $$

CREATE PROCEDURE `sp_inserir_avaliacao`(_idAluno INT, _idFuncionario INT, _dtAvaliacao DATE,
	_altura FLOAT, _peso FLOAT, _percGordura FLOAT, _percResiduos FLOAT, _percMusculo FLOAT)

BEGIN

	INSERT INTO Avaliacao(idAluno, idFuncionario, dtAvaliacao, altura, peso, percGordura, percResiduos, percMusculo) 
    VALUES (_idAluno, _idFuncionario, _dtAvaliacao, _altura, _peso, _percGordura, _percResiduos, _percMusculo);

END $$
DELIMITER ;