DELIMITER $$

CREATE PROCEDURE `sp_inserirAluno`(IN _idUsuario INT, _matricula VARCHAR(255), _status ENUM('ATIVO', 'INATIVO'))

BEGIN

	INSERT INTO Aluno(idUsuario, matricula, status) VALUES (_idUsuario, _matricula, _status);

END $$
DELIMITER ;