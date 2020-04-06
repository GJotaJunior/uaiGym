DELIMITER //

CREATE PROCEDURE `sp_atualiza_contato` (
	IN _idContato INT, _nome VARCHAR(255), _telefone VARCHAR(15), _parentesco VARCHAR(15))
    
BEGIN
	UPDATE Contato
	SET nome = _nome, telefone = _telefone, parentesco = _parentesco
	WHERE idContato = _idContato;
END; //

DELIMITER ;