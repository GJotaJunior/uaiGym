#sp_atualiza_endereco
DELIMITER //

CREATE PROCEDURE `sp_atualiza_endereco` (
	IN _idUsuario INT, _logradouro VARCHAR(255), _numero CHAR(6), _complemento VARCHAR(100),
    _bairro VARCHAR(50), _cidade VARCHAR(80), _uf CHAR(2), _cep CHAR(8))
    
BEGIN
	UPDATE Endereco
	SET logradouro =_logradouro, numero = _numero, complemento = _complemento,
		bairro = _bairro, cidade = _cidade,  uf = _uf, cep = _cep
	WHERE idUsuario = _idUsuario;
END; //

DELIMITER ;

#sp_atualiza_usuario
DELIMITER //

CREATE PROCEDURE `sp_atualiza_usuario` (
	IN _idUsuario INT, _perfil ENUM('ALUNO', 'INSTRUTOR', 'RECEPCAO', 'GERENTE'), _nome VARCHAR(255),
    _cpf CHAR(11), _dtNascimento DATE, _sexo ENUM('M', 'F'), _email VARCHAR(255), _senha VARCHAR(255),
    _logradouro VARCHAR(255), _numero CHAR(6), _complemento VARCHAR(100),
    _bairro VARCHAR(50), _cidade VARCHAR(80), _uf CHAR(2), _cep CHAR(8))
    
BEGIN
	UPDATE Usuario
    SET perfil = _perfil, nome = _nome, cpf = _cpf, dtNascimento = _dtNascimento,
		sexo = _sexo, email = _email, senha = _senha
	WHERE idUsuario = _idUsuario;
    
    CALL sp_atualiza_endereco(_idUsuario, _logradouro, _numero,
		_complemento, _bairro, _cidade, _uf, _cep);
END; //

DELIMITER ;

#sp_atualiza_aluno
DELIMITER //

CREATE PROCEDURE `sp_atualiza_aluno` (
	IN _idUsuario INT, _perfil ENUM('ALUNO', 'INSTRUTOR', 'RECEPCAO', 'GERENTE'), _nome VARCHAR(255),
    _cpf CHAR(11), _dtNascimento DATE, _sexo ENUM('M', 'F'), _email VARCHAR(255), _senha VARCHAR(255),
    _matricula VARCHAR(255), _status ENUM('ATIVO', 'INATIVO'),
    _logradouro VARCHAR(255), _numero CHAR(6), _complemento VARCHAR(100),
    _bairro VARCHAR(50), _cidade VARCHAR(80), _uf CHAR(2), _cep CHAR(8))

BEGIN
	CALL sp_atualiza_usuario(_idUsuario, _perfil, _nome, _cpf, _dtNascimento, _sexo, _email, _senha,
		_logradouro, _numero, _complemento, _bairro, _cidade, _uf, _cep);

	UPDATE Aluno
	SET matricula = _matricula, status = _status
	WHERE idUsuario = _idUsuario;
END; //

DELIMITER ;

#sp_atualiza_funcionario
DELIMITER //

CREATE PROCEDURE `sp_atualiza_funcionario` (
	IN _idUsuario INT, _perfil ENUM('ALUNO', 'INSTRUTOR', 'RECEPCAO', 'GERENTE'), _nome VARCHAR(255),
    _cpf CHAR(11), _dtNascimento DATE, _sexo ENUM('M', 'F'), _email VARCHAR(255), _senha VARCHAR(255),
    _contrato VARCHAR(255), _dtAdmissao DATE, _dtDemissao DATE,
    _logradouro VARCHAR(255), _numero CHAR(6), _complemento VARCHAR(100),
    _bairro VARCHAR(50), _cidade VARCHAR(80), _uf CHAR(2), _cep CHAR(8))

BEGIN
	CALL sp_atualiza_usuario(_idUsuario, _perfil, _nome, _cpf, _dtNascimento, _sexo, _email, _senha,
		_logradouro, _numero, _complemento, _bairro, _cidade, _uf, _cep);
    
    UPDATE Funcionario
	SET dtAdmissao = _dtAdmissao, dtDemissao = _dtDemissao, contrato = _contrato
	WHERE idUsuario = _idUsuario;
END; //

DELIMITER ;

#sp_atualiza_contato
DELIMITER //

CREATE PROCEDURE `sp_atualiza_contato` (
	IN _idContato INT, _nome VARCHAR(255), _telefone VARCHAR(15), _parentesco VARCHAR(15))
    
BEGIN
	UPDATE Contato
	SET nome = _nome, telefone = _telefone, parentesco = _parentesco
	WHERE idContato = _idContato;
END; //

DELIMITER ;

#sp_atualiza_telefone
DELIMITER //

CREATE PROCEDURE `sp_atualiza_telefone` (
	IN _idTelefone INT, _telefone VARCHAR(15))

BEGIN
	UPDATE Telefone
	SET telefone = _telefone
	WHERE idTelefone = _idTelefone;
END; //

DELIMITER ;

#sp_atualiza_treino
DELIMITER //

CREATE PROCEDURE `sp_atualiza_treino` (
	IN _idExercicioTreino INT, _idTreino INT, _nome VARCHAR(255),
    _idExercicio INT, _qtRepeticao INT, _qtSerie INT)
    
BEGIN
	UPDATE Treino
    SET nome = _nome
    WHERE idTreino = _idTreino;
    
    UPDATE ExerciciosTreino
    SET idExercicio = _idExercicio, qtRepeticao = _qtRepeticao, qtSerie = _qtSerie
    WHERE idExercicioTreino = _idExercicioTreino;
END; //

DELIMITER ;

#sp_atualiza_exercicio
DELIMITER //

CREATE PROCEDURE `sp_atualiza_exercicio` (
	IN _idExercicio INT, _idEquipamento INT, _nome VARCHAR(255))
    
BEGIN
	UPDATE Exercicio
	SET idEquipamento = _idEquipamento, nome = _nome
    WHERE idExercicio = _idExercicio;
END; //

DELIMITER ;

#sp_atualiza_equipamento
DELIMITER //

CREATE PROCEDURE `sp_atualizar_equipamento` (IN _idEquipamento INT, _nome VARCHAR(255))

BEGIN
	UPDATE Equipamento
    SET nome = _nome
    WHERE idEquipamento = _idEquipamento;
END; //

DELIMITER ;

#sp_atualiza_treino_aluno
DELIMITER //

CREATE PROCEDURE `sp_atualiza_treino_aluno` (
	IN _idAlunoTreino INT, _idAluno INT, _idTreino INT, _idFuncionario INT, _dtTreino DATE)

BEGIN
	UPDATE AlunoTreino
    SET idAluno = _idAluno, idTreino = _idTreino, idFuncionario = _idFuncionario, dtTreino = _dtTreino
    WHERE idAlunoTreino = _idAlunoTreino;
END; //

DELIMITER ;

#sp_atualiza_avaliacao
DELIMITER //

CREATE PROCEDURE `sp_atualiza_avaliacao` (
	IN _idAvaliacao INT, _idAluno INT, _altura FLOAT,
	_peso FLOAT, _percGordura FLOAT, _percResiduos FLOAT, _percMusculo FLOAT)

BEGIN
	UPDATE Avaliacao
	SET idAluno = _idAluno, altura = _altura, peso = _peso, percGordura = _percGordura,
		percResiduos = _percResiduos, percMusculo = _percMusculo
	WHERE idAvaliacao = _idAvaliacao;
END; //

DELIMITER ;