DELIMITER //
CREATE PROCEDURE `sp_desativa_aluno` (IN _idAluno INT, OUT _status INT)
BEGIN
	IF (SELECT idAluno FROM aluno WHERE idAluno = _idAluno) is null
	THEN
		SET _status = 100;
	ELSE
		SET _status = 200;
		UPDATE aluno SET status = 'INATIVO' WHERE idAluno = _idAluno ;
	END IF;
END; //

DELIMITER ;

