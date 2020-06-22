DELIMITER //

CREATE PROCEDURE `sp_atualiza_treino` (
	IN _idExercicioTreino INT, _idTreino INT, _nome VARCHAR(255),
    _idExercicio INT, _qtRepeticao INT, _qtSerie INT)
    
BEGIN
	UPDATE Treino
    SET nome = _nome
    WHERE idTreino = _idTreino;
    
    UPDATE ExerciciosTreino
    SET idExercicio = _idExercicio, qtRepeticao = _qtRepeticao, qtSerie = _qtSerie
    WHERE idExercicioTreino = _idExercicioTreino;
END; //

DELIMITER ;