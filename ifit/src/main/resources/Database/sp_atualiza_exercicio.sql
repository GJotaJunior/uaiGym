DELIMITER //

CREATE PROCEDURE `sp_atualiza_exercicio` (
	IN _idExercicio INT, _idEquipamento INT, _nome VARCHAR(255))
    
BEGIN
	UPDATE Exercicio
	SET idEquipamento = _idEquipamento, nome = _nome
    WHERE idExercicio = _idExercicio;
END; //

DELIMITER ;