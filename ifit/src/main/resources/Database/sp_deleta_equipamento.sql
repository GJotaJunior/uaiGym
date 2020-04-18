DELIMITER //
CREATE PROCEDURE `sp_deleta_equipamento` (IN _idEquipamento INT, OUT _status INT)
BEGIN
	IF (SELECT idEquipamento FROM equipamento WHERE idEquipamento = _idEquipamento) is null
	THEN
		SET _status = 100;
	ELSE
		IF (SELECT idEquipamento FROM exercicio WHERE idEquipamento = _idEquipamento) is not null
			THEN
				SET _status = 301;
			ELSE
				SET _status = 200;
                DELETE FROM equipamento WHERE idEquipamento = _idEquipamento;
			END IF;
		END IF;
END; //

DELIMITER ;
