DELIMITER //
CREATE PROCEDURE `sp_deleta_exercicio` (IN _idExercicio INT, OUT _status INT)
BEGIN
	IF((SELECT idExercicio FROM exercicio WHERE idExercicio = _idExercicio) is null)
    THEN
		SET _status = 100;
	ELSE
		IF((SELECT idExercicio FROM exerciciostreino WHERE idExercicio = _idExercicio) is null )
        THEN
			SET _status = 200;
            DELETE FROM exercicio WHERE idExercicio = _idExercicio; 
		ELSE
			SET _status = 401;
		END IF;
	END IF;
END; //

DELIMITER ;
