DELIMITER //

CREATE PROCEDURE `sp_atualizar_equipamento` (IN _idEquipamento INT, _nome VARCHAR(255))

BEGIN
	UPDATE Equipamento
    SET nome = _nome
    WHERE idEquipamento = _idEquipamento;
END; //

DELIMITER ;