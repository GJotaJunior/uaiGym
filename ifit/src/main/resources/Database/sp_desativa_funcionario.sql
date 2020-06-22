DELIMITER //
CREATE PROCEDURE `sp_desativa_funcionario` (IN _idFuncionario INT, OUT _status INT)
BEGIN
	IF (SELECT idFuncionario FROM funcionario WHERE idFuncionario = _idFuncionario) is null
	THEN
		SET _status = 100;
	ELSE
		SET _status = 200;
		UPDATE funcionario SET dtDemissao = CURDATE() WHERE idFuncionario = _idFuncionario ;
	END IF;
END; //

DELIMITER ;

