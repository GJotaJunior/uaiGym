DELIMITER //
CREATE PROCEDURE `sp_deleta_treino` (IN _idTreino INT, OUT _status INT)
BEGIN
	IF((SELECT idTreino FROM treino WHERE idTreino = _idTreino) is null)
    THEN
		SET _status = 100;
	ELSE
		IF((SELECT idTreino FROM Exercicio  WHERE idTreino = _idTreino) is null )
        THEN
			SET _status = 200;
            DELETE FROM idTreino WHERE idTreino = _idTreino; 
		ELSE
			SET _status = 301;
		END IF;
	END IF;
END; //

DELIMITER ;
