DELIMITER $$

CREATE PROCEDURE `sp_inserirAvaliacao`(_idAluno INT, _idFuncionario INT, _dtAvaliacao DATE,
	_altura FLOAT, _peso FLOAT, _percGordura FLOAT, _percResiduos FLOAT, _percMusculo FLOAT)

BEGIN

	INSERT INTO Avaliacao(idAluno, idFuncionario, dtAvaliacao, altura, peso, percGordura, percResiduos, percMusculo) 
    VALUES (_idAluno, _idFuncionario, _dtAvaliacao, _altura, _peso, _percGordura, _percResiduos, _percMusculo);

END $$
DELIMITER ;