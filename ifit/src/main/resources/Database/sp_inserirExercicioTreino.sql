DELIMITER $$

CREATE PROCEDURE `sp_inserirExercicioTreino`(IN _idExercicio INT ,_idTreino INT, _qtSerie INT, _qtRepeticao INT)

BEGIN

	INSERT INTO ExercicioTreino(idExercicio, idTreino, qtSerie, qtRepeticao) VALUES (_idExercicio, _idTreino, _qtSerie, _qtRepeticao);

END $$
DELIMITER ;