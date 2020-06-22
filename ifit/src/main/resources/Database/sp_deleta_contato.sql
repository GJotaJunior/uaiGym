DELIMITER //
CREATE PROCEDURE `sp_deleta_contato` (IN _idContato INT, OUT _status INT)
BEGIN
	IF (SELECT idContato FROM contato WHERE idContato = _idContato) is null
	THEN
		SET _status = 100;
	ELSE
		SET _status = 200;
		DELETE FROM Contato WHERE idContato = _idContato;
	END IF;
END; //

DELIMITER ;
