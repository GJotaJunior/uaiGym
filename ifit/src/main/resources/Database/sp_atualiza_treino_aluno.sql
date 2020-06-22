DELIMITER //

CREATE PROCEDURE `sp_atualiza_treino_aluno` (
	IN _idAlunoTreino INT, _idAluno INT, _idTreino INT, _idFuncionario INT, _dtTreino DATE)

BEGIN
	UPDATE AlunoTreino
    SET idAluno = _idAluno, idTreino = _idTreino, idFuncionario = _idFuncionario, dtTreino = _dtTreino
    WHERE idAlunoTreino = _idAlunoTreino;
END; //

DELIMITER ;