DELIMITER //

CREATE PROCEDURE `sp_atualiza_telefone` (
	IN _idTelefone INT, _telefone VARCHAR(15))

BEGIN
	UPDATE Telefone
	SET telefone = _telefone
	WHERE idTelefone = _idTelefone;
END; //

DELIMITER ;