DELIMITER //
CREATE PROCEDURE `sp_deleta_treino` (IN _idTreino INT, OUT _status INT)
BEGIN
	IF((SELECT idTreino FROM treino WHERE idTreino = _idTreino) is null)
    THEN
		SET _status = 100;
	ELSE
		IF((SELECT idTreino FROM exerciciostreino  WHERE idTreino = _idTreino) is not null )
        THEN
			SET _status = 501;
		ELSE
			IF((SELECT idTreino FROM alunotreino WHERE idTreino = _idTreino) is null )
            THEN
				SET _status = 200;
				DELETE FROM treino WHERE idTreino = _idTreino; 
			ELSE
				SET _status = 502;
            END IF;
		END IF;
	END IF;
END; //

DELIMITER ;

