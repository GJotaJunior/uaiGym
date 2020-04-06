DELIMITER $$

CREATE PROCEDURE `sp_inserirTreino`(IN _nome VARCHAR(255))

BEGIN

	INSERT INTO Treino(nome) VALUES (_nome);

END $$
DELIMITER ;