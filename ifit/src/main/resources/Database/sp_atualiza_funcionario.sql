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