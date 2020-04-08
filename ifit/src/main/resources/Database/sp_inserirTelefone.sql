DELIMITER $$

CREATE PROCEDURE `sp_inserirTelefone`(_idUsuario INT, _telefone VARCHAR(15))

BEGIN

	INSERT INTO Telefone(idUsuario, telefone) VALUES (_idUsuario, _telefone);

END $$
DELIMITER ;