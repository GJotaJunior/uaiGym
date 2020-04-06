DELIMITER //

CREATE PROCEDURE `sp_atualiza_usuario` (
	IN _idUsuario INT, _perfil ENUM('ALUNO', 'INSTRUTOR', 'RECEPCAO', 'GERENTE'), _nome VARCHAR(255),
    _cpf CHAR(11), _dtNascimento DATE, _sexo ENUM('M', 'F'), _email VARCHAR(255), _senha VARCHAR(255),
    _logradouro VARCHAR(255), _numero CHAR(6), _complemento VARCHAR(100),
    _bairro VARCHAR(50), _cidade VARCHAR(80), _uf CHAR(2), _cecriacaop CHAR(8))
    
BEGIN
	UPDATE Usuario
    SET perfil = _perfil, nome = _nome, cpf = _cpf, dtNascimento = _dtNascimento,
		sexo = _sexo, email = _email, senha = _senha
	WHERE idUsuario = _idUsuario;
    
    CALL sp_atualiza_endereco(_idUsuario, _logradouro, _numero,
		_complemento, _bairro, _cidade, _uf, _cep);
END; //

DELIMITER ;