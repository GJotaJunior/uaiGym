DELIMITER $$

CREATE PROCEDURE `sp_inserirContato`(_idAluno INT, _nome VARCHAR(255) NOT NULL, _telefone VARCHAR(15), _parentesco VARCHAR(15))

BEGIN

	INSERT INTO Contato(idAluno, nome, telefone, parentesco) VALUES (_idAluno, _nome, _telefone, _parentesco);

END $$
DELIMITER ;