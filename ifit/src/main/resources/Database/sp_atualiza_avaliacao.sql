DELIMITER //

CREATE PROCEDURE `sp_atualiza_avaliacao` (
	IN _idAvaliacao INT, _idAluno INT, _altura FLOAT,
	_peso FLOAT, _percGordura FLOAT, _percResiduos FLOAT, _percMusculo FLOAT)

BEGIN
	UPDATE Avaliacao
	SET idAluno = _idAluno, altura = _altura, peso = _peso, percGordura = _percGordura,
		percResiduos = _percResiduos, percMusculo = _percMusculo
	WHERE idAvaliacao = _idAvaliacao;
END; //

DELIMITER ;