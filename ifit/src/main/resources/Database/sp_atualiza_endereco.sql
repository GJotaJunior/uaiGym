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