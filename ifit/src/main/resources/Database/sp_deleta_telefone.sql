DELIMITER //
CREATE PROCEDURE `sp_deleta_telefone` (IN _idTelefone INT, OUT _status INT)
BEGIN
	IF (SELECT idTelefone FROM telefone WHERE idTelefone = _idTelefone) is null
	THEN
		SET _status = 100;
	ELSE
		SET _status = 200;
		DELETE FROM Telefone WHERE idTelefone = _idTelefone;
	END IF;
END; //

DELIMITER ;

