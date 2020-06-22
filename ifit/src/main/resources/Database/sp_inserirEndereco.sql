DELIMITER $$

CREATE PROCEDURE `sp_inserirEndereco`(_idUsuario INT, _logradouro VARCHAR(255), _numero CHAR(6), _complemento VARCHAR(100), 
                                   _bairro VARCHAR(50), _cidade VARCHAR(80), _uf CHAR(2), _cep CHAR(8))

BEGIN

	INSERT INTO Endereco(idUsuario, logradouro, numero, complemento, bairro, cidade, uf, cep) 
    VALUES (_idUsuario, _logradouro, _numero, _complemento, _bairro, _cidade, _uf, _cep);

END $$
DELIMITER ;