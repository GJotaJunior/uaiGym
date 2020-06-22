DELIMITER $$

CREATE PROCEDURE `sp_inserirEquipamento`(IN _nome VARCHAR(255))

BEGIN

	INSERT INTO Equipamento(nome) VALUES (_nome);

END $$
DELIMITER ;