DELIMITER $$

CREATE PROCEDURE `sp_inserirContato`(_idUsuario INT, _telefone VARCHAR(15))

BEGIN

	INSERT INTO Endereco(idUsuario, telefone) VALUES (_idUsuario, _telefone);

END $$
DELIMITER ;