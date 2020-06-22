#sp_inserirTelefone
DELIMITER $$

CREATE PROCEDURE `sp_inserirTelefone`(_idUsuario INT, _telefone VARCHAR(15))

BEGIN

	INSERT INTO Telefone(idUsuario, telefone) VALUES (_idUsuario, _telefone);

END $$
DELIMITER

#sp_inserirContato
DELIMITER $$

CREATE PROCEDURE `sp_inserirContato`(_idAluno INT, _nome VARCHAR(255), _telefone VARCHAR(15), _parentesco VARCHAR(15))

BEGIN

	INSERT INTO Contato(idAluno, nome, telefone, parentesco) VALUES (_idAluno, _nome, _telefone, _parentesco);

END $$
DELIMITER

#sp_inserirEndereco
DELIMITER $$

CREATE PROCEDURE `sp_inserirEndereco`(_idUsuario INT, _logradouro VARCHAR(255), _numero CHAR(6), _complemento VARCHAR(100), 
                                   _bairro VARCHAR(50), _cidade VARCHAR(80), _uf CHAR(2), _cep CHAR(8))

BEGIN

	INSERT INTO Endereco(idUsuario, logradouro, numero, complemento, bairro, cidade, uf, cep) 
    VALUES (_idUsuario, _logradouro, _numero, _complemento, _bairro, _cidade, _uf, _cep);

END $$
DELIMITER

#sp_inserirUsuario
DELIMITER $$

CREATE PROCEDURE `sp_inserirUsuario`(IN _perfil ENUM('ALUNO', 'INSTRUTOR', 'RECEPCAO', 'GERENTE'), _nome VARCHAR(255), _cpf CHAR(11), _dtNascimento DATE, _sexo ENUM('M', 'F'), _email VARCHAR(255),
_senha VARCHAR(255), _logradouro VARCHAR(255), _numero CHAR(6), _complemento VARCHAR(100), _bairro VARCHAR(50), _cidade VARCHAR(80), _uf CHAR(2), _cep CHAR(8), OUT _idUsuario INT)

BEGIN
	
	INSERT INTO Usuario(perfil, nome, cpf, dtNascimento, sexo, email, senha) VALUES (_perfil, _nome, _cpf, _dtNascimento, _sexo, _email, _senha);
	
	SET _idUsuario = LAST_INSERT_ID();
	
	CALL sp_inserirEndereco(_idUsuario, _logradouro, _numero, _complemento, _bairro, _cidade, _uf, _cep);

END $$
DELIMITER

#sp_inserirAluno
DELIMITER $$

CREATE PROCEDURE `sp_inserirAluno`(IN _perfil ENUM('ALUNO', 'INSTRUTOR', 'RECEPCAO', 'GERENTE'), _nome VARCHAR(255), _cpf CHAR(11), _dtNascimento DATE, _sexo ENUM('M', 'F'), _email VARCHAR(255),
_senha VARCHAR(255), _matricula VARCHAR(255), _status ENUM('ATIVO', 'INATIVO'), _logradouro VARCHAR(255), _numero CHAR(6), _complemento VARCHAR(100), _bairro VARCHAR(50), _cidade VARCHAR(80), _uf CHAR(2), _cep CHAR(8))


BEGIN
	
	CALL sp_inserirUsuario(_perfil, _nome, _cpf, _dtNascimento, _sexo, _email, _senha, _logradouro, _numero, _complemento, _bairro, _cidade, _uf, _cep, @_idUsuario);
	
	INSERT INTO Aluno(idUsuario, matricula, status) VALUES (@_idUsuario, _matricula, _status);

END $$
DELIMITER

#sp_inserirFuncionario
DELIMITER $$

CREATE PROCEDURE `sp_inserirFuncionario`(IN _perfil ENUM('ALUNO', 'INSTRUTOR', 'RECEPCAO', 'GERENTE'), _nome VARCHAR(255), _cpf CHAR(11), _dtNascimento DATE, _sexo ENUM('M', 'F'), _email VARCHAR(255),
_senha VARCHAR(255), _contrato VARCHAR(255), _dtAdmissao DATE, _dtDemissao DATE, _logradouro VARCHAR(255), _numero CHAR(6), _complemento VARCHAR(100), _bairro VARCHAR(50), _cidade VARCHAR(80), _uf CHAR(2), _cep CHAR(8))

BEGIN
	
	CALL sp_inserirUsuario(_perfil, _nome, _cpf, _dtNascimento, _sexo, _email, _senha, _logradouro, _numero, _complemento, _bairro, _cidade, _uf, _cep, @_idUsuario);
	
	
	INSERT INTO Funcionario(idUsuario, contrato, dtAdmissao, dtDemissao) VALUES (@_idUsuario, _contrato, _dtAdmissao, _dtDemissao);

END $$
DELIMITER

#sp_inserirEquipamento
DELIMITER $$

CREATE PROCEDURE `sp_inserirEquipamento`(IN _nome VARCHAR(255))

BEGIN

	INSERT INTO Equipamento(nome) VALUES (_nome);

END $$
DELIMITER

#sp_inserirTreino
DELIMITER $$

CREATE PROCEDURE `sp_inserirTreino`(IN _nome VARCHAR(255))

BEGIN

	INSERT INTO Treino(nome) VALUES (_nome);

END $$
DELIMITER

#sp_inserirExercicio
DELIMITER $$

CREATE PROCEDURE `sp_inserirExercicio`(IN _idEquipamento INT ,_nome VARCHAR(255))

BEGIN

	INSERT INTO Exercicio(idEquipamento, nome) VALUES (_idEquipamento, _nome);

END $$
DELIMITER

#sp_inserirExerciciosTreino
DELIMITER $$

CREATE PROCEDURE `sp_inserirExerciciosTreino`(IN _idExercicio INT ,_idTreino INT, _qtSerie INT, _qtRepeticao INT)

BEGIN

	INSERT INTO ExerciciosTreino(idExercicio, idTreino, qtSerie, qtRepeticao) VALUES (_idExercicio, _idTreino, _qtSerie, _qtRepeticao);

END $$
DELIMITER

#sp_inserirAlunoTreino
DELIMITER $$

CREATE PROCEDURE `sp_inserirAlunoTreino`(_idAluno INT, _idTreino INT, _idFuncionario INT, _dtTreino DATE)

BEGIN

	INSERT INTO AlunoTreino(idAluno, idTreino, idFuncionario, dtTreino) 
    VALUES (_idAluno, _idTreino, _idFuncionario, _dtTreino);

END $$
DELIMITER

#sp_inserirAvaliacao
DELIMITER $$

CREATE PROCEDURE `sp_inserirAvaliacao`(_idAluno INT, _idFuncionario INT, _dtAvaliacao DATE,
	_altura FLOAT, _peso FLOAT, _percGordura FLOAT, _percResiduos FLOAT, _percMusculo FLOAT)

BEGIN

	INSERT INTO Avaliacao(idAluno, idFuncionario, dtAvaliacao, altura, peso, percGordura, percResiduos, percMusculo) 
    VALUES (_idAluno, _idFuncionario, _dtAvaliacao, _altura, _peso, _percGordura, _percResiduos, _percMusculo);

END $$
DELIMITER ;