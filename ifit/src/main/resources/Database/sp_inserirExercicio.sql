DELIMITER $$

CREATE PROCEDURE `sp_inserirExercicio`(IN _idEquipamento INT ,_nome VARCHAR(255))

BEGIN

	INSERT INTO Exercicio(idEquipamento, nome) VALUES (_idEquipamento, _nome);

END $$
DELIMITER ;