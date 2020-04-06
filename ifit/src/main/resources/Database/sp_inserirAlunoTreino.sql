DELIMITER $$

CREATE PROCEDURE `sp_inserirAlunoTreino`(_idAluno INT, _idTreino INT, _idFuncionario INT, _dtTreino DATE)

BEGIN

	INSERT INTO AlunoTreino(idAluno, idTreino, idFuncionario, dtTreino) 
    VALUES (_idAluno, _idTreino, _idFuncionario, _dtTreino);

END $$
DELIMITER ;