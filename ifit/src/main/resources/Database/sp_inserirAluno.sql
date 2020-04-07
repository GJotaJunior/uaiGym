DELIMITER $$

CREATE PROCEDURE `sp_inserirAluno`(IN _perfil ENUM('ALUNO', 'INSTRUTOR', 'RECEPCAO', 'GERENTE'), _nome VARCHAR(255), _cpf CHAR(11), _dtNascimento DATE, _sexo ENUM('M', 'F'), _email VARCHAR(255),
_senha VARCHAR(255), _matricula VARCHAR(255), _status ENUM('ATIVO', 'INATIVO'), _logradouro VARCHAR(255), _numero CHAR(6), _complemento VARCHAR(100), _bairro VARCHAR(50), _cidade VARCHAR(80), _uf CHAR(2), _cep CHAR(8))


BEGIN
	
	CALL sp_inserirUsuario(_perfil, _nome, _cpf, _dtNascimento, _sexo, _email, _senha, _logradouro, _numero, _complemento, _bairro, _cidade, _uf, _cep, @_idUsuario);
	
	SET _idUsuario = SELECT @_idUsuario;

	INSERT INTO Aluno(idUsuario, matricula, status) VALUES (_idUsuario, _matricula, _status);

END $$
DELIMITER ;